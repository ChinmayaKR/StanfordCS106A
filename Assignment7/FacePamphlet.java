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

public class FacePamphlet extends Program 
					implements FacePamphletConstants {
	
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
		
		addActionListeners();
		statusField.addActionListener(this);
		pictureField.addActionListener(this);
		addFriendField.addActionListener(this);
    }
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
    	
    	if(e.getActionCommand().equals("Add")) {
    		
    	} else if(e.getActionCommand().equals("Delete")) {
    		
    	} else if(e.getActionCommand().equals("Lookup")) {
    		
    	} else if(e.getActionCommand().equals("Change Status") || e.getSource() == statusField) {
    		
    	} else if(e.getActionCommand().equals("Change Picture") || e.getSource() == pictureField) {
    		
    	} else if(e.getActionCommand().equals("Add Friend") || e.getSource() == addFriendField) {
    		
    	}
    }
}
