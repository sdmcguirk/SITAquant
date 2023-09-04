/*    */ package frontend.LoadSave;
/*    */ 
/*    */ import backend.Fragment;
/*    */ import backend.Output;
/*    */ 
/*    */ 
/*    */ public class FragDisplay
/*    */ {
/*    */   public Fragment theFragment;
/*    */   public Output theResult;
/*    */   
/*    */   public FragDisplay(Fragment theFragment, Output theResult) {
/* 13 */     this.theFragment = theFragment;
/* 14 */     this.theResult = theResult;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 19 */     String result = "";
/* 20 */     result = String.valueOf(result) + this.theFragment.fragmentName;
/* 21 */     return result;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isSame(Fragment theFragment) {
/* 26 */     return (theFragment.fragmentName.compareTo(this.theFragment.fragmentName) == 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\sdmcg\Documents\Lab\Programming\SITAquant\SITAquant2023\SITAquant-July2019.jar!\frontend\LoadSave\FragDisplay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */