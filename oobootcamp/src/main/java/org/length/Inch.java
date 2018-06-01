package org.length;

public class Inch extends Length {
    public Inch(int value) {
        this.value = value;
      this.setConvertedValue();
    }

  public void setConvertedValue(){
    this.convertedValue = value / mileToInch;
  }
}
