/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;

import java.awt.*;
import java.util.*;

import sun.org.mozilla.javascript.tools.shell.Global;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	private GLabel appMessage;
	private GLabel nameLabel;
	private GImage image;


	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		this.appMessage = new GLabel("");
		this.image = null;
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		remove(appMessage);
		appMessage = new GLabel(msg);
		appMessage.setFont(MESSAGE_FONT);
		
		double xLabel = getWidth()/2 - appMessage.getWidth()/2;
		double yLabel = getHeight() - BOTTOM_MESSAGE_MARGIN - appMessage.getDescent();
		
		appMessage.setLocation(xLabel, yLabel);
		add(appMessage);
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		removeAll();
		if(profile != null) {
			displayName(profile.getName());
			displayImage(profile.getImage());
			displayStatus(profile.getName(), profile.getStatus());
			displayFriends(profile.getFriends());
		}
	}

	/**
	 * Displays the name of the profile currently being viewed.
	 * 
	 * @param name The name of the profile being viewed
	 */
	private void displayName(String name) {
		nameLabel = new GLabel(name);
		nameLabel.setFont(PROFILE_NAME_FONT);
		
		double xLabel = LEFT_MARGIN;
		double yLabel = TOP_MARGIN + nameLabel.getAscent();
		
		nameLabel.setLocation(xLabel, yLabel);
		nameLabel.setColor(Color.BLUE);
		add(nameLabel);
	}

	/**
	 * Displays the profile picture of the profile being viewed
	 * under the profile name.
	 * 
	 * @param image The profile picture of the profile.
	 */
	private void displayImage(GImage image) {
		if(image != null) {
			this.image = image;
			
			double yImage = nameLabel.getY()+nameLabel.getDescent() + IMAGE_MARGIN;
			double xImage = LEFT_MARGIN;
			
			this.image.setBounds(xImage, yImage, IMAGE_WIDTH, IMAGE_HEIGHT);
			add(this.image);
		} else {
			double yImageBox = nameLabel.getY()+nameLabel.getDescent() + IMAGE_MARGIN;
			double xImageBox = LEFT_MARGIN;

			GRect imageBox = new GRect(xImageBox, yImageBox, IMAGE_WIDTH, IMAGE_HEIGHT);
			add(imageBox);
			
			GLabel imageLabel = new GLabel("No Image");
			imageLabel.setFont(PROFILE_IMAGE_FONT);
			
			double xLabel = LEFT_MARGIN + IMAGE_WIDTH/2 - imageLabel.getWidth()/2;
			double yLabel = yImageBox + IMAGE_HEIGHT/2;
			
			imageLabel.setLocation(xLabel, yLabel);
			add(imageLabel);
		}
	}

	/**
	 * Displays the status of the profile being viewed under the profile picture.
	 * 
	 * @param name The name of the profile being viewed
	 * @param status The status of the profile being viewed
	 */
	private void displayStatus(String name, String status) {
		GLabel statusLabel = new GLabel("");
		
		if(!status.equals("")) {
			statusLabel = new GLabel(name + " is " + status);
		} else {
			statusLabel = new GLabel("No current status");
		}
		statusLabel.setFont(PROFILE_STATUS_FONT);			
		
		double xLabel = LEFT_MARGIN;
		double yLabel = nameLabel.getY() + nameLabel.getDescent() + IMAGE_MARGIN 
				+ IMAGE_HEIGHT + STATUS_MARGIN + statusLabel.getAscent();
	
		statusLabel.setLocation(xLabel, yLabel);
		add(statusLabel);
	}

	/**
	 * Displays the list of friends the current profile has, given that
	 * the profiles of all thos friends exist.
	 * 
	 * @param friends The iterator of the friends of the current profile
	 */
	private void displayFriends(Iterator<String> friends) {
		GLabel friendsLabel = new GLabel("Friends:");
		friendsLabel.setFont(PROFILE_FRIEND_LABEL_FONT);
		
		double xLabel = getWidth()/2;
		double yLabel = nameLabel.getY() + nameLabel.getDescent() + IMAGE_MARGIN;
		
		friendsLabel.setLocation(xLabel, yLabel);
		add(friendsLabel);
		
		yLabel += friendsLabel.getHeight();
		
		while(friends.hasNext()) {
			GLabel friendsList = new GLabel(friends.next());
			friendsList.setFont(PROFILE_FRIEND_FONT);
			
			yLabel += friendsList.getHeight();
			
			friendsList.setLocation(xLabel, yLabel);
			add(friendsList);
		}
	}
}
