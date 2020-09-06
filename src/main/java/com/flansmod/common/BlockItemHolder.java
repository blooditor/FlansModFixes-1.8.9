package com.flansmod.common;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class BlockItemHolder extends BlockContainer {

  public static final PropertyDirection FACING = PropertyDirection
      .create("facing", EnumFacing.Plane.HORIZONTAL);
  public ItemHolderType type;

  public BlockItemHolder(ItemHolderType type) {
    super(Material.rock);
    this.type = type;
    setCreativeTab(FlansMod.tabFlanParts);
    setHardness(2F);
    setResistance(4F);
    setUnlocalizedName(type.shortName);
    GameRegistry.registerBlock(this, type.shortName);
    setCreativeTab(FlansMod.tabFlanParts);
    setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
    type.block = this;
    type.item = Item.getItemFromBlock(this);
  }


  public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l) {
    return false;
  }

  @Override
  public boolean isOpaqueCube() {
    return false;
  }

  @Override
  public void onBlockPlacedBy(
      World worldIn,
      BlockPos pos,
      IBlockState state,
      EntityLivingBase placer,
      ItemStack stack) {
    EnumFacing enumfacing = EnumFacing.fromAngle(placer.rotationYaw);
    worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
  }

  @Override
  public IBlockState onBlockPlaced(
      World worldIn,
      BlockPos pos,
      EnumFacing facing,
      float hitX,
      float hitY,
      float hitZ,
      int meta,
      EntityLivingBase placer) {
    if (!facing.getAxis().isHorizontal()) {
      facing = EnumFacing.NORTH;
    }

    return this.getDefaultState().withProperty(FACING, facing.getOpposite());
  }

  public IBlockState getStateFromMeta(int meta) {
    return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
  }

  public int getMetaFromState(IBlockState state) {
    byte b0 = 0;
    int i = b0 | state.getValue(FACING).getHorizontalIndex();
    return i;
  }

  protected BlockState createBlockState() {
    return new BlockState(this, FACING);
  }


  @Override
  public boolean canPlaceBlockAt(World par1World, BlockPos pos) {
    return World.doesBlockHaveSolidTopSurface(par1World, pos.add(0, -1, 0));
  }
	/*
	@Override
    public void onEntityCollidedWithBlock(World par1World, BlockPos pos, Entity par5Entity)
    {
    }*/

  @Override
  public void setBlockBoundsBasedOnState(
      IBlockAccess access,
      BlockPos pos) {//setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);

    EnumFacing facing = access.getBlockState(pos).getValue(FACING);
    switch (facing) {
      case NORTH:
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1F, 0.5F);
        break;
      case SOUTH:
        setBlockBounds(0.0F, 0.0F, 0.5F, 1.0F, 1F, 1F);
        break;
      case WEST:
        setBlockBounds(0F, 0.0F, 0.0F, 0.5F, 1F, 1F);
        break;
      case EAST:
        setBlockBounds(0.5F, 0.0F, 0F, 1.0F, 1F, 1F);
        break;
      default:
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
        break;
    }
  }

  @Override
  public void setBlockBoundsForItemRender() {
    float var1 = 0.5F;
    float var2 = 0.015625F;
    float var3 = 0.5F;
    this.setBlockBounds(0.0F, 0.5F - var2, 0.0F, 1F, 0.5F + var2, 1F);
  }

  @Override
  public int getMobilityFlag() {
    return 1;
  }

  @Override
  public TileEntity createNewTileEntity(World var1, int i) {
    return new TileEntityItemHolder(type);
  }

  @Override
  public boolean onBlockActivated(
      World world,
      BlockPos pos,
      IBlockState state,
      EntityPlayer player,
      EnumFacing side,
      float par7,
      float par8,
      float par9) {
    if (world.isRemote) {
      PlayerHandler
          .getPlayerData(player, Side.CLIENT).shootTimeLeft = PlayerHandler.getPlayerData(
          player,
          Side.CLIENT).shootTimeRight = 10;
      return true;
    }

    TileEntityItemHolder holder = (TileEntityItemHolder) world.getTileEntity(pos);
    ItemStack item = player.getCurrentEquippedItem();

    if (holder.getStackInSlot(0) == null) {
      holder.setInventorySlotContents(0, item);
      player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
    } else {
      world.spawnEntityInWorld(
          new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), holder.getStackInSlot(0)));
      holder.setInventorySlotContents(0, null);
      PlayerHandler
          .getPlayerData(player, Side.SERVER).shootTimeLeft = PlayerHandler.getPlayerData(
          player,
          Side.SERVER).shootTimeRight = 10;
    }

    world.markBlockForUpdate(pos);

    return true;
  }

  @Override
  public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
    TileEntity tileentity = worldIn.getTileEntity(pos);

    if (tileentity instanceof IInventory) {
      InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory) tileentity);
      worldIn.updateComparatorOutputLevel(pos, this);
    }

    super.breakBlock(worldIn, pos, state);
  }
}
