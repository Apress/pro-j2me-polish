// Use the //#debug directive for logging statements in your application:

public Image getImage( String url ) {
	// try to get the image from the Hashtable cache:
	Image image = (Image) this.cache.get( url );
	if (image == null) {
		try {
			image = Image.createImage( url );
			this.cache.put( url, image );
			//#debug
			System.out.println("Loaded image [" + url + "]" );
		} catch (IOException e) {
			//#debug error
			System.out.println("Unable to load image [" + url + "]" + e );
		}
	}
	return image;
}
