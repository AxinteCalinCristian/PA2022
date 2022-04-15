package homework.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

@Log
@Getter
@Setter
@AllArgsConstructor
public class Timekeeper implements Runnable{
	
	private Integer maxAllowedTime;
	
	@Getter(lombok.AccessLevel.NONE)
	@Setter(lombok.AccessLevel.NONE)
	private Integer currTime = 0;
	private Game game;
	private Boolean isTimeUp = false;
	
	/**
	 * @param maxAllowedTime in seconds.
	 * @param game the game instance.
	 */
	public Timekeeper(Integer maxAllowedTime, Game game) {
		this.maxAllowedTime = maxAllowedTime;
		this.game = game;
	}
	
	public void resetTimekeeper() {
		currTime = 0;
		isTimeUp = false;
	}
	
	@Override
	public void run() {
		while(true) {
			synchronized(game) {
				try {
					Thread.sleep(1000);
					currTime += 1;
					
					if(currTime < maxAllowedTime) {
						System.out.println("[ Timekeeper ]: Time left: " + (maxAllowedTime - currTime));
					}
					
					if(currTime >= maxAllowedTime) {
						isTimeUp = true;
						System.out.println("[ Timekeeper ]: Time's up. The game will end after current turn is over.");
						break;
					}
					
				} catch (InterruptedException e) {
					log.severe("Error running timekeeper");
					e.printStackTrace();
					break;
				}
			}
		}
	}

}
