public class Card
{
    private String face;
    private int value;
    private String suit;

    public Card()
    {
        face = " A";
        value = 1;
        suit = "Hearts";
    }

    public Card(String inFace, int inVal, String inSuit)
    {
        face = inFace;
        value = inVal;
        suit = inSuit;
    }

    public String getFace()
    {
        return face;
    }

    public int getValue()
    {
        return value;
    }

    public String getSuit()
    {
        return suit;
    }

    // NOTE: there are no modifiers (setters) because we
    //       don't want to ever change a card after we make it.

    public String toString()
    {
        return face + " of "+suit;
    }
}
