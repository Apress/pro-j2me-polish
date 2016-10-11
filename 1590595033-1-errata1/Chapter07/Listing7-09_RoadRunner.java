// now applying the CSS styles from the polish.css file
// with the use of the //#style preprocessing directive.

package com.apress.roadrunner;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public class Roadrunner extends MIDlet implements CommandListener {
	List menuScreen;
	Command startGameCmd = new Command( "Start game", Command.ITEM, 8 );
	Command quitCmd = new Command( "Quit", Command.EXIT, 10 );
	Display display;

	public Roadrunner() {
		super();
		//#style mainMenu
		this.menuScreen = new List( "Hello World", List.IMPLICIT );
		//#style mainMenuItem
		this.menuScreen.append( "Start game", null );
		//#style mainMenuItem
		this.menuScreen.append( "Settings", null );
		//#style mainMenuItem
		this.menuScreen.append( "Highscore", null );
		//#style mainMenuItem
		this.menuScreen.append( "Quit", null );
		this.menuScreen.addCommand( this.startGameCmd );
		this.menuScreen.addCommand( this.quitCmd );
		this.menuScreen.setCommandListener( this );
	}

	protected void startApp() throws MIDletStateChangeException {
		this.display = Display.getDisplay( this );
		this.display.setCurrent( this.menuScreen );
	}

	protected void pauseApp() {
		// ignore
	}

	protected void destroyApp(boolean unconditional)
	throws MIDletStateChangeException {
		// just quit
	}

	public void commandAction(Command cmd, Displayable screen) {
		if ( screen == this.menuScreen ) {
			if ( cmd == List.SELECT_COMMAND ) {
				int selectedItem =
					this.menuScreen.getSelectedIndex();
				if ( selectedItem == 3 ) {
					notifyDestroyed();
				}
			} else if ( cmd == this.quitCmd ) {
				notifyDestroyed();
			}
		}
	}
}
