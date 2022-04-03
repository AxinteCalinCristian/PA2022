package compulsory.window;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.JPanel;

import lombok.Getter;
import lombok.Setter;
import compulsory.utils.Pair;

/**
 * The visual representation of the game grid. It includes all the logic behind updating the visual game state.
 * @author Calin Axinte
 */
public class GameGrid extends JPanel{
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	private Integer noOfCols;
	
	@Getter
	@Setter
	private Integer noOfRows;
	
	private BufferedImage image;
	private Graphics2D offscreen;
	private Integer canvasWidth = 600, canvasHeight = 550;
	private Integer boardWidth, boardHeight;
	private Integer cellWidth, cellHeight;
	private Integer padX, padY;
	private Integer stoneSize = 20;
	
	private Set<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> sticks = new HashSet<>();
	
	public GameGrid(Integer noOfCols, Integer noOfRows) {
		generateGrid(noOfCols, noOfRows);
	}
	
	/**
	 * Generates a new grid with the given sizes.
	 * @param noOfCols
	 * @param noOfRows
	 */
	public void generateGrid(Integer noOfCols, Integer noOfRows) {
		this.noOfCols = noOfCols;
		this.noOfRows = noOfRows;
		this.padX = stoneSize + 20;
		this.padY = stoneSize + 20;
		this.cellWidth = (canvasWidth - 2 * padX) / (noOfCols - 1);
		this.cellHeight = (canvasHeight - 2 * padY) / (noOfRows - 1);
		this.boardWidth = (noOfCols - 1) * cellWidth;
		this.boardHeight = (noOfRows - 1) * cellHeight;
		
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
		 offscreen.setColor(Color.WHITE); //fill the image with white
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
		 
		 for (int row = 0; row < noOfRows; row++) {
			 for (int col = 0; col < noOfCols; col++) {
				 int x = padX + col * cellWidth;
				 int y = padY + row * cellHeight;
				 g.setColor(Color.LIGHT_GRAY);
				 g.drawOval(x - stoneSize / 2, y - stoneSize / 2, stoneSize, stoneSize);
			 }
		 }
		 
		 generateSticks(g);
	}
	
	/**
	 * Generates the sticks on the grid.
	 * @param g
	 */
	private void generateSticks(Graphics2D g) {
		Random rand = new Random();
		int add;
		
		for (int row = 0; row < noOfRows; row++) {
			 for (int col = 0; col < noOfCols; col++) {
				 add = rand.nextInt();
				 int x = padX + col * cellWidth;
				 int y = padY + row * cellHeight;

				 if(add % 2 == 0 && col < noOfCols - 1) {
					 Pair<Integer, Integer> first = new Pair<Integer, Integer>(padX + col * cellWidth, padY + row * cellHeight);
					 Pair<Integer, Integer> second = new Pair<Integer, Integer>(padX + (col+1) * cellWidth, padY + row * cellHeight);
					 Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> toAdd = new Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>(first, second);
					 sticks.add(toAdd);
				 }
				 
				 add = rand.nextInt();
				 if(add % 2 == 0 && row < noOfRows - 1) {
					 Pair<Integer, Integer> first = new Pair<Integer, Integer>(padX + col * cellWidth, padY + row * cellHeight);
					 Pair<Integer, Integer> second = new Pair<Integer, Integer>(padX + col * cellWidth, padY + (row+1) * cellHeight);
					 Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> toAdd = new Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>(first, second);
					 sticks.add(toAdd);
				 }
			 }
		 }
		
		Stroke prevStroke = g.getStroke();
		Color prevColor = g.getColor();
		Stroke currStroke = new BasicStroke(5);
		Color currColor = new Color(30, 35, 43);
		
		g.setStroke(currStroke);
		g.setColor(currColor);
		
		for(Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> stick : sticks) {
			g.drawLine(stick.first().first(), stick.first().second(), stick.second().first(), stick.second().second());
		}
		
		g.setStroke(prevStroke);
		g.setColor(prevColor);
	}
	
	 @Override
	 public void update(Graphics g) { } //No need for update

	 @Override
	 protected void paintComponent(Graphics graphics) {
		 graphics.drawImage(image, 0, 0, this);
		 paintGrid(graphics);
	 }

}
