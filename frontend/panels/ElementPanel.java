/*    */ package frontend.panels;
/*    */ 
/*    */ import backend.Elements;
/*    */ import frontend.ColourSettings;
/*    */ import frontend.ColouredPanel;
/*    */ import java.awt.Color;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.GridLayout;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JSpinner;
/*    */ import javax.swing.SpinnerNumberModel;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ElementPanel
/*    */   extends ColouredPanel
/*    */ {
/* 19 */   int MAX_NUM_MOLECULES = 200;
/*    */   
/* 21 */   JLabel[] elementLabels = new JLabel[Elements.length];
/* 22 */   JSpinner[] elementSpinners = new JSpinner[Elements.length];
/*    */ 
/*    */   
/*    */   public ElementPanel() {
/* 26 */     setLayout(new GridLayout(3, 2));
/* 27 */     setPreferredSize(new Dimension(100, 75));
/*    */     
/* 29 */     for (int i = 0; i < Elements.length; i++) {
/*    */       
/* 31 */       this.elementLabels[i] = new JLabel("# of " + Elements.names[i] + ":");
/* 32 */       ColourSettings.setColours(this.elementLabels[i]);
/* 33 */       this.elementLabels[i].setHorizontalAlignment(0);
/* 34 */       this.elementLabels[i].setPreferredSize(new Dimension(100, 50));
/*    */       
/* 36 */       this.elementSpinners[i] = new JSpinner(new SpinnerNumberModel(0, 0, this.MAX_NUM_MOLECULES, 1));
/* 37 */       this.elementSpinners[i].setPreferredSize(new Dimension(100, 50));
/*    */       
/* 39 */       add(this.elementLabels[i]);
/* 40 */       add(this.elementSpinners[i]);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int[] getNumElements() {
/* 47 */     int[] numElements = new int[Elements.length];
/* 48 */     for (int i = 0; i < numElements.length; i++)
/*    */     {
/* 50 */       numElements[i] = ((Integer)this.elementSpinners[i].getValue()).intValue();
/*    */     }
/*    */     
/* 53 */     return numElements;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setNumElements(int[] numElements) {
/* 58 */     for (int i = 0; i < numElements.length; i++)
/*    */     {
/* 60 */       this.elementSpinners[i].setValue(Integer.valueOf(numElements[i])); } 
/*    */   }
/*    */   public void setBGFG(Color BG, Color FG) {
/*    */     byte b;
/*    */     int i;
/*    */     JLabel[] arrayOfJLabel;
/* 66 */     for (i = (arrayOfJLabel = this.elementLabels).length, b = 0; b < i; ) { JLabel jl = arrayOfJLabel[b];
/*    */       
/* 68 */       jl.setBackground(BG);
/* 69 */       jl.setForeground(FG); b++; }
/*    */     
/* 71 */     setBackground(BG);
/* 72 */     setForeground(FG);
/*    */   }
/*    */ }


/* Location:              C:\Users\sdmcg\Documents\Lab\Programming\SITAquant\SITAquant2023\SITAquant-July2019.jar!\frontend\panels\ElementPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */