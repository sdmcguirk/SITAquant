/*     */ package frontend.LoadSave;
/*     */ 
/*     */ import backend.Fragment;
/*     */ import backend.Output;
/*     */ import frontend.ColourSettings;
/*     */ import frontend.ColouredPanel;
/*     */ import frontend.absoluteflux.Sample;
/*     */ import frontend.fields.CMButton;
/*     */ import frontend.fields.JLabelSelectable;
/*     */ import frontend.panels.FragmentPanel;
/*     */ import frontend.panels.ReplicatePanel;
/*     */ import frontend.panels.popup.DetailPanel;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.text.DateFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.DefaultListModel;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.event.ListSelectionEvent;
/*     */ import javax.swing.event.ListSelectionListener;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LoadSavePanel
/*     */   extends ColouredPanel
/*     */   implements ListSelectionListener, ActionListener
/*     */ {
/*     */   JList<Fragment> fragList;
/*     */   DefaultListModel<Fragment> model;
/*  39 */   JLabel status = new JLabel("");
/*  40 */   JLabel title = new JLabel("");
/*     */   
/*  42 */   CMButton examine = new CMButton("Examine Fragment");
/*     */   
/*  44 */   CMButton save = new CMButton("Save fragment");
/*  45 */   CMButton load = new CMButton("Load fragment");
/*  46 */   CMButton export = new CMButton("Export fragments");
/*     */   
/*     */   FragmentPanel fragmentPanel;
/*     */   
/*     */   Fragment[] exampleFragments;
/*     */   
/*     */   Sample[][] exampleSamples;
/*     */   
/*     */   ReplicatePanel[][] exampleReplicates;
/*     */   
/*     */   ArrayList<Object>[] exampleCorrectOn;
/*     */   
/*     */   public LoadSavePanel(FragmentPanel fragmentPanel) {
/*  59 */     this.fragmentPanel = fragmentPanel;
/*     */     
/*  61 */     ColourSettings.setSecondaryColours((Container)this);
/*     */ 
/*     */     
/*  64 */     setLayout(new BoxLayout((Container)this, 2));
/*  65 */     this.model = new DefaultListModel<>();
/*  66 */     this.fragList = new JList<>(this.model);
/*     */     
/*  68 */     this.fragList.setSelectionMode(0);
/*  69 */     this.fragList.setLayoutOrientation(1);
/*  70 */     this.fragList.setVisibleRowCount(1);
/*     */     
/*  72 */     this.fragList.setSelectedIndex(0);
/*  73 */     this.fragList.addListSelectionListener(this);
/*  74 */     this.fragList.setVisibleRowCount(5);
/*     */     
/*  76 */     this.exampleFragments = Examples.getSavedFragments(); byte b; int i; Fragment[] arrayOfFragment;
/*  77 */     for (i = (arrayOfFragment = this.exampleFragments).length, b = 0; b < i; ) { Fragment frag = arrayOfFragment[b];
/*     */       
/*  79 */       this.model.addElement(frag);
/*     */       b++; }
/*     */     
/*  82 */     this.exampleSamples = Examples.getSavedSamples();
/*  83 */     this.exampleReplicates = Examples.getSavedReplicates();
/*     */     
/*  85 */     this.exampleCorrectOn = Examples.getCorrectOn();
/*     */     
/*  87 */     add(this.title);
/*     */     
/*  89 */     add((Component)this.examine);
/*  90 */     add((Component)this.save);
/*  91 */     add((Component)this.load);
/*  92 */     add((Component)this.export);
/*  93 */     add(this.status);
/*     */ 
/*     */     
/*  96 */     this.save.setEnabled(false);
/*  97 */     this.export.setEnabled(false);
/*     */     
/*  99 */     this.examine.addActionListener(this);
/* 100 */     this.save.addActionListener(this);
/* 101 */     this.load.addActionListener(this);
/* 102 */     this.export.addActionListener(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(Fragment theFragment, Output theResult) {
/* 109 */     if (theFragment == null || theResult == null) {
/*     */       return;
/*     */     }
/* 112 */     for (int i = 0; i < this.model.size(); i++) {
/*     */       
/* 114 */       FragDisplay fg = null;
/* 115 */       if (fg.isSame(theFragment)) {
/*     */         
/* 117 */         fg.theFragment = theFragment;
/* 118 */         fg.theResult = theResult;
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void valueChanged(ListSelectionEvent evt) {
/* 128 */     int index = this.fragList.getSelectedIndex();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent evt) {
/* 138 */     Object source = evt.getSource();
/*     */     
/* 140 */     if (source == this.examine) {
/*     */       
/* 142 */       String fragmentName = this.fragmentPanel.getName();
/*     */       
/* 144 */       boolean valid = this.fragmentPanel.compute();
/*     */       
/* 146 */       if (!valid) {
/*     */         return;
/*     */       }
/* 149 */       DetailPanel DP = new DetailPanel();
/*     */       
/* 151 */       DP.show(fragmentName, (this.fragmentPanel.getSDP()).sdPanelArray);
/*     */     }
/* 153 */     else if (source == this.save) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 161 */       this.status.setText("Saved fragment");
/* 162 */       this.title.setText("List of saved fragments:");
/*     */     }
/* 164 */     else if (source == this.load) {
/*     */       
/* 166 */       loadFragment();
/*     */     }
/* 168 */     else if (source == this.export) {
/*     */       
/* 170 */       DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
/* 171 */       Date date = new Date();
/* 172 */       String header = "Fragments of " + dateFormat.format(date) + "\n\n";
/* 173 */       String result = header;
/*     */       
/* 175 */       for (int i = 0; i < this.model.size(); i++);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 182 */       if (this.model.size() == 0)
/*     */       {
/* 184 */         result = String.valueOf(result) + "There are no saved fragments.";
/*     */       }
/* 186 */       showSelectable(header, result);
/* 187 */       clearStatus();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearStatus() {
/* 193 */     this.status.setText("");
/*     */   }
/*     */ 
/*     */   
/*     */   public void showSelectable(String title, String input) {
/* 198 */     JPanel fragDisplayPanel = new JPanel();
/* 199 */     fragDisplayPanel.setLayout(new BoxLayout(fragDisplayPanel, 3));
/* 200 */     fragDisplayPanel.add((Component)new JLabelSelectable(input));
/* 201 */     Object[] options = { "OK" };
/*     */     
/* 203 */     JOptionPane.showOptionDialog((Component)this, fragDisplayPanel, title, 0, 1, null, options, options[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void loadFragment() {
/* 209 */     JPanel fragListPanel = new JPanel();
/* 210 */     fragListPanel.setLayout(new BoxLayout(fragListPanel, 3));
/* 211 */     fragListPanel.add(this.fragList);
/*     */     
/* 213 */     Object[] options = { "Load", "Cancel" };
/*     */     
/* 215 */     int selection = JOptionPane.showOptionDialog((Component)this, fragListPanel, "Choose a fragment", 0, 1, null, options, options[0]);
/*     */     
/* 217 */     if (selection != 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 222 */     int index = this.fragList.getSelectedIndex();
/* 223 */     if (index < 0) {
/*     */       
/* 225 */       JOptionPane.showMessageDialog((Component)this, "Please select a fragment to load.");
/*     */       
/*     */       return;
/*     */     } 
/* 229 */     Fragment fragToLoad = this.model.get(index);
/* 230 */     Sample[] sampleToLoad = this.exampleSamples[index];
/* 231 */     ReplicatePanel[] replicToLoad = this.exampleReplicates[index];
/*     */     
/* 233 */     ArrayList<Object> correctToLoad = this.exampleCorrectOn[index];
/* 234 */     this.fragmentPanel.load(fragToLoad, sampleToLoad, replicToLoad, correctToLoad);
/*     */   }
/*     */ }


/* Location:              C:\Users\sdmcg\Documents\Lab\Programming\SITAquant\SITAquant2023\SITAquant-July2019.jar!\frontend\LoadSave\LoadSavePanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */