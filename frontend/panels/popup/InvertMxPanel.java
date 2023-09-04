/*    */ package frontend.panels.popup;
/*    */ 
/*    */ import backend.MDV;
/*    */ import backend.Matrix;
/*    */ import backend.Output;
/*    */ import backend.Solver;
/*    */ import frontend.fields.JLabelSelectable;
/*    */ import frontend.panels.ElementPanel;
/*    */ import java.awt.Component;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.GridBagConstraints;
/*    */ import java.awt.GridBagLayout;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JOptionPane;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.JSpinner;
/*    */ import javax.swing.SpinnerNumberModel;
/*    */ import javax.swing.event.ChangeEvent;
/*    */ import javax.swing.event.ChangeListener;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InvertMxPanel
/*    */   extends JPanel
/*    */   implements ChangeListener
/*    */ {
/* 28 */   int MAX_NUM_PEAKS = 1000;
/* 29 */   JSpinner numPeaksSpinner = new JSpinner(new SpinnerNumberModel(1, 1, this.MAX_NUM_PEAKS, 1));
/* 30 */   JLabel numPeaksLabel = new JLabel("Number of peaks: ");
/* 31 */   JLabel copyInstructions = new JLabel("Control-A to highlight, Control-C to copy");
/*    */   
/*    */   private ElementPanel theEP;
/*    */   
/* 35 */   JLabelSelectable matrix = new JLabelSelectable("");
/*    */   
/* 37 */   GridBagConstraints gbCon = new GridBagConstraints();
/*    */ 
/*    */   
/*    */   public InvertMxPanel() {
/* 41 */     setLayout(new GridBagLayout());
/* 42 */     setPreferredSize(new Dimension(800, 600));
/*    */     
/* 44 */     this.gbCon.fill = 2;
/* 45 */     this.gbCon.gridx = 0;
/* 46 */     this.gbCon.gridy = 0;
/* 47 */     add(this.numPeaksLabel, this.gbCon);
/*    */     
/* 49 */     this.gbCon.gridx = 1;
/* 50 */     this.gbCon.gridy = 0;
/* 51 */     add(this.numPeaksSpinner, this.gbCon);
/*    */     
/* 53 */     this.gbCon.gridx = 0;
/* 54 */     this.gbCon.gridy = 1;
/* 55 */     this.gbCon.gridwidth = 5;
/* 56 */     this.matrix.setSize(new Dimension(600, 500));
/* 57 */     this.matrix.setScrollPaneSettings(20, 30, false);
/* 58 */     add((Component)this.matrix, this.gbCon);
/* 59 */     this.numPeaksSpinner.addChangeListener(this);
/*    */     
/* 61 */     this.gbCon.gridx = 0;
/* 62 */     this.gbCon.gridy = 2;
/* 63 */     add(this.copyInstructions, this.gbCon);
/*    */   }
/*    */ 
/*    */   
/*    */   public void show() {
/* 68 */     refreshMatrix();
/*    */     
/* 70 */     Object[] options = { "OK" };
/*    */     
/* 72 */     JOptionPane.showOptionDialog(this, this, 
/* 73 */         "Sample Details", 0, 
/* 74 */         1, null, options, options[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setElementPanel(ElementPanel elementPanel) {
/* 79 */     this.theEP = elementPanel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void stateChanged(ChangeEvent e) {
/* 84 */     refreshMatrix();
/*    */   }
/*    */ 
/*    */   
/*    */   private void refreshMatrix() {
/* 89 */     Integer numPeaks = (Integer)this.numPeaksSpinner.getValue();
/* 90 */     Matrix m = new Matrix(numPeaks.intValue(), numPeaks.intValue());
/* 91 */     m.show();
/* 92 */     MDV theMDV = new MDV(m);
/* 93 */     System.out.println(theMDV.toString());
/*    */     
/* 95 */     Output result = Solver.run(theMDV, this.theEP.getNumElements());
/* 96 */     this.matrix.setText(result.getCorrMx(7));
/*    */   }
/*    */ }


/* Location:              C:\Users\sdmcg\Documents\Lab\Programming\SITAquant\SITAquant2023\SITAquant-July2019.jar!\frontend\panels\popup\InvertMxPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */