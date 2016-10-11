// You can also port vibrating code to different constellations:

//#if polish.midp2
	import javax.microedition.lcdui.Display;
//#elif polish.api.nokia-ui
	import com.nokia.mid.ui.DeviceControl;
//#endif

...

//#if polish.midp2
	private Display display;
//#endif
...

public void vibrate() {
	//#if polish.midp2
		this.display.vibrate( 500 );
	//#elif polish.api.nokia-ui
		try {
			DeviceControl.startVibra( 100, 500 );
		} catch (IllegalStateException e) {
			//#debug error
			System.out.println("Device does not support vibration" + e );
		}
	//#endif
}
