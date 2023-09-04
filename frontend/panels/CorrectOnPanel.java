/*     */ package frontend.panels;
/*     */ 
/*     */ import frontend.ColourSettings;
/*     */ import frontend.ColouredPanel;
/*     */ import frontend.fields.TextEntry;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.event.DocumentEvent;
/*     */ import javax.swing.event.DocumentListener;
/*     */ 
/*     */ 
/*     */ public class CorrectOnPanel
/*     */   extends ColouredPanel
/*     */   implements ActionListener, DocumentListener
/*     */ {
/*  20 */   String[] correctOnOptions = new String[] { "Internal Standard", 
/*  21 */       "Internal Standard + Cell Number", 
/*  22 */       "Internal Standard + Protein Content", 
/*  23 */       "Cell Number", 
/*  24 */       "Protein Content" };
/*     */ 
/*     */   
/*  27 */   JComboBox coComboBox = new JComboBox<>(this.correctOnOptions);
/*     */   
/*  29 */   TextEntry unitsEntry = new TextEntry("Units:", "per million cell/ug protein");
/*     */ 
/*     */   
/*  32 */   static String unitsText = "";
/*  33 */   static String option = "";
/*     */   
/*     */   public static boolean correctOnIntStandard;
/*     */   public static boolean correctOnCellNumber;
/*     */   public static boolean correctOnProteinContent;
/*     */   
/*     */   public CorrectOnPanel() {
/*  40 */     JLabel correctOnLabel = new JLabel("Correct on: ");
/*  41 */     ColourSettings.setSecondaryColours(correctOnLabel);
/*  42 */     add(correctOnLabel);
/*     */     
/*  44 */     this.coComboBox.setSelectedIndex(0);
/*  45 */     this.coComboBox.addActionListener(this);
/*  46 */     add(this.coComboBox);
/*     */     
/*  48 */     this.unitsEntry.setEditable(false);
/*  49 */     this.unitsEntry.addDocumentListener(this);
/*  50 */     ColourSettings.setSecondaryColours((Container)this.unitsEntry);
/*  51 */     add((Component)this.unitsEntry);
/*     */     
/*  53 */     updateCorrectOn();
/*     */     
/*  55 */     ColourSettings.setSecondaryColours((Container)this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/*  61 */     if (e.getSource() == this.coComboBox)
/*     */     {
/*  63 */       updateCorrectOn();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void changedUpdate(DocumentEvent e) {}
/*     */   
/*     */   public void insertUpdate(DocumentEvent e) {
/*  71 */     updateCorrectOn();
/*     */   }
/*     */   
/*     */   public void removeUpdate(DocumentEvent e) {
/*  75 */     updateCorrectOn();
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateCorrectOn() {
/*  80 */     option = (String)this.coComboBox.getSelectedItem();
/*  81 */     doCorrectOn();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void doCorrectOn() {
/*  87 */     if (option.endsWith("Cell Number") || option.endsWith("Protein Content")) {
/*     */       
/*  89 */       this.unitsEntry.setEditable(true);
/*     */     }
/*     */     else {
/*     */       
/*  93 */       this.unitsEntry.setEditable(false);
/*     */     } 
/*     */     
/*  96 */     correctOnIntStandard = option.startsWith("Internal Standard");
/*  97 */     correctOnCellNumber = option.endsWith("Cell Number");
/*  98 */     correctOnProteinContent = option.endsWith("Protein Content");
/*     */     
/* 100 */     unitsText = this.unitsEntry.getText();
/* 101 */     for (ReplicatePanel RP : ReplicatePanel.RPArray)
/*     */     {
/* 103 */       RP.updateCorrectOn(option, unitsText);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\sdmcg\Documents\Lab\Programming\SITAquant\SITAquant2023\SITAquant-July2019.jar!\frontend\panels\CorrectOnPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */