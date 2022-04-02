package homework.main;

import javax.swing.SwingUtilities;

import homework.window.WindowManager;

public class Homework {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				WindowManager.run("Sticks 'n' stones");
			}
		});
	}
}
