package homework.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import homework.utils.Pair;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
@JsonAutoDetect(
	    fieldVisibility = JsonAutoDetect.Visibility.NONE,
	    setterVisibility = JsonAutoDetect.Visibility.NONE,
	    getterVisibility = JsonAutoDetect.Visibility.NONE,
	    isGetterVisibility = JsonAutoDetect.Visibility.NONE,
	    creatorVisibility = JsonAutoDetect.Visibility.NONE
	)
public class GridIntersection extends JPanel {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty
	private Integer id;
	@JsonProperty
	private Pair<Integer, Integer> position;
	@JsonProperty
	private Integer diameter;
	@JsonProperty
	private Boolean currPlayer;
	@JsonProperty
	private Boolean active;
	
	private Graphics2D graphics;
	private Color color;
	private Color defaultColor;
	private MainPanel mainPanel;
	
	public GridIntersection() {
		super();
		this.setVisible(true);
	}
	
	public GridIntersection(Integer id, MainPanel mainPanel, Pair<Integer, Integer> position, Integer diameter, Color color, Boolean currPlayer, Graphics graphics) {
		super();
		this.id = id;
		this.active = false;
		this.position = position;
		this.diameter = diameter;
		this.color = color;
		this.defaultColor = color;
		this.currPlayer = currPlayer;
		this.graphics = (Graphics2D) graphics;
		this.mainPanel = mainPanel;
		
		this.addMouseListener(mainPanel);
		this.setBackground(color);
		this.setColor(color);
		this.setSize(diameter, diameter);
		this.setLocation(position.first() - diameter / 2, position.second() - diameter / 2);
		
		this.setPreferredSize(new Dimension(diameter, diameter));
		this.setVisible(true);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(color);
        g2d.fillOval(position.first() - diameter / 2, position.second() - diameter / 2, diameter, diameter);
	}
	
	@Override
	public void setBackground(Color bg) {
		super.setBackground(bg);
		this.setColor(bg);
	}
	
	
	public void updateComponent(Color c1, Color c2) {
		if(active) {
			return;
		}
		if(currPlayer) {
			this.setBackground(c1);
		}
		else {
			this.setBackground(c2);
		}
	}
	
	@JsonProperty
	public Integer getColorRGB() {
		return color.getRGB();
	}
	
	public void setMainPanel(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
		this.addMouseListener(mainPanel);
	}
	
	public void setDiameter(Integer diameter) {
		this.diameter = diameter;
		this.setSize(diameter, diameter);
		this.setPreferredSize(new Dimension(diameter, diameter));
	}
	
	public void setPosition(Pair<Integer, Integer> position) {
		this.position = position;
		
		if(diameter == null) {
			diameter = 0;
		}
		
		this.setLocation(position.first() - diameter / 2, position.second() - diameter / 2);
	}
}
