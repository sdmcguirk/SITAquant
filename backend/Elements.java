/*    */ package backend;
/*    */ 
/*    */ 
/*    */ public class Elements
/*    */ {
/*  6 */   public static String[] names = new String[] { "carbon", "oxygen", "nitrogen", "hydrogen", "silicon", "sulfur" };
/*  7 */   public static int length = names.length;
/*    */ 
/*    */   
/*    */   public static boolean checkValid(int[] numElements) {
/* 11 */     boolean hasNonZeroValue = false; byte b; int i, arrayOfInt[];
/* 12 */     for (i = (arrayOfInt = numElements).length, b = 0; b < i; ) { int a = arrayOfInt[b];
/*    */       
/* 14 */       if (a < 0)
/*    */       {
/* 16 */         return false;
/*    */       }
/* 18 */       if (a > 0)
/*    */       {
/* 20 */         hasNonZeroValue = true; } 
/*    */       b++; }
/*    */     
/* 23 */     return hasNonZeroValue;
/*    */   }
/*    */ }


/* Location:              C:\Users\sdmcg\Documents\Lab\Programming\SITAquant\SITAquant2023\SITAquant-July2019.jar!\backend\Elements.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */