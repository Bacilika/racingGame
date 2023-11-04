package com.karpkoders.racinggame;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {

    public final RacingGame game;
    private final SpriteBatch batch;
    private final Car car;
    private final Array<Body> bodies;
    private final OrthographicCamera camera;
    Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
    public World world;

    public GameScreen(final RacingGame game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false,1920*Constants.PMR, 1080*Constants.PMR);
        world = new World(new Vector2(0, 0),true);
        batch = game.batch;
        car = new Car(batch, "car.png", this);
        bodies = new Array<>();
        game.gameScreen = this;
        debugRenderer.setDrawVelocities(true);
    }

    @Override
    public void show() {
        float thickness = 0.5f;
        float width = camera.viewportWidth;
        float height = camera.viewportHeight;
        // North wall
        new PhysicsWall(this, new Vector2(width/2,height-thickness/2), width, thickness);
        // East wall
        new PhysicsWall(this, new Vector2(width-thickness/2,height/2), thickness, height);
        // South wall
        new PhysicsWall(this, new Vector2(width/2,thickness/2), width, thickness);
        // West wall
        new PhysicsWall(this, new Vector2(thickness/2,height/2), thickness, height);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(game.clearColor);
        Debug.printablePos.set(0,10);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        bodies.clear();
        world.getBodies(bodies);
        for (Body body : bodies){
            Car car = (Car) body.getUserData();

            if(car != null){
                car.SetPosition(body.getPosition());
                car.rotation = MathUtils.radiansToDegrees * body.getAngle();
            }
        }
        batch.begin();
        car.render(delta);
        batch.end();

        game.UICamera.update();
        batch.setProjectionMatrix(game.UICamera.combined);
        batch.begin();
        car.debugRender();
        batch.end();

        world.step(1/60f,6,2);
        debugRenderer.render(world, camera.combined);
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
    }
}
