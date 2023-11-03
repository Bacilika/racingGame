package com.karpkoders.racinggame;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public abstract class PhysicsObject {
    protected BodyDef bodyDef;
    protected Body body;
    protected PolygonShape shape;
    protected FixtureDef fixtureDef;
    protected Fixture fixture;
    protected final Vector2 size;
    protected GameScreen gameScreen;
    protected Vector2 pos;
    public float rotation;

    public PhysicsObject(GameScreen gameScreen, BodyDef.BodyType bodyType, Vector2 pos, Vector2 size){
        this.gameScreen = gameScreen;
        this.size = size;
        this.pos = pos;

        bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(pos);
        bodyDef.angle = MathUtils.PI/2f;
        shape = new PolygonShape();
        shape.setAsBox(size.x/2, size.y/2);
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