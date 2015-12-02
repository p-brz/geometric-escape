/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.game.prototypes;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.example.game.drawable.DimensionedTexture;
import org.example.game.steering.SteeringBehavior;

/**
 *
 * @author alisonbnt
 */
public class PrototypeGamePlayer extends ApplicationAdapter {

    protected SpriteBatch batch;
    protected TheChosenOnePlayer player;
    protected SteeringBehavior behavior;

    @Override
    public void create() {
        batch = new SpriteBatch();
        Texture img = new Texture("badlogic.jpg");
        DimensionedTexture texture = new DimensionedTexture(img, 32, 32);
        behavior = createBehavior();
        player = new TheChosenOnePlayer(texture);
        player.setBehavior(behavior);
    }

    protected SteeringBehavior createBehavior() {
        return null;
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 0.7f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update();
        draw();
    }

    protected void update() {
        player.update(Gdx.graphics.getDeltaTime());
    }

    protected void draw() {
        batch.begin();
        drawAt(batch);
        batch.end();
    }

    protected void drawAt(SpriteBatch batch) {
        player.draw(batch);
        System.out.println("Player at " + player.getCharacter().getPosition());
    }

    protected void updateBehavior() {
    }

}
