package Q10_Simple_Applet;

import java.applet.Applet;
import java.awt.Graphics;

@SuppressWarnings("removal")
public class CustomApplet extends Applet {
    @Override public void paint(Graphics g) {
        g.drawString("Hello World", 100, 50);
    }
}
