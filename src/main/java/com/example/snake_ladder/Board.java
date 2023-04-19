package com.example.snake_ladder;

import javafx.util.Pair;

import java.util.ArrayList;

public class Board {
    ArrayList<Pair<Integer,Integer>> positionCordinate;
    ArrayList<Integer> snakeLadderposition;
    public  Board(){
        positionCordinate = new ArrayList<>();
        populateposition();
        populateSnakeladder();
    }
    private void populateposition(){
        positionCordinate.add(new Pair<>(0,0));
        for (int i = 0; i < snakeLadder.height; i++) {
            for (int j = 0; j < snakeLadder.width; j++) {
                int xcord =0;
                if(i%2==0){
                    xcord = j*snakeLadder.tilesize+snakeLadder.tilesize/2;
                }else{
                    xcord = snakeLadder.tilesize*snakeLadder.height-(j*snakeLadder.tilesize)-snakeLadder.tilesize/2;
                }
                int ycord= snakeLadder.tilesize*snakeLadder.height-(i*snakeLadder.tilesize)-snakeLadder.tilesize/2;
                positionCordinate.add(new Pair<>(xcord,ycord));
            }

        }
    }
    private void populateSnakeladder(){
        snakeLadderposition = new ArrayList<>();
        for(int i=0; i<=100; i++){
            snakeLadderposition.add(i);
        }
        snakeLadderposition.set(5,35);
        snakeLadderposition.set(9,51);
        snakeLadderposition.set(23,42);
        snakeLadderposition.set(36,5);
        snakeLadderposition.set(49,7);
        snakeLadderposition.set(48,86);
        snakeLadderposition.set(56,8);
        snakeLadderposition.set(62,83);
        snakeLadderposition.set(82,20);
        snakeLadderposition.set(87,66);
        snakeLadderposition.set(69,91);
        snakeLadderposition.set(95,38);
    }
    public int getNewposition(int currPosition){
        if(currPosition>0 && currPosition<=100){
            return snakeLadderposition.get(currPosition);
        }
        return -1;
    }
    int getXposition(int position){
        if(position>=1 && position<=100){
            return positionCordinate.get(position).getKey();
        }
        return -1;
    }
    int getYposition(int position){
        if(position>=1 && position<=100){
            return positionCordinate.get(position).getValue();
        }
        return -1;
    }
}
