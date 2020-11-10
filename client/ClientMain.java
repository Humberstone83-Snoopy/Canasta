/*    */ package client;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.sql.SQLException;
/*    */ import javafx.application.Application;
/*    */ import javafx.event.Event;
/*    */ import javafx.event.EventHandler;
/*    */ import javafx.stage.Stage;
/*    */ import javafx.stage.WindowEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ClientMain
/*    */   extends Application
/*    */ {
/*    */   public static Stage window;
/*    */   public static ClientConnection client;
/*    */   
/*    */   public void start(Stage stage) throws Exception {
/* 25 */     window = stage;
/* 26 */     window.setTitle("Canasta App");
/*    */     
/* 28 */     window.show();
/* 29 */     window.setOnCloseRequest(new EventHandler<WindowEvent>() {
/*    */           public void handle(WindowEvent we) {
/* 31 */             System.out.println("Stage is closing");
/*    */             try {
/* 33 */               ClientMain.client.logout();
/* 34 */             } catch (IOException e) {
/* 35 */               e.printStackTrace();
/*    */             } 
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void main(String[] args) throws IOException, SQLException {
/* 50 */     String hostname = "94.4.201.188";
/* 51 */     int port = 25565;
/*    */     
/* 53 */     client = new ClientConnection(hostname, port);
/* 54 */     launch(args);
/*    */   }
/*    */ }


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\client\ClientMain.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */