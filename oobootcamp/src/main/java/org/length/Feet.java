package org.length;

public class Feet extends Length {
  public Feet(int value) {
    super(value);
  }

  @Override
  void setConvertedValue() {

      this.convertedValue = value / mileToFeet;

  }
}
