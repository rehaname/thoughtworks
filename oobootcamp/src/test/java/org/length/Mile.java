package org.length;

public class Mile {
    private int value;

    public Mile(int value) {
        this.value = value;
    }

    public boolean equals(Object object) {
        if(object instanceof  Mile){
        Mile mile = (Mile) object;
         return this.value == mile.value;
        }else if(object instanceof  Yard){
            Yard yard = (Yard) object;
            return this.value * yard.getMileToYard() == yard.getYard();

        }
        return false;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
