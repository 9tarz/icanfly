package icanfly;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
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
	private static Obstacle[] obstacles;
	public static final int OBSTACLE_COUNT = 5;
	
	public ICanFlyGame(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		for (Obstacle obstacle : obstacles) {
		      obstacle.render();
		    }
			player.render();
		
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		player = new Player(GAME_WIDTH/2, GAME_HEIGHT/2, PLAYER_JUMP_VY);
		initObstacles();
	}

	public static void initObstacles() throws SlickException {
		 obstacles = new Obstacle[OBSTACLE_COUNT];
	    for (int i = 0; i < OBSTACLE_COUNT ; i++) {
	     obstacles[i] = new Obstacle(OBSTACLE_VY);
	    }
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
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
