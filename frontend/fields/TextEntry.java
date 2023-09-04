/*     */ package frontend.fields;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.event.DocumentListener;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TextEntry
/*     */   extends JPanel
/*     */ {
/*     */   JLabel theLabel;
/*     */   JTextField theField;
/*  18 */   public Color oldColour = Color.lightGray;
/*     */ 
/*     */   
/*     */   public TextEntry(String label) {
/*  22 */     create(label, "");
/*     */   }
/*     */ 
/*     */   
/*     */   public TextEntry(String label, String defaultText) {
/*  27 */     create(label, defaultText);
/*     */   }
/*     */ 
/*     */   
/*     */   private void create(String label, String defaultText) {
/*  32 */     this.theLabel = new JLabel(label);
/*  33 */     this.theField = new JTextField(defaultText, 20);
/*     */     
/*  35 */     add(this.theLabel);
/*  36 */     add(this.theField);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOrientation(int orientation) {
/*  41 */     if (orientation == 2 || orientation == 3) {
/*     */       
/*  43 */       setLayout(new BoxLayout(this, orientation));
/*  44 */       revalidate();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setText(String text) {
/*  50 */     this.theField.setText(text);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getText() {
/*  55 */     return this.theField.getText();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLabel(String label) {
/*  60 */     this.theLabel.setText(label);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBGFG(Color BG, Color FG) {
/*  65 */     this.oldColour = BG;
/*  66 */     setBackground(BG);
/*  67 */     setForeground(FG);
/*  68 */     this.theLabel.setForeground(FG);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEditable(boolean editable) {
/*  73 */     this.theField.setEditable(editable);
/*  74 */     this.theField.setBackground(editable ? Color.white : Color.gray);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addActionListener(ActionListener AL) {
/*  79 */     this.theField.addActionListener(AL);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSource(Object source) {
/*  84 */     return (source == this.theField);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addDocumentListener(DocumentListener DL) {
/*  89 */     this.theField.getDocument().addDocumentListener(DL);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void highlight(boolean doHighlight) {
/*  95 */     if (doHighlight) {
/*     */       
/*  97 */       setBackground(Color.RED);
/*     */     }
/*     */     else {
/*     */       
/* 101 */       setBackground(this.oldColour);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\sdmcg\Documents\Lab\Programming\SITAquant\SITAquant2023\SITAquant-July2019.jar!\frontend\fields\TextEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */