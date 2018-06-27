package com.oocl.bowling;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class BowlingTest {
    
    //TODO get final scores for frames 9-9-9-9-9-9-9-9-9-9- result is 90
    @Test
    public void bowl_no_strikes() throws Exception {
        assertThat( new Bowling("9-9-9-9-9-9-9-9-9-9-").getResult(),is(90));
    }

    //TODO get final scores for frames 12345123451234512345 result is 60
    @Test
    public void bowl_all_1_digit_() throws Exception {
        assertThat( new Bowling("12345123451234512345").getResult(),is(60));
    }

    //TODO get final scores for frames 12345123451234512345 result is 60
    @Test
    public void bowl_strike() throws Exception {
        assertThat( new Bowling("XXXXXXXXXXXX").getResult(),is(300));
    }

    //TODO get final scores for frames 12345123451234512345 result is 60
    @Test
    public void bowl_with_spare() throws Exception {
        assertThat( new Bowling("5/5/5/5/5/5/5/5/5/5/5").getResult(),is(150));
    }
}
