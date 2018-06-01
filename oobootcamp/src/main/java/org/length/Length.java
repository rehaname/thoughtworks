package org.length;

 public class Length {
  protected float mileToYard = 1760;
  protected float mileToInch = 63360;
  protected float mileToFeet = 5280;
  protected float value;
  protected float convertedValue;

   Length() {}

   public float getConvertedValue() {
     return convertedValue;
   }

   public boolean equals(Length length) {

    return length!=null? this.getConvertedValue() == length.getConvertedValue():false;
  }

}
