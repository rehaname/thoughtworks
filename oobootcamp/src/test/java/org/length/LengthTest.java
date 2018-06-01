package org.length;

import org.junit.Assert;
import org.junit.Test;

public class LengthTest {

    @Test
    public void compareMilesShouldBeEqual() throws Exception {

        Mile x = new org.length.Mile(3);
        Mile y = new org.length.Mile(3);

        Assert.assertTrue(x.equals(y));

    }

    @Test
    public void compareMilesShouldNotBeEqual() throws Exception {

        Mile x = new org.length.Mile(3);
        Mile y = new org.length.Mile(2);

        Assert.assertTrue(!x.equals(y));

    }

    @Test
    public void compareMilesShouldNotBeEqual2() throws Exception {

        Mile x = new org.length.Mile(3);
        Mile y = new org.length.Mile(4);

        Assert.assertTrue(!x.equals(y));
    }

    @Test
    public void compareMileNotEqualToNull() {
        Mile x = new org.length.Mile(3);

        Assert.assertTrue(!x.equals(null));
    }

    //Start of Requirement 2
    @Test
    public void compareMileToYardShouldBeEqual() {

        Mile x = new Mile(1);
        Yard y = new Yard(1760);

        Assert.assertTrue(x.equals(y));
    }

    @Test
    public void getCompareYardToYardShouldNotBeEqual() {

        Yard x = new Yard(3);
        Yard y = new Yard(2);

        Assert.assertTrue(!x.equals(y));


    }

    @Test
    public void getCompareMileToYardShouldNotBeEqual() {

        Mile x = new Mile(1);
        Yard y = new Yard(1761);

        Assert.assertTrue(!x.equals(y));


    }

    @Test
    public void getCompareYardToYardShouldNotBeEqual2(){
        Yard y1 = new Yard(3);
        Yard y2 = new Yard(4);

        Assert.assertTrue(!y1.equals(y2));
    }

    @Test
    public void getCompareYardEqualToMile() {
        Yard y = new Yard(3520);
        Mile m = new Mile(2);

        Assert.assertTrue(y.equals(null));
    }


//    @Test
//    public void getCompareNullMileToYardShouldNotBeEqual() {
//        Yard y = new Yard(1761);
//
//        Assert.assertTrue(!y.equals(null));
//
//    }

//    @Test
//    public void getCompareNullYardToYardShouldNotBeEqual() {
//
//    }

    //Start of Requirement 3

    @Test
    public void getCompareYardEqualToFeet(){
        Yard y = new Yard(1);
        Feet f = new Feet(3);

        Assert.assertTrue(y.equals(f));
    }

    @Test
    public void getCompareFeetEqualToInch(){
        Feet f = new Feet(1);
        Inch i = new Inch(12);

        Assert.assertTrue(f.equals(i));
    }

    @Test
    public void getCompareMileEqualToFeet(){
        Mile m = new Mile(1);
        Feet f = new Feet(5280);

        Assert.assertTrue(m.equals(f));
    }

    @Test
    public void getCompareMileEqualToInch() {
        Mile m = new Mile(1);
        Inch i = new Inch(63360);

        Assert.assertTrue(m.equals(i));
    }
}


//Todo  1 Requirement:
//Todo     As user of the library, I can use a Mile for actual Mile
//Todo
//Todo     3 Mile == 3 Mile DONE
//Todo     3 Mile != 2 Mile DONE
//Todo     3 Mile != 4 Mile DONE
//Todo      Mile != null
//Todo
//Todo     2 Requirement:
//Todo     As user of the library, I can use a Yard for actual Yard
//Todo
//Todo     1 Mile == 1760 Yard DONE
//Todo     3 Yard != 3 Yard DONE
//Todo     1 Mile != 1761 Yard DONE
//Todo     3 Yard != 4 Yard DONE
//Todo     3520yard == 2mile DONE

//Todo      Yard != null Mile
//Todo      Yard != null Yard
//Todo      null yard != Mile
//Todo
//Todo     3 Requirement:
//Todo     As user of the library, I can use a Feet and Inch
//Todo
//Todo     1 Yard == 3 Feet DONE
//Todo     1 Feet == 12 Inch DONE
//Todo     1 Mile = 5280 Feet DONE
//Todo     1 Mile = 63360 Inch DONE


//1 Mile <= 1760 Yard <= 300 Feet  <= 10 Inch
