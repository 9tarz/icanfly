package icanfly;

import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Obstacle {
	private Image obstacle;
	private float x;
	private float y;
	private float vy;
	
	public Obstacle (float vy) throws SlickException {
		this.x = randomX();
		this.y = 0;
		this.vy = vy;
		obstacle = new Image("res/obstacle.png");
	}
	
	public float getX(){
		return x;
	}
	
	public void render() {
		 obstacle.draw(x,y);
	}
	
	public void update() throws SlickException {
		if ( y > 480){
			y = -30;
			for(int i=0;i<=ICanFlyGame.OBSTACLE_COUNT;i++){
				x = randomX();
			}
			//ICanFlyGame.initObstacles();
			
		}
		 y += vy;
	}
	
	public int randomX() {
		  Random rand = new Random();
		  int n = rand.nextInt((30) + ICanFlyGame.GAME_WIDTH-30);
		  return n;
	  }
}
