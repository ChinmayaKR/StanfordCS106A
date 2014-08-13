/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

	/** Instance variable declarations */
	private JButton clearButton;
	
	private JButton graphButton;
	
	private JTextField nameField;
	
	private NameSurferDataBase dataBase;
	
	private NameSurferGraph graph;

	/* Method: init() */
	/**
	 * This method has the responsibility for reading in the data base
	 * and initializing the interactors at the top of the window.
	 */
	public void init() {

		graphButton = new JButton("Graph");
		clearButton = new JButton("Clear");
		nameField = new JTextField(GRAPH_MARGIN_SIZE);
		dataBase = new NameSurferDataBase(NAMES_DATA_FILE);
		graph = new NameSurferGraph();
		
		add(new JLabel("Name: "), NORTH);
		add(nameField, NORTH);
		add(graphButton, NORTH);
		add(clearButton, NORTH);
		add(graph);
				
		addActionListeners();
		nameField.addActionListener(this);
	}

	/* Method: actionPerformed(e) */
	/**
	 * This class is responsible for detecting when the buttons are
	 * clicked, so you will have to define a method to respond to
	 * button actions.
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Graph") || e.getSource() == nameField) {
			drawGraphOfGivenName();
		} 
		if(e.getActionCommand().equals("Clear")) {
			graph.clear();
			graph.update();
		}
	}

	/**
	 * Plots all the lines according to the ranks of the input names
	 * and displays an error if a name which is not in the list is entered.
	 */
	private void drawGraphOfGivenName() {
		String name = nameField.getText();
		if(dataBase.findEntry(name) != null) {
			graph.addEntry(dataBase.findEntry(name));
			graph.update();
		} else {
			JOptionPane.showMessageDialog(this, "The name " + name + " is not found", "Error", JOptionPane.ERROR_MESSAGE);
		}
	} 
}
