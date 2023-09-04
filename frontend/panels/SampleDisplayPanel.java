/*     */ package frontend.panels;
/*     */ 
/*     */ import frontend.ColouredPanel;
/*     */ import frontend.absoluteflux.Sample;
/*     */ import frontend.fields.CMButton;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.ButtonGroup;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JRadioButton;
/*     */ 
/*     */ 
/*     */ public class SampleDisplayPanel
/*     */   extends ColouredPanel
/*     */   implements ActionListener
/*     */ {
/*  21 */   public ArrayList<SamplePanel> sdPanelArray = new ArrayList<>();
/*  22 */   ArrayList<CMButton> sdButtonArray = new ArrayList<>();
/*  23 */   ArrayList<JRadioButton> sdRadioArray = new ArrayList<>();
/*     */   
/*  25 */   CMButton addSampleButton = new CMButton("Add Sample");
/*  26 */   ButtonGroup sdRadioGroup = new ButtonGroup();
/*     */ 
/*     */   
/*     */   public SampleDisplayPanel() {
/*  30 */     setLayout(new BoxLayout((Container)this, 3));
/*     */     
/*  32 */     SamplePanel controlSDPanel = new SamplePanel(true, this);
/*  33 */     this.sdPanelArray.add(controlSDPanel);
/*     */     
/*  35 */     add((Component)controlSDPanel);
/*  36 */     this.addSampleButton.addTo(this, (Container)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/*  41 */     Object source = e.getSource();
/*  42 */     String actionCommand = e.getActionCommand();
/*     */     
/*  44 */     if (source == this.addSampleButton) {
/*     */       
/*  46 */       remove((Component)this.addSampleButton);
/*     */       
/*  48 */       boolean isControl = true;
/*  49 */       SamplePanel newSample = new SamplePanel(isControl, this);
/*  50 */       this.sdPanelArray.add(newSample);
/*  51 */       add((Component)newSample);
/*     */       
/*  53 */       add((Component)this.addSampleButton);
/*  54 */       revalidate();
/*     */     }
/*  56 */     else if (actionCommand.startsWith("RemoveSample")) {
/*     */       
/*  58 */       if (this.sdPanelArray.size() <= 1) {
/*     */         
/*  60 */         JOptionPane.showMessageDialog((Component)this, "Cannot remove last sample");
/*     */         
/*     */         return;
/*     */       } 
/*  64 */       String[] command = actionCommand.split(":");
/*     */       
/*  66 */       int option = JOptionPane.showConfirmDialog((Component)this, "Delete this sample?", "Remove Sample?", 0);
/*  67 */       if (option != 0) {
/*     */         return;
/*     */       }
/*  70 */       for (SamplePanel sp : this.sdPanelArray) {
/*     */         
/*  72 */         if (sp.getName().equals(command[1])) {
/*     */           
/*  74 */           this.sdPanelArray.remove(sp);
/*  75 */           remove((Component)sp);
/*     */ 
/*     */           
/*  78 */           if (sp.controlRadioButton.isSelected())
/*     */           {
/*  80 */             if (this.sdPanelArray.size() > 0)
/*     */             {
/*  82 */               ((SamplePanel)this.sdPanelArray.get(0)).controlRadioButton.setSelected(true);
/*     */             }
/*     */           }
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */       
/*  90 */       if (this.sdPanelArray.size() == 0)
/*     */       {
/*  92 */         this.sdRadioGroup = new ButtonGroup();
/*     */       }
/*     */       
/*  95 */       revalidate();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void addRadioButton(JRadioButton controlRadioButton) {
/* 101 */     this.sdRadioGroup.add(controlRadioButton);
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearSamples() {
/* 106 */     for (SamplePanel sp : this.sdPanelArray)
/*     */     {
/* 108 */       remove((Component)sp);
/*     */     }
/* 110 */     this.sdPanelArray.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void load(Sample[] sampleToLoad, ReplicatePanel[] replicToLoad, ArrayList<Object> exampleCorrectOn) {
/* 116 */     clearSamples();
/* 117 */     remove((Component)this.addSampleButton);
/*     */     
/* 119 */     int currRepCount = 0;
/*     */     
/* 121 */     for (int i = 0; i < sampleToLoad.length; i++) {
/*     */       
/* 123 */       SamplePanel newSample = new SamplePanel(true, this);
/* 124 */       newSample.setSample(sampleToLoad[i]);
/*     */       
/* 126 */       this.sdPanelArray.add(newSample);
/* 127 */       add((Component)newSample);
/*     */       
/* 129 */       ArrayList<String[]> replicateData = (ArrayList)new ArrayList<>();
/*     */       
/* 131 */       for (Object o : exampleCorrectOn) {
/*     */ 
/*     */         
/* 134 */         if (o instanceof String[]) {
/*     */           
/* 136 */           String[] replicateDataArray = (String[])o;
/*     */           
/* 138 */           if (replicateDataArray[0].equals(i))
/*     */           {
/* 140 */             replicateData.add(replicateDataArray);
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 147 */       newSample.RDP.addReplicates(replicToLoad, currRepCount, replicateData);
/*     */ 
/*     */       
/* 150 */       currRepCount += replicateData.size();
/*     */     } 
/*     */     
/* 153 */     ((SamplePanel)this.sdPanelArray.get(0)).controlRadioButton.setSelected(true);
/*     */     
/* 155 */     add((Component)this.addSampleButton);
/* 156 */     revalidate();
/*     */   }
/*     */ }


/* Location:              C:\Users\sdmcg\Documents\Lab\Programming\SITAquant\SITAquant2023\SITAquant-July2019.jar!\frontend\panels\SampleDisplayPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */