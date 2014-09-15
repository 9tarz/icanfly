package icanfly;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;


public class ICanFlyGame extends BasicGame {

	public ICanFlyGame(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		
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
