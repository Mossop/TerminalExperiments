/**
 * Holds information about colour and formatting for the ColouredString class.
 */
public class ColourInfo implements Comparable
{
	/**
   * Holds the offset in the text at which this information takes effect.
   */
	private int offset;
	
	public void setOffset(int newoffset)
	{
		offset=newoffset;
	}
	
	public int getOffset()
	{
		return offset;
	}
	
	/**
   * Allows us to compare this object to another ColourInfo object or an Integer for
   * indexing purposes.
   */
	public int compareTo(Object obj)
	{
		if (obj instanceof ColourInfo)
		{
			return (new Integer(offset)).compareTo(new Integer(((ColourInfo)obj).getOffset()));
		}
		else if (obj instanceof Integer)
		{
			return (new Integer(offset)).compareTo(obj);
		}
		else
		{
			throw new ClassCastException();
		}
	}
}
