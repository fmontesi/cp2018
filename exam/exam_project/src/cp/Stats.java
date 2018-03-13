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
	 * Returns the number of times that a number was found (in any files).
	 */
	public int occurrences( int number );
	
	/**
	 * Returns the list of files that do not contain numbers that are greater than max.
	 */
	public List< Path > atMost( int max );
	
	/**
	 * Returns the number that was found the most times.
	 */
	public int mostFrequent();
	
	/**
	 * Returns the number that was found the least times.
	 */
	public int leastFrequent();
	
	/**
	 * Returns a list of all the files found in the directory, ordered from the
	 * one that contains numbers whose sum is the smallest (first of the list)
	 * to the one that contains numbers whose sum is the greatest (last of the list).
	 */
	public List< Path > byTotals();
}
