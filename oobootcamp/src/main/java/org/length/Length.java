package org.length;

public abstract class Length {
    protected float mileToYard = 1760;
    protected float mileToInch = 63360;
    protected float mileToFeet = 5280;
    protected float value;
    protected float convertedValue;

    Length() {

    }

    public Length(float value) {
        this.value = value;
        this.setConvertedValue();
    }

    public float getValue() {
        return value;
    }

    public float getConvertedValue() {
        return convertedValue;
    }

    public boolean equals(Length length) {
        return length != null && this.getConvertedValue() == length.getConvertedValue();
    }

    abstract void getSum(Length length1, Length length2);

    abstract void setConvertedValue();


}
