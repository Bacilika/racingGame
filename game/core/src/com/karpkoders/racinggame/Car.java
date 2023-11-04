package com.karpkoders.racinggame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Car extends PhysicsObject{
    private final TextureRegion textureRegion;
    private final RaceTrack currentTrack;
    private final SpriteBatch batch;
    private final int MAX_VELOCITY = 1000;
    private final Vector2 origin;
    private Vector2 vel;
    private float offset;


    public Car(final SpriteBatch batch, String textureName, GameScreen gameScreen){
        super(gameScreen, BodyDef.BodyType.DynamicBody,new Vector2(30,15), new Vector2(64*Constants.PMR, 64*Constants.PMR));

        this.batch = batch;
        Texture texture = new Texture(Gdx.files.internal(textureName));
        textureRegion = new TextureRegion(texture, 32,32);
        offset = size.x/2;
        origin = new Vector2(size.x/2, size.y/2);
        currentTrack = gameScreen.game.currentTrack;

        // Physics setup
        bodyDef.angularDamping = 0.01f;
        createBody();
        body.setUserData(this);

        fixtureDef.density = 0.5f;
        fixtureDef.friction = 2f;
        fixtureDef.restitution = 0.0f;
        createFixtures();
        fixture.setUserData(this);
        vel = body.getLinearVelocity();
    }

    public void render(float delta){
        batch.draw(textureRegion, pos.x-offset, pos.y-offset, origin.x, origin.y, size.x, size.y, 1, 1, rotation-90);
        readInput();
    }
    private void readInput(){
        float cosx = MathUtils.cosDeg(body.getAngle()*MathUtils.radiansToDegrees);
        float siny = MathUtils.sinDeg(body.getAngle()*MathUtils.radiansToDegrees);
        if (Gdx.input.isKeyPressed(Input.Keys.W) && vel.x > -MAX_VELOCITY) {
            body.applyForce(20*cosx, 20*siny, pos.x, pos.y, true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S) && vel.x < MAX_VELOCITY) {
            body.applyForce(-20*cosx, -20f*siny, pos.x, pos.y, true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) && vel.x > -MAX_VELOCITY) {
            body.applyAngularImpulse(0.01f,true);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D) && vel.x < MAX_VELOCITY) {
            body.applyAngularImpulse(-0.01f,true);
        }
    }
}
