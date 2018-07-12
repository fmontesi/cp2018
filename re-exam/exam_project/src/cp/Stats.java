package cp;



import java.nio.file.Path;
import java.util.List;

/**
 *
 * @author Fabrizio Montesi <fmontesi@imada.sdu.dk>
 */
public interface Stats
{
	/**
	 * Returns the number of times that a number was found (across all files).
	 */
	public int occurrences( int number );
	
	/**
	 * Returns the number that was found the most times (across all files).
	 */
	public int mostFrequent();
	
	/**
	 * Returns the number that was found the least times (of course, you should have found it at least once).
	 */
	public int leastFrequent();
	
	/**
	 * Returns a list of all the files found in the directory, ordered from the
	 * one that contains numbers whose sum across all lines is the smallest
	 * (first of the list),
	 * to the one that contains numbers whose sum is the greatest (last of the list).
	 */
	public List< Path > byTotals();
}
