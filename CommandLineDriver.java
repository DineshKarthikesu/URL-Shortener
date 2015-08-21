import java.util.Scanner;

public class CommandLineDriver {
	public static void main (String arrayOfArguments[]) {
		Scanner input = new Scanner (System.in);
		int userChoice;

		do {
			System.out.printf ("\nEnter a number: ");
			userChoice = input.nextInt ();
			String shortURL = BaseConverter.createShortURL (userChoice, 62);
			System.out.printf ("\nResult: %s", shortURL);
			System.out.printf ("\nOriginal ID: %d", BaseConverter.retrieveIDFromURL (shortURL, 62));
		} while (userChoice != -1);
	}
}