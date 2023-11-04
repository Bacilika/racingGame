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
    private Boolean reversing = false;


    public Car(final SpriteBatch batch, String textureName, GameScreen gameScreen){
        super(gameScreen, BodyDef.BodyType.DynamicBody,
                new TexturedObjParams(batch, textureName, 64*Constants.PMR, 64*Constants.PMR, new Vector2(30,15)));

        currentTrack = gameScreen.game.currentTrack;

        // Physics setup
        bodyDef.angularDamping = 0.9f;
        bodyDef.linearDamping = 0.9f;
        createBody();
        body.setUserData(this);

        fixtureDef.density = 1f;
        fixtureDef.friction = 0.9f;
        fixtureDef.restitution = 0.0f;
        createFixtures();
        fixture.setUserData(this);
        vel = body.getLinearVelocity();
    }

    public void render(float delta){
        super.render(delta);
        vel = body.getLinearVelocity();
        readInput();
        if(Math.abs(body.getAngularVelocity()) > 0.3) {applyCentripetalForce();}
    }
    private float getBodyAngle(){
        float angle = body.getAngle()*MathUtils.radiansToDegrees%360;
        if(angle <0){
            angle = 360 + angle;
        }
        return angle;
    }

    private void applyCentripetalForce(){
        float speed = body.getLinearVelocity().len();
        float centripetalForce = body.getMass()*speed;
        float centripetalAngle = getBodyAngle()+90;
        if(body.getAngularVelocity() < 0 && !reversing){
            centripetalAngle = getBodyAngle()-90;
        }
        else if(body.getAngularVelocity() < 0 && reversing){
            centripetalAngle = getBodyAngle()+90;
        }
        else if(body.getAngularVelocity() > 0 && !reversing){
            centripetalAngle = getBodyAngle()+90;
        }
        else if(body.getAngularVelocity() > 0 && reversing){
            centripetalAngle = getBodyAngle()-90;
        }

        Vector2 centripetalVector = new Vector2(MathUtils.cosDeg(centripetalAngle)*centripetalForce,MathUtils.sinDeg(centripetalAngle)*centripetalForce);
        body.applyForceToCenter(centripetalVector,true);
    }

    public void debugRender(){
        Debug.print(gameScreen,"Angular Vel: " +body.getAngularVelocity());
        Debug.print(gameScreen,"Body Angle: " + getBodyAngle());
        Debug.print(gameScreen,"(Cos, sin): (" + MathUtils.cosDeg(getBodyAngle()) +", "+ MathUtils.sinDeg(getBodyAngle()) +")");
        Debug.print(gameScreen,position.x + ", " + position.y );
    }

    private void readInput(){
        float cosx = MathUtils.cosDeg(getBodyAngle());
        float siny = MathUtils.sinDeg(getBodyAngle());
        if ((Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) && vel.x > -MAX_VELOCITY) {
            body.applyForceToCenter(50*cosx, 50*siny, true);
            reversing = false;
        }
        if ((Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) && vel.x > -MAX_VELOCITY) {
            body.applyForceToCenter(-10*cosx, -10f*siny, true);
            reversing = true;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            body.applyAngularImpulse(0.1f, true);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            body.applyAngularImpulse(-0.1f,true);
        }
    }
}
