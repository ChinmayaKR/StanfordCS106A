/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

public class FacePamphlet extends Program implements FacePamphletConstants {

	private JButton addButton;
	private JButton deleteButton;
	private JButton lookupButton;
	private JButton statusButton;
	private JButton pictureButton;
	private JButton addFriendButton;
	private JTextField nameField;
	private JTextField statusField;
	private JTextField pictureField;
	private JTextField addFriendField;
	private FacePamphletCanvas canvas;
	private FacePamphletDatabase database;

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {

		this.database = new FacePamphletDatabase();
		this.canvas = new FacePamphletCanvas();

		createLayout();

		addActionListeners();
		statusField.addActionListener(this);
		pictureField.addActionListener(this);
		addFriendField.addActionListener(this);
	}


	/**
	 * Creates the profile page layout for the profile being viewed.
	 */
	private void createLayout() {
		this.addButton = new JButton("Add");
		this.deleteButton = new JButton("Delete");
		this.lookupButton = new JButton("Lookup");
		this.statusButton = new JButton("Change Status");
		this.pictureButton = new JButton("Change Picture");
		this.addFriendButton = new JButton("Add Friend");
		this.nameField = new JTextField(TEXT_FIELD_SIZE);
		this.statusField = new JTextField(TEXT_FIELD_SIZE);
		this.pictureField = new JTextField(TEXT_FIELD_SIZE);
		this.addFriendField = new JTextField(TEXT_FIELD_SIZE);

		add(new JLabel("Name"), NORTH);
		add(nameField, NORTH);
		add(addButton, NORTH);
		add(deleteButton, NORTH);
		add(lookupButton, NORTH);
		add(statusField, WEST);
		add(statusButton, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		add(pictureField, WEST);
		add(pictureButton, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		add(addFriendField, WEST);
		add(addFriendButton, WEST);

		add(canvas);
	}


	/**
	 * This class is responsible for detecting when the buttons are
	 * clicked or interactors are used, so you will have to add code
	 * to respond to these actions.
	 */
	public void actionPerformed(ActionEvent e) {
		String profileName = nameField.getText();
		String status = statusField.getText();
		String imageName = pictureField.getText();
		String friendName = addFriendField.getText();

		if(e.getActionCommand().equals("Add")) {
			
			if(!profileName.equals("")) {
				if(database.containsProfile(profileName)) {
					canvas.displayProfile(database.getProfile(profileName));
					canvas.showMessage("Profile of " + profileName + " already exists");
				} else {
					database.addProfile(new FacePamphletProfile(profileName));
					canvas.displayProfile(database.getProfile(profileName));
					canvas.showMessage("New profile created");
				}
			}    		
		} else if(e.getActionCommand().equals("Delete")) {
			
			if(!profileName.equals("")) {
				if(database.containsProfile(profileName)) {
					database.deleteProfile(profileName);
					canvas.displayProfile(null);
					canvas.showMessage("Profile of " + profileName + " deleted");
				} else {
					canvas.displayProfile(null);
					canvas.showMessage("Profile of " + profileName + " does not exist");
				}
			}
		} else if(e.getActionCommand().equals("Lookup")) {
			
			if(!profileName.equals("")) {
				if(database.containsProfile(profileName)) {
					canvas.displayProfile(database.getProfile(profileName));
					canvas.showMessage("Displaying " + profileName);
				} else {
					canvas.displayProfile(null);
					canvas.showMessage("Profile of " + profileName + " does not exist");
				}
			}
		} else if(e.getActionCommand().equals("Change Status") || e.getSource() == statusField) {
			
			if(!profileName.equals("")) {
				if(database.containsProfile(profileName)) {
					database.getProfile(profileName).setStatus(status);
					canvas.displayProfile(database.getProfile(profileName));
					canvas.showMessage("Status updated");
				} else {
					canvas.displayProfile(null);
					canvas.showMessage("Profile of " + profileName + " does not exist");
				}
			}
		} else if(e.getActionCommand().equals("Change Picture") || e.getSource() == pictureField) {
			
			if(!profileName.equals("")) {
				GImage image = null;
				if(database.containsProfile(profileName)) {
					try {
						if(!imageName.equals("")) {
							image = new GImage(imageName);        					
						}
						database.getProfile(profileName).setImage(image);
						canvas.displayProfile(database.getProfile(profileName));
						canvas.showMessage("Picture Updated");
					} catch(ErrorException ex) {
						JOptionPane.showMessageDialog(canvas, imageName + " image could not be found", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					canvas.displayProfile(null);
					canvas.showMessage("Profile of " + profileName + " does not exist");
				}
			}
		} else if(e.getActionCommand().equals("Add Friend") || e.getSource() == addFriendField) {
			
			if(!profileName.equals("") && !friendName.equals("")) {
				if(database.containsProfile(profileName)) {
					if(database.containsProfile(friendName)) {
						if(!friendName.equals(profileName)) {
							database.getProfile(profileName).addFriend(friendName);
							database.getProfile(friendName).addFriend(profileName);
							canvas.displayProfile(database.getProfile(profileName));
							canvas.showMessage(friendName + " added as a friend");
						} else {
							canvas.displayProfile(null);
							canvas.showMessage("Please add a profile other than your own");
						}
					} else {
						canvas.displayProfile(null);
						canvas.showMessage("Profile of " + friendName + " does not exist");
					}
				} else {
					canvas.displayProfile(null);
					canvas.showMessage("Profile of " + profileName + " does not exist");
				}
			}
		}
	}
}
