/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5;

/**
 *
 * @author techn
 */
public class SlobberhannesCard extends Card {

    public SlobberhannesCard(int rank, Suit suit) {
        super(rank, suit);
    }

    /*
    * Cards compared based off rank. Card that matches lead > everything else
    * Apparently if neither match lead, you do not compare off value and just return false
    * which seems weird but is what the test harness expects
    */
    @Override
    public boolean outranks(Card other, Suit lead, Suit trump) {
        if(m_suit == lead){
            if(other.m_suit == lead)
                return m_rank > other.m_rank;
            return true;
        }
        
        //Don't need to check if other is lead because no comparison if neither are lead
        return false;
    }
    
}
