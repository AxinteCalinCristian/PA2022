package homework.window;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import lombok.extern.java.Log;

@Log
public class InputMenu extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JSpinner colNum;
	private JSpinner rowNum;
	private JLabel label;
	private JButton generateButton;
	private GameGrid gameGrid;
	
	public InputMenu(GameGrid gameGrid, Integer gridWidth, Integer gridHeight, String labelStr, String btnLabel) {
		this.setLayout(new FlowLayout());
		this.setBackground(new Color(171, 181, 204));
		this.gameGrid = gameGrid;
		
		setupLabel(labelStr);
		setupColSpinner(gridWidth);
		setupRowSpinner(gridHeight);
		setupButton(btnLabel);

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
	}
}
