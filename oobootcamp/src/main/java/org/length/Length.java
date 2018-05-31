package org.length;

public class Length {
  protected float mileToYard = 1760;
  protected float mileToInch = 63360;
  protected float mileToFeet = 5280;
  protected float yardToFeet = 3;
  protected float feetToInch = 12;
  protected float value;


  public float getValue() {
    return value;
  }

  public String converter(Object object) {
    float result;
    if (object == null) {
      return null;
    }
    if (object instanceof Mile) {
      return String.valueOf(((Mile) object).getValue());
    }
    if (object instanceof Yard) {
      result = (((Yard) object).getValue() / mileToYard);
      return String.valueOf(result);
    }
    if (object instanceof Feet) {
      return String.valueOf(((Feet) object).getValue() / mileToFeet);
    }
    if (object instanceof Inch) {
      return String.valueOf(((Inch) object).getValue() / mileToInch);
    }

    return null;
  }

  public boolean equals(Object object) {
    return converter(this).equals(converter(object));
  }

}
