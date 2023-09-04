/*    */ package frontend.absoluteflux;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class Sample
/*    */ {
/*  7 */   static int sampleNum = 0;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 27 */   public String name = "sample" + sampleNum++;
/*    */   
/*    */   public int InternalStandardAverage;
/*    */   
/*    */   public String getName() {
/* 32 */     return this.name;
/*    */   }
/*    */   
/*    */   public int CellNumberAverage;
/*    */   public int ProteinContentAverage;
/*    */   public ArrayList<double[]> proportionalMDVArrayList;
/*    */   public ArrayList<double[]> relativeMDVArrayList;
/*    */   public ArrayList<double[]> absoluteMDVArrayList;
/*    */   public ArrayList<String> replicateNames;
/*    */ }


/* Location:              C:\Users\sdmcg\Documents\Lab\Programming\SITAquant\SITAquant2023\SITAquant-July2019.jar!\frontend\absoluteflux\Sample.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */