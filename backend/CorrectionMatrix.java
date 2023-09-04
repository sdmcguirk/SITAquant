/*    */ package backend;
/*    */ 
/*    */ import java.util.Scanner;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CorrectionMatrix
/*    */ {
/*    */   public static void main(String[] args) {
/* 10 */     boolean doingExperiments = true;
/* 11 */     String copyright = "Thank you for using CorrectionMatrix! © Shawn McGuirk 2014";
/*    */     
/* 13 */     if (doingExperiments) {
/*    */       
/* 15 */       Experiments.run();
/* 16 */       System.out.println(copyright);
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 21 */     Fragment theFragment = getUserInput();
/*    */ 
/*    */     
/* 24 */     Solver.run(theFragment.getMDV(), theFragment.getNumElements());
/* 25 */     System.out.println(copyright);
/*    */   }
/*    */ 
/*    */   
/*    */   public static Fragment getUserInput() {
/* 30 */     Scanner keyboard = new Scanner(System.in);
/*    */ 
/*    */     
/* 33 */     Fragment theFragment = new Fragment();
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     do {
/* 39 */       System.out.print("Name your fragment: ");
/* 40 */       String fragmentName = keyboard.nextLine();
/*    */       
/* 42 */       System.out.println("In the fragment to be analyzed,");
/*    */       
/* 44 */       int[] numElements = new int[Elements.length];
/* 45 */       for (int i = 0; i < Elements.names.length; i++) {
/*    */         
/* 47 */         System.out.print("How many " + Elements.names[i] + "s? ");
/* 48 */         numElements[i] = keyboard.nextInt();
/*    */       } 
/*    */       
/* 51 */       System.out.print("How many mass peaks? ");
/* 52 */       int numPeaks = keyboard.nextInt();
/*    */       
/* 54 */       double[][] mdvArray = new double[numPeaks][1];
/*    */       
/* 56 */       for (int peakASK = 0; peakASK < mdvArray.length; peakASK++) {
/*    */ 
/*    */         
/* 59 */         System.out.println("What is the mass distribution of peak \"m+" + 
/* 60 */             peakASK + "\"?");
/* 61 */         mdvArray[peakASK][0] = keyboard.nextDouble();
/*    */       } 
/*    */ 
/*    */       
/*    */       try {
/* 66 */         theFragment.loadData(fragmentName, numElements, mdvArray);
/* 67 */       } catch (Exception e) {
/*    */         
/* 69 */         System.out.println(theFragment.getError());
/*    */       } 
/*    */       
/* 72 */       theFragment.checkValid();
/*    */     }
/* 74 */     while (!theFragment.isValid());
/*    */     
/* 76 */     keyboard.close();
/*    */     
/* 78 */     return theFragment;
/*    */   }
/*    */ }


/* Location:              C:\Users\sdmcg\Documents\Lab\Programming\SITAquant\SITAquant2023\SITAquant-July2019.jar!\backend\CorrectionMatrix.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */