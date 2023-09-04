/*     */ package frontend.panels;
/*     */ 
/*     */ import backend.Fragment;
/*     */ import frontend.ColourSettings;
/*     */ import frontend.ColouredPanel;
/*     */ import frontend.LoadSave.LoadSavePanel;
/*     */ import frontend.absoluteflux.AbsoluteFluxHandler;
/*     */ import frontend.absoluteflux.Sample;
/*     */ import frontend.fields.CMButton;
/*     */ import frontend.fields.TextEntry;
/*     */ import frontend.panels.popup.InvertMxPanel;
/*     */ import frontend.panels.popup.UnlabelledCorrectionPanel;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.Box;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FragmentPanel
/*     */   extends ColouredPanel
/*     */   implements ActionListener
/*     */ {
/*  29 */   LoadSavePanel loadSavePanel = new LoadSavePanel(this);
/*  30 */   ElementPanel elementPanel = new ElementPanel();
/*     */   
/*  32 */   CorrectOnPanel coPanel = new CorrectOnPanel();
/*  33 */   private SampleDisplayPanel SDP = new SampleDisplayPanel();
/*     */ 
/*     */ 
/*     */   
/*  37 */   TextEntry nameEntry = new TextEntry("Enter the fragment name: ", "fragment0");
/*     */ 
/*     */   
/*  40 */   String genMatrixText = "Corr. Matrix";
/*  41 */   CMButton genMatrix = new CMButton(this.genMatrixText);
/*     */   
/*  43 */   JCheckBox correctUnlabelledCheckbox = new JCheckBox("Correct for unlabelled fraction: ");
/*     */ 
/*     */   
/*     */   public FragmentPanel() {
/*  47 */     setLayout(new BoxLayout((Container)this, 3));
/*     */     
/*  49 */     ColourSettings.setSecondaryColours((Container)this.nameEntry);
/*  50 */     add((Component)this.nameEntry);
/*     */     
/*  52 */     add((Component)this.loadSavePanel);
/*  53 */     add(Box.createVerticalStrut(20));
/*  54 */     add((Component)this.elementPanel);
/*     */     
/*  56 */     JPanel tempPanel = new JPanel();
/*  57 */     ColourSettings.setSecondaryColours(tempPanel);
/*  58 */     tempPanel.add((Component)this.coPanel);
/*  59 */     this.genMatrix.addTo(this, tempPanel);
/*     */     
/*  61 */     tempPanel.add(this.correctUnlabelledCheckbox);
/*  62 */     this.correctUnlabelledCheckbox.addActionListener(this);
/*     */     
/*  64 */     add(tempPanel);
/*     */     
/*  66 */     add(Box.createVerticalStrut(20));
/*     */     
/*  68 */     add((Component)getSDP());
/*     */     
/*  70 */     SamplePanel.examineListener = this;
/*     */   }
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent evt) {
/*  75 */     Object source = evt.getSource();
/*  76 */     String actionCommand = evt.getActionCommand();
/*     */     
/*  78 */     if (actionCommand.equals(this.genMatrixText)) {
/*     */       
/*  80 */       InvertMxPanel IMP = new InvertMxPanel();
/*  81 */       IMP.setElementPanel(this.elementPanel);
/*  82 */       IMP.show();
/*     */     }
/*  84 */     else if (source == this.correctUnlabelledCheckbox) {
/*     */       
/*  86 */       if (this.correctUnlabelledCheckbox.isSelected()) {
/*     */         
/*  88 */         UnlabelledCorrectionPanel UCP = new UnlabelledCorrectionPanel(this.correctUnlabelledCheckbox);
/*  89 */         UCP.show();
/*     */       }
/*     */       else {
/*     */         
/*  93 */         UnlabelledCorrectionPanel.percentageUnlabelled = 0.0F;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 100 */     if (this.nameEntry == null)
/* 101 */       return ""; 
/* 102 */     return this.nameEntry.getText();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean compute() {
/* 107 */     return AbsoluteFluxHandler.computeAbsoluteFlux(this.elementPanel.getNumElements(), getSDP());
/*     */   }
/*     */ 
/*     */   
/*     */   public SampleDisplayPanel getSDP() {
/* 112 */     return this.SDP;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(Fragment fragToLoad, Sample[] sampleToLoad, ReplicatePanel[] replicToLoad, ArrayList<Object> exampleCorrectOn) {
/* 117 */     this.nameEntry.setText(fragToLoad.fragmentName);
/* 118 */     this.elementPanel.setNumElements(fragToLoad.getNumElements());
/*     */     
/* 120 */     String correctOnType = (String)exampleCorrectOn.get(0);
/* 121 */     String units = (String)exampleCorrectOn.get(1);
/*     */     
/* 123 */     this.coPanel.unitsEntry.setText(units);
/*     */     
/* 125 */     for (int i = 0; i < this.coPanel.coComboBox.getItemCount(); i++) {
/*     */       
/* 127 */       String type = this.coPanel.coComboBox.getItemAt(i);
/* 128 */       if (type.equals(correctOnType)) {
/*     */         
/* 130 */         this.coPanel.coComboBox.setSelectedIndex(i);
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 135 */     CorrectOnPanel.option = correctOnType;
/* 136 */     this.coPanel.doCorrectOn();
/*     */     
/* 138 */     this.SDP.load(sampleToLoad, replicToLoad, exampleCorrectOn);
/*     */   }
/*     */ }


/* Location:              C:\Users\sdmcg\Documents\Lab\Programming\SITAquant\SITAquant2023\SITAquant-July2019.jar!\frontend\panels\FragmentPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */