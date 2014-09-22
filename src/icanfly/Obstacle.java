package icanfly;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Obstacle {
	private Image obstacle;
	private float x;
	private float y;
	
	public Obstacle (float x, float y) throws SlickException {
		this.x = x;
		this.y = y;
		obstacle = new Image("res/obstacle.png");
	}
	
	public void render() {
		 obstacle.draw(x,y);
	}
	
	public void update() {
		
	}
}
