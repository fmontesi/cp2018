/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 *
 * @author fmontesi
 */
public class FileRead
{
	public static void main()
	{
		try {
			try(
				Stream<String> s =
					Files.lines( Paths.get( "/home/fmontesi/myfile.txt" ) )
			) {
				s.parallel()
					.map( line -> line.length() )
					.forEach( System.out::println );
			}
		} catch( IOException e ) { e.printStackTrace(); }
	}
}
