package icanfly;

import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Obstacle {
	private Image obstacle;
	private float x;
	private float y;
	private float vy;
	
	public Obstacle (float x, float y, float vy) throws SlickException {
		this.x = randomX();
		this.y = y;
		this.vy = vy;
		obstacle = new Image("res/obstacle.png");
	}
	
	public void render() {
		 obstacle.draw(x,y);
	}
	
	public void update() {
		 y += vy;
	}
	
	public int randomX() {
		  Random rand = new Random();
		  int n = rand.nextInt((30) + ICanFlyGame.GAME_WIDTH-30);
		  return n;
	  }
}
