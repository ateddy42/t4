package t4.nn.Player.Util;

/**
 * An Entry is an object used for sorting an array in reverse
 * order by value but also keeping its original index.
 * 
 * @author Teddy
 */
public class Entry implements Comparable<Entry> {
	public int index;
	public double value;

	/**
	 * Constructs a new Entry object with the given index and value
	 * @param index Array Index of the Entry
	 * @param value Array Value of the Entry
	 */
	public Entry(int index, double value) {
		this.index = index;
		this.value = value;
	}
	
	@Override
	public int compareTo(Entry o) {
        return (int) (o.value - this.value);
	}
	
	public String toString() {
		return this.index + " - " + this.value;
	}
}