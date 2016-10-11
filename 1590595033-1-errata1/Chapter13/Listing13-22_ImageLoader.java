package com.apress.ui;

import java.io.ByteArrayOutputStream;


public final class ImageLoader
implements Runnable
{
   //#if ImageLoader.server.url:defined
      //#= private final static String WEBSERVER_URL = "${ImageLoader.server.url}";
   //#else
      private final static String WEBSERVER_URL = "http://www.company.com/servlet/ImageProvider?img=";
   //#endif
      
   private final String imageUrl;
   private final String recordStoreName;
   
   private ImageLoader( String recordStoreName, String imageUrl ) {
      this.imageUrl = imageUrl;
      this.recordStoreName = recordStoreName;
   }

   public static Image loadImage( String url )
   throws IOException
   {
      String recordStoreName = url.substring( 1, url.length() - ".png".length() );
      try {
         RecordStore store = RecordStore.openRecordStore( recordStoreName, false );
         byte[] imageData = store.getRecord( store.getNextRecordID() - 1 );
         return Image.createImage( imageData, 0, imageData.length );
      } catch (RecordStoreNotFoundException e) {
         // load image in background:
         Thread thread = new Thread(
            new  ImageLoader( recordStoreName, url ) );
         thread.start();
         return null;
      } catch (RecordStoreException e) {
         //#debug error
         System.out.println("Unable to load image from recordstore" + e );
         throw new IOException( e.toString() );
      }
   }

   private byte[] loadImageFromHttpServer()
   throws IOException
   {
      try {
         HttpConnection connection = (HttpConnection)
                 Connector.open(
                      WEBSERVER_URL + this.imageUrl,
                    Connector.READ_WRITE, true );
         connection.setRequestMethod( HttpConnection.GET );
         connection.setRequestProperty("Connection", "close");
         int responseCode = connection.getResponseCode();
         if (responseCode == HttpConnection.HTTP_OK ) {
            InputStream in = connection.openInputStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[ 5 * 1024 ];
            int read;
            while ( (read = in.read(buffer, 0, buffer.length) ) != -1 ) {
               out.write( buffer, 0, read );
            }
            return out.toByteArray();
         } else {
            throw new IOException("Got invalid response code: " + responseCode );
         }
      } catch ( SecurityException e ) {
         //#debug error
         System.out.println("Not allowed to open connection" + e );
         throw new IOException( e.toString() );
      }
   }
   
   private void storeImageInRms( byte[] data ) {
      try {
         RecordStore store = RecordStore.openRecordStore( 
                             this.recordStoreName, true );
         store.addRecord(data, 0,  data.length );
      } catch (RecordStoreException e) {
         //#debug error
         System.out.println("Unable to store image in record-store ["
             + this.recordStoreName + "]" + e );
      }

   }

   public void run() {
      System.out.println("running image loader...");
      try {
         byte[] data = loadImageFromHttpServer();
         storeImageInRms(data);
      } catch (IOException e) {
         //#debug error
         System.out.println("Unable to load image from server [" 
             + WEBSERVER_URL + "]" + e );
      }
   }

}
