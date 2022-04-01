package window;

import javax.swing.JFrame;

public abstract class WindowManager {
	private static Integer appWidth = 600;
	private static Integer appHeight = 600;
	private static JFrame frame;
	private static MainPanel mainPanel;
	
	public static void run(String appName) {
		defaultSetup(appName);
	}
	
	public static void run(String appName, Integer appWidth, Integer appHeight) {
		setAppWidth(appWidth);
		setAppHeight(appHeight);
		defaultSetup(appName);
	}
	
	private static void defaultSetup(String appName) {
		WindowManager.mainPanel = new MainPanel(WindowManager.appWidth, WindowManager.appHeight, 10, 10);
		
		WindowManager.frame = new JFrame(appName);
		
		WindowManager.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		WindowManager.frame.setSize(WindowManager.appWidth, WindowManager.appHeight);
		
		WindowManager.frame.getContentPane().add(mainPanel);
		WindowManager.frame.pack();
		
		WindowManager.frame.setLocationRelativeTo(null);
		WindowManager.frame.setVisible(true);
	}
	
	public static boolean setAppWidth(Integer appWidth) {
		WindowManager.appWidth = appWidth;
		if(WindowManager.frame == null || WindowManager.mainPanel == null) {
			return false;
		}
		WindowManager.frame.setSize(WindowManager.appWidth, WindowManager.frame.getHeight());
		WindowManager.mainPanel.setPanelWidth(WindowManager.appWidth);
		return true;
	}
	
	public static boolean setAppHeight(Integer appHeight) {
		WindowManager.appHeight = appHeight;
		if(WindowManager.frame == null || WindowManager.mainPanel == null) {
			return false;
		}
		WindowManager.frame.setSize(WindowManager.frame.getWidth(), WindowManager.appHeight);
		WindowManager.mainPanel.setPanelHeight(WindowManager.appHeight);
		return true;
	}
}
