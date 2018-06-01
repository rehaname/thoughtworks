package org.length;

public class Mile extends Length {
    public Mile(int value) {
        this.value = value;
        this.setConvertedValue();
    }

    public void setConvertedValue() {
        this.convertedValue = value;
    }
}
