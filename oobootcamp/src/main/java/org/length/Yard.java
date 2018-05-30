package org.length;

public class Yard extends Length {


  public Yard(int value) {
    this.value = value;
  }



//  public boolean equals(Object object) {
//    if (object instanceof Yard) {
//      Yard yard = (Yard) object;
//      return this.yard == yard.getYard();
//    } else if (object instanceof Mile) {
//      Mile mile = (Mile) object;
//      return mile.getValue() * mileToYard == yard;
//    }
//    return false;
//  }
}
