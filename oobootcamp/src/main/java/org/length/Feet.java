package org.length;

public class Feet extends Length {
  public Feet(int value) {
    this.value = value;
    this.setConvertedValue();
  }

  public void setConvertedValue() {
    this.convertedValue = value / mileToFeet;
  }
}
