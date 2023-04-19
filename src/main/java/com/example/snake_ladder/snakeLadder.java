package com.example.snake_ladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class snakeLadder extends Application {
    public static final int tilesize = 40, width=10, height = 10;
    public static final int buttonline = height*tilesize+50;
    public static final int infoline = height*tilesize+20;
    private static Dice dice = new Dice();
    private Player player1,player2;
    private  boolean p1turn = false, p2turn = false, startgame = false;
    private Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width*tilesize,height*tilesize+100);

        for(int i=0; i<height; i++){
            for (int j = 0; j < width; j++) {
                Tile tile = new Tile(tilesize);
                tile.setTranslateX(j*tilesize);
                tile.setTranslateY(i*tilesize);
                root.getChildren().addAll(tile);
             }

            Image img = new Image("C:\\Users\\ANKITMAURYA\\IdeaProjects\\Snake_ladder\\src\\main\\resources\\img.jpg");
            ImageView board = new ImageView();
            board.setImage(img);
            board.setFitHeight(tilesize*height);
            board.setFitWidth(tilesize*width);

            //Buttons
            Button p1Button = new Button("Player One");
            Button p2Button = new Button("Player Two");
            Button startbutton = new Button("Start");
            Button restart = new Button("Restart");

            p1Button.setTranslateY(buttonline);
            p1Button.setTranslateX(30);
            p1Button.setDisable(true);
            p2Button.setTranslateY(buttonline);
            p2Button.setTranslateX(300);
            p2Button.setDisable(true);
            startbutton.setTranslateY(buttonline);
            startbutton.setTranslateX(180);

            Label p1label = new Label("");
            Label p2label = new Label("");
            Label startlabel = new Label("");

            p1label.setTranslateY(infoline);
            p1label.setTranslateX(20);

            p2label.setTranslateY(infoline);
            p2label.setTranslateX(290);
            startlabel.setTranslateY(infoline);
            startlabel.setTranslateX(160);

            player1= new Player(tilesize-3, Color.RED,"Player 1");
            player2= new Player(tilesize-5, Color.BLUE ,"Player 2");

            p1Button.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent actionEvent) {
                    if(startgame){
                        if(p1turn){
                            int dicevalue = dice.getdicevalue();
                            startlabel.setText("");
                            startlabel.setText("Dice value :- "+dicevalue);
                            player1.moveplayer(dicevalue);
                            // Winning Condition
                            if(player1.isWinner()){
                                startlabel.setText("Winner is :- "+player1.getName());
                                p1turn=false;
                                p1Button.setDisable(true);
                                p1label.setText("");

                                p2turn=false;
                                p2Button.setDisable(true);
                                p2label.setText("Your Turn "+player2.getName());

                                startbutton.setDisable(false);
                                startlabel.setText("Restart Game");
                            }else{
                                p1turn=false;
                                p1Button.setDisable(true);
                                p1label.setText("");

                                p2turn=true;
                                p2Button.setDisable(false);
                                p2label.setText("Your Turn "+player2.getName());
                            }
                        }
                    }

                }
            });
            p2Button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if(startgame){
                        if(p2turn){
                            int dicevalue = dice.getdicevalue();
                            startlabel.setText("");
                            startlabel.setText("Dice value :- "+dicevalue);
                            player2.moveplayer(dicevalue);
                            //Winning Condition
                            if(player2.isWinner()){
                                startlabel.setText("Winner is :- "+player2.getName());
                                p1turn=false;
                                p1Button.setDisable(true);
                                p1label.setText("");

                                p2turn=false;
                                p2Button.setDisable(true);
                                p2label.setText("Your Turn "+player2.getName());

                                startbutton.setDisable(false);
                                startlabel.setText("Restart Game");
                            }else{
                                p2turn=false;
                                p2Button.setDisable(true);
                                p2label.setText("");

                                p1turn=true;
                                p1Button.setDisable(false);
                                p1label.setText("Your Turn "+player1.getName());
                            }

                        }
                    }

                }
            });
            startbutton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    startgame = true;
                    startlabel.setText("Game Started");
                    startbutton.setDisable(true);

                    p1turn=true;
                    p1Button.setDisable(false);
                    p1label.setText("Your Turn "+player1.getName());
                    player1.startingPosition();

                    p2turn=false;
                    p2label.setText("");
                    player2.startingPosition();
                }
            });
            root.getChildren().addAll(board,p1Button,p2Button,startbutton,p1label,p2label,startlabel,player1.getCoin(), player2.getCoin());
        }


        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene( createContent());
        stage.setTitle("Snake & Ladder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}