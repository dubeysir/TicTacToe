package com.alone.ttt;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameLogic {

    private  String[] playerName={"Player 1","Player 2"};
    private  int [][] gameBoard;

//wintype {row,col,linetype}
    private int[ ] winType={-1,-1,-1};
    private Button playAgainBTN;
    private Button homeBTN;
    private TextView playerTurn;
    private  int player=1;
    GameLogic(){
        gameBoard =new int[3][3];
        for(int r=0;r<3;r++){
            for(int c=0;c<3;c++){
                gameBoard[r][c]=0;
            }
        }
    }
  public  boolean updateGB(int row,int col){

        if(gameBoard[row-1][col-1]==0){
            gameBoard[row-1][col-1]=player;

            if(player==1){
                playerTurn.setText(playerName[1]+"'s Turn");
            }else {
                playerTurn.setText(playerName[2]+"'s Turn");
            }
            return true;
        }
        else {return false;}
  }


  public boolean winnerCheck(){
        boolean isWinner=false;

        //horizontal check
        for(int r=0;r<3;r++){
            if(gameBoard[r][0]==gameBoard[r][1]&&gameBoard[r][0]==gameBoard[r][2]&&gameBoard[r][0]!=0)
            {
                winType=new int[]{r,0,1};
                isWinner=true;
            }
        }
//vertical check
      for(int c=0;c<3;c++){
          if(gameBoard[c][0]==gameBoard[c][1]&&gameBoard[c][0]==gameBoard[c][2]&&gameBoard[c][0]!=0)
          {
              winType=new int[]{0,c,2};
              isWinner=true;
          }
      }
//negative digobal check
      if(gameBoard[0][0]==gameBoard[1][1]&&gameBoard[0][0]==gameBoard[2][2]&&gameBoard[0][0]!=0){
          winType=new int[]{0,2,3};
          isWinner=true;
      }

 //positive digonal check
      if(gameBoard[2][0]==gameBoard[1][1]&&gameBoard[2][0]==gameBoard[2][2]&&gameBoard[2][0]!=0){
          winType=new int[]{2,2,4};
          isWinner=true;
      }
int boardFilled=0;
      for(int r=0;r<3;r++){
          for(int c=0;c<3;c++){
             if(gameBoard[r][c]!=0){
                 boardFilled++;
             }
          }
      }

      if(isWinner){
          playAgainBTN.setVisibility(View.VISIBLE);
          homeBTN.setVisibility(View.VISIBLE);
          playerTurn.setText(playerName[player-1]+ "WON!!!");
               return  true;
      } else if (boardFilled==9) {

          playAgainBTN.setVisibility(View.VISIBLE);
          homeBTN.setVisibility(View.VISIBLE);
          playerTurn.setText(playerName[player-1]+ "Game Tied!!!");
      return  true;

      }else {return false;}

  }
  public void resetGame(){

      for(int r=0;r<3;r++){
          for(int c=0;c<3;c++){
              gameBoard[r][c]=0;
          }
      }

      player=1;

      playAgainBTN.setVisibility(View.GONE);
      homeBTN.setVisibility(View.GONE);
      playerTurn.setText((playerName[0]+ "'s Turn"));

  }

    public void setPlayAgainBTN(Button playAgainBTN) {
        this.playAgainBTN = playAgainBTN;
    }

    public void setHomeBTN(Button homeBTN) {
        this.homeBTN = homeBTN;
    }

    public void setPlayerTurn(TextView playerTurn) {
        this.playerTurn = playerTurn;
    }

    public void setPlayerName(String[] playerName) {
        this.playerName = playerName;
    }

    public int[][] getGameBoard() {
        return gameBoard;
    }

    public void setPlayer(int player){
        this.player=player;
    }

    public int getPlayer(){
        return player;
    }

    public int[] getWinType() {
        return winType;
    }
}
