// Use this snippet for creating a flexible audio player that doesn't rely
// on dynamic class loading.

public class AudioPlayer {

	public void playAudio() {
		//#if polish.api.mmapi || polish.midp2
			try {
				Player player = Manager.createPlayer(
					getClass().getResourceAsStream("/music.mid"), "audio/midi");
				player.realize();
				player.prefetch();
				player.start();
			} catch (Exception e) {
				//#debug error
				System.out.println("Unable to start audio playback" + e);
			}
		//#elif polish.api.nokia-ui
			try {
				byte[] soundData = loadSoundData();
				Sound sound = new Sound( soundData, Sound.FORMAT_TONE );
				sound.play( 1 );
			} catch (IllegalArgumentException e) {
				//#debug error
				System.out.println("Unable to play Nokia sound" + e );
			}
		//#else
			System.out.println("No sound playback supported.");
		//#endif
	}

	//#if polish.api.nokia-ui
	public byte[] loadSoundData() {
		// TODO implement loadSoundData
		return new byte[ 0 ];
	}
	//#endif

}
