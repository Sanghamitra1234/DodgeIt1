package com.sleepygamers.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import States.GameStateManager;
import States.SplashState;

public class DodgeIt extends ApplicationAdapter {
    SpriteBatch batch;
    public static final int HIGHT = 800;
    public static final int WIDTH = 480;
    public static Preferences preferences;
    private GameStateManager gameStateManager;

    @Override
    public void create() {
        preferences = Gdx.app.getPreferences("PreferenceName");
        if (!preferences.contains("highScore")) {
            preferences.putInteger("highScore", 0);
        }
        if (!preferences.contains("highScoreHard")) {
            preferences.putInteger("highScoreHard", 0);
        }
        if (!preferences.contains("highScoreTime")) {
            preferences.putInteger("highScoreTime", 0);
        }
        preferences.flush();
        batch = new SpriteBatch();
        gameStateManager = new GameStateManager();
        gameStateManager.push(new SplashState(gameStateManager));

        Gdx.gl.glClearColor(1, 1, 1, 1);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameStateManager.update(Gdx.graphics.getDeltaTime());
        gameStateManager.render(batch);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
