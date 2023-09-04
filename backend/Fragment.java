/*     */ package backend;
/*     */ 
/*     */ public class Fragment
/*     */ {
/*   5 */   public String fragmentName = "";
/*     */   
/*   7 */   private int[] numElements = new int[Elements.length];
/*     */   
/*     */   private MDV theMDV;
/*     */ 
/*     */   
/*     */   public int getLength() {
/*  13 */     return this.theMDV.length;
/*     */   }
/*     */ 
/*     */   
/*     */   public MDV getMDV() {
/*  18 */     return this.theMDV;
/*     */   }
/*     */   
/*     */   public void setMDV(double[] mdvArray) {
/*  22 */     this.theMDV = new MDV(mdvArray);
/*     */   }
/*     */   
/*     */   public void setMDV(double[][] mdvArray) {
/*  26 */     this.theMDV = new MDV(mdvArray);
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] getNumElements() {
/*  31 */     return this.numElements;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNumElements(int[] numElements) {
/*  36 */     this.numElements = numElements;
/*     */   }
/*     */   
/*     */   enum ErrorCode
/*     */   {
/*  41 */     NO_ERROR, UNINTIALIZED, INVALID_ELEMENTS, NO_PEAKS, SUM_NOT_ONE;
/*     */   }
/*     */   
/*  44 */   ErrorCode theErrorCode = ErrorCode.UNINTIALIZED;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  52 */     return this.fragmentName;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValid() {
/*  57 */     return (this.theErrorCode == ErrorCode.NO_ERROR);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getError() {
/*  62 */     switch (this.theErrorCode) {
/*     */       
/*     */       case NO_ERROR:
/*  65 */         return "No error detected.";
/*     */       case UNINTIALIZED:
/*  67 */         return "The data has not been intialized.";
/*     */       case null:
/*  69 */         return "The number of atoms entered is invalid.";
/*     */       case NO_PEAKS:
/*  71 */         return "No mass distributions were entered.";
/*     */       case SUM_NOT_ONE:
/*  73 */         return "The mass distributions do not sum to one.";
/*     */     } 
/*  75 */     return "Unknown error detected: " + this.theErrorCode.ordinal();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void printError() throws Exception {
/*  81 */     if (!isValid())
/*     */     {
/*  83 */       throw new Exception(getError());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void checkValid() {
/*  89 */     this.theErrorCode = ErrorCode.NO_ERROR;
/*     */     
/*  91 */     if (this.theMDV == null) {
/*     */       
/*  93 */       this.theErrorCode = ErrorCode.UNINTIALIZED;
/*     */       
/*     */       return;
/*     */     } 
/*  97 */     if (!Elements.checkValid(this.numElements)) {
/*     */       
/*  99 */       this.theErrorCode = ErrorCode.INVALID_ELEMENTS;
/*     */       
/*     */       return;
/*     */     } 
/* 103 */     double sum = this.theMDV.getSum();
/*     */     
/* 105 */     if (!Util.compare(sum, 1.0D, 1.0E-4D)) {
/*     */       
/* 107 */       System.out.printf("Sum: %2.20f\n", new Object[] { Double.valueOf(sum) });
/* 108 */       this.theErrorCode = ErrorCode.SUM_NOT_ONE;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void loadData(String fragmentName, int[] numElements, double[][] peaks) throws Exception {
/* 115 */     this.fragmentName = fragmentName;
/*     */     
/* 117 */     this.numElements = numElements;
/*     */     
/* 119 */     this.theMDV = new MDV(peaks);
/*     */     
/* 121 */     checkValid();
/*     */     
/* 123 */     printError();
/*     */   }
/*     */ }


/* Location:              C:\Users\sdmcg\Documents\Lab\Programming\SITAquant\SITAquant2023\SITAquant-July2019.jar!\backend\Fragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */