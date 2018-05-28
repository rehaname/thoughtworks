package org.length;

public class Yard {
    private int mileToYard = 1760;
    private int yard;

    public Yard(int yard) {
        this.yard = yard;
    }

    public int getMileToYard() {
        return mileToYard;
    }

    public void setMileToYard(int mileToYard) {
        this.mileToYard = mileToYard;
    }

    public int getYard() {
        return yard;
    }

    public void setYard(int yard) {
        this.yard = yard;
    }

    public boolean equals(Object object) {
        if(object instanceof  Yard){
            Yard yard = (Yard) object;
            return this.yard == yard.getYard();
        }
//        else if(object instanceof  Mile){
//            Yard yard = (Yard) object;
//            return this.yard * yard.getMileToYard() == yard.getYard();
//
//        }
        return false;
    }
}
