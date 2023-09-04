/*     */ package frontend.panels;
/*     */ 
/*     */ import backend.MDV;
/*     */ import frontend.ColourSettings;
/*     */ import frontend.ColouredPanel;
/*     */ import frontend.absoluteflux.Replicate;
/*     */ import frontend.fields.CMButton;
/*     */ import frontend.fields.TextEntry;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JOptionPane;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ReplicatePanel
/*     */   extends ColouredPanel
/*     */   implements ActionListener
/*     */ {
/*  28 */   static ArrayList<ReplicatePanel> RPArray = new ArrayList<>();
/*     */   
/*     */   private TextEntry nameEntry;
/*  31 */   TextEntry MDVEntry = new TextEntry("MDV:", "");
/*     */   
/*  33 */   TextEntry internalStandardEntry = new TextEntry("m+0 value of internal standard:", "");
/*  34 */   TextEntry cellNumOrProteinEntry = new TextEntry("Cell Number/Protein Content:", "");
/*     */   
/*  36 */   CMButton enterDetails = new CMButton("MDV Wizard");
/*  37 */   CMButton removeReplicate = new CMButton("Remove Replicate");
/*     */   
/*  39 */   GridBagConstraints gbCon = new GridBagConstraints();
/*     */   
/*  41 */   public Replicate theReplicate = null;
/*     */   
/*  43 */   public double[] controlValues = null;
/*     */ 
/*     */   
/*     */   public ReplicatePanel(ActionListener removeReplicateListener) {
/*  47 */     setLayout(new GridBagLayout());
/*  48 */     setBorder(BorderFactory.createLoweredBevelBorder());
/*     */     
/*  50 */     this.theReplicate = new Replicate();
/*  51 */     this.nameEntry = new TextEntry("Replicate Name: ", this.theReplicate.name);
/*     */     
/*  53 */     updateCorrectOn(CorrectOnPanel.option, CorrectOnPanel.unitsText);
/*     */     
/*  55 */     this.gbCon.fill = 2;
/*  56 */     this.gbCon.gridx = 0;
/*  57 */     this.gbCon.gridy = 0;
/*  58 */     add((Component)this.nameEntry, this.gbCon);
/*     */     
/*  60 */     this.gbCon.gridx = 1;
/*  61 */     this.gbCon.gridy = 0;
/*     */     
/*  63 */     if (removeReplicateListener != null) {
/*  64 */       this.removeReplicate.addTo(removeReplicateListener, (Container)this, this.gbCon);
/*     */     }
/*  66 */     this.removeReplicate.setActionCommand("RemoveReplicate:" + getName());
/*     */     
/*  68 */     this.gbCon.gridx = 2;
/*  69 */     this.gbCon.gridy = 0;
/*  70 */     add((Component)this.MDVEntry, this.gbCon);
/*     */     
/*  72 */     this.gbCon.gridx = 3;
/*  73 */     this.gbCon.gridy = 0;
/*  74 */     this.enterDetails.addTo(this, (Container)this, this.gbCon);
/*     */     
/*  76 */     this.gbCon.gridx = 0;
/*  77 */     this.gbCon.gridy = 1;
/*  78 */     this.gbCon.gridwidth = 2;
/*  79 */     this.internalStandardEntry.setOrientation(3);
/*  80 */     add((Component)this.internalStandardEntry, this.gbCon);
/*     */     
/*  82 */     this.gbCon.gridx = 2;
/*  83 */     this.gbCon.gridy = 1;
/*  84 */     this.gbCon.gridwidth = 2;
/*  85 */     this.cellNumOrProteinEntry.setOrientation(3);
/*  86 */     add((Component)this.cellNumOrProteinEntry, this.gbCon);
/*     */     
/*  88 */     ColourSettings.setSecondaryColours((Container)this);
/*  89 */     ColourSettings.setSecondaryColours((Container)this.nameEntry);
/*  90 */     ColourSettings.setSecondaryColours((Container)this.MDVEntry);
/*  91 */     ColourSettings.setSecondaryColours((Container)this.internalStandardEntry);
/*  92 */     ColourSettings.setSecondaryColours((Container)this.cellNumOrProteinEntry);
/*  93 */     RPArray.add(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRemoveListener(ActionListener removeReplicateListener) {
/*  98 */     this.removeReplicate.addTo(removeReplicateListener, (Container)this, this.gbCon);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 103 */     if (this.theReplicate == null)
/*     */     {
/* 105 */       return "";
/*     */     }
/* 107 */     return this.nameEntry.getText();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setName(String newName) {
/* 112 */     this.nameEntry.setText(newName);
/*     */   }
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/* 117 */     if (e.getSource() == this.enterDetails) {
/*     */       
/* 119 */       MDVEntryPanel MDVEPanel = new MDVEntryPanel();
/*     */       
/* 121 */       boolean valid = MDVEPanel.init();
/* 122 */       if (!valid) {
/*     */         return;
/*     */       }
/* 125 */       this.theReplicate.theMDV = new MDV(MDVEPanel.peaks);
/*     */       
/* 127 */       this.MDVEntry.setText(this.theReplicate.theMDV.toString());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateCorrectOn(String option, String unitsText) {
/* 133 */     this.internalStandardEntry.setEditable(option.startsWith("Internal Standard"));
/*     */     
/* 135 */     if (option.endsWith("Cell Number") || option.endsWith("Protein Content")) {
/*     */       
/* 137 */       this.cellNumOrProteinEntry.setEditable(true);
/* 138 */       if (option.endsWith("Cell Number"))
/*     */       {
/* 140 */         this.cellNumOrProteinEntry.setLabel("Enter Cell Number (units: " + unitsText + ")");
/*     */       }
/* 142 */       else if (option.endsWith("Protein Content"))
/*     */       {
/* 144 */         this.cellNumOrProteinEntry.setLabel("Enter Protein Content (units: " + unitsText + ")");
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 149 */       this.cellNumOrProteinEntry.setEditable(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public double getInternalStandardSum() {
/* 155 */     if (!CorrectOnPanel.correctOnIntStandard) return 0.0D;
/*     */     
/*     */     try {
/* 158 */       double value = Double.parseDouble(this.internalStandardEntry.getText());
/* 159 */       this.internalStandardEntry.highlight(false);
/* 160 */       return value;
/*     */     }
/* 162 */     catch (Exception e) {
/*     */       
/* 164 */       JOptionPane.showMessageDialog((Component)this, "Error with Internal Standard.\nError: " + e.getMessage());
/* 165 */       this.internalStandardEntry.highlight(true);
/* 166 */       return -1.0D;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getCellNumberSum() {
/* 173 */     if (!CorrectOnPanel.correctOnCellNumber) return 0.0D;
/*     */     
/*     */     try {
/* 176 */       double value = Double.parseDouble(this.cellNumOrProteinEntry.getText());
/* 177 */       this.cellNumOrProteinEntry.highlight(false);
/* 178 */       return value;
/*     */     }
/* 180 */     catch (Exception e) {
/*     */       
/* 182 */       JOptionPane.showMessageDialog((Component)this, "Error with Cell Number Sum.\nError: " + e.getMessage());
/* 183 */       this.cellNumOrProteinEntry.highlight(true);
/* 184 */       return -1.0D;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public double getProteinCountSum() {
/* 190 */     if (!CorrectOnPanel.correctOnProteinContent) return 0.0D;
/*     */     
/*     */     try {
/* 193 */       double value = Double.parseDouble(this.cellNumOrProteinEntry.getText());
/* 194 */       this.cellNumOrProteinEntry.highlight(false);
/* 195 */       return value;
/*     */     }
/* 197 */     catch (Exception e) {
/*     */       
/* 199 */       JOptionPane.showMessageDialog((Component)this, "Error with Protein Content.\nError: " + e.getMessage());
/* 200 */       this.cellNumOrProteinEntry.highlight(true);
/* 201 */       return -1.0D;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean correct(int[] numElements) {
/* 207 */     String MDV = this.MDVEntry.getText();
/*     */ 
/*     */     
/*     */     try {
/* 211 */       String[] s = MDV.split(" ");
/* 212 */       double[] peaks = new double[s.length];
/*     */       
/* 214 */       for (int i = 0; i < s.length; i++)
/*     */       {
/* 216 */         peaks[i] = Double.parseDouble(s[i]);
/*     */       }
/*     */       
/* 219 */       this.theReplicate.theMDV = new MDV(peaks);
/* 220 */       this.MDVEntry.highlight(false);
/*     */       
/* 222 */       this.theReplicate.name = this.nameEntry.getText();
/* 223 */       this.theReplicate.correct(numElements);
/*     */     
/*     */     }
/* 226 */     catch (Exception e) {
/*     */       
/* 228 */       JOptionPane.showMessageDialog((Component)this, "Error with MDV.\nError: " + e.getMessage());
/*     */       
/* 230 */       this.MDVEntry.highlight(true);
/*     */       
/* 232 */       return false;
/*     */     } 
/*     */     
/* 235 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\sdmcg\Documents\Lab\Programming\SITAquant\SITAquant2023\SITAquant-July2019.jar!\frontend\panels\ReplicatePanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */