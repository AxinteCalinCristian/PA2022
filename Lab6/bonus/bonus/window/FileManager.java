package bonus.window;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import bonus.utils.Pair;
import lombok.extern.java.Log;

/**
 * Menu that controls the file management aspect of the application
 * @author Calin Axinte
 */
@Log
public class FileManager extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JButton saveButton;
	private JButton loadButton;
	private JButton exitButton;
	private MainPanel mainFrame;
	private JFileChooser saveFileChooser;
	private JFileChooser loadFileChooser;
	private ObjectMapper mapper = new ObjectMapper();
	
	public FileManager(MainPanel mainFrame) {
		this.mainFrame = mainFrame;
		this.setLayout(new FlowLayout());
		this.setBackground(new Color(171, 181, 204));
		
		saveFileChooser = new JFileChooser();
		saveFileChooser.setCurrentDirectory(new File("."));
		saveFileChooser.setDialogTitle("Save game");
		saveFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JPEG", "jpg"));
		saveFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JSON", "json"));
		saveFileChooser.setAcceptAllFileFilterUsed(false);
		
		loadFileChooser = new JFileChooser();
		loadFileChooser.setCurrentDirectory(new File("."));
		loadFileChooser.setDialogTitle("Load game");
		loadFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JSON", "json"));
		loadFileChooser.setAcceptAllFileFilterUsed(false);
		
		setupSaveBtn();
		setupLoadBtn();
		setupExitBtn();
		this.setVisible(true);
	}
	
	private void setupSaveBtn() {
		saveButton = new JButton("Save");
		saveButton.setVisible(true);
		saveButton.addActionListener(e -> 
			saveFile()
		);
		
		this.add(saveButton);
	}
	
	private void setupLoadBtn() {
		loadButton = new JButton("Load");
		loadButton.setVisible(true);
		loadButton.addActionListener(e -> 
			loadFile()
		);
		
		this.add(loadButton);
	}
	
	private void setupExitBtn() {
		exitButton = new JButton("Exit");
		exitButton.addActionListener(e -> 
			this.mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING))
		);
		exitButton.setVisible(true);
		this.add(exitButton);
	}
	
	/**
	 * Loads a selected file.
	 */
	@SuppressWarnings("unchecked")
	private void loadFile() {
		loadFileChooser.showOpenDialog(null);
    	try {
    		File selectedFile = loadFileChooser.getSelectedFile();
    		
    		if(selectedFile == null) {
    			return;
    		}
    		
    		String absPath = selectedFile.getAbsolutePath();
        	if(!absPath.endsWith(".json")){
    			throw new IOException();
    		}
    		
    		Map<String, Object> loadFile = new HashMap<>();
    		
    		loadFile = mapper.readValue(selectedFile, Map.class);
    		
    		loadGameFile(loadFile);
    		
		} catch (IOException e) {
			log.warning("Could not load json file.");
		}
    	catch (IllegalArgumentException e) {
			log.warning("Invalid JSON save file.");
		}
	}
	
	/**
	 * Loads a selected game file.
	 */
	@SuppressWarnings("unchecked")
	private void loadGameFile(Map<String, Object> loadFile) throws IllegalArgumentException {
		if(!loadFile.containsKey("sticks")) {
			throw new IllegalArgumentException();
		}
		if(!loadFile.containsKey("intersections")) {
			throw new IllegalArgumentException();
		}
		if(!loadFile.containsKey("currPlayer")) {
			throw new IllegalArgumentException();
		}
		if(!loadFile.containsKey("rowNum")) {
			throw new IllegalArgumentException();
		}
		if(!loadFile.containsKey("colNum")) {
			throw new IllegalArgumentException();
		}
		
		Integer colNum = (Integer) loadFile.get("colNum");
		Integer rowNum = (Integer) loadFile.get("rowNum");
		Boolean currPlayer = ((String) loadFile.get("currPlayer")).equals("1") ? true : false;
		
		Set<GridIntersection> intersections = new HashSet<>();
		
		for(Map<String, Object> entry : ((List<Map<String, Object>>) loadFile.get("intersections"))) {
			GridIntersection i = new GridIntersection();
			i.setId((Integer) entry.get("id"));
			Pair<Integer, Integer> pos = new Pair<Integer, Integer>(((Map<String, Integer>) entry.get("position")).get("first"), 
					((Map<String, Integer>) entry.get("position")).get("second"));
			i.setDiameter((Integer) entry.get("diameter"));
			i.setPosition(pos);
			i.setColor(new Color((Integer) entry.get("colorRGB")));
			i.setDefaultColor(Color.gray);
			i.setCurrPlayer((Boolean) entry.get("currPlayer"));
			i.setActive((Boolean) entry.get("active"));
			i.setMainPanel(mainFrame);
			i.setBackground(i.getColor());
			intersections.add(i);
		}
		
		Set<Pair<GridIntersection, GridIntersection>> sticks = new HashSet<>();
		
		for(Map<String, Integer> entry : (List<Map<String, Integer>>)loadFile.get("sticks") ) {
			
			GridIntersection first = intersections.stream().filter(i -> i.getId().equals(entry.get("first"))).findFirst().orElse(null);
			GridIntersection second = intersections.stream().filter(i -> i.getId().equals(entry.get("second"))).findFirst().orElse(null);
			
			if(first == null || second == null) {
				throw new IllegalArgumentException();
			}
			
			sticks.add(new Pair<GridIntersection, GridIntersection>(first, second));
		}
		
		mainFrame.getGameGrid().loadSaveFile(colNum, rowNum, currPlayer, sticks, intersections);
	}
	
	/**
	 * Saves the game as either JPEG or JSON.
	 */
	private void saveFile() {
		saveFileChooser.showSaveDialog(null);
		File theFileToSave = saveFileChooser.getSelectedFile();

        if(saveFileChooser.getFileFilter().getDescription().equals("JPEG")) {
        	saveJPEG(theFileToSave);
        }
        
        if(saveFileChooser.getFileFilter().getDescription().equals("JSON")) {
        	saveJSON(theFileToSave);
        }
	}
	
	private void saveJPEG(File theFileToSave) {
		try {
    		File selectedFile = saveFileChooser.getSelectedFile();
    		
    		if(selectedFile == null) {
    			return;
    		}
    		
    		String absPath = selectedFile.getAbsolutePath();
        	if(!absPath.endsWith(".jpg")){
    			theFileToSave = new File(saveFileChooser.getSelectedFile() + ".jpg");
    		}
        	
        	BufferedImage image = new BufferedImage(
    				585,
    				565,
    				BufferedImage.TYPE_INT_RGB
    				);

    		mainFrame.getContentPane().paint( image.getGraphics() ); 
    		ImageIO.write(image, "jpg", theFileToSave);
			theFileToSave.createNewFile();
		} catch (IOException e) {
			log.warning("Could not save jpg file");
		}
	}
	
	private void saveJSON(File theFileToSave) {
		try {
        	if(!saveFileChooser.getSelectedFile().getAbsolutePath().endsWith(".json")){
    			theFileToSave = new File(saveFileChooser.getSelectedFile() + ".json");
    		}
			theFileToSave.createNewFile();
			
			Map<String, Object> saveFile = new HashMap<>();
			
			Set<Pair<Integer, Integer>> sticks = new HashSet<>();
			
			for(Pair<GridIntersection, GridIntersection> stick : mainFrame.getGameGrid().getSticks()) {
				sticks.add(new Pair<Integer, Integer>(stick.first().getId(), stick.second().getId()));
			}
			
			saveFile.put("rowNum", mainFrame.getGameGrid().getNoOfRows());
			saveFile.put("colNum", mainFrame.getGameGrid().getNoOfCols());
			saveFile.put("currPlayer", mainFrame.getCurrentPlayer() ? "1" : "2");
			saveFile.put("sticks", sticks);
			saveFile.put("intersections", mainFrame.getGameGrid().getIntersections());
			
		    mapper.writeValue(theFileToSave, saveFile);
			
		} catch (IOException e) {
			log.warning("Could not save json file");
		}
	}
}
