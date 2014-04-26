package view;

import javax.microedition.lcdui.Graphics;

public interface ViewObject {

	void paintMe(Graphics g);

	void setCanvas(GameCanvas gc);

}
