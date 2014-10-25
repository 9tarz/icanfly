package icanfly;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Player {
  public static final float X_MOVE_RANGE = 0.5f;
  
  private float x;
  private float y;
  private Image image;
  private float vy;
  private float vjump;
  private int hp;
  public Rectangle hitbox;
  
  public float getX() { 
    return x; 
  }
  
  public float getY() { 
    return y; 
  }

  public int getHP() { 
    return hp; 
  }

  public void setHP(int hp) {
    this.hp = hp;
  }

  public void getHit(int type) {
    switch(type) {
      case 0: 
        this.hp -= 2;
        break;
      case 1:
        this.hp -= 5;
        break;
      case 2:
        this.hp -= 10;
        break;
      default : 
        this.hp -= 5;
        break;
     }
  }

  public void getHeal() {
    this.hp = 100;
  }

  public Player(float x, float y,float vjump) throws SlickException {
    this.x = x;
    this.y = y;
    this.vy = vjump;
    this.vjump = vjump;
    this.image = new Image("res/player.png");
    this.hitbox = new Rectangle(x, y, 63f, 103f);
  }

  public void render(Graphics g) {
    image.draw(x,y);
  }

  public void update(int delta) {
    this.hitbox.setLocation(x, y);
    if(this.x > ICanFlyGame.GAME_WIDTH) {
      this.x = 0;
    } else if(this.x < 0) {
      this.x = ICanFlyGame.GAME_WIDTH;
    }
    y += vy;
    vy += ICanFlyGame.G;
  }

  public void setVy(float vy) {
    this.vy = vy;
  }

  public void moveLeft(int delta) {
    x -= X_MOVE_RANGE *delta ;
  }

  public void moveRight(int delta) {
    x += X_MOVE_RANGE *delta;
  }

  public void jump(){
    this.vy = vjump;
  }
}
