package icanfly;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Player {
  private static final float X_MOVE_RANGE = 0.5f;
  private static final int DAMAGE_OBSTACLE_S = 2;
  private static final int DAMAGE_OBSTACLE_M = 5;
  private static final int DAMAGE_OBSTACLE_L = 10;
  private static final int HEAL_HP = 30;
  private static final String PLAYER_IMAGE_PATH = "res/player.png";
  private static final float PLAYER_IMAGE_WIDTH = 63f;
  private static final float PLAYER_IMAGE_HEIGHT = 103f;
  
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
      case 9: 
        this.hp -= DAMAGE_OBSTACLE_S;
        break;
      case 10:
        this.hp -= DAMAGE_OBSTACLE_M;
        break;
      case 11:
        this.hp -= DAMAGE_OBSTACLE_L;
        break;
      case 99:
        this.getHeal();
        break;
      default : 
        this.hp -= DAMAGE_OBSTACLE_M;
        break;
     }
  }

  public void getHeal() {
    if (this.hp >= (ICanFlyGame.INITIAL_HP - HEAL_HP)) {
      this.hp = ICanFlyGame.INITIAL_HP - HEAL_HP;
    }
    this.hp += HEAL_HP;
  }

  public Player(float x, float y,float vjump) throws SlickException {
    this.x = x;
    this.y = y;
    this.vy = vjump;
    this.vjump = vjump;
    this.image = new Image(PLAYER_IMAGE_PATH);
    this.hitbox = new Rectangle(x, y, PLAYER_IMAGE_WIDTH, PLAYER_IMAGE_HEIGHT);
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

  public void jump() {
    this.vy = vjump;
  }
  
  public boolean isDeathZone() {
    return (this.y <= (ICanFlyGame.GAME_HEIGHT - 330));
  }
  
  public boolean isDie() {
    if (this.getY() > ICanFlyGame.GAME_HEIGHT || this.getHP() <= 0 ) {
      return true;
    } else {
      return false;
    }
  }
}
