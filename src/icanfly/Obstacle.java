package icanfly;

import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Obstacle implements Entity {
  private static final float KRYPTONITE_S_IMAGE_WIDTH = 21f;
  private static final float KRYPTONITE_S_IMAGE_HEIGHT = 45f;
  private static final float KRYPTONITE_M_IMAGE_WIDTH = 29f;
  private static final float KRYPTONITE_M_IMAGE_HEIGHT = 67f;
  private static final float KRYPTONITE_L_IMAGE_WIDTH = 40f;
  private static final float KRYPTONITE_L_IMAGE_HEIGHT = 91f;
  private static final float HEALSTONE_IMAGE_WIDTH = 45f;
  private static final float HEALSTONE_IMAGE_HEIGHT = 45f;
  private static final String KRYPTONITE_S_IMAGE_PATH = "res/kryptonite_s.png";
  private static final String KRYPTONITE_M_IMAGE_PATH = "res/kryptonite_m.png";
  private static final String KRYPTONITE_L_IMAGE_PATH = "res/kryptonite_l.png";
  private static final String HEALSTONE_IMAGE_PATH = "res/heal_stone.png";
  
  private Image obstacle;
  private float x;
  private float y;
  private float vy;
  public Rectangle hitbox;
  private int type;
  
  public Obstacle (float vy, int type) throws SlickException {
    this.x = randomX();
    this.y = 0;
    this.vy = vy;
    handleObstacleType(type);
    }

  private void handleObstacleType(int type) throws SlickException {
    switch(type) {
      case 9:
        obstacle = new Image(KRYPTONITE_S_IMAGE_PATH);
        this.hitbox = new Rectangle(this.x, this.y, KRYPTONITE_S_IMAGE_WIDTH, KRYPTONITE_S_IMAGE_HEIGHT);
        this.type = type;
        break;
      case 10:
        obstacle = new Image(KRYPTONITE_M_IMAGE_PATH);
        this.hitbox = new Rectangle(this.x, this.y, KRYPTONITE_M_IMAGE_WIDTH, KRYPTONITE_M_IMAGE_HEIGHT);
        this.type = type;
        break;
      case 11:
        obstacle = new Image(KRYPTONITE_L_IMAGE_PATH);
        this.hitbox = new Rectangle(this.x, this.y, KRYPTONITE_L_IMAGE_WIDTH, KRYPTONITE_L_IMAGE_HEIGHT);
        this.type = type;
        break;
      case 8:
        obstacle = new Image(HEALSTONE_IMAGE_PATH);
        this.hitbox = new Rectangle(this.x, this.y, HEALSTONE_IMAGE_WIDTH, HEALSTONE_IMAGE_HEIGHT);
        this.type = 99;
        break;
      default :
        obstacle = new Image(KRYPTONITE_M_IMAGE_PATH);
        this.hitbox = new Rectangle(this.x, this.y, KRYPTONITE_M_IMAGE_WIDTH, KRYPTONITE_M_IMAGE_HEIGHT);
        this.type = 1;
        break;
        }
  }

  public int getType(){
    return type;
  }
	
  @Override
  public void render(Graphics g) {
    obstacle.draw(x,y);
  }

  @Override
  public void update(int delta){
    this.hitbox.setLocation(this.x, this.y);
    y += vy;
  }

  public int randomX() {
    Random rand = new Random();
    int n = rand.nextInt((((ICanFlyGame.GAME_WIDTH - 30) - 30) + 1) + 30);
    return n;
  }

  public boolean isCollide(Player player){
    if (this.hitbox.intersects(player.hitbox)) {
      return true;
    } else {
      return false;
    }
  }
  
  public boolean isDeletable() {
    if ( y > ICanFlyGame.GAME_HEIGHT || ICanFlyGame.isGameOver) {
      return true;
    } else {
      return false;
    }
  }
}
