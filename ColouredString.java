import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class holds a set of text along with colour and formatting information.
 * It implements CharSequence so that regex matching may be used on it.
 */
public abstract class ColouredString implements CharSequence
{
	protected StringBuffer text = new StringBuffer();
	protected List colours = new ArrayList();
	
	/**
   * This method returns an iterator to a set of StringColourPair objects.
   * These objects in order give the text and colour of each part of the ColouredString.
   */
	public Iterator iterator()
	{
		List newlist = new ArrayList();
		ColourInfo colour = (ColourInfo)colours.get(0);
		ColourInfo nextcolour;
		StringColourPair pair;
		for (int loop=1; loop<colours.size(); loop++)
		{
			nextcolour = (ColourInfo)colours.get(loop);
			pair = new StringColourPair(colour,text.substring(colour.getOffset(),nextcolour.getOffset()));
			newlist.add(pair);
			colour=nextcolour;
		}
		pair = new StringColourPair(colour,text.substring(colour.getOffset()));
		newlist.add(pair);
		return newlist.iterator();
	}
	
	/**
   * Returns the character at a specified index in the text.
   */
	public char charAt(int index)
	{
		return text.charAt(index);
	}
	
	/**
   * Returns the length of the text.
   */
	public int length()
	{
		return text.length();
	}
	
	/**
   * Returns a CharSequence of part of the text.
   * No colour information is held in the subsequence.
   */
	public CharSequence subSequence(int start, int end)
	{
		return text.subSequence(start,end);
	}
	
	/**
   * Returns the plain text.
   */
	public String toString()
	{
		return text.toString();
	}
}
