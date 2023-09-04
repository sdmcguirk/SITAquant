/*    */ package backend;
/*    */ 
/*    */ 
/*    */ public class Output
/*    */ {
/*    */   private MDV MDV;
/*  7 */   private Matrix[] mxArray = new Matrix[8];
/*    */ 
/*    */   
/*    */   public double fractionLabelled;
/*    */ 
/*    */   
/*    */   public Output(double[] experimentData) {
/* 14 */     setMDV(new MDV(experimentData));
/*    */   }
/*    */ 
/*    */   
/*    */   public Output() {}
/*    */ 
/*    */   
/*    */   public MDV getMDV() {
/* 22 */     return this.MDV;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMDV(MDV mdv) {
/* 27 */     this.MDV = mdv;
/* 28 */     this.MDV = this.MDV.normalize();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 33 */     return "Corrected MDV: \n" + this.MDV.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toFragDisplay() {
/* 38 */     String resultStr = "";
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 46 */     return resultStr;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean compare(Output validation) {
/* 52 */     double[] a = this.MDV.toArray();
/* 53 */     double[] b = validation.MDV.toArray();
/*    */     
/* 55 */     if (this.MDV.length != validation.MDV.length) {
/*    */       
/* 57 */       System.out.println("Validation data has wrong size!");
/* 58 */       return false;
/*    */     } 
/*    */     
/* 61 */     for (int i = 0; i < this.MDV.length; i++) {
/*    */       
/* 63 */       if (!Util.compare(a[i], b[i], 0.0D))
/*    */       {
/* 65 */         return false;
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 70 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCorrMx(int i) {
/* 75 */     return Util.toPrettyString(this.mxArray[i].get());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setResult(Matrix corrC, Matrix corrO, Matrix corrN, Matrix corrH, Matrix corrSi, Matrix corrS, Matrix corrTotal, Matrix corrInvert) {
/* 82 */     this.mxArray[0] = corrC; this.mxArray[1] = corrO; this.mxArray[2] = corrN;
/* 83 */     this.mxArray[3] = corrH; this.mxArray[4] = corrSi; this.mxArray[5] = corrS;
/* 84 */     this.mxArray[6] = corrTotal; this.mxArray[7] = corrInvert;
/*    */   }
/*    */ }


/* Location:              C:\Users\sdmcg\Documents\Lab\Programming\SITAquant\SITAquant2023\SITAquant-July2019.jar!\backend\Output.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */