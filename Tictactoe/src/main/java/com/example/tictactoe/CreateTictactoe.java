package com.example.tictactoe;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateTictactoe extends Application {
    private Label playerXLabel,playerOLabel; //to store the player score and display
    private Button buttons[][]=new Button[3][3];//to be clickable when user clicks
    private boolean playerXturn=true;//to set once each player
    private int playerXScore=0,playerOScore=0;
    private BorderPane createContent(){//borderpane is for we can have center top bottom seperately
        BorderPane pane=new BorderPane();
        pane.setPadding(new Insets(20));//to set gap on every side
        //Title-to display the name of the application
        Label titlelabel=new Label();//lable is used to display text
        titlelabel.setText("TIC TAC TOE");
        titlelabel.setStyle("-fx-font-size : 35pt; -fx-font-weight : bold");
        pane.setTop(titlelabel);//this set the label on the top of the border pane
        BorderPane.setAlignment(titlelabel, Pos.CENTER);//to set the whole title label at center
        //GameBoard
        GridPane board=new GridPane();//for creating a matrix set of buttons
        board.setHgap(10);
        board.setVgap(10);
        for(int i=0;i<3;i++){ //creating grid of buttons
            for(int j=0;j<3;j++){
                Button button=new Button("");
                button.setPrefSize(100,100);
                button.setStyle("-fx-font-size : 24pt; -fx-font-weight : bold");
                button.setOnAction(event->buttonclicked(button));
                buttons[j][i]=button;
                board.add(button,i,j);
            }
        }
        board.setAlignment(Pos.CENTER);//to set the whole grid pane at center
        pane.setCenter(board);
        //DisplayScore
        HBox scoreBoard=new HBox();
        playerXLabel=new Label("PLAYER X: 0");
        playerXLabel.setStyle("-fx-font-size : 16pt; -fx-font-weight : bold");
        playerOLabel=new Label(":PLAYER O:0");
        playerOLabel.setStyle("-fx-font-size : 16pt; -fx-font-weight : bold");
        scoreBoard.getChildren().addAll(playerXLabel,playerOLabel);
        pane.setBottom(scoreBoard);
        scoreBoard.setAlignment(Pos.CENTER);
        return pane;
    }
    public void buttonclicked(Button button){//when a button got clicked this function will be executed
        if(button.getText().equals("")){
            if(playerXturn){
                button.setText("X");
//            button.setStyle("-fx-font-size : 16pt; -fx-font-weight : bold");
            }
            else{
                button.setText("O");
            }
        }
        checkwinner(button);
        playerXturn=!playerXturn;
        return;
    }
    public void checkwinner(Button button){//we have to check winner each time a button got clicked
        for(int row=0;row<3;row++){//check for row wise
            if((buttons[row][0].getText().equals(buttons[row][1].getText()))&&buttons[row][1].getText().equals(buttons[row][2].getText())&&!buttons[row][0].getText().isEmpty()){
                String winner=buttons[row][0].getText();
                showDialog(winner);
                updateScore(winner);
                resetGame();
//                System.out.println("winner");
                return;
            }
        }
        for(int col=0;col<3;col++){//check for column wise
            if((buttons[0][col].getText().equals(buttons[1][col].getText()))&&buttons[1][col].getText().equals(buttons[2][col].getText())&&!buttons[0][col].getText().isEmpty()){
                String winner=buttons[0][col].getText();
                showDialog(winner);
                updateScore(winner);
                resetGame();
//                System.out.println("winner");
                return;
            }
        }
        //to check diagonally
        if((buttons[0][0].getText().equals(buttons[1][1].getText()))&&buttons[1][1].getText().equals(buttons[2][2].getText())&&!buttons[0][0].getText().isEmpty()){
            String winner=buttons[0][0].getText();
            showDialog(winner);
            updateScore(winner);
            resetGame();
//            System.out.println("winner");
            return;
        }
        if((buttons[0][2].getText().equals(buttons[1][1].getText()))&&buttons[1][1].getText().equals(buttons[2][0].getText())&&!buttons[0][2].getText().isEmpty()){
            String winner=buttons[0][2].getText();
            showDialog(winner);
            updateScore(winner);
            resetGame();
//            System.out.println("winner");
            return;
        }
        //if no one win and tie
        boolean tie=true;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(buttons[i][j].getText().isEmpty()){
                    tie=false;
                }
            }
        }
        if(tie){
            showtieDialog();
            resetGame();
        }
    }
    public void showDialog(String winner){//this function is to diplay alert message when there is winner
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Congratulations "+winner+"!You are the Winner");
        alert.setTitle("Winner");
        alert.setHeaderText("");
        alert.showAndWait();
        return;
    }
    public void updateScore(String winner){//each time when a player won, Score is updated
        if(winner.equals("X")){
            playerXScore++;
            playerXLabel.setText("Player X: "+playerXScore);
        }
        else{
            playerOScore++;
            playerOLabel.setText("Player O: "+playerOScore);
        }
    }
    public void showtieDialog(){//it is displayed when there is tie
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("The Game is Tie...");
        alert.setTitle("Game Tie");
        alert.setHeaderText("");
        alert.showAndWait();
        return;
    }
    public void resetGame(){//to reset the game, we have set each button text empty
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                buttons[i][j].setText("");
            }
        }
        return;
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("TICTACTOE!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}