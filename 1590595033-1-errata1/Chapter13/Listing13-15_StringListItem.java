//#condition polish.midp2 || polish.usePolishGui
package com.apress.ui;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.CustomItem;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

//#ifdef polish.usePolishGui
   import de.enough.polish.ui.Style;
//#endif

public class StringListItem extends CustomItem {
 
   private final Display  display;
   private final String[] entries;
   private final String shortestEntry;
   private final String longestEntry;
   private final int numberOfVisibleItems;
   private Font font;
   private int linePadding = 2;
   private boolean inTraversal;
   private int focusedIndex = -1;
   private int topIndex;
   private int lineHeight;
   private int highlightedBackgroundColor;
   private int backgroundColor;
   private int highlightedForegroundColor;
   private int foregroundColor;
   private boolean isInitialized;
 

   public StringListItem( String label, 
          String[] entries, int numberOfVisibleItems, 
          Display display )
   {
      //#style customListStyle, default 
      super( label );
      this.display = display;
      this.entries = entries;
      this.numberOfVisibleItems = numberOfVisibleItems;
      String longest = null;
      String shortest = null;
      int shortestLength = -1;
      int longestLength = -1;
      for (int i = 0; i < entries.length; i++) {
         String entry = entries[i];
         int length = entry.length();
         if (length < shortestLength || shortest == null) {
            shortest = entry;
            shortestLength = length;
         }
         if (length > longestLength ) {
            longest = entry;
            longestLength = length;
         }
      }
      this.longestEntry = longest;
      this.shortestEntry = shortest;
   }
   
   private void init() {
      this.isInitialized = true;
      if ( this.font == null ) {
         this.font = Font.getDefaultFont();
      }
      this.lineHeight = this.font.getHeight() + this.linePadding;
      if (this.backgroundColor == this.highlightedBackgroundColor) {
         // the colors haven't been set so far:
         //#if polish.midp2
            this.highlightedBackgroundColor = 
                this.display.getColor( Display.COLOR_HIGHLIGHTED_BACKGROUND );
            this.backgroundColor = 
                this.display.getColor( Display.COLOR_BACKGROUND );
            this.highlightedForegroundColor = 
                this.display.getColor( Display.COLOR_HIGHLIGHTED_FOREGROUND );
            this.foregroundColor = 
                this.display.getColor( Display.COLOR_FOREGROUND );
         //#else
            this.highlightedBackgroundColor = 0;
            this.backgroundColor = 0xFFFFFF;
            this.highlightedForegroundColor = 0xFFFFFF;
            this.foregroundColor = 0;
         //#endif
      }
   }


   public int getMinContentHeight(){
      if ( !this.isInitialized ) {
         init();
      }
      return this.lineHeight;
   }

   public int getMinContentWidth(){
      if ( !this.isInitialized ) {
         init();
      }
      return this.font.stringWidth( this.shortestEntry );
   }

   public int getPrefContentHeight( int width ){
      if ( !this.isInitialized ) {
         init();
      }
      return this.lineHeight * this.numberOfVisibleItems;
   }

   public int getPrefContentWidth( int height ){
      if ( !this.isInitialized ) {
         init();
      }
      return this.font.stringWidth( this.longestEntry );
   }

   public int getFocusedIndex(){
      return this.focusedIndex;
   }

   private void showFocusedEntry(){
      if( this.focusedIndex < this.topIndex ){
         this.topIndex = this.focusedIndex;
      } else if( this.focusedIndex - this.topIndex + 1 
        > this.numberOfVisibleItems )
      {
         this.topIndex = this.focusedIndex - this.numberOfVisibleItems + 1;
      }
   }

   protected void paint( Graphics g, int width, int height ){
      int numberOfLines = height / this.lineHeight;
      int lastLineIndex = this.topIndex + numberOfLines;
      if (lastLineIndex > this.entries.length) {
         lastLineIndex = this.entries.length;
      }
      int y = 0;
      g.setFont( this.font );
      for (int i = this.topIndex; i < lastLineIndex; i++ ) {
         String entry = this.entries[i];
         boolean isSel = ( i == this.focusedIndex );
         g.setColor( isSel ? 
         this.highlightedBackgroundColor : this.backgroundColor );
         g.fillRect( 0, y, width, this.lineHeight );
         g.setColor( isSel ? 
         this.highlightedForegroundColor : this.foregroundColor );
         g.drawString( entry, 0, y + this.linePadding/2, Graphics.TOP | Graphics.LEFT );
         y += this.lineHeight;
      }

      if( y < height ){
         g.setColor( this.backgroundColor );
         g.fillRect( 0, y, width, height - y );
      }
   }

   public boolean traverse( int direction, int viewportWidth, 
                    int viewportHeight, int[] visRectInOut )
   {
      if ( !this.inTraversal ){
         // this user entered the ListItem
         this.inTraversal = true;
         if ( this.focusedIndex == -1  ){
            if ( direction == Canvas.UP || direction == Canvas.LEFT ){
               if ( this.topIndex + this.numberOfVisibleItems 
                  < this.entries.length) 
               {
                   this.focusedIndex = 
                      this.topIndex + this.numberOfVisibleItems;
                   } else {
                      this.focusedIndex = this.entries.length - 1;
                   }
                } else {
                   this.focusedIndex = 0;                   
                }
                showFocusedEntry();
                repaint();
                notifyStateChanged();
             }
         visRectInOut[0] = 0;
         visRectInOut[1] = 0;
         visRectInOut[2] = viewportWidth;
         visRectInOut[3] = viewportHeight;
         return true;
      }


      if ( direction == Canvas.DOWN ) {
         if( this.focusedIndex < this.entries.length - 1 ){
                this.focusedIndex++;
         } else {
            return false;
         }
      } else if ( direction == Canvas.UP ) {
         if( this.focusedIndex > 0 ){
            this.focusedIndex--;
         } else {
            return false;
         }
      } else if ( direction != NONE ) {
         return false;
      }

      showFocusedEntry();
      repaint();
      visRectInOut[0] = 0;
      visRectInOut[1] = (this.focusedIndex - this.topIndex) 
                      * this.lineHeight;
      visRectInOut[2] = viewportWidth;
      visRectInOut[3] = this.lineHeight;

      notifyStateChanged();
      return true;
   }

   public void traverseOut(){
      this.inTraversal = false;
   }

    //#ifdef polish.hasPointerEvents
   protected void pointerPressed(int x, int y) {
      int row = y / this.lineHeight + this.topIndex;
      if (row != this.focusedIndex) {
         this.focusedIndex =  row;
         showFocusedEntry();
      }
   }
   //#endif
   
   //#ifdef polish.usePolishGui
   public void setStyle( Style style ) {
      //#if true
         // the super-call needs to be hidden:
         //# super.setStyle( style );
      //#endif
      this.font = style.font;
      //#ifdef polish.css.stringlistitem-foreground-color
           Integer foregroundColorInt = 
            style.getIntProperty( "stringlistitem-foreground-color" );
           if (foregroundColorInt != null) {
              this.foregroundColor = foregroundColorInt.intValue(); 
           }
        //#endif
        //#ifdef polish.css.stringlistitem-highlighted-foreground-color
           Integer highlightedForegroundColorInt = 
            style.getIntProperty( "stringlistitem-highlighted-foreground-color" );
           if (highlightedForegroundColorInt != null) {
              System.out.println("setting highlighted foreground color to [" + Integer.toHexString( highlightedForegroundColorInt.intValue() ) + "]");
              this.highlightedForegroundColor = 
                 highlightedForegroundColorInt.intValue(); 
           }
        //#endif
        //#ifdef polish.css.stringlistitem-background-color
           Integer backgroundColorInt = 
            style.getIntProperty( "stringlistitem-background-color" );
           if (backgroundColorInt != null) {
              this.backgroundColor = backgroundColorInt.intValue(); 
           }
        //#endif
        //#ifdef polish.css.stringlistitem-highlighted-background-color
           Integer highlightedBackgroundColorInt = 
            style.getIntProperty( "stringlistitem-highlighted-background-color" );
           if (highlightedBackgroundColorInt != null) {
              this.highlightedBackgroundColor =
                 highlightedBackgroundColorInt.intValue(); 
           }
        //#endif
        this.linePadding = style.paddingVertical;
        init();
        invalidate();
   }
   //#endif
}
