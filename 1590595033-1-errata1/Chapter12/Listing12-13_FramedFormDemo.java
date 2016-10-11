// use the FramedForm for having a form with scrollable contents and with content that does not scroll at all.

package com.apress.ui;

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.TextField;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemStateListener;
import de.enough.polish.ui.FramedForm;

public class FramedFormDemo implements ItemStateListener {

	private final TextField inputField;
	private final Item[] items;

	public FramedFormDemo( CommandListener commandListener,
		Display display, Command returnCmd, Item[] items )
	{
		//#style framedScreen
		FramedForm form = new FramedForm( "TabbedDemo" );
		// add all normal items:
		for ( int i = 0; i < items.length; i ++ ) {
			form.append( items[i] );
		}
		//#style inputFilter
		this.inputField = new TextField( "Filter: ", "", 30, TextField.ANY );
		form.append( Graphics.BOTTOM, this.inputField );
		form.addCommand( returnCmd );
		form.setCommandListener( commandListener );
		display.setCurrentItem( this.inputField );
	}

	public void itemStateChanged( Item item ) {
		// the TextField has been changed, now filter
		// the item accordingly...
	}
}
