package bonus.window;

import javax.swing.JFrame;

/**
 * Manages the application window.
 * @author Calin Axinte
 */
public abstract class WindowManager {
	private static Integer appWidth = 600;
	private static Integer appHeight = 600;
	private static MainPanel mainFrame;
	
	public static void run(String appName) {
		defaultSetup(appName);
	}
	
	public static void run(String appName, Integer appWidth, Integer appHeight) {
		setAppWidth(appWidth);
		setAppHeight(appHeight);
		defaultSetup(appName);
	}
	
	private static void defaultSetup(String appName) {
		WindowManager.mainFrame = new MainPanel(appName, WindowManager.appWidth, WindowManager.appHeight, 10, 10);
		
		WindowManager.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		WindowManager.mainFrame.setSize(WindowManager.appWidth, WindowManager.appHeight);

		WindowManager.mainFrame.pack();
		
		WindowManager.mainFrame.setLocationRelativeTo(null);
		WindowManager.mainFrame.setVisible(true);
	}
	
	public static boolean setAppWidth(Integer appWidth) {
		WindowManager.appWidth = appWidth;
		if(WindowManager.mainFrame == null) {
			return false;
		}
		WindowManager.mainFrame.setSize(WindowManager.appWidth, WindowManager.mainFrame.getHeight());
		WindowManager.mainFrame.setPanelWidth(WindowManager.appWidth);
		return true;
	}
	
	public static boolean setAppHeight(Integer appHeight) {
		WindowManager.appHeight = appHeight;
		if(WindowManager.mainFrame == null) {
			return false;
		}
		WindowManager.mainFrame.setSize(WindowManager.mainFrame.getWidth(), WindowManager.appHeight);
		WindowManager.mainFrame.setPanelHeight(WindowManager.appHeight);
		return true;
	}
}
