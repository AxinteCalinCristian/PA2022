package homework.graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1;
	
	private Integer panelWidth = 600;
	private Integer panelHeight = 600;
	private MapController mapController;
	
	public MainPanel(Integer width, Integer height, List<MapPoint> points) {
		this.panelWidth = width;
		this.panelHeight = height;
		this.mapController = new MapController(this, points);

		this.add(mapController);
		this.setVisible(true);
	}
	
	@Override
    public Dimension getPreferredSize() {
       return new Dimension(panelWidth, panelHeight);
    }
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		this.mapController.paint(g);
	}
}
