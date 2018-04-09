package ru.job4j;

/**
 * Calculate is a template class for "Hello world" app
 *
 * @author Vlad G.
 */

public class Calculate {
	
	/**
	 * Main method.
	 * Prints "Hello world!" via command line.
	 */
	public static void main(String[] args) {
		System.out.print("Hello World!");
	}
	
	/**
	* Method echo.
	* @param name Your name.
	* @return Echo plus your name.
	*/
	public String echo(String name) {
		return "Echo, echo, echo : " + name;
	}
}