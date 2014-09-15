package icanfly;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player {
	private float x;
	private float y;
	private Image image;
	public Player(float x, float y) throws SlickException {
	    this.x = x;
	    this.y = y;
	    image = new Image("res/player.png");
	}
	public void render() {
	    image.draw(x,y);
	}
	public void moveLeft() {
		x -= 10;
	}
	public void moveRight() {
		x += 10;
	}
}
