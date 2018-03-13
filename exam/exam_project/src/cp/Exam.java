package cp;

import java.nio.file.Path;
import java.util.List;

/**
 * 
 * @author Fabrizio Montesi <fmontesi@imada.sdu.dk>
 */
public class Exam
{
	/**
	 * This method recursively visits a directory to find all the text files contained in it and its subdirectories.
	 * 
	 * You must consider only files ending with a .txt suffix. You are guaranteed that they will be text files.
	 * 
	 * You can assume that each text file contains a (non-empty) comma-separated sequence of
	 * numbers. For example: 100,200,34,25
	 * There won't be any new lines, spaces, etc., and the sequence never ends with a comma.
	 * You are guaranteed that each number will be at least or equal to 0 (zero), i.e., no negative numbers.
	 * 
	 * The search is recursive: if the directory contains subdirectories,
	 * these are also searched and so on so forth (until there are no more
	 * subdirectories).
	 * 
	 * This method returns a list of results. The list contains a result for each text file that you find.
	 * Each {@link Result} stores the path of its text file, and the lowest number (maximum) found inside of the text file.
	 * 
	 * @param dir the directory to search
	 * @return a list of results ({@link Result}), each giving the lowest number found in a file
	 */
	public static List< Result > m1( Path dir )
	{
		throw new UnsupportedOperationException();
	}

	
}
