/*
**	Author:      Dinesh Karthikesu
**	Email:       dineshkarthikesu@live.com
**	Text Editor: Sublime Text 2
**	Project:     URL Shortener – Mindvalley Challenge
**  Class        DataManager
*/

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/* File Handling */
import java.io.File;
import java.io.FileInputStream;     //reading
import java.io.ObjectInputStream;   //reading
import java.io.FileOutputStream;    //writing
import java.io.ObjectOutputStream;  //writing
import java.io.PrintStream;			//writing

import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.ClassNotFoundException;
import java.lang.SecurityException;


public class DataManager {
	public static ArrayList<URLEntry> entryList = new ArrayList<URLEntry> ();
	
	public static File urlFile = new File ("urlEntries.data");
	public static boolean urlFileExists;

	public static File reportFile = new File ("report.txt");
	public static PrintStream reportStream;

	public static void addNewURLEntry (URLEntry newEntry) {
		entryList.add (newEntry);
	}

	public static int getNewID () {
		if (entryList.size () == 0) {
			return 1;
		}

		else {
			return entryList.get (entryList.size () - 1).getUniqueID () + 1;
		}
	}

	public static void printEntryList () {
		int counter;

		for (counter = 0; counter < entryList.size (); counter++) {
			System.out.printf ("\n%d::%s::%s", entryList.get (counter).getUniqueID (), entryList.get (counter).getShortURL (), entryList.get (counter).getLongURL ());
		}
	}

	public static void printReport () {
		int counter;

		try {
			reportStream = new PrintStream (new FileOutputStream (reportFile, false));

			reportStream.printf ("====================");
			reportStream.printf ("\n");
			reportStream.printf ("URL - ID Map Report");
			reportStream.printf ("\n====================");

			for (counter = 0; counter < entryList.size (); counter++) {
				reportStream.printf ("\n");
				reportStream.printf ("\nID:%d\t\tShort URL: %s\t\tOriginal URL: %s", entryList.get (counter).getUniqueID (), entryList.get (counter).getShortURL (), entryList.get (counter).getLongURL ());
			}
		}

		catch (IOException ioe)
		{
			System.out.printf ("\nIO Exception");
		}

		catch (SecurityException se)
		{
			System.out.printf ("\nSecurity Exception");
		}	
	}

	public static void saveList () {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream ("urlEntries.data");
			ObjectOutputStream objectOutputStream = new ObjectOutputStream (fileOutputStream);

			objectOutputStream.writeObject (entryList);
			objectOutputStream.close ();
		}
		
		catch (IOException ioe) {
			System.out.printf ("\nIO Exception");
		}

		catch (SecurityException se) {
			System.out.printf ("\nSecurity Exception");
		}
	}

	public static void readFromObjectFile () {
		urlFileExists = urlFile.exists ();

		try {
			if (urlFileExists) {
				FileInputStream fileInputStream = new FileInputStream (urlFile);
				ObjectInputStream objectInputStream = new ObjectInputStream (fileInputStream);

				entryList = (ArrayList<URLEntry>) objectInputStream.readObject ();
				objectInputStream.close ();
			}

			else {
				entryList = (ArrayList<URLEntry>) new ArrayList<URLEntry> ();
			}
		}
			
		catch (IOException ioe) {
			System.out.printf ("\nIO Exception");
		}

		catch (ClassNotFoundException cnfe) {
			System.out.printf ("\nClass Not Found Exception");
		}
	}

	public static boolean validURL (String longURL) {
		Pattern pattern = Pattern.compile ("(@)?(href=')?(HREF=')?(HREF=\")?(href=\")?(http://)?[a-zA-Z_0-9\\-]+(\\.\\w[a-zA-Z_0-9\\-]+)+(/[#&\\n\\-=?\\+\\%/\\.\\w]+)?");  

    	Matcher matcher = pattern.matcher (longURL); 	

    	return matcher.matches ();
    }

    public static boolean validID (int uniqueID) {
    	int counter;

    	for (counter = 0; counter < entryList.size (); counter++) {
    		if (entryList.get (counter).getUniqueID () == uniqueID) {
    			return true;
    		}
    	}

    	return false;
    }

    public static String getURLFromID (int uniqueID) {
    	int counter;

    	for (counter = 0; counter < entryList.size (); counter++) {
    		if (entryList.get (counter).getUniqueID () == uniqueID) {
    			return entryList.get (counter).getLongURL ();	
    		}
    	}

    	return null;
    }
}