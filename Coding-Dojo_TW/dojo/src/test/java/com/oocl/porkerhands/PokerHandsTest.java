package com.oocl.porkerhands;

import com.oocl.pokerhands.Hand;
import org.junit.Assert;
import org.junit.Test;

public class PokerHandsTest {
    //todo 1.	High cards - done 2H 3D 5S 9C KD  - 2C 3H 4S 8C AH Player 2 wins
    @Test
    public void highCardToHighCard(){
        Hand playerOne = new Hand("2H","3D","5S","9C","KD");
        Hand playerTwo = new Hand("2C","3H","4S","8C","AH");

        Assert.assertEquals(playerOne.compareHand(playerTwo), "Lose");
    }

    // one pair > high cards - done 2H 2D 5S 9C KD 2C 3H 4S 8C AH Player 1 wins
    @Test
    public void pairToHighCard(){
        Hand playerOne = new Hand("2H","2D","5S","9C","KD");
        Hand playerTwo = new Hand("2C","3H","4S","8C","AH");

        Assert.assertEquals(playerOne.compareHand(playerTwo), "Win");
    }

    //one pair higher win - done 2H 2D 5S 9C KD 3C 3H 4S 8C AH Player 2 wins
    @Test
    public void pairToPairCard(){
        Hand playerOne = new Hand("2H","2D","5S","9C","KD");
        Hand playerTwo = new Hand("3C","3H","4S","8C","AH");

        Assert.assertEquals(playerOne.compareHand(playerTwo), "Lose");
    }

    //two pair < one pair - done 2H 2D 5S 9C KD 3C 3H 4S 4C AH Player 2 wins
    @Test
    public void twoPairToOnePair(){
        Hand playerOne = new Hand("2H","2D","5S","9C","KD");
        Hand playerTwo = new Hand("3C","3H","4S","4C","AH");

        Assert.assertEquals(playerOne.compareHand(playerTwo), "Lose");
    }

    //two pair highest win - done 2H 2D 9S 9C KD 3C 3H 4S 4C AH Player 1 wins
    @Test
    public void twoPairToTwoPair(){
        Hand playerOne = new Hand("2H","2D","9S","9C","KD");
        Hand playerTwo = new Hand("3C","3H","4S","4C","AH");

        Assert.assertEquals(playerOne.compareHand(playerTwo), "Win");
    }
}




//todo 2.	one pair > high cards - done 2H 2D 5S 9C KD -  2C 3H 4S 8C AH Player 1 wins


//todo 3.	one pair higer win - done 2H 2D 5S 9C KD 3C 3H 4S 8C AH Player 2 wins
//todo 4.	two pair > one pair - done 2H 2D 5S 9C KD 3C 3H 4S 4C AH Player 2 wins
//todo 5.	two pair highest win - done 2H 2D 9S 9C KD 3C 3H 4S 4C AH Player 1 wins
//todo 6.	two pair highest same, second higher win - done 2H 2D 9S 9C KD 3C 3H 9H 9D AH Player 2 wins
//todo 7.	two pair all same, high card win - done 3C 3D 9S 9C KD 3C 3H 9H 9D AH Player 2 wins
//todo 8.	three of kind > two pairs - done 3C 3D 3C 9C AD 4C 4H 9H 9D AH player 1 wins
//todo 9.	three of kind higher win - done 3C 3D 3C 9C 8D 4C 4H 4C 9D AH Player 2 wins
//todo 10.	straight > three of kind - done 3C 3D 3C 9C AD 4C 5H 6C 7D 8H Player 2 wins
//todo 11.	two straights higher wins - done 3C 4D 5C 6C 7D 4C 5H 6C 7D 8H Player 2 wins
//todo 12.	flush > straight - done 3C 8C 5C 6C JC 4C 5H 6C 7D 8H Player 1 wins
//todo 13.	two flush higher wins - done 3C 8C 5C 6C JC 4H 9H JH QH AH Player 2 wins
//todo 14.	Full house > flush - done 3C 3H 3D 6C 6D 4H 9H JH QH AH Player 1 wins
//todo 15.	two full house higher wins - done 3C 3H 3D 6C 6D 4H 4D 4C QH QD Player 2 wins
//todo 16.	Four of a kind > full house - done 3C 3H 3D 3S 6D 4H 4D 4C QH QD Player 1 wins
//todo 17.	two four of kind higher wins - done 3C 3H 3D 3S 6D 4H 4D 4C 4S QD Player 2 wins
//todo 18.	straight flush > four of kind - done 5C 6C 7C 8C 9C 4H 4D 4C 4S QD Player 1 wins
//todo 19.	two straight flush higher wins - done 5C 6C 7C 8C 9C 7H 8H 9H 10H JH Player 2 wins

