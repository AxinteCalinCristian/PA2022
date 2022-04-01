package compulsory.window;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MainPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private Integer panelWidth = 600;
	private Integer panelHeight = 600;
	private Integer noOfCols = 10;
	private Integer noOfRows = 10;
	
	private InputMenu inputMenu;
	private FileManager fileManager;
	private GameGrid gameGrid;
	
	public MainPanel(Integer panelWidth, Integer panelHeight, Integer noOfCols, Integer noOfRows) {
		this.panelHeight = panelHeight;
		this.panelWidth = panelWidth;
		gameGrid = new GameGrid(noOfCols, noOfRows);
		inputMenu = new InputMenu(gameGrid, noOfCols, noOfRows, "Grid size", "Generate");
		fileManager = new FileManager();
		
		this.setLayout(new BorderLayout());
		this.add(inputMenu, BorderLayout.PAGE_START);
		this.add(gameGrid, BorderLayout.CENTER);
		this.add(fileManager, BorderLayout.PAGE_END);
		this.setVisible(true);
	}
	
	@Override
    public Dimension getPreferredSize() {
       return new Dimension(panelWidth, panelHeight);
    }
}
