/*    */ package frontend;
/*    */ 
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ColouredPanel
/*    */   extends JPanel
/*    */ {
/*    */   protected ColouredPanel() {
/* 12 */     ColourSettings.setColours(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\sdmcg\Documents\Lab\Programming\SITAquant\SITAquant2023\SITAquant-July2019.jar!\frontend\ColouredPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */