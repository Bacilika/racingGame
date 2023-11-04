package com.karpkoders.racinggame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RacingGame extends Game {
	public SpriteBatch batch;
	public Color clearColor;
	public RaceTrack currentTrack;
	public GameScreen gameScreen;
	public OrthographicCamera UICamera;

	@Override
	public void create () {
		batch = new SpriteBatch();
		clearColor = new Color(79/255f, 132/255f, 36/255f, 1);
		currentTrack = new RaceTrack();
		UICamera = new OrthographicCamera();
		UICamera.setToOrtho(false,1920,1080);
		this.setScreen(new GameScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		if(gameScreen != null){
			gameScreen.dispose();
		}
		Debug.generator.dispose();
	}
}
