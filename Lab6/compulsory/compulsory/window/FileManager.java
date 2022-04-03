package compulsory.window;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Menu that controls the file management aspect of the application
 * @author Calin Axinte
 */
public class FileManager extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JButton saveButton;
	private JButton loadButton;
	private JButton exitButton;
	
	public FileManager() {
		this.setLayout(new FlowLayout());
		this.setBackground(new Color(171, 181, 204));
		
		setupSaveBtn();
		setupLoadBtn();
		setupExitBtn();
		this.setVisible(true);
	}
	
	private void setupSaveBtn() {
		saveButton = new JButton("Save");
		saveButton.setVisible(true);
		this.add(saveButton);
	}
	
	private void setupLoadBtn() {
		loadButton = new JButton("Load");
		loadButton.setVisible(true);
		this.add(loadButton);
	}
	
	private void setupExitBtn() {
		exitButton = new JButton("Exit");
		exitButton.setVisible(true);
		this.add(exitButton);
	}
}
