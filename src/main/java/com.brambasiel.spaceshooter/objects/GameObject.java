package com.brambasiel.spaceshooter.objects;

import com.brambasiel.spaceshooter.states.State;

public abstract class GameObject implements RenderUpdateable {
    public float x, y;

    private State state;
    private boolean isActive;

    public GameObject(float x, float y) {
        this.isActive = true;
        this.x = x;
        this.y = y;
    }

    public GameObject() {
        this(0, 0);
    }

    public void attach(State state) {
        this.state = state;
    }

    public void selfDestruct() {
        isActive = false;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public State getState() {
        if (state == null) {
            throw new IllegalStateException("GameObject isn't attached to any state!");
        }
        return state;
    }
}