// Listing 16-7. Accelerating the switch Statement



public char slowSwitch( int keyEvent ) {

	switch ( keyEvent ) {

		case Canvas.KEY_NUM6: return '6';
		case Canvas.KEY_NUM3: return '3';
		case Canvas.KEY_NUM8: return '8';
		case Canvas.KEY_NUM9: return '9';
		case Canvas.KEY_NUM2: return '2';
		case Canvas.KEY_NUM1: return '1';
		case Canvas.KEY_NUM5: return '5';
	}
}



public char fastSwitch( int keyEvent ) {

	switch ( keyEvent ) {
		case Canvas.KEY_NUM1: return '1';
		case Canvas.KEY_NUM2: return '2';
		case Canvas.KEY_NUM3: return '3';
		case Canvas.KEY_NUM4: return '4';
		case Canvas.KEY_NUM5: return '5';
		case Canvas.KEY_NUM6: return '6';
		case Canvas.KEY_NUM7: return '7';
CHAPTER 16 ■ 336 OPTIMIZING APPLICATIONS
5033CH16.qxd 6/13/05 2:40 PM Page 336
		case Canvas.KEY_NUM8: return '8';
		case Canvas.KEY_NUM9: return '9';
	}
}

