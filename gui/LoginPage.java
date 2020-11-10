/*     */ package gui;
/*     */ 
/*     */ import client.ClientMain;
/*     */ import java.io.IOException;
/*     */ import java.sql.SQLException;
/*     */ import javafx.application.Application;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.event.Event;
/*     */ import javafx.event.EventHandler;
/*     */ import javafx.geometry.Pos;
/*     */ import javafx.scene.Node;
/*     */ import javafx.scene.Scene;
/*     */ import javafx.scene.control.Button;
/*     */ import javafx.scene.control.Label;
/*     */ import javafx.scene.control.PasswordField;
/*     */ import javafx.scene.control.TextField;
/*     */ import javafx.scene.image.Image;
/*     */ import javafx.scene.image.ImageView;
/*     */ import javafx.scene.layout.BorderPane;
/*     */ import javafx.scene.layout.HBox;
/*     */ import javafx.scene.layout.VBox;
/*     */ import javafx.scene.paint.Color;
/*     */ import javafx.scene.text.Font;
/*     */ import javafx.scene.text.Text;
/*     */ import javafx.stage.Stage;
/*     */ import javafx.stage.WindowEvent;
/*     */ import sample.TopTips;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LoginPage
/*     */   extends Application
/*     */   implements LoginPageInterface
/*     */ {
/*  42 */   private Font headerFont = new Font("Ariel", 30.0D);
/*  43 */   private Font textFont = new Font("Ariel", 16.0D);
/*  44 */   private Text tips = new Text();
/*  45 */   private Image canastaLogo = new Image("res/canastaLogo.png");
/*  46 */   private ImageView ivCanastaLogo = new ImageView(this.canastaLogo);
/*     */ 
/*     */   
/*     */   private BorderPane root;
/*     */ 
/*     */ 
/*     */   
/*     */   public LoginPage() {
/*  54 */     this.root = createLoginPage();
/*  55 */     Scene scene = new Scene(this.root, 1000.0D, 725.0D);
/*  56 */     String css = getClass().getResource("style.css").toExternalForm();
/*  57 */     scene.getStylesheets().add(css);
/*  58 */     ClientMain.window.setScene(scene);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BorderPane createLoginPage() {
/*  69 */     BorderPane bp = new BorderPane();
/*  70 */     bp.setBottom(null);
/*     */     
/*  72 */     Label login = new Label("Log In");
/*  73 */     login.setFont(this.headerFont);
/*  74 */     Label username = new Label("Username: ");
/*  75 */     username.setFont(this.textFont);
/*  76 */     Label password = new Label("Password: ");
/*  77 */     password.setFont(this.textFont);
/*     */     
/*  79 */     TextField usernameInput = new TextField();
/*  80 */     PasswordField pb = new PasswordField();
/*     */     
/*  82 */     Button submit = new Button("Submit");
/*  83 */     Button newUser = new Button("Sign up");
/*     */     
/*  85 */     submit.setOnAction(action -> {
/*     */           String userUsername = paramTextField.getText();
/*     */           String userPassword = paramPasswordField.getText();
/*     */           try {
/*     */             ClientMain.client.signIn(userUsername, userPassword);
/*  90 */           } catch (IOException|SQLException e) {
/*     */             e.printStackTrace();
/*     */           } 
/*     */         });
/*     */ 
/*     */     
/*  96 */     newUser.setOnAction(action -> {
/*     */         
/*     */         });
/*     */     
/* 100 */     setTip();
/*     */     
/* 102 */     HBox usernameHB = new HBox();
/* 103 */     HBox passwordHB = new HBox();
/* 104 */     VBox vb = new VBox();
/*     */     
/* 106 */     usernameHB.getChildren().addAll(new Node[] { username, usernameInput });
/* 107 */     passwordHB.getChildren().addAll(new Node[] { password, pb });
/* 108 */     usernameHB.setAlignment(Pos.CENTER);
/* 109 */     passwordHB.setAlignment(Pos.CENTER);
/* 110 */     usernameHB.setSpacing(10.0D);
/* 111 */     passwordHB.setSpacing(10.0D);
/*     */     
/* 113 */     vb.getChildren().addAll(new Node[] { this.ivCanastaLogo, login, usernameHB, passwordHB, submit, newUser, this.tips });
/* 114 */     vb.setSpacing(30.0D);
/* 115 */     vb.setAlignment(Pos.CENTER);
/*     */     
/* 117 */     bp.setCenter(vb);
/* 118 */     bp.setTop(null);
/*     */     
/* 120 */     return bp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTip() {
/* 127 */     this.tips.setText(TopTips.randomTip());
/* 128 */     this.tips.setFont(this.textFont);
/* 129 */     this.tips.setWrappingWidth(600.0D);
/* 130 */     this.tips.setLineSpacing(5.0D);
/* 131 */     this.tips.setFill(Color.WHITE);
/* 132 */     this.tips.setId("tipsText");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void start(Stage stage) throws Exception {
/* 140 */     Stage window = stage;
/* 141 */     BorderPane bp = createLoginPage();
/* 142 */     bp.setBottom(null);
/* 143 */     window.setTitle("Login Page Test");
/* 144 */     Scene scene = new Scene(bp, 1000.0D, 1000.0D);
/* 145 */     window.setScene(scene);
/* 146 */     String css = getClass().getResource("style.css").toExternalForm();
/* 147 */     scene.getStylesheets().add(css);
/* 148 */     window.show();
/* 149 */     window.setOnCloseRequest(new EventHandler<WindowEvent>() {
/*     */           public void handle(WindowEvent we) {
/* 151 */             System.out.println("Stage is closing");
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) throws IOException, SQLException {
/* 165 */     launch(args);
/*     */   }
/*     */ }


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\gui\LoginPage.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */