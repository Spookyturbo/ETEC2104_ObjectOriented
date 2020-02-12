package lab5;
/*
 */


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
        
        Card.Suit[] suitList = new Card.Suit[]{ Card.Suit.CLUBS, Card.Suit.DIAMONDS, Card.Suit.HEARTS, Card.Suit.SPADES};
        for(int k=0;k<4;++k){
            for(int l=0;l<4;++l){
                var suit1 = suitList[k];
                var suit2 = suitList[l];

                for(int rank1=2;rank1<=Card.ACE;rank1++){
                    for(int rank2=2;rank2<=Card.ACE;rank2++){
                        Card w1 = new WarCard(rank1,suit1);
                        Card w2 = new WarCard(rank2,suit2);
                        Card s1 = new SlobberhannesCard(rank1,suit1);
                        Card s2 = new SlobberhannesCard(rank2,suit2);
                        Card h1 = new WhistCard(rank1,suit1);
                        Card h2 = new WhistCard(rank2,suit2);

                        assertTrue( w1.outranks(w2,null,null) == (rank1 > rank2) );
                        
                        for(int m=0;m<4;++m){
                            var leadsuit = suitList[m];
                            if( suit1 != leadsuit && suit2 != leadsuit ){
                                assertFalse( s1.outranks(s2,leadsuit,null) );
                                assertFalse( s2.outranks(s1,leadsuit,null) );
                            }
                            else if( suit1 == leadsuit && suit2 != leadsuit ){
                                assertTrue( s1.outranks(s2,leadsuit,null) );
                                assertFalse( s2.outranks(s1,leadsuit,null) );
                            }
                            else if( suit1 != leadsuit && suit2 == leadsuit ){
                                assertFalse( s1.outranks(s2,leadsuit,null) );
                                assertTrue( s2.outranks(s1,leadsuit,null) );
                            }
                            else {
                                assertTrue( (rank1 > rank2) == s1.outranks(s2,leadsuit,null) );
                            }
                      
                            for(int n=0;n<4;++n){
                                var trumpsuit = suitList[n];
                                if( suit1 == trumpsuit && suit2 == trumpsuit ){
                                    assertTrue( (rank1 > rank2) == h1.outranks(h2,leadsuit,trumpsuit) );
                                } else if( suit1 == trumpsuit && suit2 != trumpsuit ){
                                    assertTrue( h1.outranks(h2,leadsuit,trumpsuit) );
                                } else if( suit2 == trumpsuit && suit1 != trumpsuit ){
                                    assertTrue( h2.outranks(h1,leadsuit,trumpsuit) );
                                } else {
                                    assertTrue( s1.outranks(s2,leadsuit,null) == h1.outranks(h2,leadsuit,trumpsuit) );
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("All tests OK");
    }
}
