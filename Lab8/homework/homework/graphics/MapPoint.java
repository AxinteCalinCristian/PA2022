package homework.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MapPoint extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Double xPos;
	private Double yPos;
	private Double diameter;
	private String name;
	private MainPanel mainPanel;
	
	public MapPoint(Double x, Double y, Double diameter, String name, MainPanel mainPanel) {
		this.xPos = x;
		this.yPos = y;
		this.diameter = diameter;
		this.name = name;
		this.mainPanel = mainPanel;
		
		this.setVisible(true);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.red);
		System.out.println((int) (xPos - diameter / 2) + " " + (int) (yPos - diameter / 2) + " " + diameter.intValue());
        g2d.fillRect((int) (xPos - diameter / 2), (int) (yPos - diameter / 2), diameter.intValue(), diameter.intValue());
	}
}
