/*    */ package backend;
/*    */ 
/*    */ 
/*    */ public class MDV
/*    */ {
/*    */   Matrix theMDVMatrix;
/*    */   public int length;
/*    */   
/*    */   public MDV(double[] peaks) {
/* 10 */     double[][] data = new double[peaks.length][1];
/*    */     
/* 12 */     for (int i = 0; i < peaks.length; i++)
/*    */     {
/* 14 */       data[i][0] = peaks[i];
/*    */     }
/*    */     
/* 17 */     this.theMDVMatrix = new Matrix(data);
/* 18 */     this.length = peaks.length;
/*    */   }
/*    */ 
/*    */   
/*    */   public MDV(double[][] data) {
/* 23 */     this.theMDVMatrix = new Matrix(data);
/* 24 */     this.length = data.length;
/*    */   }
/*    */ 
/*    */   
/*    */   public MDV(Matrix m) {
/* 29 */     this.theMDVMatrix = m;
/* 30 */     this.length = (m.get()).length;
/*    */   }
/*    */ 
/*    */   
/*    */   public void show() {
/* 35 */     System.out.println(toString());
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 40 */     return this.theMDVMatrix.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public MDV normalize() {
/* 45 */     return new MDV(Matrix.normalize(this.theMDVMatrix));
/*    */   }
/*    */ 
/*    */   
/*    */   public double[] toArray() {
/* 50 */     double[][] oldMDV = this.theMDVMatrix.get();
/* 51 */     double[] arrayOfDouble = new double[oldMDV.length];
/* 52 */     for (int i = 0; i < arrayOfDouble.length; i++)
/*    */     {
/* 54 */       arrayOfDouble[i] = oldMDV[i][0];
/*    */     }
/* 56 */     return arrayOfDouble;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public double getSum() {
/* 66 */     double[][] data = this.theMDVMatrix.get();
/* 67 */     double sum = 0.0D; byte b; int i; double[][] arrayOfDouble1;
/* 68 */     for (i = (arrayOfDouble1 = data).length, b = 0; b < i; ) { double[] arrayOfDouble = arrayOfDouble1[b];
/*    */       
/* 70 */       sum += arrayOfDouble[0]; b++; }
/*    */     
/* 72 */     return sum;
/*    */   }
/*    */ 
/*    */   
/*    */   public Matrix getMatrix() {
/* 77 */     return this.theMDVMatrix;
/*    */   }
/*    */ }


/* Location:              C:\Users\sdmcg\Documents\Lab\Programming\SITAquant\SITAquant2023\SITAquant-July2019.jar!\backend\MDV.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */