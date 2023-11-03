package com.karpkoders.racinggame;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {

    private final RacingGame game;
    private final SpriteBatch batch;
    private final Car car;


    public GameScreen(final RacingGame game){
        this.game = game;
        batch = game.batch;
        car = new Car(batch,"badlogic.jpg",game);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(game.clearColor);
        batch.begin();
        car.render(delta);
        batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
