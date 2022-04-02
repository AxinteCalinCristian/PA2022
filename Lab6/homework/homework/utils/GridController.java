package homework.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import homework.window.GameGrid;
import homework.window.GridIntersection;
import homework.window.MainPanel;

public abstract class GridController {
	public static Set<Pair<GridIntersection, GridIntersection>> generateSticks(GameGrid gameGrid, Set<GridIntersection> intersections) {
		Set<Pair<GridIntersection, GridIntersection>> sticks = new HashSet<>();
		Random rand = new Random();
		int add;

		for (int row = 0; row < gameGrid.getNoOfRows(); row++) {
			 for (int col = 0; col < gameGrid.getNoOfCols(); col++) {
				 add = rand.nextInt();
				 final int colVal = col;
				 final int rowVal = row;
				 if(add % 2 == 0 && col < gameGrid.getNoOfCols() - 1) {
					 GridIntersection first = intersections.stream().filter(i -> 
					 i.getPosition().first().equals(gameGrid.getPadX() + colVal * gameGrid.getCellWidth()) && 
					 i.getPosition().second().equals(gameGrid.getPadY() + rowVal * gameGrid.getCellHeight())).findFirst().orElse(null);
					 
					 GridIntersection second = intersections.stream().filter(i -> 
					 i.getPosition().first().equals(gameGrid.getPadX() + (colVal+1) * gameGrid.getCellWidth()) && 
					 i.getPosition().second().equals(gameGrid.getPadY() + rowVal * gameGrid.getCellHeight())).findFirst().orElse(null);
					 
					 if(first != null && second != null) {
						 sticks.add(new Pair<GridIntersection, GridIntersection>(first, second));
					 }
				 }
				 
				 add = rand.nextInt();
				 if(add % 2 == 0 && row < gameGrid.getNoOfRows() - 1) {
					 GridIntersection first = intersections.stream().filter(i -> 
					 i.getPosition().first().equals(gameGrid.getPadX() + colVal * gameGrid.getCellWidth()) && 
					 i.getPosition().second().equals(gameGrid.getPadY() + rowVal * gameGrid.getCellHeight())).findFirst().orElse(null);
					 
					 GridIntersection second = intersections.stream().filter(i -> 
					 i.getPosition().first().equals(gameGrid.getPadX() + colVal * gameGrid.getCellWidth()) && 
					 i.getPosition().second().equals(gameGrid.getPadY() + (rowVal+1) * gameGrid.getCellHeight())).findFirst().orElse(null);
					 
					 if(first != null && second != null) {
						 sticks.add(new Pair<GridIntersection, GridIntersection>(first, second));
					 }
				 }
			 }
		 }
		return sticks;
	}
	
	public static void drawSticks(Graphics2D g, Set<Pair<GridIntersection, GridIntersection>> sticks) {
		Stroke prevStroke = g.getStroke();
		Color prevColor = g.getColor();
		Stroke currStroke = new BasicStroke(5);
		Color currColor = new Color(30, 35, 43);
		
		g.setStroke(currStroke);
		g.setColor(currColor);
		
		for(Pair<GridIntersection, GridIntersection> stick : sticks) {
			g.drawLine(stick.first().getPosition().first(), stick.first().getPosition().second(),
					stick.second().getPosition().first(), stick.second().getPosition().second());
		}
		
		g.setStroke(prevStroke);
		g.setColor(prevColor);
	}
	
	public static Set<GridIntersection> generateIntersections(MainPanel mainFrame, GameGrid gameGrid, Boolean currPlayer) {
		Set<GridIntersection> intersections = new HashSet<>();
		
		int idx = 0;
		for (int row = 0; row < gameGrid.getNoOfRows(); row++) {
			 for (int col = 0; col < gameGrid.getNoOfCols(); col++) {
				 int x = gameGrid.getPadX() + col * gameGrid.getCellWidth();
				 int y = gameGrid.getPadY() + row * gameGrid.getCellHeight();
				 intersections.add(new GridIntersection(idx, mainFrame, new Pair<Integer, Integer>(x, y), gameGrid.getDiameter(), Color.GRAY, currPlayer, null));
				 idx++;
			 }
		 }
		
		for(GridIntersection intersection : intersections) {
			gameGrid.add(intersection);
		}
		
		return intersections;
	}
	
	public static void drawIntersections(Graphics2D graphics, GameGrid gameGrid, Set<GridIntersection> intersections) {
		for(GridIntersection intersection : intersections) {
			intersection.setGraphics(graphics);
			intersection.repaint();
		}
	}
	
	public static boolean validateMove(Boolean currentPlayer, GridIntersection i, GameGrid gameGrid) {
		Set<Pair<GridIntersection, GridIntersection>> sticks = gameGrid.getSticks();
		Set<GridIntersection> intersections = gameGrid.getIntersections();
		
		boolean isValid = false;
		
		if(!i.getActive()) {
			for(Pair<GridIntersection, GridIntersection> stick : sticks) {
				if((stick.first().equals(i) || stick.second().equals(i)) && stick.first().getActive() != stick.second().getActive()) {
					isValid = true;
				}
			}
		}
		
		if(gameGrid.getFirstMove()) {
			isValid = true;
			gameGrid.setFirstMove(false);
		}
		
		if(isValid) {
			for(GridIntersection inter : intersections) {
				inter.setCurrPlayer(!currentPlayer);
			}
			i.setActive(true);
			return !currentPlayer;
		}
		return currentPlayer;
	}
	
	public static boolean checkMovesAvailable(Set<Pair<GridIntersection, GridIntersection>> sticks) {

		for(Pair<GridIntersection, GridIntersection> stick : sticks) {
			if(stick.first().getActive() != stick.second().getActive()) {
				return true;
			}
		}
		return false;
	}
}
