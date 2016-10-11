// if you want to configure your extension you need to provide set[parametername] methods:


package com.apress.preprocess;

import java.io.File;
import java.io.IOException;
import de.enough.polish.Device;
import de.enough.polish.preprocess.CustomPreprocessor;
import de.enough.polish.Variable;
import de.enough.polish.util.FileUtil;
import de.enough.polish.util.StringList;

public class FullScreenPreprocessor extends CustomPreprocessor {
	private Variable[] parameters;
	private File logfile;
	private boolean enabledLogging;

	public FullScreenPreprocessor() {
		super();
	}

	public void notifyDevice(Device device, boolean usePolishGui) {
		super.notifyDevice(device, usePolishGui);
		// set default values:
		this.logfile = null;
		// configure this extension:
		configure( this.parameters );
	}

	public void processClass( StringList lines, String className ) {
		// now preprocess class...
		System.out.println( "FullScreenPrepocessor: processing class "
			+ className );
	}
	public void setParameters( Variable[] parameters, File baseDir ) {
		this.parameters = parameters;
	}

	public void setLogfile( File logfile ) {
		this.logfile = logfile;
	}

	public void setEnableLogging( boolean enabledLogging ) {
		this.enabledLogging = enabledLogging;
	}
}
