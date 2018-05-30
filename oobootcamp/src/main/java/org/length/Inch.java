package org.length;

public class Inch {

  private int value;
  private int mileToInch = 63360;
  private int feetToInch = 12;

  public Inch(int value) {
    this.value = value;
  }

  public int getValue() {

    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public boolean equals(Object object){
    if(object instanceof Inch){
      return true;
    }

    return false;
  }
}
