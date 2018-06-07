package org.length;

public class Yard extends Length {
    public Yard(int value) {
        super();
        this.value = value;
        this.setConvertedValue();
    }

    public Yard() {
    }

    @Override
     public void getSum(Length length1, Length length2) {
         this.value= (float) Math.ceil((length1.getConvertedValue() + length2.getConvertedValue()) * mileToYard);
    }

    @Override
    public void setConvertedValue() {
        this.convertedValue = value / mileToYard;
    }
}
