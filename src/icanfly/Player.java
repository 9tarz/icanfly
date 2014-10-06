package icanfly;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player {
	public static final int X_MOVE_RANGE = 7;
	private float x;
	private float y;
	private Image image;
	private float vy;
	private float vjump;
	private int hp;
	  
	public float getX() { return x; }
	public float getY() { return y; }
	public int getHP() { return hp; }
	public void setHP(int hp) {
		this.hp = hp;
	 }
	
	public void getHit() {
		this.hp -= 10;
	}
	
	public void getHeal() {
		this.hp = 100;
	}
	  
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
		if(this.x > ICanFlyGame.GAME_WIDTH){
			this.x = 0;
		}
		else if(this.x < 0) {
			this.x = ICanFlyGame.GAME_WIDTH;
		}
		if(this.y <= 0){
			getHeal();
		}
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
