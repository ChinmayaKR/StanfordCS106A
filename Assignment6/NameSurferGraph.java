/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas implements NameSurferConstants, ComponentListener {

	private ArrayList<NameSurferEntry> graphData;

	/**
	 * Creates a new NameSurferGraph object that displays the data.
	 */
	public NameSurferGraph() {

		addComponentListener(this);

		graphData = new ArrayList<NameSurferEntry>();
	}

	/* Method: clear() */
	/**
	 * Clears the list of name surfer entries stored inside this class.
	 */
	public void clear() {
		graphData.clear();
	}

	/* Method: addEntry(entry) */
	/**
	 * Adds a new NameSurferEntry to the list of entries on the display.
	 * Note that this method does not actually draw the graph, but
	 * simply stores the entry; the graph is drawn by calling update.
	 */
	public void addEntry(NameSurferEntry entry) {
		graphData.add(entry);
	}

	/**
	 * Updates the display image by deleting all the graphical objects
	 * from the canvas and then reassembling the display according to
	 * the list of entries. Your application must call update after
	 * calling either clear or addEntry; update is also called whenever
	 * the size of the canvas changes.
	 */
	public void update() {
		removeAll();
		createBackgroundGrid();
		createYearLabels();
		plotGraphs();
	}

	/** 
	 * Plots all graphs according to the entries stored in graphData
	 */
	private void plotGraphs() {
		double unitPlotLength = (getHeight() - (GRAPH_MARGIN_SIZE * 2))/(double)MAX_RANK;
		int numPlots = 1;

		for(int i = 0 ; i < graphData.size() ; i++) {
			NameSurferEntry entry = graphData.get(i);

			for(int j = 0 ; j < NDECADES  ; j++) {
				double x1Plot = (getWidth()/(double)NDECADES) * j;
				double x2Plot = x1Plot + getWidth()/(double)NDECADES;
				double y1Plot = unitPlotLength * entry.getRank(j) + GRAPH_MARGIN_SIZE; 
				double y2Plot = unitPlotLength * entry.getRank((j < NDECADES - 1)?(j + 1):NDECADES-1) + GRAPH_MARGIN_SIZE;

				if(entry.getRank(j) == 0) {
					y1Plot = getHeight() - GRAPH_MARGIN_SIZE;
				}
				if(entry.getRank((j < NDECADES - 1)?(j + 1):NDECADES-1) == 0) {
					y2Plot = getHeight() - GRAPH_MARGIN_SIZE;
				}

				GLine plotLine = new GLine(x1Plot, y1Plot, x2Plot, y2Plot);
				GLabel pointName = getNameLabel(entry, j, x1Plot, y1Plot);

				pointName.setColor(getNextColor(numPlots));
				plotLine.setColor(getNextColor(numPlots));

				add(plotLine);
				add(pointName);
			}
			numPlots++;
		}
	}

	/**
	 * Returns a label to name the respective points with correctly set positioning.
	 */
	private GLabel getNameLabel(NameSurferEntry entry, int i, double x1Plot, double y1Plot) {
		GLabel pointLabel;
		if(entry.getRank(i) != 0) {
			if(entry.getRank(i) < MAX_RANK/2) {
				pointLabel = new GLabel(entry.getName() + " " + entry.getRank(i), x1Plot + 5, y1Plot + 10);
			} else {
				pointLabel = new GLabel(entry.getName() + " " + entry.getRank(i), x1Plot + 5, y1Plot - 5);						
			}					
		} else {
			pointLabel = new GLabel(entry.getName() + " *", x1Plot + 5, y1Plot -5);
		}
		return pointLabel;
	}

	/**
	 * Returns the color according to the parameter passed.
	 * 
	 * @param numPlots The number of plots drawn.
	 * @return The color according to number of plots drawn.
	 */
	private Color getNextColor(int numPlots) {

		switch(numPlots) {

		case 1: return Color.BLUE;

		case 2:	return Color.GREEN;

		case 3: return Color.RED;

		case 4: return Color.ORANGE;

		case 5:	return Color.MAGENTA;

		case 6: return Color.CYAN;

		case 7: return Color.YELLOW;

		default : return Color.BLACK;	
		}
	}

	/** 
	 * Labels each grid line with its respective year.
	 */
	private void createYearLabels() {

		for(int i = 0 ; i < NDECADES ; i++) {
			int year = START_DECADE + (10*i);
			double labelStart = getWidth()/(double)NDECADES;

			GLabel yearLabel = new GLabel("" + year, labelStart * i + 2, getHeight() - GRAPH_MARGIN_SIZE/4);

			yearLabel.setFont("Courier-17");
			add(yearLabel);
		}
	}

	/** 
	 * Creates the complete background grid lines along with margins.
	 */
	private void createBackgroundGrid() {

		drawGridLines();

		GLine baseLine = new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE);
		GLine topLine = new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);

		add(baseLine);
		add(topLine);
	}


	/**
	 * Draws the required amount of vertical grid lines.
	 */
	private void drawGridLines() {
		for(double i = 0 ; i < NDECADES ; i++) {
			double xLine = getWidth() - ((getWidth()/(double)NDECADES) * i);
			double y1Line = getHeight();
			double y2Line = 0;

			GLine line = new GLine(xLine, y1Line, xLine, y2Line);
			add(line);
		}
	}

	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
