// this is a snippet that shows you  how to port sound playback:

//#if polish.audio.midi && (polish.api.mmapi || polish.midp2)
	import javax.microedition.media.Manager;
	import javax.microedition.media.Player;
//#elif polish.api.nokia-ui
	import com.nokia.mid.sound.Sound;
	import java.io.ByteArrayOutputStream;
//#endif

...

public void playMusic() throws Exception {
	//#if polish.audio.midi && (polish.midp2 || polish.api.mmapi)
		Player musicPlayer =
			Manager.createPlayer(
				getClass().getResourceAsStream("/music.mid"), "audio/midi");
		musicPlayer.realize();
		musicPlayer.prefetch();
		musicPlayer.start();
	//#elif polish.api.nokia-ui
		InputStream is = getClass().getResourceAsStream("/music.tt");
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int read;
		byte[] buffer = new byte[ 1024 ];
		while( ( read = is.read( buffer, 0, 1024 ) ) != -1 ) {
			out.write( buffer, 0, read );
		}
		Sound sound = new Sound( out.getByteArray(), Sound.FORMAT_TONE );
		sound.play( 1 );
	//#endif
}


