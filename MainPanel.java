/*
**	Author:      Dinesh Karthikesu
**	Email:       dineshkarthikesu@live.com
**	Text Editor: Sublime Text 2
**	Project:     URL Shortener – Mindvalley Challenge
**  Class        MainPanel
*/

/* Layout Managers */
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Dimension;
import java.awt.Font;

/* Light-weight Swing Components */
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;

/* Event Handlers */
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainPanel extends JFrame {

	private GridBagConstraints constraint = new GridBagConstraints ();

	private JLabel shortURLLabel = new JLabel ("URL ID");
	private JLabel longURLLabel = new JLabel ("Long URL");

	private Font font = shortURLLabel.getFont ();
	private Font boldFont = new Font (font.getFontName (), Font.BOLD, font.getSize ());
	private JTextField shortURLTextField = new JTextField (15);
	private JTextField longURLTextField = new JTextField (15);

	private JButton conversionButton = new JButton ("Generate");
	private JButton printReportButton = new JButton ("Print Report");
	private JButton saveButton = new JButton ("Save & Exit");

	private String actionOptions[] = {"Shorten URL", "Expand URL"};
	private JComboBox<String> conversionComboBox = new JComboBox<String> (actionOptions);

	private Dimension preferredButtonSize = new Dimension (265, 30);

	public MainPanel () {
		super ("URL Shortener – Mindvalley Challenge");
		setLayout (new GridBagLayout ());
		DataManager.readFromObjectFile ();

		/* Event Handler Instances */
		ItemHandler itemHandler = new ItemHandler ();
		ButtonHandler buttonHandler = new ButtonHandler ();

		/* Add Event Handlers to GUI Components */
		conversionComboBox.addItemListener (itemHandler);
		conversionButton.addActionListener (buttonHandler);
		saveButton.addActionListener (buttonHandler);
		printReportButton.addActionListener (buttonHandler);

		/* Organisation of the components */
		constraint.gridx = 0;
		constraint.gridy = 0;
		constraint.gridwidth = 2;
		((JLabel) conversionComboBox.getRenderer ()).setHorizontalAlignment (SwingConstants.CENTER);
		conversionComboBox.setPreferredSize (preferredButtonSize);
		add (conversionComboBox, constraint);

		constraint.gridx = 0;
		constraint.gridy = 1;
		constraint.gridwidth = 1;
		add (shortURLLabel, constraint);

		constraint.gridx = 1;
		constraint.gridy = 1;
		shortURLTextField.setHorizontalAlignment (SwingConstants.CENTER);
		add (shortURLTextField, constraint);

		constraint.gridx = 0;
		constraint.gridy = 2;
		add (longURLLabel, constraint);

		constraint.gridx = 1;
		constraint.gridy = 2;
		longURLTextField.setHorizontalAlignment (SwingConstants.CENTER);
		add (longURLTextField, constraint);

		constraint.gridx = 0;
		constraint.gridy = 3;
		constraint.gridwidth = 2;
		conversionButton.setPreferredSize (preferredButtonSize);
		add (conversionButton, constraint);

		constraint.gridx = 0;
		constraint.gridy = 4;
		printReportButton.setPreferredSize (preferredButtonSize);
		add (printReportButton, constraint);

		constraint.gridx = 0;
		constraint.gridy = 5;
		saveButton.setPreferredSize (preferredButtonSize);
		add (saveButton, constraint);

		/* Default */
		shortURLTextField.setEditable (false);
		longURLTextField.setEditable (true);
		longURLLabel.setFont (boldFont);
		shortURLLabel.setFont (font);
	}

	/* Button Handler class that handles button presses */
	private class ButtonHandler implements ActionListener {
		public void actionPerformed (ActionEvent event) {
			if (event.getSource () == conversionButton) {
				if ("Shorten URL".equals (conversionComboBox.getSelectedItem ())) {
					
					/* Create new URL Entry */

					/* Check if the URL is valid */

					if (DataManager.validURL (longURLTextField.getText ())) {

						URLEntry newEntry = new URLEntry ();

						newEntry.setUniqueID (DataManager.getNewID ());
						newEntry.setLongURL (longURLTextField.getText ());
						newEntry.setShortURL (BaseConverter.createShortURL (newEntry.getUniqueID (), 62));

						DataManager.addNewURLEntry (newEntry);

						shortURLTextField.setText (newEntry.getShortURL ());
						JOptionPane.showMessageDialog (null, "Successfully added " + longURLTextField.getText (), "Valid URL", JOptionPane.INFORMATION_MESSAGE);
					}

					else {
						JOptionPane.showMessageDialog (null, "The URL Entered is Invalid", "Invalid URL", JOptionPane.ERROR_MESSAGE);
						System.out.printf ("\nInvalid URL");
					}
				}

				else if ("Expand URL".equals (conversionComboBox.getSelectedItem ())) {

					/* Retrieve ID from URL ID*/

					/* Check if the URL ID is valid */

					if (DataManager.validID (BaseConverter.retrieveIDFromURL (shortURLTextField.getText (), 62))) {
						System.out.printf ("\nValid");
						longURLTextField.setText (DataManager.getURLFromID (BaseConverter.retrieveIDFromURL (shortURLTextField.getText (), 62)));
					}

					else {
						JOptionPane.showMessageDialog (null, "The URL ID Entered is Invalid", "Invalid URL ID", JOptionPane.ERROR_MESSAGE);
					}
				}
			}

			if (event.getSource () == printReportButton) {
				DataManager.printReport ();
				JOptionPane.showMessageDialog (null, "Successfully printed the requested report (report.txt)", "Success", JOptionPane.INFORMATION_MESSAGE);
			}

			if (event.getSource () == saveButton) {
				DataManager.saveList ();
				JOptionPane.showMessageDialog (null, "All data has been successfully saved. Exiting..", "Save Success", JOptionPane.INFORMATION_MESSAGE);
				System.exit (0);
			}
		}
	}

	/* Item Handler class that handles the changes in the ComboBox */
	private class ItemHandler implements ItemListener {
		public void itemStateChanged (ItemEvent event) {

			Object item = conversionComboBox.getSelectedItem ();

			if ("Shorten URL".equals (item)) {
				shortURLTextField.setEditable (false);
				longURLTextField.setEditable (true);
				longURLTextField.setText ("");
				shortURLTextField.setText ("");
				longURLLabel.setFont (boldFont);
				shortURLLabel.setFont (font);
			}

			else if ("Expand URL".equals (item)) {
				shortURLTextField.setEditable (true);
				longURLTextField.setEditable (false);	
				longURLTextField.setText ("");
				shortURLTextField.setText ("");
				shortURLLabel.setFont (boldFont);
				longURLLabel.setFont (font);
			}

			conversionComboBox.revalidate ();
			conversionComboBox.repaint ();
		}
	}
}