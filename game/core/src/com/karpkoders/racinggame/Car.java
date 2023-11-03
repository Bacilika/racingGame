package com.karpkoders.racinggame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Car {
    private final Texture texture;
    private final Rectangle hitbox;
    private RaceTrack currentTrack;
    private final SpriteBatch batch;

    private final int offset;

    public Car(final SpriteBatch batch, String textureName, RacingGame game){

        this.batch = batch;
        texture = new Texture(Gdx.files.internal(textureName));
        currentTrack = game.currentTrack;
        hitbox = new Rectangle();
        hitbox.set(currentTrack.startPos.x,currentTrack.startPos.y,32,32);
        offset = (int) (hitbox.height/2);
    }

    public void render(float delta){
        batch.draw(texture,hitbox.x-offset,hitbox.y-offset, hitbox.width,hitbox.height);
    }
}
