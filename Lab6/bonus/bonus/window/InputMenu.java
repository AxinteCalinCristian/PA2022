package bonus.window;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

/**
 * Menu that manages user input.
 * @author Calin Axinte
 */
@Log
@Getter
@Setter
public class InputMenu extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JSpinner colNum;
	private JSpinner rowNum;
	private JLabel label;
	private JLabel gameOverLabel;
	private JButton generateButton;
	private GameGrid gameGrid;
	private JButton retryButton;
	private MainPanel mainPanel;
	
	public InputMenu(MainPanel mainPanel, GameGrid gameGrid, Integer gridWidth, Integer gridHeight, String labelStr, String btnLabel) {
		this.setLayout(new FlowLayout());
		this.setBackground(new Color(171, 181, 204));
		this.gameGrid = gameGrid;
		this.mainPanel = mainPanel;
		
		setupLabel(labelStr);
		setupColSpinner(gridWidth);
		setupRowSpinner(gridHeight);
		setupButton(btnLabel);
		
		setupGOLabel("Player 1 wins");
		setupRetryButton("Play again");
		
		this.setVisible(true);
	}
	
	private void setupColSpinner(Integer gridWidth) {
		try {
			colNum = new JSpinner(new SpinnerNumberModel(gridWidth.intValue(), 2, (gridWidth * gridWidth), 1));
			colNum.setVisible(true);
			this.add(colNum);
		}
		catch(IllegalArgumentException | NullPointerException e) {
			log.severe("Invalid values provided for the grid width input box");
		}
	}
	
	private void setupRowSpinner(Integer gridHeight) {
		try {
			rowNum = new JSpinner(new SpinnerNumberModel(gridHeight.intValue(), 2, (gridHeight * gridHeight), 1));
			rowNum.setVisible(true);
			this.add(rowNum);
		}
		catch(IllegalArgumentException | NullPointerException e) {
			log.severe("Invalid values provided for the grid height input box");
		}
	}
	
	private void setupLabel(String labelStr) {
		label = new JLabel(labelStr);
		label.setVisible(true);
		this.add(label);
	}
	
	private void setupButton(String btnLabel) {
		generateButton = new JButton(btnLabel);
		generateButton.setVisible(true);
		this.add(generateButton);
		generateButton.addActionListener(e -> {
		    gameGrid.generateGrid(Integer.parseInt(colNum.getValue().toString()) , Integer.parseInt(rowNum.getValue().toString()));
		    gameGrid.repaint();
		});
	}
	
	private void setupGOLabel(String labelStr) {
		gameOverLabel = new JLabel(labelStr);
		gameOverLabel.setVisible(false);
		this.add(gameOverLabel);
	}
	
	private void setupRetryButton(String btnLabel) {
		retryButton = new JButton(btnLabel);
		retryButton.setVisible(false);
		this.add(retryButton);
		retryButton.addActionListener(e ->
		{	
			label.setVisible(true);
			colNum.setVisible(true);
			rowNum.setVisible(true);
			generateButton.setVisible(true);
			
			gameOverLabel.setVisible(false);
			retryButton.setVisible(false);
			
		    gameGrid.generateGrid(Integer.parseInt(colNum.getValue().toString()) , Integer.parseInt(rowNum.getValue().toString()));
		    gameGrid.repaint();
		});
	}
	
	/**
	 * Switches the menu view to the Game Over Menu.
	 */
	public void showGameOverMenu() {
		label.setVisible(false);
		colNum.setVisible(false);
		rowNum.setVisible(false);
		generateButton.setVisible(false);
		
		if(mainPanel.getCurrentPlayer()) {
			gameOverLabel.setText("Player 2 wins");
		} else {
			gameOverLabel.setText("Player 1 wins");
		}
		
		gameOverLabel.setVisible(true);
		retryButton.setVisible(true);
	}
}
