/*    */ package frontend.fields;
/*    */ 
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Font;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.JScrollPane;
/*    */ import javax.swing.JTextPane;
/*    */ import javax.swing.UIManager;
/*    */ import javax.swing.border.Border;
/*    */ 
/*    */ 
/*    */ public class JLabelSelectable
/*    */   extends JPanel
/*    */ {
/*    */   JScrollPane theScrollPane;
/* 16 */   JTextPane theText = new JTextPane();
/*    */ 
/*    */ 
/*    */   
/*    */   public JLabelSelectable(String text) {
/* 21 */     setText(text);
/* 22 */     this.theText.setEditable(false);
/*    */ 
/*    */     
/* 25 */     this.theText.setBorder((Border)null);
/* 26 */     this.theText.setForeground(UIManager.getColor("Label.foreground"));
/* 27 */     this.theText.setFont(UIManager.getFont("Label.font"));
/*    */     
/* 29 */     this.theScrollPane = new JScrollPane(this.theText, 22, 31);
/* 30 */     this.theScrollPane.setBounds(3, 3, 200, 200);
/* 31 */     add(this.theScrollPane);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setMonospace() {
/* 37 */     this.theText.setFont(new Font("monospaced", 0, 12));
/*    */   }
/*    */ 
/*    */   
/*    */   public void setText(String text) {
/* 42 */     this.theText.setText(text);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setScrollPaneSettings(int verticalPolicy, int horizontalPolicy, boolean lineWrap) {
/* 47 */     this.theScrollPane.setVerticalScrollBarPolicy(verticalPolicy);
/* 48 */     this.theScrollPane.setHorizontalScrollBarPolicy(horizontalPolicy);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSize(Dimension d) {
/* 54 */     setPreferredSize(d);
/* 55 */     Dimension size = new Dimension(d.width, (int)(d.height * 0.95D));
/* 56 */     this.theScrollPane.setPreferredSize(size);
/*    */   }
/*    */ }


/* Location:              C:\Users\sdmcg\Documents\Lab\Programming\SITAquant\SITAquant2023\SITAquant-July2019.jar!\frontend\fields\JLabelSelectable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */