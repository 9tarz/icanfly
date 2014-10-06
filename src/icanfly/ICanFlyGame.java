package icanfly;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class ICanFlyGame extends BasicGame {
	private Player player;
	public static final int GAME_WIDTH = 640;
	public static final int GAME_HEIGHT = 480;
	public static final float PLAYER_JUMP_VY = -5;
	public static final float OBSTACLE_VY = 4;
	public static final float G = (float) 0.7;
	private static ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	public static final int OBSTACLE_COUNT = 5;
	private boolean isGameOver;
	private int score;
	private int timer;
	
	public ICanFlyGame(String title) {
		super(title);
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		for (Obstacle obstacle : obstacles) {
		      obstacle.render();
		    }
			player.render();
			g.setColor(new Color(255,255,255));
			g.drawString("Score:" + score, 100, 100);
			g.drawString("HP:" + player.getHP(), 100, 150);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		isGameOver = false;
		score = 0;
		timer = 0;
		player = new Player(GAME_WIDTH/2, GAME_HEIGHT/2, PLAYER_JUMP_VY);
		player.setHP(100);
		createObstacles();
	}
	
	private static int randomNumberofObstacles(){
		int obstacleCount = 0;
		while(obstacleCount == 0){
			Random rand = new Random();
			obstacleCount = 5+rand.nextInt(OBSTACLE_COUNT) ;
		}
		return obstacleCount;
	}
	
	public static void createObstacles() throws SlickException {
		int obstacleCount = randomNumberofObstacles();
	    for (int i = 0; i < obstacleCount ; i++) {
	     obstacles.add(new Obstacle(OBSTACLE_VY));
	    }
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		if (isGameOver) {
			isGameOver = false;
			for(int i=0;i<obstacles.size();i++){
				obstacles.remove(i);
			}
			this.init(container);
			
		}
		Input input = container.getInput();
		if (input.isKeyDown(Input.KEY_A )) {
			player.moveLeft();
		}
		if (input.isKeyDown(Input.KEY_D)) {
			player.moveRight();
		}
		player.update();
		for (Obstacle obstacle : obstacles) {
			obstacle.update();
		}
		if (player.getY() > 480 || player.getHP() <= 0 ) {
			isGameOver = true;
		}
		timer += delta;
		if(timer >= 50){
			score++;
			timer=0;
		}
		for(int i=0;i<obstacles.size();i++){
			if(obstacles.get(i).checkDelete){
				obstacles.remove(i);
			}
		}
		if(obstacles.size() == 0){
			createObstacles();
		}
		
		for(int i=0;i<obstacles.size();i++){
			if(obstacles.get(i).isCollide(player.getX(), player.getY())){
				player.getHit();
				obstacles.remove(i);
			}
		}
	}
	
	@Override
	  public void keyPressed(int key, char c) {
	    if (key == Input.KEY_SPACE) {
	    	player.jump();
	    }
	 }
	
	public static void main(String[] args) {
	    try {
	      ICanFlyGame game = new ICanFlyGame("ICanFlyGame");
	      AppGameContainer appgc = new AppGameContainer(game);
	      appgc.setDisplayMode(640,480, false);
	      appgc.start();
	    } catch (SlickException e) {
	      e.printStackTrace();
	   }
}
	
}
