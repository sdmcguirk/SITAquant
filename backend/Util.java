/*     */ package backend;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Util
/*     */ {
/*     */   public static double getFactorial(int num) {
/*  12 */     double fact = 1.0D;
/*  13 */     for (int i = num; i > 1; i--) {
/*  14 */       fact *= i;
/*     */     }
/*  16 */     return fact;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String toString(double[] result) {
/*  21 */     return toString(result, " ");
/*     */   }
/*     */   
/*     */   public static String toString(double[] result, String delimiter) {
/*  25 */     String returnString = "";
/*  26 */     for (int i = 0; i < result.length; i++)
/*     */     {
/*  28 */       returnString = String.valueOf(returnString) + delimiter + result[i];
/*     */     }
/*  30 */     return returnString;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String toPrettyString(double[] result) {
/*  35 */     return toPrettyString(result, " ");
/*     */   }
/*     */ 
/*     */   
/*     */   public static String toPrettyString(double[] result, String delimiter) {
/*  40 */     String returnString = "";
/*  41 */     for (int i = 0; i < result.length; i++) {
/*     */       
/*  43 */       returnString = String.valueOf(returnString) + delimiter + String.format("%20.10f", new Object[] { Double.valueOf(result[i]) });
/*     */     } 
/*  45 */     return returnString;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String toPrettyString(double[][] result) {
/*  50 */     String returnString = "";
/*  51 */     for (int i = 0; i < result.length; i++) {
/*     */       
/*  53 */       for (int j = 0; j < (result[i]).length; j++) {
/*     */         
/*  55 */         returnString = String.valueOf(returnString) + String.format("%10.15f\t", new Object[] { Double.valueOf(result[i][j]) });
/*     */       } 
/*  57 */       returnString = String.valueOf(returnString) + "\n";
/*     */     } 
/*  59 */     return returnString;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void normalize(double[][] mdvArray) {
/*  64 */     double mdvSUM = 0.0D; int mdvCount;
/*  65 */     for (mdvCount = 0; mdvCount < mdvArray.length; mdvCount++) {
/*  66 */       mdvSUM += mdvArray[mdvCount][0];
/*     */     }
/*  68 */     if (mdvSUM != 1.0D && mdvSUM != 0.0D) {
/*  69 */       for (mdvCount = 0; mdvCount < mdvArray.length; mdvCount++) {
/*  70 */         mdvArray[mdvCount][0] = mdvArray[mdvCount][0] / mdvSUM;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean compare(double a, double b, double epsilon) {
/*  79 */     double value = Math.abs(a - b);
/*  80 */     return (value < epsilon);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String printElements(int[] numElements) {
/*  85 */     if (numElements.length != Elements.names.length) {
/*     */       
/*  87 */       System.out.println("ERROR: Util.printElements length mismatch!");
/*  88 */       return null;
/*     */     } 
/*     */     
/*  91 */     String out = "";
/*  92 */     for (int i = 0; i < numElements.length; i++) {
/*     */       
/*  94 */       if (i == numElements.length - 1) {
/*  95 */         out = String.valueOf(out) + "and ";
/*     */       }
/*  97 */       out = String.valueOf(out) + numElements[i] + " " + Elements.names[i];
/*  98 */       if (numElements[i] == 0 || numElements[i] > 1) {
/*     */         
/* 100 */         out = String.valueOf(out) + "s";
/* 101 */         if (i < numElements.length - 1)
/* 102 */           out = String.valueOf(out) + ", "; 
/*     */       } 
/*     */     } 
/* 105 */     return out;
/*     */   }
/*     */ 
/*     */   
/*     */   public static double[] multiply(double[] array, double scalar) {
/* 110 */     double[] newArray = new double[array.length];
/* 111 */     for (int i = 0; i < newArray.length; i++)
/*     */     {
/* 113 */       newArray[i] = array[i] * scalar;
/*     */     }
/* 115 */     return newArray;
/*     */   }
/*     */ 
/*     */   
/*     */   public static double[] subtract(double[] array1, double[] array2) {
/* 120 */     double[] newArray = new double[array1.length];
/*     */     
/* 122 */     if (array1.length != array2.length) {
/* 123 */       return newArray;
/*     */     }
/* 125 */     for (int i = 0; i < newArray.length; i++)
/*     */     {
/* 127 */       newArray[i] = array1[i] - array2[i];
/*     */     }
/* 129 */     return newArray;
/*     */   }
/*     */   
/*     */   public enum Operation
/*     */   {
/* 134 */     MEAN,
/* 135 */     STD_DEV,
/* 136 */     SEM;
/*     */   }
/*     */ 
/*     */   
/*     */   public static double[] doColumnwise(ArrayList<double[]> list, Operation op) {
/* 141 */     double[][] a = new double[list.size()][0];
/* 142 */     for (int i = 0; i < list.size(); i++)
/*     */     {
/* 144 */       a[i] = list.get(i);
/*     */     }
/* 146 */     return doColumnwise(a, op);
/*     */   }
/*     */ 
/*     */   
/*     */   static double[] doColumnwise(double[][] a, Operation op) {
/* 151 */     if (a.length == 0)
/*     */     {
/* 153 */       return new double[0];
/*     */     }
/*     */     
/* 156 */     double[] data = new double[(a[0]).length];
/* 157 */     for (int i = 0; i < data.length; i++) {
/*     */       
/* 159 */       double[] temp = new double[a.length];
/* 160 */       for (int j = 0; j < a.length; j++)
/*     */       {
/* 162 */         temp[j] = a[j][i];
/*     */       }
/*     */       
/* 165 */       if (op == Operation.MEAN) {
/* 166 */         data[i] = Statistics.getMean(temp);
/* 167 */       } else if (op == Operation.STD_DEV) {
/* 168 */         data[i] = Statistics.getStdDev(temp);
/* 169 */       } else if (op == Operation.SEM) {
/* 170 */         data[i] = Statistics.getSEM(temp);
/*     */       } else {
/* 172 */         System.out.println("Error: Unknown Operation!");
/*     */       } 
/* 174 */     }  return data;
/*     */   }
/*     */ }


/* Location:              C:\Users\sdmcg\Documents\Lab\Programming\SITAquant\SITAquant2023\SITAquant-July2019.jar!\backend\Util.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */