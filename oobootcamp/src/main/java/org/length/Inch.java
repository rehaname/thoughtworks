package org.length;

public class Inch extends Length {
    public Inch(int value) {
        this.value = value;
      this.setConvertedValue();
    }

    public Inch(){}

    @Override
    void getSum(Length length1, Length length2) {
        this.value= (float) Math.ceil((length1.getConvertedValue() + length2.getConvertedValue()) * mileToInch);
    }

    @Override
    public void setConvertedValue(){
    this.convertedValue = value / mileToInch;
  }
}
