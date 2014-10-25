package icanfly;

import org.newdawn.slick.Graphics;

public interface Entity {
  void render(Graphics g);
  void update(int delta);
  boolean isDeletable();
  boolean isCollide(Player player);
  int getType();
}
