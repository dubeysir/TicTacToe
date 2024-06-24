package com.alone.ttt;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class TTTboard extends View {

    private final int boardColor;
    private final int Xcolor;
    private  final int Ocolor;
    private final int winningLinecolor;


    private boolean wl=false;

    private final Paint p=new Paint();

    private final GameLogic game;
  private int cellSize=getWidth()/3;
    public TTTboard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
game=new GameLogic();
        TypedArray a=context.getTheme().obtainStyledAttributes(attrs,R.styleable.TTTboard,0,0);

        try {
            boardColor=a.getInteger(R.styleable.TTTboard_boardColor,0);
            Xcolor=a.getInteger(R.styleable.TTTboard_Xcolor,0);
            Ocolor=a.getInteger(R.styleable.TTTboard_Ocolor,0);
            winningLinecolor=a.getInteger(R.styleable.TTTboard_winningLinecolor,0);

        }finally {
            a.recycle();
        }


    }

    @Override
    protected void onMeasure(int w,int h){
        super.onMeasure(w,h);

        int d=Math.min(getMeasuredWidth(),getMeasuredHeight());
    cellSize=d/3;
        setMeasuredDimension(d,d);
    }

    @Override
    protected void onDraw(Canvas c){
        p.setStyle(Paint.Style.STROKE);
        p.setAntiAlias(true);

        drawGameB(c);

//        drawX(c,1,1);
//        drawO(c,0,0);

        drawMarkers(c);

        if(wl){
            p.setColor(winningLinecolor);
            drawWL(c);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        float x=event.getX();
        float y=event.getY();

        int action =event.getAction();

        if(action==MotionEvent.ACTION_DOWN){
            int row=(int) Math.ceil(y/cellSize);
            int col=(int) Math.ceil(x/cellSize);


            if(!wl){
            if(game.updateGB(row,col)){
                invalidate();


                if(game.winnerCheck()){
                    wl=true;
                    invalidate();
                }

                if(game.getPlayer()%2==0){
                    game.setPlayer(game.getPlayer()-1);
                }else {
                    game.setPlayer(game.getPlayer()+1);
                }
            }}
            invalidate();
            return  true;
        }return false;
    }

    private void drawGameB(Canvas c){
        p.setColor(boardColor);
        p.setStrokeWidth(16);

    for (int cc=1;cc<3;cc++){
   c.drawLine(cellSize*cc,0,cellSize*cc,c.getWidth(),p);
    }
        for (int rr=1;rr<3;rr++){
            c.drawLine(0,cellSize*rr,c.getWidth(),cellSize*rr,p);

        }
    }

    private  void drawMarkers(Canvas c){
        for(int r=0;r<3;r++){
            for(int ccc=0;ccc<3;ccc++){
                if(game.getGameBoard()[r][ccc]!=0){
                    if(game.getGameBoard()[r][ccc]==1){
                   drawX(c,r,ccc);
                    }else {
                        drawO(c,r,ccc);
                    }
            }
        }
    }}
    private void drawX(Canvas c,int row,int col){
        p.setColor(Xcolor);

        c.drawLine((float)((col+1)*cellSize+cellSize*0.2),(float)(row*cellSize+cellSize*0.2),(float)(col*cellSize-cellSize*0.2),(float)((row+1)*cellSize-cellSize*0.2),p);
        c.drawLine((float)(col*cellSize+cellSize*0.2),(float)(row*cellSize+cellSize*0.2),(float)((col+1)*cellSize-cellSize*0.2),(float)((row+1)*cellSize-cellSize*0.2),p);

    }
    private void drawO(Canvas c,int row,int col){
        p.setColor(Ocolor);

     //   c.drawOval((col+1)*cellSize,row*cellSize,col*cellSize,(row+1)*cellSize,p);

        c.drawOval((float)(col*cellSize+cellSize*0.2),(float)(row*cellSize+cellSize*0.2),(float)((col*cellSize+cellSize)-cellSize*0.2),(float)((row*cellSize+cellSize)-cellSize*0.2),p);


    }


    private void drawHorizontalLine(Canvas can,int r,int c){
        can.drawLine(c,r*cellSize+(float)cellSize/2,cellSize*3,r*cellSize+(float)cellSize/2,p);
    }
    private void drawVerticalLine(Canvas can,int r,int c){
        can.drawLine(c*cellSize+(float)cellSize/2,r,c*cellSize+(float)cellSize/2,cellSize*3,p);
    }

    private void DDLP(Canvas can){
        can.drawLine(0,3*cellSize,cellSize*3,0,p);
    }
    private void DDLN(Canvas can){
        can.drawLine(0,0,cellSize*3,cellSize*3,p);
    }

    public  void drawWL(Canvas c){
        int row =game.getWinType()[0];
        int col =game.getWinType()[1];
    switch (game.getWinType()[2]){
        case 1:
            drawHorizontalLine(c,row,col);
            break;
        case 2:
            drawVerticalLine(c,row,col);
            break;
        case 3:
            DDLN(c);
            break;
        case 4:
            DDLP(c);
            break;
    }
    }

public void setUpGame(Button playAgain, Button home, TextView playerDisplay,String[] name ){
game.setPlayAgainBTN(playAgain);
game.setHomeBTN(home);
    game.setPlayerName(name);
}
    public void resetGame(){
        game.resetGame();
        wl=false;
    }
}
