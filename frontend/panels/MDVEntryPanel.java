/*    */ package frontend.panels;
/*    */ 
/*    */ import frontend.ColouredPanel;
/*    */ import java.awt.Component;
/*    */ import java.awt.Container;
/*    */ import javax.swing.BoxLayout;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JOptionPane;
/*    */ import javax.swing.JTextField;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MDVEntryPanel
/*    */   extends ColouredPanel
/*    */ {
/*    */   double[] peaks;
/*    */   
/*    */   public MDVEntryPanel() {
/* 19 */     setLayout(new BoxLayout((Container)this, 3));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean init() {
/* 24 */     String input = JOptionPane.showInputDialog((Component)this, "Please enter the number of mass distribution peaks.");
/* 25 */     if (input == null) {
/*    */       
/* 27 */       JOptionPane.showMessageDialog((Component)this, "Invalid input.");
/* 28 */       return false;
/*    */     } 
/*    */     
/*    */     try {
/* 32 */       int numPeaks = Integer.parseInt(input);
/*    */       
/* 34 */       add(new JLabel("Please enter the mass distribution peaks."));
/*    */       
/* 36 */       JTextField[] fieldArray = new JTextField[numPeaks];
/* 37 */       for (int i = 0; i < fieldArray.length; i++) {
/*    */         
/* 39 */         fieldArray[i] = new JTextField(10);
/* 40 */         add(fieldArray[i]);
/*    */       } 
/*    */ 
/*    */ 
/*    */       
/* 45 */       Object[] options = { "OK", "Cancel" };
/* 46 */       int returnVal = JOptionPane.showOptionDialog((Component)this, this, 
/* 47 */           "Enter peaks", 2, 
/* 48 */           3, null, options, options[0]);
/*    */       
/* 50 */       this.peaks = null;
/*    */       
/* 52 */       if (returnVal == 0)
/*    */       {
/* 54 */         this.peaks = new double[fieldArray.length];
/*    */         
/* 56 */         for (int j = 0; j < fieldArray.length; j++)
/*    */         {
/* 58 */           this.peaks[j] = Double.parseDouble(fieldArray[j].getText());
/*    */         }
/*    */       }
/*    */       else
/*    */       {
/* 63 */         return false;
/*    */       }
/*    */     
/*    */     }
/* 67 */     catch (Exception e) {
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 72 */       JOptionPane.showMessageDialog((Component)this, "Invalid input.\nError: " + e.getMessage());
/* 73 */       return false;
/*    */     } 
/*    */     
/* 76 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\sdmcg\Documents\Lab\Programming\SITAquant\SITAquant2023\SITAquant-July2019.jar!\frontend\panels\MDVEntryPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */