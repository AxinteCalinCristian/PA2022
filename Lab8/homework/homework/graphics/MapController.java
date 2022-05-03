package homework.graphics;

import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import homework.utils.Pair;

public class MapController extends JPanel {
	private static final long serialVersionUID = 1L;

	private List<MapPoint> points;
	private MainPanel mainPanel;
	
	public MapController(MainPanel mainPanel, List<MapPoint> points) {
		this.mainPanel = mainPanel;
		this.points = points;
		
		normalizePositions();
		
		this.setPreferredSize(this.mainPanel.getPreferredSize());
		this.setSize(getPreferredSize());
		
		for(MapPoint p : this.points) {
			p.setMainPanel(this.mainPanel);
			this.add(p);
		}
		
		this.setVisible(true);
	}
	
	private void normalizePositions() { 
		Double mpWidth = Double.parseDouble(this.mainPanel.getPanelWidth().toString()); 
		Double mpHeight = Double.parseDouble(this.mainPanel.getPanelHeight().toString()); 
		Double offset = 10.0; 
		
		Pair<Double, Double> widths = new Pair<Double,Double>(offset, mpWidth); 
		Pair<Double, Double> heights = new Pair<Double,Double>(offset, mpHeight); 
		
		Pair<Double, Double> mins = getMins(); 
		addMins(mins); 
		 
		Pair<Double, Double> maxs = getMaxs(); 
		convertToInterval(widths, heights, maxs);
	} 
	
	private Pair<Double, Double> getMins() { 
		Double minX = 0.0, minY = 0.0; 
		for(MapPoint p : points) { 
			if(p.getXPos() < minX) {
				minX = p.getXPos(); 
			} 
			if(p.getYPos() < minY) { 
				minY = p.getYPos(); 
			} 
		} 
		return new Pair<Double, Double>(minX, minY); 
	} 
	
	private Pair<Double, Double> getMaxs() { 
		Double maxX = 0.0, maxY = 0.0; 
		for(MapPoint p : points) { 
			if(p.getXPos() > maxX) { 
				maxX = p.getXPos(); 
			} 
			if(p.getYPos() > maxY) { 
				maxY = p.getYPos(); 
			} 
		}
		return new Pair<Double, Double>(maxX, maxY); 
	} 
	
	private void addMins(Pair<Double, Double> mins) { 
		for(MapPoint p : points) { 
			p.setXPos(p.getXPos() + Math.abs(mins.first())); 
			p.setYPos(p.getYPos() + Math.abs(mins.second())); 
		} 
	} 
	
	private void convertToInterval(Pair<Double, Double> widths, Pair<Double, Double> heights, Pair<Double, Double> maxs) { 
		for(MapPoint p : points) {
			Double x = ((widths.second() - 2 * widths.first()) * p.getXPos()) / maxs.first();
			Double y = ((heights.second() - 2 * heights.first()) * p.getYPos()) / maxs.second();
			x += widths.first();
			y += widths.first();
			
			p.setXPos(x);
			p.setYPos(y);
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		for(MapPoint p : points) {
			p.paint(g);
		}
	}
}
