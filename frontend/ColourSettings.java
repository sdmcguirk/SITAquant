/*    */ package frontend;
/*    */ 
/*    */ import frontend.fields.TextEntry;
/*    */ import java.awt.Color;
/*    */ import java.awt.Container;
/*    */ import javax.swing.JButton;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ColourSettings
/*    */ {
/* 15 */   static Color background = new Color(150, 30, 0);
/* 16 */   static Color foreground = Color.white;
/*    */ 
/*    */   
/* 19 */   static Color background2 = new Color(206, 207, 206);
/* 20 */   static Color foreground2 = Color.black;
/*    */ 
/*    */   
/* 23 */   static Color buttonsBG = Color.gray;
/* 24 */   static Color buttonsFG = Color.white;
/*    */ 
/*    */   
/*    */   public static void setColours(Container contentPane) {
/* 28 */     setColours(contentPane, background, foreground);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void setSecondaryColours(Container contentPane) {
/* 33 */     setColours(contentPane, background2, foreground2);
/*    */   }
/*    */ 
/*    */   
/*    */   private static void setColours(Container contentPane, Color bg, Color fg) {
/* 38 */     contentPane.setBackground(bg);
/* 39 */     contentPane.setForeground(fg);
/*    */     
/* 41 */     if (contentPane instanceof TextEntry) {
/*    */       
/* 43 */       TextEntry te = (TextEntry)contentPane;
/* 44 */       te.setBGFG(bg, fg);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void setColours(JButton button) {
/* 50 */     button.setBackground(buttonsBG);
/* 51 */     button.setForeground(buttonsFG);
/*    */   }
/*    */ }


/* Location:              C:\Users\sdmcg\Documents\Lab\Programming\SITAquant\SITAquant2023\SITAquant-July2019.jar!\frontend\ColourSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */