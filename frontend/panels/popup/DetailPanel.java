/*     */ package frontend.panels.popup;
/*     */ 
/*     */ import backend.Statistics;
/*     */ import backend.Util;
/*     */ import frontend.absoluteflux.Sample;
/*     */ import frontend.fields.JLabelSelectable;
/*     */ import frontend.panels.SamplePanel;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.GridLayout;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTabbedPane;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DetailPanel
/*     */   extends JPanel
/*     */ {
/*  23 */   JTabbedPane TPane = new JTabbedPane();
/*     */   
/*     */   JPanel propPanel;
/*     */   
/*     */   JPanel absolPanel;
/*     */   
/*     */   JPanel relativePanel;
/*     */   JPanel fractionalPanel;
/*     */   String propString;
/*     */   String absolString;
/*     */   String relativeString;
/*     */   String fractionalString;
/*  35 */   String delimiter = ";";
/*     */ 
/*     */   
/*     */   public DetailPanel() {
/*  39 */     setLayout(new GridLayout(1, 1));
/*  40 */     setPreferredSize(new Dimension(800, 600));
/*     */     
/*  42 */     add(this.TPane);
/*     */   }
/*     */ 
/*     */   
/*     */   public void show(String fragmentName, ArrayList<SamplePanel> sdPanelArray) {
/*  47 */     String prefix = String.valueOf(String.format("%18s", new Object[] { "Fragment Name" })) + this.delimiter + String.format("%20s", new Object[] { fragmentName });
/*     */     
/*  49 */     this.propString = prefix;
/*  50 */     this.absolString = prefix;
/*  51 */     this.relativeString = prefix;
/*  52 */     this.fractionalString = prefix;
/*     */     
/*  54 */     init();
/*     */     
/*  56 */     for (SamplePanel SP : sdPanelArray) {
/*     */       
/*  58 */       SP.theSample.name = SP.nameEntry.getText();
/*  59 */       boolean isControl = SP.controlRadioButton.isSelected();
/*  60 */       addSample(SP.theSample, isControl);
/*     */     } 
/*     */     
/*  63 */     showDetails();
/*     */   }
/*     */ 
/*     */   
/*     */   public void show(Sample theSample) {
/*  68 */     this.propString = "";
/*  69 */     this.absolString = "";
/*  70 */     this.relativeString = "";
/*  71 */     this.fractionalString = "";
/*     */     
/*  73 */     init();
/*     */     
/*  75 */     addSample(theSample, false);
/*     */     
/*  77 */     showDetails();
/*     */   }
/*     */ 
/*     */   
/*     */   private void addSample(Sample theSample, boolean isControl) {
/*  82 */     String sampleNameString = "\n\n" + String.format("%18s", new Object[] { "Sample Name" }) + this.delimiter + String.format("%20s", new Object[] { theSample.name });
/*  83 */     if (isControl)
/*     */     {
/*  85 */       sampleNameString = String.valueOf(sampleNameString) + this.delimiter + String.format("%18s", new Object[] { "(Control)" });
/*     */     }
/*  87 */     sampleNameString = String.valueOf(sampleNameString) + "\n";
/*     */     
/*  89 */     this.propString = String.valueOf(this.propString) + sampleNameString;
/*  90 */     this.absolString = String.valueOf(this.absolString) + sampleNameString;
/*  91 */     this.relativeString = String.valueOf(this.relativeString) + sampleNameString;
/*  92 */     this.fractionalString = String.valueOf(this.fractionalString) + sampleNameString;
/*     */     
/*  94 */     double[] fractionalArray = new double[theSample.proportionalMDVArrayList.size()];
/*     */     
/*  96 */     for (int i = 0; i < theSample.proportionalMDVArrayList.size(); i++) {
/*     */       
/*  98 */       String replicateName = String.format("%18s", new Object[] { theSample.replicateNames.get(i) });
/*  99 */       this.propString = String.valueOf(this.propString) + "\n" + replicateName + Util.toPrettyString(theSample.proportionalMDVArrayList.get(i), this.delimiter);
/* 100 */       this.absolString = String.valueOf(this.absolString) + "\n" + replicateName + Util.toPrettyString(theSample.absoluteMDVArrayList.get(i), this.delimiter);
/* 101 */       this.relativeString = String.valueOf(this.relativeString) + "\n" + replicateName + Util.toPrettyString(theSample.relativeMDVArrayList.get(i), this.delimiter);
/*     */       
/* 103 */       double fractionalAmount = getFractionLabelled(theSample.proportionalMDVArrayList.get(i));
/* 104 */       fractionalArray[i] = fractionalAmount;
/* 105 */       this.fractionalString = String.valueOf(this.fractionalString) + "\n" + replicateName + this.delimiter + String.format("%20.10f", new Object[] { Double.valueOf(fractionalAmount) });
/*     */     } 
/*     */     
/* 108 */     this.propString = String.valueOf(this.propString) + detailsToString("Proportional", theSample.proportionalMDVArrayList);
/* 109 */     this.absolString = String.valueOf(this.absolString) + detailsToString("Absolute", theSample.absoluteMDVArrayList);
/* 110 */     this.relativeString = String.valueOf(this.relativeString) + detailsToString("Relative", theSample.relativeMDVArrayList);
/*     */     
/* 112 */     this.fractionalString = String.valueOf(this.fractionalString) + "\n";
/* 113 */     this.fractionalString = String.valueOf(this.fractionalString) + "\n" + String.format("%18s", new Object[] { "Average" }) + this.delimiter + String.format("%20.10f", new Object[] { Double.valueOf(Statistics.getMean(fractionalArray)) });
/* 114 */     this.fractionalString = String.valueOf(this.fractionalString) + "\n" + String.format("%18s", new Object[] { "StdDev" }) + this.delimiter + String.format("%20.10f", new Object[] { Double.valueOf(Statistics.getStdDev(fractionalArray)) });
/* 115 */     this.fractionalString = String.valueOf(this.fractionalString) + "\n" + String.format("%18s", new Object[] { "SEM" }) + this.delimiter + String.format("%20.10f", new Object[] { Double.valueOf(Statistics.getSEM(fractionalArray)) });
/*     */     
/* 117 */     String postfixString = "\n";
/* 118 */     this.propString = String.valueOf(this.propString) + postfixString;
/* 119 */     this.absolString = String.valueOf(this.absolString) + postfixString;
/* 120 */     this.relativeString = String.valueOf(this.relativeString) + postfixString;
/*     */   }
/*     */ 
/*     */   
/*     */   private double getFractionLabelled(double[] mdv) {
/* 125 */     if (mdv.length == 0) {
/* 126 */       return 0.0D;
/*     */     }
/* 128 */     double num = 0.0D;
/* 129 */     for (int i = 0; i < mdv.length; i++)
/*     */     {
/* 131 */       num += i * mdv[i];
/*     */     }
/*     */     
/* 134 */     return num / mdv.length;
/*     */   }
/*     */ 
/*     */   
/*     */   private String detailsToString(String name, ArrayList<double[]> list) {
/* 139 */     String details = "\n";
/* 140 */     double[] means = Util.doColumnwise(list, Util.Operation.MEAN);
/* 141 */     double[] StdDev = Util.doColumnwise(list, Util.Operation.STD_DEV);
/* 142 */     double[] SEM = Util.doColumnwise(list, Util.Operation.SEM);
/*     */     
/* 144 */     details = String.valueOf(details) + "\n" + String.format("%18s", new Object[] { "Average" }) + Util.toPrettyString(means, this.delimiter);
/* 145 */     details = String.valueOf(details) + "\n" + String.format("%18s", new Object[] { "StdDev" }) + Util.toPrettyString(StdDev, this.delimiter);
/* 146 */     details = String.valueOf(details) + "\n" + String.format("%18s", new Object[] { "SEM" }) + Util.toPrettyString(SEM, this.delimiter);
/*     */     
/* 148 */     return details;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init() {
/* 155 */     this.TPane.removeAll();
/*     */     
/* 157 */     this.propPanel = new JPanel();
/* 158 */     this.absolPanel = new JPanel();
/* 159 */     this.relativePanel = new JPanel();
/* 160 */     this.fractionalPanel = new JPanel();
/*     */     
/* 162 */     this.TPane.addTab("Proportional MDVs", this.propPanel);
/* 163 */     this.TPane.addTab("Relative MDVs", this.relativePanel);
/* 164 */     this.TPane.addTab("Absolute MDVs", this.absolPanel);
/* 165 */     this.TPane.addTab("Fractional Labelling", this.fractionalPanel);
/* 166 */     this.TPane.setTabLayoutPolicy(1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void showDetails() {
/* 176 */     this.propString = (new StringBuilder(String.valueOf(this.propString))).toString();
/* 177 */     this.absolString = (new StringBuilder(String.valueOf(this.absolString))).toString();
/* 178 */     this.relativeString = (new StringBuilder(String.valueOf(this.relativeString))).toString();
/* 179 */     this.fractionalString = (new StringBuilder(String.valueOf(this.fractionalString))).toString();
/*     */ 
/*     */     
/* 182 */     this.propPanel.add((Component)createLabel(this.propString));
/* 183 */     this.absolPanel.add((Component)createLabel(this.absolString));
/* 184 */     this.relativePanel.add((Component)createLabel(this.relativeString));
/* 185 */     this.fractionalPanel.add((Component)createLabel(this.fractionalString));
/*     */     
/* 187 */     Object[] options = { "OK" };
/*     */     
/* 189 */     JOptionPane.showOptionDialog(this, this, 
/* 190 */         "Sample Details", 0, 
/* 191 */         1, null, options, options[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public JLabelSelectable createLabel(String theString) {
/* 196 */     JLabelSelectable theLabel = new JLabelSelectable(theString);
/* 197 */     theLabel.setSize(getPreferredSize());
/* 198 */     theLabel.setMonospace();
/* 199 */     return theLabel;
/*     */   }
/*     */ }


/* Location:              C:\Users\sdmcg\Documents\Lab\Programming\SITAquant\SITAquant2023\SITAquant-July2019.jar!\frontend\panels\popup\DetailPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */