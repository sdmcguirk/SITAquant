/*    */ package backend;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Statistics
/*    */ {
/*    */   public static double getMean(double[] data) {
/*  9 */     double sum = 0.0D; byte b; int i; double[] arrayOfDouble;
/* 10 */     for (i = (arrayOfDouble = data).length, b = 0; b < i; ) { double a = arrayOfDouble[b];
/* 11 */       sum += a; b++; }
/* 12 */      return sum / data.length;
/*    */   }
/*    */ 
/*    */   
/*    */   public static double getVariance(double[] data) {
/* 17 */     double mean = getMean(data);
/* 18 */     double temp = 0.0D; byte b; int i; double[] arrayOfDouble;
/* 19 */     for (i = (arrayOfDouble = data).length, b = 0; b < i; ) { double a = arrayOfDouble[b];
/* 20 */       temp += (mean - a) * (mean - a); b++; }
/* 21 */      return temp / (data.length - 1);
/*    */   }
/*    */ 
/*    */   
/*    */   public static double getStdDev(double[] data) {
/* 26 */     return Math.sqrt(getVariance(data));
/*    */   }
/*    */ 
/*    */   
/*    */   public static double getSEM(double[] data) {
/* 31 */     return getStdDev(data) / Math.sqrt(data.length);
/*    */   }
/*    */ }


/* Location:              C:\Users\sdmcg\Documents\Lab\Programming\SITAquant\SITAquant2023\SITAquant-July2019.jar!\backend\Statistics.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */