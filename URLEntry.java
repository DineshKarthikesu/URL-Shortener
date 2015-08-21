/*
**	Author:      Dinesh Karthikesu
**	Email:       dineshkarthikesu@live.com
**	Text Editor: Sublime Text 2
**	Project:     URL Shortener – Mindvalley Challenge
**  Class        URLEntry
*/

import java.io.Serializable;

public class URLEntry implements Serializable {
	private int uniqueID;
	private String longURL;
	private String shortURL;

	public URLEntry () {
		/* Empty Constructor */
	}

	public URLEntry (int uniqueID, String longURL, String shortURL) {
		setUniqueID (uniqueID);
		setLongURL (longURL);
		setShortURL (shortURL);
	}

	/* Getters and Setters */
	public void setUniqueID (int uniqueID) {
		this.uniqueID = uniqueID;
	}

	public void setLongURL (String longURL) {
		this.longURL = longURL;
	}

	public void setShortURL (String shortURL) {
		this.shortURL = shortURL;
	}

	public int getUniqueID () {
		return this.uniqueID;
	}

	public String getLongURL () {
		return this.longURL;
	}

	public String getShortURL () {
		return this.shortURL;
	}
}