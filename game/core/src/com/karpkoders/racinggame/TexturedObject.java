package com.karpkoders.racinggame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class TexturedObject {
    //region Private fields
    private final TextureRegion textureRegion;
    private final SpriteBatch batch;
    protected final Vector2 position;
    protected float rotation;
    protected float sizeX;
    protected float sizeY;
    protected float offsetX;
    protected float offsetY;
    //endregion

    //region Constructors
    public TexturedObject(final SpriteBatch batch, String texturePath, float sizeX, float sizeY, final Vector2 initialPosition){
        this.batch = batch;
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        if(texturePath != null){
            Texture texture = new Texture(Gdx.files.internal(texturePath));
            textureRegion = new TextureRegion(texture);
        }
        else {
            textureRegion = null;
        }

        offsetX = sizeX/2;
        offsetY = sizeY/2;

        position = initialPosition;
        rotation = 0;
    }

    public TexturedObject(TexturedObjParams o){
        this(o.batch, o.texturePath, o.sizeX, o.sizeY, o.initialPosition);
    }
    //endregion

    //region Parameter Container
    public static class TexturedObjParams{
        public SpriteBatch batch;
        public String texturePath;
        public float sizeX;
        public float sizeY;
        public Vector2 initialPosition;
        public TexturedObjParams(final SpriteBatch batch, String texturePath, float sizeX, float sizeY, final Vector2 initialPosition){
            this.batch = batch;
            this.texturePath = texturePath;
            this.sizeX = sizeX;
            this.sizeY = sizeY;
            this.initialPosition = initialPosition;
        }
    }
    //endregion

    //region Accessors
    public float GetOriginX(){
        return sizeX-offsetX;
    }

    public float GetOriginY(){
        return sizeY-offsetY;
    }

    public Vector2 GetOriginPos(){
        return new Vector2(position.x - offsetX, position.y - offsetY);
    }

    public void SetPosition(Vector2 newPos){
        position.x = newPos.x;
        position.y = newPos.y;
    }
    //endregion

    //region Methods
    public void render(float delta){
        if(textureRegion != null){
            batch.draw(textureRegion,
                    GetOriginPos().x, GetOriginPos().y,
                    GetOriginX(), GetOriginY(),
                    sizeX, sizeY, 1, 1, rotation);
        }
    }
    //endregion
}
