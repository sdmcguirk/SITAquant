/*     */ package frontend.absoluteflux;
/*     */ 
/*     */ import backend.Matrix;
/*     */ import backend.Solver;
/*     */ import backend.Util;
/*     */ import frontend.panels.CorrectOnPanel;
/*     */ import frontend.panels.ReplicateDisplayPanel;
/*     */ import frontend.panels.ReplicatePanel;
/*     */ import frontend.panels.SampleDisplayPanel;
/*     */ import frontend.panels.SamplePanel;
/*     */ import frontend.panels.popup.UnlabelledCorrectionPanel;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ public class AbsoluteFluxHandler
/*     */ {
/*     */   static int[] numElements;
/*  18 */   static double[] controlValues = null;
/*     */ 
/*     */   
/*     */   public static boolean computeAbsoluteFlux(int[] numElements, SampleDisplayPanel SDP) {
/*  22 */     AbsoluteFluxHandler.numElements = numElements;
/*     */     
/*  24 */     SamplePanel controlPanel = null;
/*  25 */     for (SamplePanel SP : SDP.sdPanelArray) {
/*     */ 
/*     */       
/*  28 */       for (ReplicatePanel RP : SP.RDP.RPArray) {
/*     */         
/*  30 */         boolean valid = RP.correct(numElements);
/*     */         
/*  32 */         if (!valid)
/*     */         {
/*  34 */           return false;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/*  39 */       if (SP.controlRadioButton.isSelected()) {
/*     */         
/*  41 */         if (controlPanel != null) {
/*     */           
/*  43 */           System.out.println("ERROR: Two controls detected!");
/*     */           break;
/*     */         } 
/*  46 */         controlPanel = SP;
/*     */       } 
/*     */     } 
/*     */     
/*  50 */     if (controlPanel == null) {
/*     */       
/*  52 */       System.out.println("Error: No Control!");
/*  53 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  58 */     controlValues = computeControl(controlPanel);
/*     */     
/*  60 */     if (controlValues == null) {
/*  61 */       return false;
/*     */     }
/*  63 */     for (SamplePanel SP : SDP.sdPanelArray) {
/*     */       
/*  65 */       for (ReplicatePanel RP : SP.RDP.RPArray)
/*     */       {
/*     */         
/*  68 */         RP.controlValues = controlValues;
/*     */       }
/*     */     } 
/*     */     
/*  72 */     for (SamplePanel SP : SDP.sdPanelArray)
/*     */     {
/*  74 */       computeAbsoluteFlux(SP.RDP, SP.theSample);
/*     */     }
/*     */     
/*  77 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private static double[] computeControl(SamplePanel SP) {
/*  82 */     double PreNormalizationMDVSumAvg = 0.0D;
/*  83 */     double InternalStandardAvg = 0.0D;
/*  84 */     double CellNumberAvg = 0.0D;
/*  85 */     double ProteinCountAvg = 0.0D;
/*     */ 
/*     */ 
/*     */     
/*  89 */     for (ReplicatePanel RP : SP.RDP.RPArray) {
/*     */       
/*  91 */       PreNormalizationMDVSumAvg += RP.theReplicate.theMDV.getSum();
/*  92 */       InternalStandardAvg += RP.getInternalStandardSum();
/*  93 */       CellNumberAvg += RP.getCellNumberSum();
/*  94 */       ProteinCountAvg += RP.getProteinCountSum();
/*     */ 
/*     */       
/*  97 */       if (RP.getInternalStandardSum() == -1.0D || RP.getCellNumberSum() == -1.0D || RP.getProteinCountSum() == -1.0D) {
/*     */         
/*  99 */         System.out.println("Error with Correcting values");
/* 100 */         return null;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 105 */     int size = SP.RDP.RPArray.size();
/* 106 */     if (size > 0) {
/*     */       
/* 108 */       PreNormalizationMDVSumAvg /= size;
/* 109 */       InternalStandardAvg /= size;
/* 110 */       CellNumberAvg /= size;
/* 111 */       ProteinCountAvg /= size;
/*     */     } 
/*     */ 
/*     */     
/* 115 */     double[] returnArray = new double[4];
/* 116 */     returnArray[0] = PreNormalizationMDVSumAvg;
/* 117 */     returnArray[1] = InternalStandardAvg;
/* 118 */     returnArray[2] = CellNumberAvg;
/* 119 */     returnArray[3] = ProteinCountAvg;
/*     */     
/* 121 */     return returnArray;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void computeAbsoluteFlux(ReplicateDisplayPanel RDP, Sample theSample) {
/* 128 */     theSample.proportionalMDVArrayList = (ArrayList)new ArrayList<>();
/* 129 */     theSample.absoluteMDVArrayList = (ArrayList)new ArrayList<>();
/* 130 */     theSample.relativeMDVArrayList = (ArrayList)new ArrayList<>();
/*     */     
/* 132 */     theSample.replicateNames = new ArrayList<>();
/*     */     
/* 134 */     for (ReplicatePanel RP : RDP.RPArray) {
/*     */       
/* 136 */       double[] propMDV = RP.theReplicate.corrected.getMDV().toArray();
/* 137 */       if (UnlabelledCorrectionPanel.percentageUnlabelled > 0.0F) {
/* 138 */         propMDV = correctForUnlabelledFraction(propMDV);
/*     */       }
/* 140 */       theSample.proportionalMDVArrayList.add(propMDV);
/*     */       
/* 142 */       double[] absoluteMDV = getAbsoluteMDV(RP);
/* 143 */       theSample.absoluteMDVArrayList.add(absoluteMDV);
/*     */       
/* 145 */       double[] relativeMDV = getRelativeMDV(RP);
/* 146 */       theSample.relativeMDVArrayList.add(relativeMDV);
/*     */       
/* 148 */       theSample.replicateNames.add(RP.theReplicate.name);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static double[] correctForUnlabelledFraction(double[] corrMDV) {
/* 154 */     float fractionUnlabelled = UnlabelledCorrectionPanel.percentageUnlabelled;
/*     */     
/* 156 */     Matrix unlabelledMatrix = Solver.getCorrMatrix(corrMDV.length, UnlabelledCorrectionPanel.numberOfCarbons, 0.9893D, 0.0107D);
/* 157 */     double[] unlabelledArray = unlabelledMatrix.transpose().get()[0];
/* 158 */     unlabelledArray = Util.multiply(unlabelledArray, fractionUnlabelled);
/*     */     
/* 160 */     corrMDV = Util.subtract(corrMDV, unlabelledArray);
/* 161 */     corrMDV = Util.multiply(corrMDV, (1.0F - fractionUnlabelled));
/* 162 */     return corrMDV;
/*     */   }
/*     */ 
/*     */   
/*     */   private static double getReplicateCorrFactor(ReplicatePanel RP) {
/* 167 */     double CorrFactor = RP.getInternalStandardSum();
/* 168 */     if (CorrectOnPanel.correctOnCellNumber) {
/* 169 */       CorrFactor *= RP.getCellNumberSum();
/* 170 */     } else if (CorrectOnPanel.correctOnProteinContent) {
/* 171 */       CorrFactor *= RP.getProteinCountSum();
/* 172 */     }  return CorrFactor;
/*     */   }
/*     */ 
/*     */   
/*     */   private static double getControlCorrFactor() {
/* 177 */     if (controlValues == null) {
/*     */       
/* 179 */       System.out.println("Error: Control values is null");
/* 180 */       return 0.0D;
/*     */     } 
/* 182 */     double ControlCorrFactor = controlValues[1];
/* 183 */     if (CorrectOnPanel.correctOnCellNumber) {
/* 184 */       ControlCorrFactor *= controlValues[2];
/* 185 */     } else if (CorrectOnPanel.correctOnProteinContent) {
/* 186 */       ControlCorrFactor *= controlValues[3];
/* 187 */     }  return ControlCorrFactor;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static double[] getAbsoluteMDV(ReplicatePanel RP) {
/* 193 */     double absoluteCorrFactor = RP.theReplicate.theMDV.getSum();
/* 194 */     absoluteCorrFactor *= controlValues[1];
/* 195 */     absoluteCorrFactor /= getReplicateCorrFactor(RP);
/* 196 */     return Util.multiply(RP.theReplicate.corrected.getMDV().toArray(), absoluteCorrFactor);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static double[] getRelativeMDV(ReplicatePanel RP) {
/* 202 */     double relativeCorrFactor = RP.theReplicate.theMDV.getSum() / controlValues[0];
/* 203 */     relativeCorrFactor *= getControlCorrFactor();
/* 204 */     relativeCorrFactor /= getReplicateCorrFactor(RP);
/* 205 */     return Util.multiply(RP.theReplicate.corrected.getMDV().toArray(), relativeCorrFactor);
/*     */   }
/*     */ }


/* Location:              C:\Users\sdmcg\Documents\Lab\Programming\SITAquant\SITAquant2023\SITAquant-July2019.jar!\frontend\absoluteflux\AbsoluteFluxHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */