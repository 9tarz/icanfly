package icanfly;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class ICanFlyGame extends BasicGame {
  public static final int GAME_WIDTH = 640;
  public static final int GAME_HEIGHT = 480;
  public static final float PLAYER_JUMP_VY = -5;
  public static final float OBSTACLE_VY = 4;
  public static final float G = (float) 0.7;
  private static final int OBSTACLE_DELAY_EASY = 500;
  private static final int OBSTACLE_DELAY_MEDIUM = 300;
  private static final int OBSTACLE_DELAY_HARD = 100;
  private static final int OBSTACLE_DELAY_GODLIKE = 50;
  public static final int OBSTACLE_COUNT = 5;
  public static final int INITIAL_HP = 100;
  public static final int INITIAL_SCORE = 0;
  public static final int INITIAL_SCORE_TIMER = 0;
  public static final int INITIAL_DELAY_TIMER = 0;

  public static boolean isGameOver;
  private Player player;
  private int score;
  private int score_timer;
  private int delay_timer;
  private Image gameoverBG;
  private Image gameBG;

  private LinkedList<Entity> entities = new  LinkedList<Entity>();

  public ICanFlyGame(String title) {
    super(title);
  }

  @Override
  public void render(GameContainer container, Graphics g) throws SlickException {
    if (!isGameOver) {
      renderGameBG(g);
      renderScoreAndHP(g);
      player.render(g);
      for (Entity entity : entities) {
        entity.render(g);
      }
    } else {
      renderGameOver(g);
    }
  }

  private void renderGameOver(Graphics g) throws SlickException {
    gameoverBG = new Image("res/gameoverBG.jpg");
    g.drawImage(gameoverBG,0,0,null);
    g.drawString("Score:" + score, 280, 100);
    g.drawString("ENTER TO START!", 250, 150);
  }

  private void renderGameBG(Graphics g) throws SlickException {
    gameBG = new Image("res/BG.png"); 
    g.drawImage( gameBG, 0, 0, null);
  }

  private void renderScoreAndHP(Graphics g) {
    g.drawString("Score:" + score, 500, 30);
    g.drawString("HP:" + player.getHP(), 500, 60);
  }

  @Override
  public void init(GameContainer container) throws SlickException {
    isGameOver = false;
    score = INITIAL_SCORE;
    score_timer = INITIAL_SCORE_TIMER;
    delay_timer = INITIAL_DELAY_TIMER;
    player = new Player(GAME_WIDTH/2, GAME_HEIGHT/2, PLAYER_JUMP_VY);
    player.setHP(INITIAL_HP);
    createObstacles();
  }

  public int randomTypeofObstacle() {
    Random rand = new Random();
    int n = rand.nextInt(20);
    return n;
  }

  public void createObstacles() throws SlickException {
    for (int i = 0; i < 1 ; i++) {
      entities.add(new Obstacle( OBSTACLE_VY, randomTypeofObstacle()));
    }
  }

  @Override
  public void update(GameContainer container, int delta) throws SlickException {
    player.update(delta);
    playerControl(container,delta);
    increaseScore(delta);
    handleGameMode(delta);
    handleEntity(delta);
    if (player.getY() > GAME_HEIGHT || player.getHP() <= 0 ) {
      isGameOver = true;
    }
  }

  private void handleEntity(int delta) {
    Iterator<Entity> iterator = entities.iterator();
    while (iterator.hasNext()) {
      Entity entity = iterator.next();
      entity.update(delta);
      if(entity.isCollide(player)) {
        player.getHit(entity.getType());
        iterator.remove();
      }
      if (entity.isDeletable()) {
        iterator.remove();
      }
    }
  }

  private void handleGameMode(int delta) throws SlickException {
    delay_timer -= delta;
    if(delay_timer <= 0) {
      createObstacles();
      if (this.score < 500) {
        delay_timer = OBSTACLE_DELAY_EASY;
      } else if (this.score >= 500 && this.score < 1500) {
        delay_timer = OBSTACLE_DELAY_MEDIUM;
      } else if (this.score >= 1500 && this.score < 3000) {
        delay_timer = OBSTACLE_DELAY_HARD;
      } else if (this.score >= 3000) {
        delay_timer = OBSTACLE_DELAY_GODLIKE;
      } else {
        delay_timer = OBSTACLE_DELAY_EASY;
      }
      if (player.isDeathZone()) {
        delay_timer = OBSTACLE_DELAY_GODLIKE;
      }
    }
  }

  public void increaseScore(int delta) {
    score_timer += delta;
    if(score_timer >= 50 && !isGameOver){
      score++;
      score_timer = 0;
    }
  }

  public void playerControl(GameContainer container,int delta) {
    Input input = container.getInput();
    if (input.isKeyDown(Input.KEY_A)) {
      player.moveLeft(delta);
    }
    if (input.isKeyDown(Input.KEY_D)) {
      player.moveRight(delta);
    }
  }

  @Override
  public void keyPressed(int key, char c) {
    if (key == Input.KEY_SPACE) {
      player.jump();
    }
    if (key == Input.KEY_ENTER && isGameOver) {
      try {
        init(null);
      } catch (SlickException e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
    try {
      ICanFlyGame game = new ICanFlyGame("ICanFlyGame by nullnil");
      AppGameContainer appgc = new AppGameContainer(game);
      appgc.setDisplayMode(GAME_WIDTH,GAME_HEIGHT, false);
      appgc.start();
    } catch (SlickException e) {
      e.printStackTrace();
    }
  }
}
