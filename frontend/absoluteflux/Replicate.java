/*    */ package frontend.absoluteflux;
/*    */ 
/*    */ import backend.MDV;
/*    */ import backend.Output;
/*    */ import backend.Solver;
/*    */ 
/*    */ 
/*    */ public class Replicate
/*    */ {
/* 10 */   static int replicateNum = 0;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 19 */   public String name = "replicate" + replicateNum++;
/*    */   public MDV theMDV;
/*    */   public Output corrected;
/*    */   
/*    */   public void correct(int[] numElements) {
/* 24 */     MDV normalized = this.theMDV.normalize();
/* 25 */     this.corrected = Solver.run(normalized, numElements);
/*    */   }
/*    */ }


/* Location:              C:\Users\sdmcg\Documents\Lab\Programming\SITAquant\SITAquant2023\SITAquant-July2019.jar!\frontend\absoluteflux\Replicate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */