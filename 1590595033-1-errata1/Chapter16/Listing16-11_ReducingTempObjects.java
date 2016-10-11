// Listing 16-11. Reducing the Number of Temporary Objects by Using StringBuffer Instead of String


public String getSlowNumberString( int top ) {
	String number = "";
	for ( int i = 0; i <= top; i++ ) {
		number += i;
	}
	return number;
}


public String getFastNumberString( int top ) {
	// we assume that top is less than 10 here:
	StringBuffer number = new StringBuffer( top );
	for ( int i = 0; i <= top; i++ ) {
		number.append( i );
	}
	return number.toString();
}
