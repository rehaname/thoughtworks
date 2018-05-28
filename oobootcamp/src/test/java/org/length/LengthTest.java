package org.length;

import org.junit.Assert;
import org.junit.Test;

public class LengthTest {

    @Test
    public void compareMilesShouldBeEqual() throws Exception {

        Mile x = new Mile(3);
        Mile y = new Mile(3);

        Assert.assertTrue(x.equals(y));

    }

    @Test
    public void compareMilesShouldNotBeEqual() throws Exception {

        Mile x = new Mile(3);
        Mile y = new Mile(2);

        Assert.assertTrue(!x.equals(y));

    }

    @Test
    public void compareMilesShouldNotBeEqual2() throws Exception {

        Mile x = new Mile(3);
        Mile y = new Mile(4);


        Assert.assertTrue(!x.equals(y));
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
    public void getCompareNullMileToYardShouldNotBeEqual() {


        Yard y = new Yard(1761);

        Assert.assertTrue(!y.equals(null));



    }

    @Test
    public void getCompareNullYardToYardShouldNotBeEqual() {

    }




}
//TODO compare miles x Mile == y Mile
//TODO compare miles x Mile != y Mile
//TODO compare miles x Mile !=null
//TODO compare miles null != y Mile
//TODO  compare miles to yard  1 Mile = 1760 Yard
//TODO  3 Yard != 2 Yard
//TODO  1 Mile != 1761 Yard
//TODO compare  null != Yard


// you are not gonna need it (YAGNI)
//