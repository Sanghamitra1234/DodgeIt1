package com.sleepygamers.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import States.GameStateManager;
import States.SplashState;

public class DodgeIt extends ApplicationAdapter {
    SpriteBatch batch;
    public static final int HIGHT = 800;
    public static final int WIDTH = 480;
    public static Preferences preferences;
    public static int MUSICON = 1;
    public static int SOUNDON = 1;
    public static int ggg = 0;
    public static int chk5 = 0;
    public static Music music;
    private GameStateManager gameStateManager;

    public static PlayServices playServices;

    public DodgeIt(PlayServices playServices) {
        this.playServices = playServices;
    }

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
        music = Gdx.audio.newMusic(Gdx.files.internal("mus2.mp3"));
        music.setLooping(true);
        batch = new SpriteBatch();
        gameStateManager = new GameStateManager();
        gameStateManager.push(new SplashState(gameStateManager, this));

        Gdx.gl.glClearColor(0.8705f, 0.8745f, 0.8078f, 1);
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
