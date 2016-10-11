//#condition false
package com.apress.ui;

import java.util.HashMap;

/**
 * <p>Converts CSS code into a MovingBackground call.</p>
 *
 * <p>Copyright Enough Software 2004, 2005</p>
 * @author Robert Virkus, j2mepolish@enough.de
 */
public class MovingBackgroundConverter extends BackgroundConverter {

	public MovingBackgroundConverter() {
		super();
	}

	/* (non-Javadoc)
	 * @see de.enough.polish.preprocess.BackgroundConverter#createNewStatement(java.util.HashMap, de.enough.polish.preprocess.Style, de.enough.polish.preprocess.StyleSheet)
	 */
	protected String createNewStatement(
			HashMap background, 
			Style style,
			StyleSheet styleSheet ) 
	throws BuildException 
	{
		String speedStr = (String) background.get( "speed");
		if (speedStr != null) {
			super.parseInt("speed", speedStr);
		} else {
			speedStr = "5";
		}

		return "new com.apress.ui.MovingBackground( "
			+ this.color + ", " + speedStr + ")";
	}

}

