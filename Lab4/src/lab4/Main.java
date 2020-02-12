package lab4;


/**
 *
 * @author jhudson
 */
public class Main {

    static void assertTrue(boolean b){
        if( !b ){
            throw new RuntimeException("Test failed");
        }
    }

    static void assertFalse(boolean b){
        if( b ){
            throw new RuntimeException("Test failed");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Card c1 = new Card( 4, Card.Suit.DIAMONDS);
        Card c2 = new Card( 5, Card.Suit.HEARTS);
        Card c3 = new Card( Card.ACE, Card.Suit.SPADES);
        Card c4 = new Card( 4, Card.Suit.DIAMONDS);
        Card c5 = new Card( Card.KING, Card.Suit.HEARTS);
        Card c6 = new Card( Card.QUEEN, Card.Suit.DIAMONDS);
        Card c7 = new Card( Card.JACK, Card.Suit.CLUBS);
        Card c8 = new Card( 10, Card.Suit.SPADES);
        
        assertTrue( c2.outranks(c1) );
        assertFalse( c1.outranks(c2) );
        assertFalse( c1.outranks(c1) );
        assertTrue( c3.outranks(c1));
        assertTrue( c3.outranks(c2));
        assertFalse( c1.outranks(c3));
        assertFalse( c2.outranks(c3));
        assertFalse( c4.outranks(c1) );
        assertTrue(c1.matchesSuit(Card.Suit.DIAMONDS));
        assertFalse(c1.matchesSuit(Card.Suit.SPADES));
        assertTrue( "Ace of Spades".equals( c3.toString() ));
        assertTrue( "King of Hearts".equals( c5.toString() ));
        assertTrue( "Queen of Diamonds".equals( c6.toString() ));
        assertTrue( "Jack of Clubs".equals( c7.toString() ));
        assertTrue( "10 of Spades".equals( c8.toString() ));
        assertTrue( "4 of Diamonds".equals( c4.toString() ));
    }
    
}
