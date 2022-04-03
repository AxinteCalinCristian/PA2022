package bonus.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import bonus.utils.GameAI;
import bonus.utils.GridController;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The main frame of the application.
 * @author Calin Axinte
 */
@Getter
@Setter
@NoArgsConstructor
public class MainPanel extends JFrame implements MouseListener{

	private static final long serialVersionUID = 1L;
	
	private Integer panelWidth = 600;
	private Integer panelHeight = 700;
	private Integer noOfCols = 10;
	private Integer noOfRows = 10;
	
	private InputMenu inputMenu;
	private FileManager fileManager;
	private GameGrid gameGrid;
	private Boolean currentPlayer = true;

	public MainPanel(String appName, Integer panelWidth, Integer panelHeight, Integer noOfCols, Integer noOfRows) {
		super(appName);
		this.panelHeight = panelHeight;
		this.panelWidth = panelWidth;
		
		gameGrid = new GameGrid(this, noOfCols, noOfRows, currentPlayer);
		inputMenu = new InputMenu(this, gameGrid, noOfCols, noOfRows, "Grid size", "Generate");
		fileManager = new FileManager(this);
		
		this.setSize(panelWidth, panelHeight);
		this.setLayout(new BorderLayout());
		this.add(inputMenu, BorderLayout.PAGE_START);
		this.add(gameGrid, BorderLayout.CENTER);
		this.add(fileManager, BorderLayout.PAGE_END);
		this.addMouseListener(this);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	@Override
    public Dimension getPreferredSize() {
       return new Dimension(panelWidth, panelHeight);
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) { } 

	@Override
	public void mouseReleased(MouseEvent e) { 
		for(GridIntersection i : gameGrid.getIntersections()) {
			if(i.equals(e.getSource()) && !i.getActive() && GridController.isMoveValid(i, gameGrid)) {
				gameGrid.repaint();
				i.updateComponent(Color.red, Color.blue);
				currentPlayer = GridController.validateMove(currentPlayer, i, gameGrid);
				
				if(!GridController.checkMovesAvailable(gameGrid.getSticks())) {
					inputMenu.showGameOverMenu();
				}
				
				gameGrid.repaint();
				GridIntersection ai = GameAI.makeMove();
				
				if(ai == null) {
					inputMenu.showGameOverMenu();
					break;
				}
				
				ai.updateComponent(Color.red, Color.blue);
				currentPlayer = GridController.validateMove(currentPlayer, ai, gameGrid);
				
				if(!GridController.checkMovesAvailable(gameGrid.getSticks())) {
					inputMenu.showGameOverMenu();
				}
				
				break;
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		for(GridIntersection i : gameGrid.getIntersections()) {
			if(i == e.getSource()) {
				gameGrid.repaint();
				i.updateComponent(Color.orange, Color.cyan);
				break;
			}
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		for(GridIntersection i : gameGrid.getIntersections()) {
			if(i == e.getSource()) {
				i.updateComponent(i.getDefaultColor(), i.getDefaultColor());
				break;
			}
		}
		
	}
}
