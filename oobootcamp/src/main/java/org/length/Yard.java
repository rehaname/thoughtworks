package org.length;

public class Yard extends Length {
    public Yard(int value) {
        this.value = value;
      this.setConvertedValue();
    }

  public void setConvertedValue(){
    this.convertedValue = value / mileToYard;
  }
}
