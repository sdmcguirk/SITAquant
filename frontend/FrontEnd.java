/*    */ package frontend;

/*    */ import frontend.panels.FragmentPanel;
/*    */ import java.awt.Component;
/*    */ import java.awt.Container;
/*    */ import java.awt.FlowLayout;
/*    */ import javax.swing.JApplet;
/*    */ import javax.swing.JScrollPane;
/*    */ import javax.swing.UIManager;
/*    */ import javax.swing.UnsupportedLookAndFeelException;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ public class FrontEnd
/*    */   extends JPanel {
/*    */   private static final long serialVersionUID = 1L;
/*    */   

public static void main(String[] args) {
	  JFrame frame = new JFrame();
	  frame.addWindowListener(new java.awt.event.WindowAdapter() {
	       public void windowClosing(java.awt.event.WindowEvent e) {
	       System.exit(0);
	       };
	     });

	  FrontEnd ut = new FrontEnd();
	  ut.setSize(1000,1000); // same size as defined in the HTML APPLET
	  frame.add(ut);
	  frame.pack();
	  ut.init();
	  frame.setSize(1000,1020); // add 20, seems enough for the Frame title,
	  frame.setVisible(true);
	  frame.setResizable(true);
	  frame.setLayout(new FlowLayout());
	  frame.show();
	  }



/*    */   public void init() {
/*    */     try {
/*    */       byte b;
/*    */       int i;
/*    */       UIManager.LookAndFeelInfo[] arrayOfLookAndFeelInfo;
/* 21 */       for (i = (arrayOfLookAndFeelInfo = UIManager.getInstalledLookAndFeels()).length, b = 0; b < i; ) { UIManager.LookAndFeelInfo info = arrayOfLookAndFeelInfo[b];
/* 22 */         if ("Nimbus".equals(info.getName())) {
/* 23 */           UIManager.setLookAndFeel(info.getClassName()); break;
/*    */         } 
/*    */         b++; }
/*    */     
/* 27 */     } catch (UnsupportedLookAndFeelException e) {
/* 28 */       System.out.println("UnsupportedLookAndFeelException");
/* 29 */     } catch (ClassNotFoundException e) {
/* 30 */       System.out.println("ClassNotFoundException");
/* 31 */     } catch (InstantiationException e) {
/* 32 */       System.out.println("InstantiationException");
/* 33 */     } catch (IllegalAccessException e) {
/* 34 */       System.out.println("IllegalAccessException");
/*    */     } 
/*    */     
/* 37 */     Container contentPane = getRootPane();
/*    */     
/* 39 */     ColourSettings.setColours(contentPane);
/*    */ 
/*    */     
/* 42 */     FragmentPanel fragmentPanel = new FragmentPanel();
/* 43 */     JScrollPane mainScrollPane = new JScrollPane((Component)fragmentPanel);
/*    */     
/* 45 */     contentPane.setSize(800,1000);
/* 46 */     mainScrollPane.setSize(800,1000);
/*    */     
/* 48 */     contentPane.setLayout(new FlowLayout());
/* 49 */     contentPane.add(mainScrollPane);
/*    */     
/* 51 */     validate();
/* 52 */     repaint();
/*    */   }
/*    */ }


/* Location:              C:\Users\sdmcg\Documents\Lab\Programming\SITAquant\SITAquant2023\SITAquant-July2019.jar!\frontend\FrontEnd.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */