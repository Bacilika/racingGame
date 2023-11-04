package com.karpkoders.racinggame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;


public class Debug {

    public static FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Roboto-Regular.ttf"));
    public static FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    public static Vector2 printablePos = new Vector2(0,10);


    public static void print(GameScreen screen, String debugText){
        parameter.size = 12;
        parameter.color = Color.BLACK;
        BitmapFont font12 = generator.generateFont(parameter);
        font12.setUseIntegerPositions(false);
        font12.draw(screen.game.batch, debugText, printablePos.x,printablePos.y);
        printablePos.set(printablePos.x, printablePos.y+20);
    }
}
