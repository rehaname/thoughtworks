package org.length;

import org.junit.Assert;
import org.junit.Test;
import org.length.Mile;

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
    public void compareMileNotEqualToNull(){
        Mile x = new org.length.Mile(3);

        Assert.assertTrue(!x.equals(null));
    }

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
    public void getCompareYardToMileShouldBeEqual(){
        Yard y = new Yard(3520);
        Mile m = new Mile(2);

        Assert.assertTrue(y.equals(m));
    }





    @Test
    public void getCompareNullMileToYardShouldNotBeEqual() {


        Yard y = new Yard(1761);

        Assert.assertTrue(!y.equals(null));

    }

    @Test
    public void getCompareNullYardToYardShouldNotBeEqual() {

    }



    @Test
    public void getCompareMileEqualToInch(){
        Mile m = new Mile(1);
        Inch i = new Inch(63360);

        Assert.assertTrue(m.equals(i));
    }







}




//Todo  1 Requirement:
//Todo     As user of the library, I can use a Mile for actual Mile
//Todo
//Todo     3 Mile == 3 Mile
//Todo     3 Mile != 2 Mile
//Todo     3 Mile != 4 Mile
//Todo      Mile != null
//Todo
//Todo     2 Requirement:
//Todo     As user of the library, I can use a Yard for actual Yard
//Todo
//Todo     1 Mile == 1760 Yard
//Todo     3 Yard != 3 Yard
//Todo     1 Mile != 1761 Yard
//Todo     3 Yard != 4 Yard
//Todo     3520yard == 2mile
//Todo      Yard != null Mile
//Todo      Yard != null Yard
//Todo      null yard != Mile
//Todo
//Todo     3 Requirement:
//Todo     As user of the library, I can use a Feet and Inch
//Todo
//Todo     1 Yard == 3 Feet
//Todo     1 Feet == 12 Inch
//Todo     1 Mile = 5280 Feet
//Todo     1 Mile = 63360 Inch


//1 Mile <= 1760 Yard <= 300 Feet  <= 10 Inch
