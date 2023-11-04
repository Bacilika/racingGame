package com.karpkoders.racinggame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Car extends PhysicsObject{
    private final RaceTrack currentTrack;
    private final int MAX_VELOCITY = 1000;
    private Vector2 vel;


    public Car(final SpriteBatch batch, String textureName, GameScreen gameScreen){
        super(gameScreen, BodyDef.BodyType.DynamicBody,
                new TexturedObjParams(batch, textureName, 64*Constants.PMR, 64*Constants.PMR, new Vector2(30,15)));

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
        super.render(delta);
        readInput();
    }
    private void readInput(){
        float cosx = MathUtils.cosDeg(body.getAngle()*MathUtils.radiansToDegrees);
        float siny = MathUtils.sinDeg(body.getAngle()*MathUtils.radiansToDegrees);
        if (Gdx.input.isKeyPressed(Input.Keys.W) && vel.x > -MAX_VELOCITY) {
            body.applyForce(20*cosx, 20*siny, position.x, position.y, true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S) && vel.x < MAX_VELOCITY) {
            body.applyForce(-20*cosx, -20f*siny, position.x, position.y, true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) && vel.x > -MAX_VELOCITY) {
            body.applyAngularImpulse(0.01f,true);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D) && vel.x < MAX_VELOCITY) {
            body.applyAngularImpulse(-0.01f,true);
        }
    }
}
