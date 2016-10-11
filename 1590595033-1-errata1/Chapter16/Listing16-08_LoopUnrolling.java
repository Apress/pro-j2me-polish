// Listing 16-8. Accelerating Loops by Unrolling


public void slowFill( int[] numbers ) {
	for ( int i = numbers.length; --i >= 0; ) {
		numbers[ i ] = i;
	}
}


public void fastFill( int[] numbers ) {
	// warning: this will crash if numbers.length != x * 4
	for ( int i = numbers.length; --i >= 0; ) {
		numbers[ i ] = i;
		numbers[ --i ] = i;
		numbers[ --i ] = i;
		numbers[ --i ] = i;
	}
}
