/*     */ package gui;
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
/*     */ import javafx.scene.layout.BorderPane;
/*     */ import javafx.scene.layout.HBox;
/*     */ import javafx.scene.layout.VBox;
/*     */ import javafx.scene.text.Font;
/*     */ import javafx.stage.Stage;
/*     */ import javafx.stage.WindowEvent;
/*     */ 
/*     */ public class CreateNewUser extends Application implements CreateNewUserInterface {
/*  24 */   private static Font titleFont = new Font("Ariel", 50.0D);
/*  25 */   private static Font textFont = new Font("Ariel", 16.0D);
/*     */   
/*     */   private BorderPane root;
/*     */   
/*     */   private VBox vb;
/*     */ 
/*     */   
/*     */   public CreateNewUser() {
/*  33 */     this.root = createNewUserPage();
/*  34 */     Scene scene = new Scene(this.root, 1000.0D, 725.0D);
/*  35 */     String css = getClass().getResource("style.css").toExternalForm();
/*  36 */     scene.getStylesheets().add(css);
/*  37 */     ClientMain.window.setScene(scene);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BorderPane createNewUserPage() {
/*  46 */     BorderPane bp = new BorderPane();
/*     */ 
/*     */     
/*  49 */     Label login = new Label("Create New User Profile");
/*  50 */     login.setFont(titleFont);
/*     */ 
/*     */ 
/*     */     
/*  54 */     Label username = new Label("Username: ");
/*  55 */     username.setFont(textFont);
/*  56 */     Label password = new Label("Password: ");
/*  57 */     password.setFont(textFont);
/*  58 */     Label confirmPassword = new Label("Confirm password:");
/*  59 */     confirmPassword.setFont(textFont);
/*  60 */     Label firstName = new Label("First name:");
/*  61 */     firstName.setFont(textFont);
/*  62 */     Label lastName = new Label("Last name:");
/*  63 */     lastName.setFont(textFont);
/*  64 */     Label age = new Label("Age:");
/*  65 */     age.setFont(textFont);
/*     */ 
/*     */     
/*  68 */     TextField usernameInput = new TextField();
/*  69 */     PasswordField passwordInput = new PasswordField();
/*  70 */     PasswordField confirmPasswordInput = new PasswordField();
/*  71 */     TextField firstNameInput = new TextField();
/*  72 */     TextField lastNameInput = new TextField();
/*  73 */     TextField ageInput = new TextField();
/*     */ 
/*     */     
/*  76 */     Button register = new Button("Confirm");
/*  77 */     register.setOnAction(action -> {
/*     */           String userUsername = paramTextField1.getText();
/*     */           
/*     */           String userPassword = paramPasswordField1.getText();
/*     */           
/*     */           String userPassword2 = paramPasswordField2.getText();
/*     */           
/*     */           String userFirstName = paramTextField2.getText();
/*     */           
/*     */           String userLastName = paramTextField3.getText();
/*     */           
/*     */           String userAge = paramTextField4.getText();
/*     */           if (userPassword.equals(userPassword2)) {
/*     */             try {
/*     */               if (ClientMain.client.signUp(userUsername, userPassword, userFirstName, userLastName, userAge)) {
/*     */                 Label addedUser = new Label("Account created. \nGo back to log in.");
/*     */                 addedUser.setFont(textFont);
/*     */                 this.vb.getChildren().add(addedUser);
/*     */               } else {
/*     */                 Label addedUser = new Label("Account not created. \nGo back and try again.");
/*     */                 addedUser.setFont(textFont);
/*     */                 this.vb.getChildren().add(addedUser);
/*     */               } 
/* 100 */             } catch (IOException e) {
/*     */               e.printStackTrace();
/*     */             } 
/*     */           } else {
/*     */             System.out.println("Passwords dont match");
/*     */           } 
/*     */         });
/*     */ 
/*     */ 
/*     */     
/* 110 */     Button back = new Button("Go Back");
/* 111 */     back.setOnAction(action -> {
/*     */         
/*     */         });
/*     */     
/* 115 */     back.setAlignment(Pos.CENTER);
/*     */     
/* 117 */     HBox usernameHB = new HBox();
/* 118 */     HBox passwordHB = new HBox();
/* 119 */     HBox passConHB = new HBox();
/* 120 */     HBox fNameHB = new HBox();
/* 121 */     HBox lNameHB = new HBox();
/* 122 */     HBox ageHB = new HBox();
/* 123 */     HBox title = new HBox();
/* 124 */     HBox buttons = new HBox();
/* 125 */     this.vb = new VBox();
/*     */     
/* 127 */     title.getChildren().addAll(new Node[] { login });
/* 128 */     usernameHB.getChildren().addAll(new Node[] { username, usernameInput });
/* 129 */     fNameHB.getChildren().addAll(new Node[] { firstName, firstNameInput });
/* 130 */     lNameHB.getChildren().addAll(new Node[] { lastName, lastNameInput });
/* 131 */     ageHB.getChildren().addAll(new Node[] { age, ageInput });
/* 132 */     passwordHB.getChildren().addAll(new Node[] { password, passwordInput });
/* 133 */     passConHB.getChildren().addAll(new Node[] { confirmPassword, confirmPasswordInput });
/* 134 */     buttons.getChildren().addAll(new Node[] { back, register });
/*     */     
/* 136 */     title.setAlignment(Pos.CENTER);
/* 137 */     usernameHB.setAlignment(Pos.CENTER);
/* 138 */     fNameHB.setAlignment(Pos.CENTER);
/* 139 */     lNameHB.setAlignment(Pos.CENTER);
/* 140 */     ageHB.setAlignment(Pos.CENTER);
/* 141 */     passwordHB.setAlignment(Pos.CENTER);
/* 142 */     passConHB.setAlignment(Pos.CENTER);
/* 143 */     buttons.setAlignment(Pos.CENTER);
/*     */     
/* 145 */     usernameHB.setSpacing(70.0D);
/* 146 */     fNameHB.setSpacing(70.0D);
/* 147 */     lNameHB.setSpacing(70.0D);
/* 148 */     ageHB.setSpacing(120.0D);
/* 149 */     passwordHB.setSpacing(70.0D);
/* 150 */     passConHB.setSpacing(10.0D);
/* 151 */     buttons.setSpacing(10.0D);
/*     */ 
/*     */     
/* 154 */     this.vb.getChildren().addAll(new Node[] { usernameHB, fNameHB, lNameHB, ageHB, passwordHB, passConHB, buttons });
/* 155 */     this.vb.setSpacing(20.0D);
/* 156 */     this.vb.setAlignment(Pos.CENTER);
/* 157 */     bp.setTop(title);
/* 158 */     bp.setCenter(this.vb);
/* 159 */     bp.setBottom(null);
/*     */     
/* 161 */     return bp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void start(Stage stage) throws Exception {
/* 169 */     Stage window = stage;
/* 170 */     BorderPane bp = createNewUserPage();
/* 171 */     bp.setBottom(null);
/* 172 */     window.setTitle("Canasta App");
/* 173 */     Scene scene = new Scene(bp, 1000.0D, 1000.0D);
/* 174 */     window.setScene(scene);
/*     */     
/* 176 */     String css = getClass().getResource("style.css").toExternalForm();
/* 177 */     scene.getStylesheets().add(css);
/* 178 */     window.show();
/* 179 */     window.setOnCloseRequest(new EventHandler<WindowEvent>() {
/*     */           public void handle(WindowEvent we) {
/* 181 */             System.out.println("Stage is closing");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) throws IOException, SQLException {
/* 201 */     launch(args);
/*     */   }
/*     */ }


/* Location:              C:\Users\jack.humberstone\Documents\Canasta2.0 Test1\CanastaV2.0.jar!\gui\CreateNewUser.class
 * Java compiler version: 12 (56.0)
 * JD-Core Version:       1.1.3
 */