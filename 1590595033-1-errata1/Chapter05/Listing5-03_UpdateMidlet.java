package com.apress.update;
import javax.microedition.io.ConnectionNotFoundException;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;

public class UpdateMidlet extends MIDlet {
	private static final int VERSION = 125; // 1.2.5

	public UpdateMidlet() {
		super();
	}

	protected void startApp() throws MIDletStateChangeException {
		try {
			RecordStore versionStore = RecordStore.openRecordStore( "version", false );
			versionStore.closeRecordStore();
			byte[] versionData = versionStore.getRecord(
			versionStore.getNextRecordID() - 1 );
			String versionStr = new String( versionData );
			int version = Integer.parseInt( versionStr );
			if ( version != VERSION ) {
				// app has been updated:
				showUpdateMessage();
				// remove version record store:
				RecordStore.deleteRecordStore("version");
				return;
			}
		} catch (RecordStoreException e) {
			// no update has been requested
		}
		showMainMenu();
	}

	protected void requestUpdate() {
		//#ifdef polish.midp2
			try {
				// request update:
				//#if updateUrl:defined
					//#= platformRequest( "${updateUrl}" );
				//#else
					platformRequest( "http://www.company.com/app/update.jad" );
				//#endif
				// persist current version:
				RecordStore versionStore = RecordStore.openRecordStore( "version", true );
				byte[] versionData = Integer.toString( VERSION ).getBytes();
				versionStore.addRecord( versionData, 0, versionData.length );
				versionStore.closeRecordStore();
			} catch (ConnectionNotFoundException e) {
				//#debug error
				System.out.println("Unable to issue update request" + e );
			} catch (RecordStoreException e) {
				//#debug error
				System.out.println("Unable to persist current version" + e );
			}
		//#endif
	}
	
	protected void showMainMenu() {
		// TODO implement showMainMenu
	}

	protected void showUpdateMessage() {
		// TODO implement showUpdateMessage
	}

	protected void pauseApp() {
		// ignore
	}

	protected void destroyApp(boolean unconditional) throws
		MIDletStateChangeException {
		// exit gracefully
	}
}
