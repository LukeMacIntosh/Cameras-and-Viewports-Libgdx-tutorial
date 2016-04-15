package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class CameraExample extends ApplicationAdapter implements InputProcessor {
    SpriteBatch batch;
    Sprite sprBackGround;
    OrthographicCamera orthoCam;
    float fGameworldWidth = 100, fGameworldHeight = 50;
    Viewport viewport;

    @Override
    public void create() {
        batch = new SpriteBatch();
        sprBackGround = new Sprite(new Texture("TheWorld.png"));
        sprBackGround.setSize(fGameworldWidth, fGameworldHeight);
        float aspectRatio = (float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth();
        orthoCam = new OrthographicCamera();
        viewport = new FitViewport(fGameworldWidth * aspectRatio, fGameworldHeight, orthoCam);
        // ^ to change how this looks you can mess around width he aspect ratio in the desktop launcher and different viewports like FitViewport, StretchViewport, ScreenViewport etc.
        viewport.apply();
        orthoCam.position.set(fGameworldWidth / 2, fGameworldHeight / 2, 0);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        orthoCam.position.set(fGameworldWidth / 2, fGameworldHeight / 2, 0);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        orthoCam.update();
        batch.begin();
        batch.setProjectionMatrix(orthoCam.combined);
        sprBackGround.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        sprBackGround.getTexture().dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.LEFT) {
            orthoCam.translate(-1f, 0f);
        } else if (keycode == Input.Keys.RIGHT) {
            orthoCam.translate(1f, 0f);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}