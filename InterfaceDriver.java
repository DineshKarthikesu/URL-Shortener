/*
**	Author:      Dinesh Karthikesu
**	Email:       dineshkarthikesu@live.com
**	Text Editor: Sublime Text 2
**	Project:     URL Shortener – Mindvalley Challenge
**  Class        InterfaceDriver
*/

import javax.swing.JFrame;

public class InterfaceDriver {
	public static void main (String arrayOfArguments[]) {
		MainPanel mainPanel = new MainPanel ();
		mainPanel.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		mainPanel.setSize (400, 400);
		mainPanel.setLocation (60, 60);
		mainPanel.setVisible (true);
	}
}