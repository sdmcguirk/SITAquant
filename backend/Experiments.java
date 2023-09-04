/*     */ package backend;
/*     */ 
/*     */ import frontend.absoluteflux.Replicate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Experiments
/*     */ {
/*  11 */   static int[][] experimentNumElements = new int[][] {
/*  12 */       { 20, 6, 41, 3
/*  13 */       }, { 11, 3, 25, 2
/*  14 */       }, { 6, 3, 1, 12, 1 }
/*     */     };
/*     */   
/*  17 */   static double[][] experimentPeaks = new double[][] {
/*  18 */       { 0.434816243D, 
/*  19 */         0.219845376D, 
/*  20 */         0.149478747D, 
/*  21 */         0.07200536D, 
/*  22 */         0.071550658D, 
/*  23 */         0.037679662D, 
/*  24 */         0.014623953D
/*     */       },
/*  26 */       { 0.713626062D, 
/*  27 */         0.184956739D, 
/*  28 */         0.08404657D, 
/*  29 */         0.01737063D
/*     */       },
/*     */       {
/*  32 */         0.849049459D, 
/*  33 */         0.105300516D, 
/*  34 */         0.038455628D, 
/*  35 */         0.007194397D
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  42 */   static double[][] experimentResults = new double[][] {
/*  43 */       { 0.696353445D, 
/*  44 */         0.090671874D, 
/*  45 */         0.081285933D, 
/*  46 */         0.037796218D, 
/*  47 */         0.074304077D, 
/*  48 */         0.019791551D, 
/*  49 */         -2.03099E-4D
/*     */       },
/*     */       {
/*  52 */         0.949690972D, 
/*  53 */         0.032871332D, 
/*  54 */         0.014162076D, 
/*  55 */         0.003275619D
/*     */       
/*     */       },
/*     */       {
/*  59 */         0.993154628D, 
/*  60 */         0.002134872D, 
/*  61 */         -3.94263E-4D, 
/*  62 */         0.005104763D
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */   
/*     */   public static void run() {
/*  69 */     boolean[] successfulExperiments = new boolean[experimentNumElements.length];
/*  70 */     Replicate theReplicate = new Replicate();
/*     */     
/*  72 */     for (int i = 0; i < experimentNumElements.length; i++) {
/*     */       
/*  74 */       System.out.println("\nStarting experiment " + i + "...");
/*     */       
/*  76 */       theReplicate.name = "Experiment " + i;
/*  77 */       theReplicate.theMDV = new MDV(experimentPeaks[i]);
/*     */       
/*  79 */       theReplicate.correct(experimentNumElements[i]);
/*     */       
/*  81 */       Output test = new Output(experimentResults[i]);
/*  82 */       successfulExperiments[i] = theReplicate.corrected.compare(test);
/*     */       
/*  84 */       if (successfulExperiments[i]) {
/*     */         
/*  86 */         System.out.println("\nExperiment success!");
/*     */       }
/*     */       else {
/*     */         
/*  90 */         System.out.println("\nExperiment failure!");
/*     */         
/*  92 */         double[] a = theReplicate.theMDV.toArray();
/*  93 */         double[] b = test.getMDV().toArray();
/*     */         
/*  95 */         for (int k = 0; k < a.length; k++)
/*     */         {
/*  97 */           System.out.println(String.valueOf(a[k]) + " : " + b[k] + " Difference: " + Math.abs(a[k] - b[k]));
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 103 */     System.out.println("\n======RESULTS======\n");
/*     */     
/* 105 */     int succSum = 0;
/* 106 */     for (int j = 0; j < successfulExperiments.length; j++) {
/*     */       
/* 108 */       if (successfulExperiments[j]) {
/* 109 */         succSum++;
/*     */       } else {
/* 111 */         System.out.println("Experiment #" + j + " failed!");
/*     */       } 
/*     */     } 
/* 114 */     System.out.println(String.valueOf(succSum) + " out of " + experimentNumElements.length + " experiments were successful.");
/*     */   }
/*     */ }


/* Location:              C:\Users\sdmcg\Documents\Lab\Programming\SITAquant\SITAquant2023\SITAquant-July2019.jar!\backend\Experiments.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */