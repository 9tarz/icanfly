package icanfly;

import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Obstacle implements Entity {
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
        obstacle = new Image("res/kryptonite_s.png");
        this.hitbox = new Rectangle(x, y, 21f, 45f);
        this.type = type;
        break;
      case 10:
        obstacle = new Image("res/kryptonite_m.png");
        this.hitbox = new Rectangle(x, y, 29f, 67f);
        this.type = type;
        break;
      case 11:
        obstacle = new Image("res/kryptonite_l.png");
        this.hitbox = new Rectangle(x, y, 40f, 91f);
        this.type = type;
        break;
      case 8:
        obstacle = new Image("res/heal_stone.png");
        this.hitbox = new Rectangle(x, y, 90f, 90f);
        this.type = 99;
        break;
      default :
        obstacle = new Image("res/kryptonite_m.png");
        this.hitbox = new Rectangle(x, y, 29f, 67f);
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
    int n = rand.nextInt((30) + ICanFlyGame.GAME_WIDTH-30);
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
