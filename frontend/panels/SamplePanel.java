/*     */ package frontend.panels;
/*     */ 
/*     */ import frontend.ColourSettings;
/*     */ import frontend.ColouredPanel;
/*     */ import frontend.absoluteflux.Sample;
/*     */ import frontend.fields.CMButton;
/*     */ import frontend.fields.TextEntry;
/*     */ import frontend.panels.popup.DetailPanel;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JRadioButton;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SamplePanel
/*     */   extends ColouredPanel
/*     */   implements ActionListener
/*     */ {
/*     */   public static FragmentPanel examineListener;
/*     */   public TextEntry nameEntry;
/*  28 */   public Sample theSample = null;
/*     */ 
/*     */   
/*     */   public JRadioButton controlRadioButton;
/*     */   
/*  33 */   CMButton removeSample = new CMButton("Remove Sample");
/*  34 */   CMButton addReplicate = new CMButton("Add Replicate");
/*  35 */   CMButton examineSample = new CMButton("Examine Sample");
/*     */   
/*  37 */   GridBagConstraints gbCon = new GridBagConstraints();
/*     */   
/*     */   public ReplicateDisplayPanel RDP;
/*     */ 
/*     */   
/*     */   public SamplePanel(boolean isControl, SampleDisplayPanel sampleDisplayPanel) {
/*  43 */     setLayout(new GridBagLayout());
/*  44 */     setBorder(BorderFactory.createLineBorder(Color.black));
/*     */     
/*  46 */     this.theSample = new Sample();
/*  47 */     this.nameEntry = new TextEntry("Sample name: ", this.theSample.getName());
/*  48 */     ColourSettings.setColours((Container)this.nameEntry);
/*  49 */     this.controlRadioButton = new JRadioButton("Make Control Sample", isControl);
/*  50 */     ColourSettings.setColours(this.controlRadioButton);
/*     */     
/*  52 */     this.gbCon.fill = 2;
/*  53 */     this.gbCon.gridx = 0;
/*  54 */     this.gbCon.gridy = 0;
/*  55 */     this.gbCon.gridwidth = 2;
/*  56 */     add((Component)this.nameEntry, this.gbCon);
/*     */ 
/*     */     
/*  59 */     this.gbCon.gridx = 2;
/*  60 */     this.gbCon.gridy = 0;
/*  61 */     this.gbCon.gridwidth = 1;
/*  62 */     this.examineSample.addTo(this, (Container)this, this.gbCon);
/*     */ 
/*     */ 
/*     */     
/*  66 */     this.gbCon.gridx = 0;
/*  67 */     this.gbCon.gridy = 1;
/*  68 */     add(this.controlRadioButton, this.gbCon);
/*  69 */     sampleDisplayPanel.addRadioButton(this.controlRadioButton);
/*     */     
/*  71 */     this.gbCon.gridx = 1;
/*  72 */     this.gbCon.gridy = 1;
/*  73 */     this.removeSample.addTo(sampleDisplayPanel, (Container)this, this.gbCon);
/*  74 */     this.removeSample.setActionCommand("RemoveSample:" + getName());
/*     */     
/*  76 */     this.gbCon.gridx = 2;
/*  77 */     this.gbCon.gridy = 1;
/*  78 */     this.addReplicate.addTo(this, (Container)this, this.gbCon);
/*     */     
/*  80 */     this.gbCon.fill = 2;
/*  81 */     this.gbCon.gridx = 0;
/*  82 */     this.gbCon.gridy = 2;
/*  83 */     this.gbCon.gridwidth = 3;
/*  84 */     this.RDP = new ReplicateDisplayPanel();
/*  85 */     this.RDP.addReplicatePanel();
/*  86 */     add((Component)this.RDP, this.gbCon);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSample(Sample s) {
/*  91 */     this.theSample = s;
/*  92 */     this.nameEntry.setText(s.getName());
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  97 */     if (this.theSample == null)
/*     */     {
/*  99 */       return "";
/*     */     }
/* 101 */     return this.theSample.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/* 106 */     if (e.getSource() == this.addReplicate) {
/*     */       
/* 108 */       this.RDP.addReplicatePanel();
/*     */       
/* 110 */       revalidate();
/*     */     }
/* 112 */     else if (e.getSource() == this.examineSample) {
/*     */       
/* 114 */       boolean valid = examineListener.compute();
/*     */       
/* 116 */       if (!valid) {
/*     */         return;
/*     */       }
/* 119 */       this.theSample.name = this.nameEntry.getText();
/* 120 */       DetailPanel DP = new DetailPanel();
/* 121 */       DP.show(this.theSample);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\sdmcg\Documents\Lab\Programming\SITAquant\SITAquant2023\SITAquant-July2019.jar!\frontend\panels\SamplePanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */