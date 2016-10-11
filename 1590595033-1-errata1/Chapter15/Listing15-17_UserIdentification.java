// Listing 15-17. Retrieving a Globally Unique Identification ID


public String getIdentification( MIDlet midlet ) 
{
	//#if user.msisdn:defined
		//#= return "msisdn:${user.msisdn}";
	//#elif polish.Property.imei:defined
		//#= return "imei:" + System.getProperty( "${polish.Property.imei}" );
	//#elif polish.Property.imsi:defined
		//#= return "imsi:" + System.getProperty( "${polish.Property.imsi}" );
	//#elif polish.api.btapi
		return "bluetooth:" + LocalDevice.getLocalDevice().getBluetoothAddress();
	//#else
		String msisdn = midlet.getAppProperty( "user.msisdn" );
		if ( msisdn != null ) {
			return "misdn:" + msisdn;
		} else {
			return null;
		}
	//#endif
}
