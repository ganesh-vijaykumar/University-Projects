package connect4;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView; 
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.*;
import java.util.ArrayList;

enum state {RED, BLACK, EMPTY};

public class Connect4 extends Application {
    private Button playAgain;
    private state[][] array = new state[6][7];
    private int tokenCounter = 0;
    private ArrayList<Circle> colors = new ArrayList<>();
    
    Text p1 = new Text(175, 15, "Player 1");
    Text p2 = new Text(175, 15, "Player 2");
    Text p1wins = new Text(175, 15, "Player 1 Wins!");
    Text p2wins = new Text(175, 15, "Player 2 Wins!");
    Text draw = new Text(175, 15, "Draw!");
    
    Rectangle one = new Rectangle(46, 52);
    Rectangle two = new Rectangle(46, 52);
    Rectangle three = new Rectangle(46, 52);
    Rectangle four = new Rectangle(46, 52);
    Rectangle five = new Rectangle(46, 52);
    Rectangle six = new Rectangle(46, 52);
    Rectangle seven = new Rectangle(46, 52);
    
    public static void main(String[] args) {
        launch(args);
    }
         
    @Override
    public void start(Stage stage) throws Exception {
        for(int i=0; i<6; i++) {
            for(int j=0; j<7; j++) {
                array[i][j] = state.EMPTY;
                }
            }
        
        Text play = new Text(50, 350, "Click above the column to drop");
        
        Image board = new Image("resources/board.png");
        ImageView imageView = new ImageView(board);
            
        imageView.setFitHeight(300);
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);
            
        BorderPane pane = new BorderPane();
        pane.setPrefSize(400, 400);
        pane.setCenter(imageView);
       
        one.setLayoutY(20);
        one.setLayoutX(51);
        two.setLayoutY(20);
        two.setLayoutX(93.7);
        three.setLayoutY(20);
        three.setLayoutX(136.4);
        four.setLayoutY(20);
        four.setLayoutX(179.1);
        five.setLayoutY(20);
        five.setLayoutX(221.8);
        six.setLayoutY(20);
        six.setLayoutX(264.5);
        seven.setLayoutY(20);
        seven.setLayoutX(304);
        one.setFill(javafx.scene.paint.Color.TRANSPARENT);
        two.setFill(javafx.scene.paint.Color.TRANSPARENT);
        three.setFill(javafx.scene.paint.Color.TRANSPARENT);
        four.setFill(javafx.scene.paint.Color.TRANSPARENT);
        five.setFill(javafx.scene.paint.Color.TRANSPARENT);
        six.setFill(javafx.scene.paint.Color.TRANSPARENT);
        seven.setFill(javafx.scene.paint.Color.TRANSPARENT);

        pane.getChildren().addAll(one,two,three,four,five,six,seven, play);
        
        playAgain = new Button("Play Again");
        ButtonHandler handler = new ButtonHandler();
        playAgain.setOnAction(handler);
        
        pane.getChildren().add(playAgain);
        playAgain.setVisible(false);
        playAgain.resize(100,50);
        playAgain.setLayoutX(250);
        playAgain.setLayoutY(335);
        
        p2.setVisible(false);
        p1wins.setVisible(false);
        p2wins.setVisible(false);
        draw.setVisible(false);
        pane.getChildren().addAll(p1,p2,p1wins,p2wins,draw);
        
        one.setOnMouseClicked(new EventHandler<MouseEvent>(){
         public void handle(MouseEvent event) {
                pane.getChildren().add(fillBoard(1));
         }
        });
        two.setOnMouseClicked(new EventHandler<MouseEvent>(){
         public void handle(MouseEvent event) {
                pane.getChildren().add(fillBoard(2));
            }
        });
        three.setOnMouseClicked(new EventHandler<MouseEvent>(){
         public void handle(MouseEvent event) {
                pane.getChildren().add(fillBoard(3));
            }
        });
        four.setOnMouseClicked(new EventHandler<MouseEvent>(){
         public void handle(MouseEvent event) {
                pane.getChildren().add(fillBoard(4));
            }
        });
        five.setOnMouseClicked(new EventHandler<MouseEvent>(){
         public void handle(MouseEvent event) {
                pane.getChildren().add(fillBoard(5));
            }
        });
        six.setOnMouseClicked(new EventHandler<MouseEvent>(){
         public void handle(MouseEvent event) {
                pane.getChildren().add(fillBoard(6));
            }
        });
        seven.setOnMouseClicked(new EventHandler<MouseEvent>(){
         public void handle(MouseEvent event) {
                pane.getChildren().add(fillBoard(7));
            }
        });
        
        stage.setResizable(false);
        stage.setScene(new Scene(pane));
        stage.setTitle("Connect 4");  
        stage.show();
    }
    
    public void checkWin(int row, int col, state color) {
        if(tokenCounter >= 7) {
            for(int i=0; i<4; i++){
                for(int j=0; j<4; j++){
                    if(i == 0 && row+1 <= 5 && row+2 <= 5 && row+3 <= 5){
                        if(array[row][col] == color && array[row+1][col] == color 
                            && array[row+2][col] == color && array[row+3][col] == color){
                            gameOver(color);
                            return;
                        }
                    }
                    if(i == 1 && col+j >= 0 && col+j <= 6 && col-1+j >= 0 && col-1+j <= 6
                            && col-2+j >= 0 && col-2+j <= 6 && col-3+j >= 0 && col-3+j <= 6){
                        if(array[row][col+j] == color && array[row][col-1+j] == color 
                            && array[row][col-2+j] == color && array[row][col-3+j] == color){
                            gameOver(color);
                            return;
                        }
                    }
                    if(i == 2 && col+j >= 0 && col+j <= 6 && col-1+j >= 0 && col-1+j <= 6
                            && col-2+j >= 0 && col-2+j <= 6 && col-3+j >= 0 && col-3+j <= 6 
                            && row+j >= 0 && row+j <= 5 && row-1+j >= 0 && row-1+j <= 5 
                            && row-2+j >= 0 && row-2+j <= 5 && row-3+j >= 0 && row-3+j <= 5){
                        if(array[row+j][col+j] == color && array[row-1+j][col-1+j] == color 
                            && array[row-2+j][col-2+j] == color && array[row-3+j][col-3+j] == color){
                            gameOver(color);
                            return;
                        }
                    }
                    if(i == 3 && col-j >= 0 && col-j <= 6 && col+1-j >= 0 && col+1-j <= 6
                            && col+2-j >= 0 && col+2-j <= 6 && col+3-j >= 0 && col+3-j <= 6 
                            && row+j >= 0 && row+j <= 5 && row-1+j >= 0 && row-1+j <= 5 
                            && row-2+j >= 0 && row-2+j <= 5 && row-3+j >= 0 && row-3+j <= 5){
                        if(array[row+j][col-j] == color && array[row-1+j][col+1-j] == color 
                            && array[row-2+j][col+2-j] == color && array[row-3+j][col+3-j] == color){
                            gameOver(color);
                            return;
                        }
                    }
                }
            }
            if(tokenCounter == 42) {
                playAgain.setVisible(true);
                p1.setVisible(false);
                p2.setVisible(false);
                draw.setVisible(true);
            }
        }
    }
    
    public void hideRect() {
        one.setDisable(true);
        two.setDisable(true);
        three.setDisable(true);
        four.setDisable(true);
        five.setDisable(true);
        six.setDisable(true);
        seven.setDisable(true);
    }
    
    public void gameOver(state color) {
        playAgain.setVisible(true);
        p1.setVisible(false);
        p2.setVisible(false);
        if(color == state.RED) {
            p1wins.setVisible(true);
            }
        else
            p2wins.setVisible(true);
        hideRect();
    }
 
    public Circle fillBoard(int column) {
        for(int i=5; i>=0; i--) {
          if(array[i][column-1] == state.EMPTY) {
            if(p1.isVisible() == true) {
                array[i][column-1] = state.RED;
                p1.setVisible(false);
                p2.setVisible(true);
                Circle red = new Circle(18);
                red.setFill(javafx.scene.paint.Color.RED);
                red.setLayoutX(32+column*42.68-column);
                red.setLayoutY(11+(i+2)*42.68-(i+2));
                colors.add(red);
                tokenCounter++;
                checkWin(i,column-1,state.RED);
                return red;
                }
            else {
                array[i][column-1] = state.BLACK;
                p1.setVisible(true);
                p2.setVisible(false);
                Circle black = new Circle(18);
                black.setFill(javafx.scene.paint.Color.BLACK);
                black.setLayoutX(32+column*42.68-column);
                black.setLayoutY(11+(i+2)*42.68-(i+2));
                colors.add(black);
                tokenCounter++;
                checkWin(i,column-1,state.BLACK);
                return black;
                }
            }
        }
        return null;
    }
    
    class ButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent t) {
            if(t.getSource() == playAgain) {
                for(int i=0; i<6; i++) {
                    for(int j=0; j<7; j++) {
                        array[i][j] = state.EMPTY;
                    }
                }
                for(int i=0; i<colors.size(); i++){
                    colors.get(i).setFill(javafx.scene.paint.Color.TRANSPARENT);
                }
                colors.clear();
                tokenCounter = 0;
                p1wins.setVisible(false);
                p2wins.setVisible(false);
                p2.setVisible(false);
                draw.setVisible(false);
                p1.setVisible(true);
                playAgain.setVisible(false);
                one.setDisable(false);
                two.setDisable(false);
                three.setDisable(false);
                four.setDisable(false);
                five.setDisable(false);
                six.setDisable(false);
                seven.setDisable(false);
            }   
        }
    }
}