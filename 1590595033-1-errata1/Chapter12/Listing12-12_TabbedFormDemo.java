// Use the TabbedForm for haveing several tabs on a single screen:

package com.apress.ui;

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemStateListener;
import de.enough.polish.ui.TabbedForm;

public class TabbedFormDemo implements ItemStateListener {
	private final TextField nameField;

	public TabbedFormDemo( CommandListener commandListener,
		Display display, Command returnCmd )
	{
		String[] tabNames = new String[]{ "Input", "Choice",
			"Connection" };
		//#style tabbedScreen
		TabbedForm form = new TabbedForm( "TabbedDemo", tabNames, null );
		//#style label
		StringItem label = new StringItem( null, "name:" );
		form.append( 0, label );
		//#style input
		this.nameField = new TextField( null, "Robert", 30,
		TextField.ANY | TextField.INITIAL_CAPS_WORD );
		form.append( 0, this.nameField );
		//#style label
		label = new StringItem( null, "birthday:" );
		form.append( 0, label );
		//#style input
		DateField birthdate = new DateField( null, DateField.DATE );
		form.append( 0, birthdate );
		//#style label
		label = new StringItem( null, "What kind of animals do you like:" );
		form.append( 1, label );
		//#style multipleChoice
		ChoiceGroup choice = new ChoiceGroup( null, Choice.MULTIPLE );
		//#style choiceItem
		choice.append( "dogs", null );
		//#style choiceItem
		choice.append( "cats", null );
		//#style choiceItem
		choice.append( "birds", null );
		form.append( 1, choice );
		//#style label
		label = new StringItem( null, "Connection:" );
		form.append( 2, label );
		//#style multipleChoice
		choice = new ChoiceGroup( null, Choice.MULTIPLE );
		//#style choiceItem
		choice.append( "ISDN", null );
		//#style choiceItem
		choice.append( "DSL", null );
		//#style choiceItem
		choice.append( "Cable", null );
		form.append( 2, choice );
		form.addCommand( returnCmd );
		form.setCommandListener( commandListener );
		form.setItemStateListener( this );
		display.setCurrentItem( choice );
	}

	public void itemStateChanged( Item item ) {
		System.out.println( "Item State Changed: " + item );
	}
}
