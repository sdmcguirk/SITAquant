/*     */ package frontend.LoadSave;
/*     */ 
/*     */ import backend.Fragment;
/*     */ import frontend.absoluteflux.Sample;
/*     */ import frontend.panels.ReplicatePanel;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Examples
/*     */ {
/*     */   public static Fragment[] getSavedFragments() {
/*  14 */     Fragment[] fragments = new Fragment[1];
/*  15 */     for (int i = 0; i < 1; i++) {
/*     */       
/*  17 */       Fragment fragment = new Fragment();
/*  18 */       fragment.fragmentName = "Example";
/*     */       
/*  20 */       int[] numElements = { 6, 3, 1, 12, 1 };
/*  21 */       fragment.setNumElements(numElements);
/*     */       
/*  23 */       fragments[i] = fragment;
/*     */     } 
/*  25 */     return fragments;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Sample[][] getSavedSamples() {
/*  30 */     Sample[][] exampleSamples = new Sample[1][3];
/*  31 */     exampleSamples[0][0] = new Sample();
/*  32 */     (exampleSamples[0][0]).name = "Sample #0";
/*     */     
/*  34 */     exampleSamples[0][1] = new Sample();
/*  35 */     (exampleSamples[0][1]).name = "Sample #1";
/*     */     
/*  37 */     exampleSamples[0][2] = new Sample();
/*  38 */     (exampleSamples[0][2]).name = "Sample #2";
/*     */     
/*  40 */     return exampleSamples;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ReplicatePanel[][] getSavedReplicates() {
/*  45 */     ReplicatePanel[][] exampleReplicates = new ReplicatePanel[1][9];
/*  46 */     exampleReplicates[0][0] = new ReplicatePanel(null);
/*  47 */     exampleReplicates[0][0].setName("Replicate # 0");
/*     */     
/*  49 */     exampleReplicates[0][1] = new ReplicatePanel(null);
/*  50 */     exampleReplicates[0][1].setName("Replicate # 1");
/*     */     
/*  52 */     exampleReplicates[0][2] = new ReplicatePanel(null);
/*  53 */     exampleReplicates[0][2].setName("Replicate # 2");
/*     */     
/*  55 */     exampleReplicates[0][3] = new ReplicatePanel(null);
/*  56 */     exampleReplicates[0][3].setName("Replicate # 3");
/*     */     
/*  58 */     exampleReplicates[0][4] = new ReplicatePanel(null);
/*  59 */     exampleReplicates[0][4].setName("Replicate # 4");
/*     */     
/*  61 */     exampleReplicates[0][5] = new ReplicatePanel(null);
/*  62 */     exampleReplicates[0][5].setName("Replicate # 5");
/*     */     
/*  64 */     exampleReplicates[0][6] = new ReplicatePanel(null);
/*  65 */     exampleReplicates[0][6].setName("Replicate # 6");
/*     */     
/*  67 */     exampleReplicates[0][7] = new ReplicatePanel(null);
/*  68 */     exampleReplicates[0][7].setName("Replicate # 7");
/*     */     
/*  70 */     exampleReplicates[0][8] = new ReplicatePanel(null);
/*  71 */     exampleReplicates[0][8].setName("Replicate # 8");
/*     */     
/*  73 */     return exampleReplicates;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<Object>[] getCorrectOn() {
/*  79 */     ArrayList[] correctOn = new ArrayList[1];
/*     */     
/*  81 */     ArrayList<Object> firstCorrectOn = new ArrayList();
/*     */     
/*  83 */     firstCorrectOn.add("Internal Standard + Cell Number");
/*  84 */     firstCorrectOn.add("per million cell/ug protein");
/*     */ 
/*     */ 
/*     */     
/*  88 */     String[] replicate1 = { "0", "973312", "0.285", "6012 17 10 6233" };
/*  89 */     firstCorrectOn.add(replicate1);
/*     */     
/*  91 */     String[] replicate2 = { "0", "1110886", "0.285", "10585 836 13 8074" };
/*  92 */     firstCorrectOn.add(replicate2);
/*     */     
/*  94 */     String[] replicate3 = { "0", "1257934", "0.285", "6796 19 11 7041" };
/*  95 */     firstCorrectOn.add(replicate3);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 101 */     String[] replicate4 = { "1", "677942", "0.325", "12699 1036 1126 6516" };
/* 102 */     firstCorrectOn.add(replicate4);
/*     */     
/* 104 */     String[] replicate5 = { "1", "1051940", "0.325", "13311 2125 1174 6260" };
/* 105 */     firstCorrectOn.add(replicate5);
/*     */     
/* 107 */     String[] replicate6 = { "1", "1092388", "0.325", "13775 1218 1429 7757" };
/* 108 */     firstCorrectOn.add(replicate6);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 113 */     String[] replicate7 = { "2", "850893", "0.422", "21293 193 29 12760" };
/* 114 */     firstCorrectOn.add(replicate7);
/*     */     
/* 116 */     String[] replicate8 = { "2", "882880", "0.422", "36835 4605 2326 17138" };
/* 117 */     firstCorrectOn.add(replicate8);
/*     */     
/* 119 */     String[] replicate9 = { "2", "1048613", "0.422", "22768 3296 21 13454" };
/* 120 */     firstCorrectOn.add(replicate9);
/*     */ 
/*     */     
/* 123 */     correctOn[0] = firstCorrectOn;
/*     */     
/* 125 */     return (ArrayList<Object>[])correctOn;
/*     */   }
/*     */ }


/* Location:              C:\Users\sdmcg\Documents\Lab\Programming\SITAquant\SITAquant2023\SITAquant-July2019.jar!\frontend\LoadSave\Examples.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */