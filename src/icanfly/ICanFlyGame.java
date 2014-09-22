package icanfly;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class ICanFlyGame extends BasicGame {
	private Player player;
	private Obstacle obstacle;
	public static final int GAME_WIDTH = 640;
	public static final int GAME_HEIGHT = 480;
	public static final float PLAYER_JUMP_VY = -5;
	public static final float OBSTACLE_VY = 4;
	public static final float G = (float) 0.7;
	
	public ICanFlyGame(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		player.render();
		obstacle.render();
		
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		player = new Player(GAME_WIDTH/2, GAME_HEIGHT/2, PLAYER_JUMP_VY);
		obstacle = new Obstacle(GAME_WIDTH/2  , 0,OBSTACLE_VY);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();
		if (input.isKeyDown(Input.KEY_LEFT)) {
			player.moveLeft();
		}
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			player.moveRight();
		}
		player.update();
		obstacle.update();
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
