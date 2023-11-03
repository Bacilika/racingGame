package com.karpkoders.racinggame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RacingGame extends Game {
	public SpriteBatch batch;
	public Color clearColor;
	public RaceTrack currentTrack;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		clearColor = new Color(79/255f, 132/255f, 36/255f, 1);
		currentTrack = new RaceTrack();

		this.setScreen(new GameScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
