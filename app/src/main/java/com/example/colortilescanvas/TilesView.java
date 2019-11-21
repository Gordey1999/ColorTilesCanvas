package com.example.colortilescanvas;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class TilesView extends View {
    private ColorTilesGame game;
    final int colorLightTile = getResources().getColor(R.color.colorLightTile);
    final int colorDarkTile = getResources().getColor(R.color.colorDarkTile);

    int width, height; // ширина и высота канвы

    public TilesView(Context context) {
        super(context);
    }

    public TilesView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        game = new ColorTilesGame(4, 4);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = canvas.getWidth();
        height = canvas.getHeight();

        float tileWidth = width / game.getCols();
        float tileHeight = height / game.getRows();

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < game.getRows(); i++)
            for (int j = 0; j < game.getCols(); j++) {

                boolean tile = game.get(i, j);
                paint.setColor(tile ? colorDarkTile : colorLightTile);

                canvas.drawRoundRect(tileWidth * j + 4, tileHeight * i + 4,
                        tileWidth * j + tileWidth - 4, tileHeight * i + tileHeight - 4,
                        20, 20, paint);
            }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        if (event.getAction() != MotionEvent.ACTION_DOWN)
            return true;

        int tileWidth = width / game.getCols();
        int tileHeight = height / game.getRows();

        int row = y / tileHeight;
        int col = x / tileWidth;

        game.switchTile(row, col);
        // если игрок выиграл, то поле увеличивается на 2 по длине и высоте
        if (game.hasWon())
            game.restart(game.getRows() + 2, game.getCols() + 2);

        invalidate();
        return true;
    }
}
