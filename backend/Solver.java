/*     */ package backend;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class Solver
/*     */ {
/*     */   public static final double c12 = 0.9893D;
/*     */   public static final double c13 = 0.0107D;
/*     */   
/*     */   public static Output run(MDV theMDV, int[] numElements) {
/*  11 */     printDetails(theMDV, numElements);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  16 */     Output returnOutput = new Output();
/*     */     
/*  18 */     Matrix CorrCarbon = getCorrMatrix(theMDV.length, numElements[0], 0.9893D, 0.0107D);
/*  19 */     Matrix CorrOxygen = getCorrMatrix(theMDV.length, numElements[1], o16, o17, o18);
/*  20 */     Matrix CorrNitrogen = getCorrMatrix(theMDV.length, numElements[2], n14, n15);
/*  21 */     Matrix CorrHydrogen = getCorrMatrix(theMDV.length, numElements[3], h1, h2);
/*  22 */     Matrix CorrSilicon = getCorrMatrix(theMDV.length, numElements[4], si28, si29, si30);
/*  23 */     Matrix CorrSulfur = getCorrMatrix(theMDV.length, numElements[5], s32, s33, s34);
/*     */ 
/*     */     
/*  26 */     Matrix corrTotal = CorrCarbon.times(CorrOxygen.times(CorrNitrogen.times(CorrHydrogen
/*  27 */             .times(CorrSilicon.times(CorrSulfur)))));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  35 */     Matrix mdvA = theMDV.getMatrix();
/*  36 */     Matrix corrInvert = Matrix.invert(corrTotal);
/*  37 */     Matrix mdvAcM = corrInvert.times(mdvA);
/*     */     
/*  39 */     returnOutput.setResult(CorrCarbon, CorrOxygen, CorrNitrogen, CorrHydrogen, CorrSilicon, CorrSulfur, corrTotal, corrInvert);
/*     */     
/*  41 */     returnOutput.setMDV(new MDV(mdvAcM));
/*     */     
/*  43 */     System.out.println("");
/*  44 */     System.out.println("This is the corrected MDV:");
/*  45 */     System.out.println(returnOutput.getMDV());
/*     */     
/*  47 */     return returnOutput;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void printDetails(MDV theMDV, int[] numElements) {
/*  52 */     System.out.println("The MDV has " + theMDV.length + " mass peaks and contains " + Util.printElements(numElements) + ".");
/*  53 */     System.out.println("The Mass Distribution Vector entered is:");
/*     */     
/*  55 */     theMDV.show();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void CorrectForUnlabelledFraction(Output theOutput, double dilutionRate, double time, int numC) {
/*  61 */     double[] mdv = theOutput.getMDV().toArray();
/*  62 */     double fractionUnlabelled = Math.exp(-dilutionRate * time);
/*     */     
/*  64 */     double[] mdvAA = new double[mdv.length];
/*  65 */     double[] mdvUnlabelled = new double[mdv.length];
/*     */     
/*     */     int i;
/*  68 */     for (i = 0; i < mdvUnlabelled.length; i++) {
/*     */       
/*  70 */       double fact1 = Util.getFactorial(numC);
/*  71 */       double fact2 = Util.getFactorial(numC - i);
/*  72 */       double fact3 = Util.getFactorial(i);
/*     */       
/*  74 */       mdvUnlabelled[i] = fact1 * 
/*  75 */         Math.pow(0.9893D, (numC - i)) / fact2 * 
/*  76 */         Math.pow(0.0107D, i) / fact3;
/*     */     } 
/*     */ 
/*     */     
/*  80 */     for (i = 0; i < mdvUnlabelled.length; i++) {
/*  81 */       mdvUnlabelled[i] = mdvUnlabelled[i] * fractionUnlabelled;
/*     */     }
/*  83 */     for (i = 0; i < mdvAA.length; i++) {
/*  84 */       mdvAA[i] = mdv[i] - mdvUnlabelled[i];
/*     */     }
/*  86 */     for (i = 0; i < mdvAA.length; i++) {
/*  87 */       mdvAA[i] = mdv[i] / (1.0D - fractionUnlabelled);
/*     */     }
/*     */     
/*  90 */     System.out.println("This is the final MDV:");
/*     */ 
/*     */     
/*  93 */     System.out.println(Util.toString(mdvAA));
/*     */ 
/*     */ 
/*     */     
/*  97 */     double fracTOP = 0.0D;
/*  98 */     double fracBOT = 0.0D; int j;
/*  99 */     for (j = 0; j < mdvAA.length; j++) {
/* 100 */       fracTOP += j * mdvAA[j];
/*     */     }
/* 102 */     for (j = 0; j < mdvAA.length; j++) {
/* 103 */       fracBOT += mdvAA[j];
/*     */     }
/* 105 */     fracBOT *= (mdvAA.length - 1);
/* 106 */     double fracL = fracTOP / fracBOT;
/* 107 */     fracL *= 100.0D;
/* 108 */     System.out.println("The fraction labelled was " + fracL + "%.");
/*     */     
/* 110 */     theOutput.fractionLabelled = fracL;
/*     */   }
/*     */ 
/*     */   
/*     */   private static Matrix getCorrMatrix(int length, int numOfElement, double element1, double element2, double element3) {
/* 115 */     if (numOfElement == 0)
/*     */     {
/* 117 */       return Matrix.identity(length);
/*     */     }
/*     */     
/* 120 */     double[][] mx = new double[length][length]; int i;
/* 121 */     for (i = 0; i < mx.length; i++) {
/*     */ 
/*     */       
/* 124 */       ArrayList<Double> combos = new ArrayList<>();
/* 125 */       double fact1 = Util.getFactorial(numOfElement);
/*     */ 
/*     */ 
/*     */       
/* 129 */       for (int iterate = 0; iterate < mx.length; iterate++) {
/* 130 */         int q0 = numOfElement - i + iterate;
/* 131 */         int q1 = i - 2 * iterate;
/* 132 */         int q2 = iterate;
/*     */         
/* 134 */         if (q0 >= 0 && q1 >= 0 && q2 >= 0 && q2 * 2 + q1 == i && 
/* 135 */           q0 + q1 + q2 == numOfElement) {
/* 136 */           double factq0 = Util.getFactorial(q0);
/* 137 */           double factq1 = Util.getFactorial(q1);
/* 138 */           double factq2 = Util.getFactorial(q2);
/* 139 */           double toAdd = Math.abs(Math.pow(element1, q0) / factq0 * 
/* 140 */               Math.pow(element2, q1) / factq1 * 
/* 141 */               Math.pow(element3, q2) / factq2);
/* 142 */           combos.add(Double.valueOf(toAdd));
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 147 */       double added = 0.0D;
/*     */       
/* 149 */       for (int arrayPosition = 0; arrayPosition < combos.size(); arrayPosition++) {
/* 150 */         added += ((Double)combos.get(arrayPosition)).doubleValue();
/*     */       }
/* 152 */       mx[i][0] = Math.abs(fact1 * added);
/*     */     } 
/*     */     
/* 155 */     for (i = 0; i < mx.length; i++) {
/* 156 */       for (int j = 0; j < mx.length; j++) {
/* 157 */         if (i > j && i > 0 && j > 0) {
/* 158 */           mx[i][j] = mx[i - 1][j - 1];
/*     */         }
/* 160 */         if (i == j && i > 0 && j > 0) {
/* 161 */           mx[i][j] = mx[0][0];
/*     */         }
/* 163 */         if (i < j) {
/* 164 */           mx[i][j] = 0.0D;
/*     */         }
/*     */       } 
/*     */     } 
/* 168 */     return new Matrix(mx);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Matrix getCorrMatrix(int length, int numOfElement, double element1, double element2) {
/* 173 */     if (numOfElement == 0)
/*     */     {
/* 175 */       return Matrix.identity(length);
/*     */     }
/*     */     
/* 178 */     double[][] mx = new double[length][length];
/*     */     int i;
/* 180 */     for (i = 0; i < length; i++) {
/* 181 */       double fact1 = Util.getFactorial(numOfElement);
/*     */ 
/*     */       
/* 184 */       int q0 = numOfElement - i;
/* 185 */       int q1 = i;
/* 186 */       if (q0 >= 0 && q1 >= 0 && q1 == i && q1 + q0 == numOfElement) {
/* 187 */         double factq0 = Util.getFactorial(q0);
/* 188 */         double factq1 = Util.getFactorial(q1);
/*     */         
/* 190 */         double toAdd = Math.abs(Math.pow(element1, q0) / factq0 * 
/* 191 */             Math.pow(element2, q1) / factq1);
/*     */ 
/*     */ 
/*     */         
/* 195 */         mx[i][0] = Math.abs(fact1 * toAdd);
/*     */       } 
/*     */     } 
/*     */     
/* 199 */     for (i = 0; i < length; i++) {
/* 200 */       for (int j = 0; j < length; j++) {
/* 201 */         if (i > j && i > 0 && j > 0) {
/* 202 */           mx[i][j] = mx[i - 1][j - 1];
/*     */         }
/* 204 */         if (i == j && i > 0 && j > 0) {
/* 205 */           mx[i][j] = mx[0][0];
/*     */         }
/* 207 */         if (i < j) {
/* 208 */           mx[i][j] = 0.0D;
/*     */         }
/*     */       } 
/*     */     } 
/* 212 */     return new Matrix(mx);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 218 */   static double h1 = 0.99985D;
/* 219 */   static double h2 = 1.5E-4D;
/* 220 */   static double n14 = 0.99632D;
/* 221 */   static double n15 = 0.00368D;
/* 222 */   static double o16 = 0.99757D;
/* 223 */   static double o17 = 3.8E-4D;
/* 224 */   static double o18 = 0.00205D;
/* 225 */   static double si28 = 0.922297D;
/* 226 */   static double si29 = 0.046832D;
/* 227 */   static double si30 = 0.030872D;
/* 228 */   static double s32 = 0.9502D;
/* 229 */   static double s33 = 0.0075D;
/* 230 */   static double s34 = 0.0421D;
/*     */ }


/* Location:              C:\Users\sdmcg\Documents\Lab\Programming\SITAquant\SITAquant2023\SITAquant-July2019.jar!\backend\Solver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */