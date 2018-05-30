package org.length;

public class Length {
  protected int mileToYard = 1760;
  protected int mileToInch = 63360;
  protected int mileToFeet = 5280;
  protected int yardToFeet = 3;
  protected int feetToInch = 12;
  protected int value;


  public int getValue() {
    return value;
  }

  public int converter(Object object){
    if(this instanceof Mile){
        if(object instanceof Yard){
          return  ((Yard) object).getValue()/mileToYard;
        }
    }
    return 1;
  }

  public boolean equals(Object object) {
    return this.value ==  converter(object);
  }

}
