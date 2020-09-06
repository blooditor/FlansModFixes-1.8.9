package com.flansmod.client;

import com.flansmod.common.vector.Vector3f;
import java.awt.Color;
import java.util.ArrayList;
import net.minecraft.nbt.NBTTagCompound;


public class ColorUtils {

  private static ColorUtils inst = new ColorUtils();
  public ArrayList<ColorName> colorList = new ArrayList<ColorName>();

  public static ColorUtils getInstance() {
    return inst;
  }

  public ColorUtils() {
    initColorList();
  }

  /**
   * Initialize the color list that we have.
   */
  private ArrayList<ColorName> initColorList() {
    colorList.add(new ColorName("Default", 0xFF, 0xFF, 0xFF, 1));
    colorList.add(new ColorName("AliceBlue", 0xF0, 0xF8, 0xFF, 1.6f));
    colorList.add(new ColorName("AntiqueWhite", 0xFA, 0xEB, 0xD7));
    colorList.add(new ColorName("Aqua", 0x00, 0xFF, 0xFF, 1.7f));
    colorList.add(new ColorName("Aquamarine", 0x7F, 0xFF, 0xD4, 1.6f));
    colorList.add(new ColorName("Azure", 0xF0, 0xFF, 0xFF));
    colorList.add(new ColorName("Beige", 0xF5, 0xF5, 0xDC));
    colorList.add(new ColorName("Bisque", 0xFF, 0xE4, 0xC4));
    //colorList.add(new ColorName("Black", 0x00, 0x00, 0x00, 4));
    colorList.add(new ColorName("BlanchedAlmond", 0xFF, 0xEB, 0xCD));
    colorList.add(new ColorName("Blue", 0x00, 0x00, 0xFF, 1.8f));
    colorList.add(new ColorName("BlueViolet", 0x8A, 0x2B, 0xE2));
    colorList.add(new ColorName("Brown", 0xA5, 0x2A, 0x2A));
    colorList.add(new ColorName("BurlyWood", 0xDE, 0xB8, 0x87));
    colorList.add(new ColorName("CadetBlue", 0x5F, 0x9E, 0xA0));
    colorList.add(new ColorName("Chartreuse", 0x7F, 0xFF, 0x00, 1.85f));
    colorList.add(new ColorName("Chocolate", 0xD2, 0x69, 0x1E, 1.8f));
    colorList.add(new ColorName("Coral", 0xFF, 0x7F, 0x50));
    colorList.add(new ColorName("CornflowerBlue", 0x64, 0x95, 0xED, 1.7f));
    colorList.add(new ColorName("Cornsilk", 0xFF, 0xF8, 0xDC));
    colorList.add(new ColorName("Crimson", 0xDC, 0x14, 0x3C, 1.8f));
    colorList.add(new ColorName("Cyan", 0x00, 0xFF, 0xFF, 1.7f));
    colorList.add(new ColorName("DarkBlue", 0x00, 0x00, 0x8B, 1.8f));
    colorList.add(new ColorName("DarkCyan", 0x00, 0x8B, 0x8B, 1.7f));
    colorList.add(new ColorName("DarkGoldenRod", 0xB8, 0x86, 0x0B, 1.8f));
    colorList.add(new ColorName("DarkGray", 0xA9, 0xA9, 0xA9));
    colorList.add(new ColorName("DarkGreen", 0x00, 0x64, 0x00, 1.8f));
    colorList.add(new ColorName("DarkKhaki", 0xBD, 0xB7, 0x6B));
    colorList.add(new ColorName("DarkMagenta", 0x8B, 0x00, 0x8B, 2f));
    colorList.add(new ColorName("DarkOliveGreen", 0x55, 0x6B, 0x2F, 1.8f));
    colorList.add(new ColorName("DarkOrange", 0xFF, 0x8C, 0x00, 1.9f));
    colorList.add(new ColorName("DarkOrchid", 0x99, 0x32, 0xCC, 1.8f));
    colorList.add(new ColorName("DarkRed", 0x8B, 0x00, 0x00, 2.2f));
    colorList.add(new ColorName("DarkSalmon", 0xE9, 0x96, 0x7A));
    colorList.add(new ColorName("DarkSeaGreen", 0x8F, 0xBC, 0x8F));
    colorList.add(new ColorName("DarkSlateBlue", 0x48, 0x3D, 0x8B, 1.6f));
    colorList.add(new ColorName("DarkSlateGray", 0x2F, 0x4F, 0x4F, 1.55f));
    colorList.add(new ColorName("DarkTurquoise", 0x00, 0xCE, 0xD1, 1.8f));
    colorList.add(new ColorName("DarkViolet", 0x94, 0x00, 0xD3, 1.9f));
    colorList.add(new ColorName("DeepPink", 0xFF, 0x14, 0x93, 1.95f));
    colorList.add(new ColorName("DeepSkyBlue", 0x00, 0xBF, 0xFF, 1.8f));
    colorList.add(new ColorName("DimGray", 0x69, 0x69, 0x69, 1.6f));
    colorList.add(new ColorName("DodgerBlue", 0x1E, 0x90, 0xFF, 1.75f));
    colorList.add(new ColorName("FireBrick", 0xB2, 0x22, 0x22, 1.9f));
    colorList.add(new ColorName("FloralWhite", 0xFF, 0xFA, 0xF0));
    colorList.add(new ColorName("ForestGreen", 0x22, 0x8B, 0x22, 1.8f));
    colorList.add(new ColorName("Fuchsia", 0xFF, 0x00, 0xFF, 1.9f));
    colorList.add(new ColorName("Gainsboro", 0xDC, 0xDC, 0xDC));
    colorList.add(new ColorName("GhostWhite", 0xF8, 0xF8, 0xFF));
    colorList.add(new ColorName("Gold", 0xFF, 0xD7, 0x00, 3f));
    colorList.add(new ColorName("GoldenRod", 0xDA, 0xA5, 0x20, 3f));
    colorList.add(new ColorName("Gray", 0x80, 0x80, 0x80));
    colorList.add(new ColorName("Green", 0x00, 0x80, 0x00, 1.8f));
    colorList.add(new ColorName("GreenYellow", 0xAD, 0xFF, 0x2F, 1.7f));
    colorList.add(new ColorName("HoneyDew", 0xF0, 0xFF, 0xF0));
    colorList.add(new ColorName("HotPink", 0xFF, 0x69, 0xB4, 1.65f));
    colorList.add(new ColorName("IndianRed", 0xCD, 0x5C, 0x5C, 1.7f));
    colorList.add(new ColorName("Indigo", 0x4B, 0x00, 0x82, 1.6f));
    colorList.add(new ColorName("Ivory", 0xFF, 0xFF, 0xF0));
    colorList.add(new ColorName("Khaki", 0xF0, 0xE6, 0x8C));
    colorList.add(new ColorName("Lavender", 0xE6, 0xE6, 0xFA));
    colorList.add(new ColorName("LavenderBlush", 0xFF, 0xF0, 0xF5));
    colorList.add(new ColorName("LawnGreen", 0x7C, 0xFC, 0x00, 1.7f));
    colorList.add(new ColorName("LemonChiffon", 0xFF, 0xFA, 0xCD));
    colorList.add(new ColorName("LightBlue", 0xAD, 0xD8, 0xE6));
    colorList.add(new ColorName("LightCoral", 0xF0, 0x80, 0x80));
    colorList.add(new ColorName("LightCyan", 0xE0, 0xFF, 0xFF));
    colorList.add(new ColorName("LightGoldenRodYellow", 0xFA, 0xFA, 0xD2));
    colorList.add(new ColorName("LightGray", 0xD3, 0xD3, 0xD3));
    colorList.add(new ColorName("LightGreen", 0x90, 0xEE, 0x90));
    colorList.add(new ColorName("LightPink", 0xFF, 0xB6, 0xC1));
    colorList.add(new ColorName("LightSalmon", 0xFF, 0xA0, 0x7A));
    colorList.add(new ColorName("LightSeaGreen", 0x20, 0xB2, 0xAA, 1.7f));
    colorList.add(new ColorName("LightSkyBlue", 0x87, 0xCE, 0xFA, 1.7f));
    colorList.add(new ColorName("LightSlateGray", 0x77, 0x88, 0x99));
    colorList.add(new ColorName("LightSteelBlue", 0xB0, 0xC4, 0xDE));
    colorList.add(new ColorName("LightYellow", 0xFF, 0xFF, 0xE0));
    colorList.add(new ColorName("Lime", 0x00, 0xFF, 0x00, 1.9f));
    colorList.add(new ColorName("LimeGreen", 0x32, 0xCD, 0x32, 1.8f));
    colorList.add(new ColorName("Linen", 0xFA, 0xF0, 0xE6));
    colorList.add(new ColorName("Magenta", 0xFF, 0x00, 0xFF, 2f));
    colorList.add(new ColorName("Maroon", 0x80, 0x00, 0x00, 1.9f));
    colorList.add(new ColorName("MediumAquaMarine", 0x66, 0xCD, 0xAA, 1.7f));
    colorList.add(new ColorName("MediumBlue", 0x00, 0x00, 0xCD, 1.8f));
    colorList.add(new ColorName("MediumOrchid", 0xBA, 0x55, 0xD3));
    colorList.add(new ColorName("MediumPurple", 0x93, 0x70, 0xDB));
    colorList.add(new ColorName("MediumSeaGreen", 0x3C, 0xB3, 0x71));
    colorList.add(new ColorName("MediumSlateBlue", 0x7B, 0x68, 0xEE));
    colorList.add(new ColorName("MediumSpringGreen", 0x00, 0xFA, 0x9A));
    colorList.add(new ColorName("MediumTurquoise", 0x48, 0xD1, 0xCC, 1.6f));
    colorList.add(new ColorName("MediumVioletRed", 0xC7, 0x15, 0x85, 1.6f));
    colorList.add(new ColorName("MidnightBlue", 0x19, 0x19, 0x70, 2f));
    colorList.add(new ColorName("MintCream", 0xF5, 0xFF, 0xFA));
    colorList.add(new ColorName("MistyRose", 0xFF, 0xE4, 0xE1));
    colorList.add(new ColorName("Moccasin", 0xFF, 0xE4, 0xB5));
    colorList.add(new ColorName("NavajoWhite", 0xFF, 0xDE, 0xAD));
    colorList.add(new ColorName("Navy", 0x00, 0x00, 0x80, 2f));
    colorList.add(new ColorName("OldLace", 0xFD, 0xF5, 0xE6));
    colorList.add(new ColorName("Olive", 0x80, 0x80, 0x00, 1.8f));
    colorList.add(new ColorName("OliveDrab", 0x6B, 0x8E, 0x23, 1.8f));
    colorList.add(new ColorName("Orange", 0xFF, 0xA5, 0x00, 1.9f));
    colorList.add(new ColorName("OrangeRed", 0xFF, 0x45, 0x00, 2.1f));
    colorList.add(new ColorName("Orchid", 0xDA, 0x70, 0xD6, 1.7f));
    colorList.add(new ColorName("PaleGoldenRod", 0xEE, 0xE8, 0xAA));
    colorList.add(new ColorName("PaleGreen", 0x98, 0xFB, 0x98));
    colorList.add(new ColorName("PaleTurquoise", 0xAF, 0xEE, 0xEE));
    colorList.add(new ColorName("PaleVioletRed", 0xDB, 0x70, 0x93, 1.6f));
    colorList.add(new ColorName("PapayaWhip", 0xFF, 0xEF, 0xD5));
    colorList.add(new ColorName("PeachPuff", 0xFF, 0xDA, 0xB9));
    colorList.add(new ColorName("Peru", 0xCD, 0x85, 0x3F, 1.7f));
    colorList.add(new ColorName("Pink", 0xFF, 0xC0, 0xCB));
    colorList.add(new ColorName("Plum", 0xDD, 0xA0, 0xDD));
    colorList.add(new ColorName("PowderBlue", 0xB0, 0xE0, 0xE6));
    colorList.add(new ColorName("Purple", 0x80, 0x00, 0x80, 1.9f));
    colorList.add(new ColorName("Red", 0xFF, 0x00, 0x00, 2.2f));
    colorList.add(new ColorName("RosyBrown", 0xBC, 0x8F, 0x8F));
    colorList.add(new ColorName("RoyalBlue", 0x41, 0x69, 0xE1, 1.8f));
    colorList.add(new ColorName("SaddleBrown", 0x8B, 0x45, 0x13, 1.7f));
    colorList.add(new ColorName("Salmon", 0xFA, 0x80, 0x72));
    colorList.add(new ColorName("SandyBrown", 0xF4, 0xA4, 0x60));
    colorList.add(new ColorName("SeaGreen", 0x2E, 0x8B, 0x57, 1.8f));
    colorList.add(new ColorName("SeaShell", 0xFF, 0xF5, 0xEE));
    colorList.add(new ColorName("Sienna", 0xA0, 0x52, 0x2D, 1.7f));
    colorList.add(new ColorName("Silver", 0xC0, 0xC0, 0xC0, 1.6f));
    colorList.add(new ColorName("SkyBlue", 0x87, 0xCE, 0xEB, 1.7f));
    colorList.add(new ColorName("SlateBlue", 0x6A, 0x5A, 0xCD, 1.8f));
    colorList.add(new ColorName("SlateGray", 0x70, 0x80, 0x90));
    colorList.add(new ColorName("Snow", 0xFF, 0xFA, 0xFA));
    colorList.add(new ColorName("SpringGreen", 0x00, 0xFF, 0x7F, 1.6f));
    colorList.add(new ColorName("SteelBlue", 0x46, 0x82, 0xB4, 1.8f));
    colorList.add(new ColorName("Tan", 0xD2, 0xB4, 0x8C));
    colorList.add(new ColorName("Teal", 0x00, 0x80, 0x80, 1.8f));
    colorList.add(new ColorName("Thistle", 0xD8, 0xBF, 0xD8));
    colorList.add(new ColorName("Tomato", 0xFF, 0x63, 0x47, 1.7f));
    colorList.add(new ColorName("Turquoise", 0x40, 0xE0, 0xD0, 1.8f));
    colorList.add(new ColorName("Violet", 0xEE, 0x82, 0xEE, 1.7f));
    colorList.add(new ColorName("Wheat", 0xF5, 0xDE, 0xB3));
    colorList.add(new ColorName("WhiteSmoke", 0xF5, 0xF5, 0xF5));
    colorList.add(new ColorName("Yellow", 0xFF, 0xFF, 0x00, 1.9f));
    colorList.add(new ColorName("YellowGreen", 0x9A, 0xCD, 0x32, 1.8f));
    return colorList;
  }


  public String getColorNameFromNbt(NBTTagCompound tag) {
    if (tag.hasKey("color_r")) {
      float cx = tag.getFloat("color_r");
      float cy = tag.getFloat("color_g");
      float cz = tag.getFloat("color_b");
      return getColorNameFrom3f(new Vector3f(cx, cy, cz));
    }
    return null;
  }

  public String getColorNameFrom3f(Vector3f vector3f) {
    if (vector3f.x == 1 && vector3f.y == 1 && vector3f.z == 1) {
      return null;
    }
    return getColorNameFromRgb((int) (vector3f.x * 255), (int) (vector3f.y * 255),
        (int) (vector3f.z * 255));
  }

  /**
   * Get the closest color name from our list
   */
  public String getColorNameFromRgb(int r, int g, int b) {

    ColorName closestMatch = null;
    int minMSE = Integer.MAX_VALUE;
    int mse;
    for (ColorName c : colorList) {
      mse = c.computeMSE(r, g, b);
      if (mse < minMSE) {
        minMSE = mse;
        closestMatch = c;
      }
    }

    if (closestMatch != null) {
      return closestMatch.getName();
    } else {
      return "Colored";
    }
  }

  /**
   * Convert hexColor to rgb, then call getColorNameFromRgb(r, g, b)
   *
   * @param hexColor
   * @return
   */
  public String getColorNameFromHex(int hexColor) {
    int r = (hexColor & 0xFF0000) >> 16;
    int g = (hexColor & 0xFF00) >> 8;
    int b = (hexColor & 0xFF);
    return getColorNameFromRgb(r, g, b);
  }

  public int colorToHex(Color c) {
    return Integer.decode("0x"
        + Integer.toHexString(c.getRGB()).substring(2));
  }

  public String getColorNameFromColor(Color color) {
    return getColorNameFromRgb(color.getRed(), color.getGreen(),
        color.getBlue());
  }


  /**
   * SubClass of ColorUtils. In order to lookup color name
   *
   * @author Xiaoxiao Li
   */
  public class ColorName {

    public int r, g, b;
    public String name;
    public float priceModifier;

    public ColorName(String name, int r, int g, int b) {
      this.r = r;
      this.g = g;
      this.b = b;
      this.name = name;
      priceModifier = 1.5f;
    }

    public ColorName(String name, int r, int g, int b, float priceModifier) {
      this.r = r;
      this.g = g;
      this.b = b;
      this.name = name;
      this.priceModifier = priceModifier;
    }

    public int computeMSE(int pixR, int pixG, int pixB) {
      return ((pixR - r) * (pixR - r) + (pixG - g) * (pixG - g) + (pixB - b)
          * (pixB - b)) / 3;
    }

    public int getR() {
      return r;
    }

    public int getG() {
      return g;
    }

    public int getB() {
      return b;
    }

    public String getName() {
      return name;
    }

    public void setNbt(NBTTagCompound tags) {
      tags.setFloat("color_r", r / 255f);
      tags.setFloat("color_g", g / 255f);
      tags.setFloat("color_b", b / 255f);
    }
  }
}