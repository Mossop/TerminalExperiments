import java.util.Collections;

/**
 * This is a ColouredString that can be altered.
 */
public class ColouredStringBuffer extends ColouredString
{
	/**
   * Creates a new ColouredStringBuffer with the contents specified.
   */
	public ColouredStringBuffer(String newtext)
	{
		super();
		text.append(newtext);
		ColourInfo start = new ColourInfo();
		start.setOffset(0);
		insertColourInfo(start);
	}
	
	/**
   * Inserts a ColouredString into the middle of this one.
   * This is currently broken - it loses the colour information given.
   */
	public void insert(int offset, ColouredString newtext)
	{
		text.insert(offset,newtext.toString());
		advanceColourOffsets(offset,newtext.length());
	}
	
	/**
   * Inserts some object into the text.
   */
	public void insert(int offset, Object newtext)
	{
		int inc=text.length();
		text.insert(offset,newtext);
		inc=text.length()-inc;
		advanceColourOffsets(offset,inc);
	}
		
	/**
   * Every ColourInfo offset after a point in the text gets advanced by the given increment.
   * Used when inserting text into this object.
   */
	private void advanceColourOffsets(int offset, int increment)
	{
		int pos = Collections.binarySearch(colours,new Integer(offset));
		if (pos<0)
		{
			pos=-(pos+1);
		}
		ColourInfo ci;
		for (int loop=pos; loop<colours.size(); loop++)
		{
			ci = (ColourInfo)colours.get(loop);
			ci.setOffset(ci.getOffset()+increment);
		}
	}
	
	/**
   * Helper method to insert the colour information into the correct place.
   */
	private void insertColourInfo(ColourInfo colour)
	{
		int pos = Collections.binarySearch(colours,colour);
		if (pos>=0)
		{
			colours.remove(pos);
			colours.add(pos,colour);
		}
		else
		{
			colours.add(-(pos+1),colour);
		}
	}
}
