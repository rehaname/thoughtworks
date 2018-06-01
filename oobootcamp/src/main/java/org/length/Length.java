package org.length;

public class Length {
  protected float mileToYard = 1760;
  protected float mileToInch = 63360;
  protected float mileToFeet = 5280;
  protected float value;


  public float getValue() {
    return value;
  }

  public float converter(Object object) {
    if (object instanceof Mile) {
      return ((Mile) object).getValue();
    }
    else if (object instanceof Yard) {
      return (((Yard) object).getValue() / mileToYard);
    }
    else if (object instanceof Feet) {
      return ((Feet) object).getValue() / mileToFeet;
    }
    else if (object instanceof Inch) {
      return ((Inch) object).getValue() / mileToInch;
    }
    return 0;
  }

  public boolean equals(Object object) {
    return converter(this) == converter(object);
  }

}
