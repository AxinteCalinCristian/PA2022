package bonus.main;

import javax.swing.SwingUtilities;

import bonus.window.WindowManager;

public class Bonus {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				WindowManager.run("Sticks 'n' stones");
			}
		});
	}
}
