// You can use user-defined log levels as well, such as the benchmark level:

public void run() {
	//#if polish.debug.info
		int numberOfFrames = 0;
		int framesPerSecond = 0;
		long lastFpsTime = System.currentTimeMillis();
	//#endif
	while (gameIsRunning) {
		processUserInput();
		animateWorld();
		renderWorld();
		flushGraphics();
		//#if polish.debug.info
			numberOfFrames++;
			if (System.currentTimeMillis() - lastFpsTime >= 1000) {
				lastFpsTime = System.currentTimeMillis();
				framesPerSecond = numberOfFrames;
				numberOfFrames = 0;
			}
			this.graphics.drawString( "fps=" + framesPerSecond, 0, 0,
				Graphics.TOP | Graphics.LEFT );
		//#endif
	}
}
