package org.length;

public class Mile extends Length {
    public Mile(int value) {
        this.value = value;
        this.setConvertedValue();
    }

    public Mile(){}

    @Override
    void getSum(Length length1, Length length2) {
        this.value= (float) Math.ceil((length1.getConvertedValue() + length2.getConvertedValue()));
    }

    @Override
    public void setConvertedValue() {
        this.convertedValue = value;
    }
}
