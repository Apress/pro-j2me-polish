//#condition polish.usePolishGui
package com.apress.ui;

import javax.microedition.lcdui.Graphics;

/**
 * <p>Paints a background that follows the currently focused item</p>
 *
 * <p>Copyright Enough Software 2004, 2005</p>
 * @author Robert Virkus, j2mepolish@enough.de
 */
public class MovingBackground extends Background {
	
	private final int color;
	private final int speed;
	private int nextX;
	private int nextY;
	private int currentX;
	private int currentY;

	public MovingBackground( int color, int speed ) {
		super();
		this.color = color;
		this.speed = speed;
	}

	public void paint(int x, int y, int width, int height, Graphics g) {
		if (x != this.nextX || y != this.nextY ) {
			this.nextX = x;
			this.nextY = y;
		}
		g.setColor( this.color );
		g.fillRect( this.currentX, this.currentY, width, height );
	}

	public boolean animate() {
		if (this.currentX == this.nextX && this.currentY == this.nextY ) {
			return false;
		}
		if ( this.currentX < this.nextX ) {
			this.currentX += this.speed;
			if ( this.currentX > this.nextX ) {
				this.currentX = this.nextX;
			}
		} else if ( this.currentX > this.nextX ) {
			this.currentX -= this.speed;
			if ( this.currentX < this.nextX ) {
				this.currentX = this.nextX;
			}
		}
		if ( this.currentY < this.nextY ) {
			this.currentY += this.speed;
			if ( this.currentY > this.nextY ) {
				this.currentY = this.nextY;
			}
		} else if ( this.currentY > this.nextY ) {
			this.currentY -= this.speed;
			if ( this.currentY < this.nextY ) {
				this.currentY = this.nextY;
			}
		}
		return true;
	}
}

