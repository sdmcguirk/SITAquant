/*    */ package frontend.panels.popup;
/*    */ 
/*    */ import frontend.fields.TextEntry;
/*    */ import java.awt.Component;
/*    */ import javax.swing.BoxLayout;
/*    */ import javax.swing.JCheckBox;
/*    */ import javax.swing.JOptionPane;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ 
/*    */ public class UnlabelledCorrectionPanel
/*    */   extends JPanel
/*    */ {
/* 14 */   public static float percentageUnlabelled = 0.0F;
/* 15 */   public static int numberOfCarbons = 0;
/*    */   
/* 17 */   TextEntry percentageUnlabelledEntry = new TextEntry("Unlabelled fraction: ", "0.00");
/* 18 */   TextEntry numberOfCarbonsEntry = new TextEntry("Number of carbons in unaltered metabolite: ", "0");
/*    */   
/*    */   JCheckBox correctUnlabelledCheckbox;
/*    */   
/*    */   public UnlabelledCorrectionPanel(JCheckBox correctUnlabelledCheckbox) {
/* 23 */     this.correctUnlabelledCheckbox = correctUnlabelledCheckbox;
/*    */   }
/*    */ 
/*    */   
/*    */   public void show() {
/* 28 */     setLayout(new BoxLayout(this, 3));
/* 29 */     add((Component)this.percentageUnlabelledEntry);
/* 30 */     add((Component)this.numberOfCarbonsEntry);
/*    */     
/* 32 */     Object[] options = { "OK" };
/* 33 */     int returnVal = JOptionPane.showOptionDialog(this, this, 
/* 34 */         "Sample Details", 0, 
/* 35 */         1, null, options, options[0]);
/*    */     
/* 37 */     if (returnVal == 0) {
/*    */       
/*    */       try
/*    */       {
/* 41 */         percentageUnlabelled = Float.parseFloat(this.percentageUnlabelledEntry.getText());
/*    */         
/* 43 */         numberOfCarbons = Integer.parseInt(this.numberOfCarbonsEntry.getText());
/*    */       }
/* 45 */       catch (Exception e)
/*    */       {
/* 47 */         showError();
/*    */       }
/*    */     
/*    */     } else {
/*    */       
/* 52 */       showError();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void showError() {
/* 58 */     this.correctUnlabelledCheckbox.setSelected(false);
/*    */     
/* 60 */     JOptionPane.showMessageDialog(this, "Invalid input");
/*    */     
/* 62 */     percentageUnlabelled = 0.0F;
/* 63 */     numberOfCarbons = 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\sdmcg\Documents\Lab\Programming\SITAquant\SITAquant2023\SITAquant-July2019.jar!\frontend\panels\popup\UnlabelledCorrectionPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */