package org.length;

public class Feet extends Length {
    public Feet(int value) {
        super(value);
    }

    public Feet(){}

    @Override
    void getSum(Length length1, Length length2) {
        this.value= (float) Math.ceil((length1.getConvertedValue() + length2.getConvertedValue()) * mileToFeet);
    }

    @Override
    void setConvertedValue() {

        this.convertedValue = value / mileToFeet;

    }
}
