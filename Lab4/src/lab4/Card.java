package lab4;

/**
 *
 * @author Andy Riedlinger
 */
public class Card {
    public enum Suit {
        HEARTS, CLUBS, DIAMONDS, SPADES;
        
        @Override
        public String toString() {
            String name = name();
            return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        }
    }
    
    public static final int JACK = 11;
    public static final int QUEEN = 12;
    public static final int KING = 13;
    public static final int ACE = 14;
    
    private int m_rank;
    private Suit m_suit;
    
    public Card(int rank, Suit suit) {
        if(rank < 2 || rank > 14)
            throw new RuntimeException("Bad rank");
        m_rank = rank;
        m_suit = suit;
    }
    
    public int getRank() {
        return m_rank;
    }
    
    public Suit getSuit() {
        return m_suit;
    }
    
    public boolean outranks(Card other) {
        return m_rank > other.getRank();
    }
    
    @Override
    public String toString() {
        String rank;
        
        switch(m_rank) {
            case JACK:
                rank = "Jack";
                break;
            case QUEEN:
                rank = "Queen";
                break;
            case KING:
                rank = "King";
                break;
            case ACE:
                rank = "Ace";
                break;
            default:
                rank = Integer.toString(m_rank);
        }
        
        return rank + " of " + m_suit.toString();
    }
    
    public boolean matchesSuit(Suit s) {
        return m_suit == s;
    }
    
}
