package icanfly;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player {
	public static final int X_MOVE_RANGE = 10;
	private float x;
	private float y;
	private Image image;
	private float vy;
	private float vjump;
	  
	public Player(float x, float y,float vjump) throws SlickException {
	    this.x = x;
	    this.y = y;
	    this.vy = vjump;
	    this.vjump = vjump;
	    image = new Image("res/player.png");
	}
	
	public void render() {
	    image.draw(x,y);
	}
	
	public void update() {
	    y += vy;
	    vy += ICanFlyGame.G;
	}
	
	public void setVy(float vy) {
	    this.vy = vy;
	}
	
	public void moveLeft() {
		x -= X_MOVE_RANGE;
	}
	
	public void moveRight() {
		x += X_MOVE_RANGE;
	}
	
	public void jump(){
		  this.vy = vjump;
	}
}
