/*
**	Author:      Dinesh Karthikesu
**	Email:       dineshkarthikesu@live.com
**	Text Editor: Sublime Text 2
**	Project:     URL Shortener – Mindvalley Challenge
**  Class        BaseConverter
*/

public class BaseConverter {
	/*
		0-9, A-Z, a-z
	*/

	public static String[] mapOfValues = {
		"0","1","2","3","4","5","6","7","8","9",
		"A","B","C","D","E","F","G","H","I","J",
		"K","L","M","N","O","P","Q","R","S","T",
		"U","V","W","X","Y","Z","a","b","c","d",
		"e","f","g","h","i","j","k","l","m","n",
		"o", "p","q","r","s","t","u","v","w","x"
		,"y","z"
	};

	public static StringBuilder shortURL = new StringBuilder ();

	/*
		Method Name :: baseTenConverter (int number, int base)

		Accepts a base as well as a number as the arguments, and returns the converted number in the form
		of a string
	*/

	public static String createShortURL (int number, int base) {

		/* Clear the string */
		if (shortURL.length () > 0) {
			shortURL.delete (0, shortURL.length ());
		}

		while (number > 0) {
			shortURL.append (mapOfValues[number % base]);
			number = number / base;
		}

		shortURL = shortURL.reverse ();
		return shortURL.toString ();
	}

	public static int retrieveIDFromURL (String shortURL, int base) {
		int counter = 0;
		int uniqueID = 0;

		for (counter = 0; counter < shortURL.length (); counter++) {

			if ('0' <= shortURL.charAt (counter) && shortURL.charAt (counter) <= '9') {
				uniqueID = uniqueID * base + shortURL.charAt (counter) - '0';
			}

			if ('A' <= shortURL.charAt (counter) && shortURL.charAt (counter) <= 'Z') {
				uniqueID = uniqueID * base + shortURL.charAt (counter) - 'A' + 10;
			}

			if ('a' <= shortURL.charAt (counter) && shortURL.charAt (counter) <= 'z') {
				uniqueID = uniqueID * base + shortURL.charAt (counter) - 'a' + 36;
			}
		}

		return uniqueID;
	}
}