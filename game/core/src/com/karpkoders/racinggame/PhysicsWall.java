package com.karpkoders.racinggame;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
public class PhysicsWall extends PhysicsObject {

    public PhysicsWall(GameScreen gameScreen, Vector2 position, float sizeX, float sizeY){
        super(gameScreen, BodyDef.BodyType.StaticBody, new TexturedObjParams(gameScreen.game.batch, null,sizeX,sizeY,position));

        createBody();
        createFixtures();
    }
}
