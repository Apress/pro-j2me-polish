// Use this snippet for learning how to integrate localized messages in your J2ME application.

import de.enough.polish.util.Locale;

[...]

// getting a simple translation:
this.menuScreen.append( Locale.get( "menu.StartGame" ), null );

// getting a translation with one parameter:
this.menuScreen.setTitle( Locale.get( "title.Main", userName ) );

// getting a translation with several parameters:
String[] parameters = new String[2];
parameters[0] = userName;
parameters[1] = enemyName;
this.textField.setString( Locale.get( "messages.Introduction", parameters ) );
