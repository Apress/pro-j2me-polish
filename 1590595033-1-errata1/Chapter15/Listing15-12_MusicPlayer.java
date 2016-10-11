// Listing 15-12. Playing Background Music with the MMAPI


//#condition polish.api.mmapi || polish.midp2

package com.apress.multimedia;

import java.io.IOException;
import javax.microedition.media.*;

public class MusicPlayer
implements PlayerListener 
{
	public boolean playMusic = true;
	private Player player;

	public void playMusic( String url, String contentType )
	throws MediaException, IOException 
	{
		boolean registerListener = ( this.player == null );

		if ( !registerListener ) {
			this.player.stop();
			this.player.deallocate();
		}

		this.player = Manager.createPlayer(
			getClass().getResourceAsStream( url ), contentType );
		if ( registerListener ) {
			player.addPlayerListener( this );
		}
		player.realize();
		player.prefetch();
		player.start();
	}

	public void playerUpdate( Player p, String event, Object data )
	throws MediaException {
		if ( this.playMusic && event.equals( END_OF_MEDIA ) ) {
			p.start();
		}
	}
}
