package com.example.snake_ladder;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player {
    private Circle coin;
    int currPosition;
    String name;
    static Board gameBoard = new Board();

    public Player(int tilesize, Color coincolor,String playername){
        coin=new Circle(tilesize/2);
        coin.setFill(coincolor);
        currPosition=1;
        moveplayer(0);
        name = playername;
    }
    public void moveplayer(int dicevalue){
        if(currPosition+dicevalue<=100){
            currPosition+=dicevalue;
           TranslateTransition secondmove=null,firstmove = translateAnimation();
           int newposition = gameBoard.getNewposition(currPosition);
           if(newposition!=currPosition && newposition!=-1){
               currPosition=newposition;
               secondmove=translateAnimation();
           }
            if(secondmove==null){
                firstmove.play();
            }else{
                SequentialTransition sequentialTransition = new SequentialTransition(firstmove,new PauseTransition(Duration.millis(500)),secondmove);
                sequentialTransition.play();
            }
        }
//        int x = gameBoard.getXposition(currPosition);
//        int y = gameBoard.getYposition(currPosition);
//        coin.setTranslateX(x);
//        coin.setTranslateY(y);

    }

  private TranslateTransition translateAnimation(){
        TranslateTransition animate = new TranslateTransition(Duration.millis(1000),coin);
        animate.setToX(gameBoard.getXposition(currPosition));
        animate.setToY(gameBoard.getYposition(currPosition));
        animate.setAutoReverse(false);
        return animate;
  }
  public void startingPosition(){
        currPosition=0;
        moveplayer(1);
  }
  boolean isWinner(){
        if(currPosition==100){
            return true;
      }else{
            return false;
        }
  }
    public Circle getCoin() {
        return coin;
    }

    public int getCurrPosition() {
        return currPosition;
    }

    public String getName() {
        return name;
    }


}
