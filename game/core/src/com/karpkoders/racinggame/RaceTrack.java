package com.karpkoders.racinggame;

import com.badlogic.gdx.math.Vector2;

public class RaceTrack {
    public Vector2 startPos;

    public RaceTrack(){
        startPos = new Vector2(ScreenUtils.getFrameBufferTexture().getRegionWidth()/2f, ScreenUtils.getFrameBufferTexture().getRegionHeight()/2f);
    }
}
