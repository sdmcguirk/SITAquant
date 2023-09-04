/*    */ package frontend.fields;
/*    */ 
/*    */ import frontend.ColourSettings;
/*    */ import java.awt.Container;
/*    */ import java.awt.event.ActionListener;
/*    */ import javax.swing.JButton;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CMButton
/*    */   extends JButton
/*    */ {
/*    */   public CMButton(String string) {
/* 17 */     super(string);
/* 18 */     setActionCommand(string);
/*    */     
/* 20 */     ColourSettings.setColours(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void addTo(ActionListener theAL, Container theContainer) {
/* 25 */     addActionListener(theAL);
/* 26 */     theContainer.add(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void addTo(ActionListener theAL, Container theContainer, Object constraints) {
/* 31 */     addActionListener(theAL);
/* 32 */     theContainer.add(this, constraints);
/*    */   }
/*    */ }


/* Location:              C:\Users\sdmcg\Documents\Lab\Programming\SITAquant\SITAquant2023\SITAquant-July2019.jar!\frontend\fields\CMButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */