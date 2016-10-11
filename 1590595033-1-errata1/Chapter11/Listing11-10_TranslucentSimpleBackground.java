// this example shows you how to port translucent background to MIDP 1.0 devices that
// support the Nokia UI API

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
//#if polish.api.nokia-ui && !polish.midp2
	import com.nokia.mid.ui.DirectGraphics;
	import com.nokia.mid.ui.DirectUtils;
//#endif
import de.enough.polish.ui.Background;

public class TranslucentSimpleBackground extends Background {
	private final int argbColor;
	//#ifdef polish.midp2
		// int MIDP/2.0 the buffer is always used:
		private int[] buffer;
		private int lastWidth;
	//#elif polish.api.nokia-ui
		private Image imageBuffer;
		//# private int lastWidth;
		private int lastHeight;
	//#endif

	public TranslucentSimpleBackground( int argbColor ) {
		super();
		this.argbColor = argbColor;
	}

	public void paint(int x, int y, int width, int height, Graphics g) {
		//#ifdef polish.midp2
			//#ifdef polish.Bugs.drawRgbOrigin
				x += g.getTranslateX();
				y += g.getTranslateY();
			//#endif
			// check if the buffer needs to be created:
			if (width != this.lastWidth) {
				this.lastWidth = width;
				int[] newBuffer = new int[ width ];
				for (int i = newBuffer.length - 1; i >= 0 ; i--) {
					newBuffer[i] = this.argbColor;
				}
				this.buffer = newBuffer;
			}
			if (x < 0) {
				width += x;
				if (width < 0) {
					return;
				}
				x = 0;
			}
			if (y < 0) {
				height += y;
				if (height < 0) {
					return;
				}
				y = 0;
			}
			g.drawRGB(this.buffer, 0, 0, x, y, width, height, true);
		//#elif polish.api.nokia-ui
			if (width != this.lastWidth || height != this.lastHeight) {
				this.lastWidth = width;
				this.lastHeight = height;
				this.imageBuffer = DirectUtils.createImage( width, height,
					this.argbColor );
			}
			DirectGraphics dg = DirectUtils.getDirectGraphics(g);
			dg.drawImage(this.imageBuffer, x, y, Graphics.TOP | Graphics.LEFT, 0 );
		//#else
			// ignore alpha-value
			g.setColor( this.argbColor );
			g.fillRect(x, y, width, height);
		//#endif
	}
}
