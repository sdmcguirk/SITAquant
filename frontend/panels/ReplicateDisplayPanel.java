/*    */ package frontend.panels;
/*    */ 
/*    */ import frontend.ColouredPanel;
/*    */ import java.awt.Component;
/*    */ import java.awt.Container;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import java.util.ArrayList;
/*    */ import javax.swing.BoxLayout;
/*    */ import javax.swing.JOptionPane;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ReplicateDisplayPanel
/*    */   extends ColouredPanel
/*    */   implements ActionListener
/*    */ {
/* 18 */   public ArrayList<ReplicatePanel> RPArray = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public ReplicateDisplayPanel() {
/* 22 */     setLayout(new BoxLayout((Container)this, 3));
/*    */   }
/*    */ 
/*    */   
/*    */   public void addReplicatePanel() {
/* 27 */     ReplicatePanel RP = new ReplicatePanel(this);
/* 28 */     this.RPArray.add(RP);
/* 29 */     add((Component)RP);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void actionPerformed(ActionEvent e) {
/* 35 */     String actionCommand = e.getActionCommand();
/* 36 */     if (actionCommand.startsWith("RemoveReplicate")) {
/*    */       
/* 38 */       if (this.RPArray.size() == 1) {
/*    */         
/* 40 */         JOptionPane.showMessageDialog((Component)this, "Cannot delete last replicate in a sample");
/*    */         return;
/*    */       } 
/* 43 */       String[] command = actionCommand.split(":");
/*    */ 
/*    */       
/* 46 */       for (ReplicatePanel rp : this.RPArray) {
/*    */         
/* 48 */         if (rp.getName().equals(command[1])) {
/*    */           
/* 50 */           this.RPArray.remove(rp);
/* 51 */           remove((Component)rp);
/*    */           
/*    */           break;
/*    */         } 
/*    */       } 
/* 56 */       revalidate();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   void clearReplicates() {
/* 62 */     for (ReplicatePanel rp : this.RPArray)
/*    */     {
/* 64 */       remove((Component)rp);
/*    */     }
/* 66 */     this.RPArray.clear();
/*    */   }
/*    */   
/*    */   public void addReplicates(ReplicatePanel[] replicToLoad, int currRepCount, ArrayList<String[]> replicateData) {
/* 70 */     clearReplicates();
/*    */     
/* 72 */     int i = 0;
/* 73 */     for (int repCount = currRepCount; repCount < currRepCount + replicateData.size(); repCount++) {
/*    */ 
/*    */       
/* 76 */       ReplicatePanel rp = replicToLoad[repCount];
/*    */       
/* 78 */       String[] replicateDataArray = replicateData.get(i);
/*    */ 
/*    */       
/* 81 */       String internalStandard = replicateDataArray[1];
/* 82 */       String cellprotein = replicateDataArray[2];
/* 83 */       String mdv = replicateDataArray[3];
/*    */       
/* 85 */       rp.internalStandardEntry.setText(internalStandard);
/* 86 */       rp.cellNumOrProteinEntry.setText(cellprotein);
/* 87 */       rp.MDVEntry.setText(mdv);
/*    */       
/* 89 */       rp.setRemoveListener(this);
/* 90 */       this.RPArray.add(rp);
/* 91 */       add((Component)rp);
/*    */       
/* 93 */       i++;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\sdmcg\Documents\Lab\Programming\SITAquant\SITAquant2023\SITAquant-July2019.jar!\frontend\panels\ReplicateDisplayPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */