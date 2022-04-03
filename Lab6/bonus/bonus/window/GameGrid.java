package bonus.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;

import lombok.Getter;
import lombok.Setter;
import bonus.utils.GridController;
import bonus.utils.Pair;

/**
 * The visual representation of the game grid. It includes all the logic behind updating the visual game state.
 * @author Calin Axinte
 */
@Getter
@Setter
public class GameGrid extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private Integer noOfCols;
	private Integer noOfRows;
	private BufferedImage image;
	private Graphics2D offscreen;
	private Integer canvasWidth = 600, canvasHeight = 500;
	private Integer boardWidth, boardHeight;
	private Integer cellWidth, cellHeight;
	private Integer padX, padY;
	private Integer diameter = 20;
	private MainPanel mainFrame;
	private Boolean currPlayer;
	private Boolean init = false;
	private Boolean firstMove = true;
	
	private Set<Pair<GridIntersection, GridIntersection>> sticks = new HashSet<>();
	private Set<GridIntersection> intersections = new HashSet<>();
	
	public GameGrid(MainPanel mainFrame, Integer noOfCols, Integer noOfRows, Boolean currPlayer) {
		this.mainFrame = mainFrame;
		this.currPlayer = currPlayer;
		generateGrid(noOfCols, noOfRows);
	}
	
	/**
	 * Generates a new grid with the given sizes.
	 * @param noOfCols
	 * @param noOfRows
	 */
	public void generateGrid(Integer noOfCols, Integer noOfRows) {
		this.removeAll();
		this.firstMove = true;
		this.noOfCols = noOfCols;
		this.noOfRows = noOfRows;
		this.padX = diameter + 20;
		this.padY = diameter + 20;
		this.cellWidth = (canvasWidth - 2 * padX) / (noOfCols - 1);
		this.cellHeight = (canvasHeight - 2 * padY) / (noOfRows - 1);
		this.boardWidth = (noOfCols - 1) * cellWidth;
		this.boardHeight = (noOfRows - 1) * cellHeight;
		
		this.intersections = GridController.generateIntersections(mainFrame, this, currPlayer);
		this.sticks = GridController.generateSticks(this, intersections);
		
		this.setLayout(null);
		setPreferredSize(new Dimension(canvasWidth, canvasHeight));
		createOffscreenImage();
		this.setVisible(true);
	}
	
	/**
	 * Generates background image of the grid.
	 */
	private void createOffscreenImage() {
		 image = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_ARGB);
		 offscreen = image.createGraphics();
		 offscreen.setColor(Color.WHITE);
		 offscreen.fillRect(0, 0, canvasWidth, canvasHeight);
	}

	/**
	 * Displays the grid.
	 * @param graphics
	 */
	private void paintGrid(Graphics graphics) {
		Graphics2D g = (Graphics2D) graphics;
		g.setColor(Color.DARK_GRAY);
		 for (int row = 0; row < noOfRows; row++) {
			 int x1 = padX;
			 int y1 = padY + row * cellHeight;
			 int x2 = padX + boardWidth;
			 int y2 = y1;
			 g.drawLine(x1, y1, x2, y2);
		 }
		 
		 for (int col = 0; col < noOfCols; col++) {
			 int x1 = padX + col * cellWidth;
			 int y1 = padY;
			 int x2 = padX + col * cellWidth;
			 int y2 = y1 + boardHeight;
			 g.drawLine(x1, y1, x2, y2);
		 }
		 GridController.drawSticks(g, sticks);
		 GridController.drawIntersections(g, this, intersections);
		 init = true;
	}
	
	 @Override
	 protected void paintComponent(Graphics graphics) {
		 super.paintComponent(graphics);
		 paintGrid(graphics);
	 }
	 
	 /**
	  * Loads the values from a save file into the current game grid.
	  * @param noOfCols
	  * @param noOfRows
	  * @param currPlayer
	  * @param sticks
	  * @param intersections
	  */
	 public void loadSaveFile(Integer noOfCols, Integer noOfRows, Boolean currPlayer,
			 Set<Pair<GridIntersection, GridIntersection>> sticks, Set<GridIntersection> intersections) {
		 
		 	this.removeAll();
			this.firstMove = false;
			this.noOfCols = noOfCols;
			this.noOfRows = noOfRows;
			this.padX = diameter + 20;
			this.padY = diameter + 20;
			this.cellWidth = (canvasWidth - 2 * padX) / (noOfCols - 1);
			this.cellHeight = (canvasHeight - 2 * padY) / (noOfRows - 1);
			this.boardWidth = (noOfCols - 1) * cellWidth;
			this.boardHeight = (noOfRows - 1) * cellHeight;
			this.currPlayer = currPlayer;
			this.mainFrame.setCurrentPlayer(currPlayer);
			
			this.intersections = intersections;
			this.sticks = sticks;
			
			for(GridIntersection i : intersections) {
				this.add(i);
			}
			
			this.setLayout(null);
			setPreferredSize(new Dimension(canvasWidth, canvasHeight));
			createOffscreenImage();
			this.setVisible(true);
			this.setBackground(Color.white);
			this.repaint();
	 }
}
