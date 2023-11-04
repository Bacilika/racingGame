package com.karpkoders.racinggame;

import com.badlogic.gdx.physics.box2d.*;

public abstract class PhysicsObject extends TexturedObject{
    protected BodyDef bodyDef;
    protected Body body;
    protected PolygonShape shape;
    protected FixtureDef fixtureDef;
    protected Fixture fixture;
    protected GameScreen gameScreen;

    public PhysicsObject(GameScreen gameScreen, BodyDef.BodyType bodyType, TexturedObjParams textureParams){
        super(textureParams);
        this.gameScreen = gameScreen;

        bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(position);
        bodyDef.angle = 0;
        shape = new PolygonShape();
        shape.setAsBox(sizeX/2, sizeY/3);
        fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;

    }

    public void createBody(){
        body = gameScreen.world.createBody(bodyDef);
    }

    public void createFixtures(){
        fixture = body.createFixture(fixtureDef);
        shape.dispose();
    }
}