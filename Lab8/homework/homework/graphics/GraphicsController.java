package homework.graphics;

import java.util.List;

import javax.swing.JFrame;

public abstract class GraphicsController {
	private static Integer appWidth = 600;
	private static Integer appHeight = 600;
	private static String name = "App";
	private static JFrame frame;
	private static MainPanel mainPanel;

	public static void run(String name, Integer width, Integer height, List<MapPoint> points) {
		GraphicsController.appWidth = width;
		GraphicsController.appHeight = height;
		GraphicsController.name = name;
		
		GraphicsController.mainPanel = new MainPanel(GraphicsController.appWidth, GraphicsController.appHeight, points);
		
		GraphicsController.frame = new JFrame(GraphicsController.name);
		
		GraphicsController.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GraphicsController.frame.setSize(GraphicsController.appWidth, GraphicsController.appHeight);
		
		GraphicsController.frame.getContentPane().add(mainPanel);
		GraphicsController.frame.pack();
		
		GraphicsController.frame.setLocationRelativeTo(null);
		GraphicsController.frame.setResizable(false);
		GraphicsController.frame.setVisible(true);
	}
	
}
