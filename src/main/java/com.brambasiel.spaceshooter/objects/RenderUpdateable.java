package com.brambasiel.spaceshooter.objects;

import java.awt.*;

public interface RenderUpdateable {

    void update(float delta, float timePassed);
    void draw(Graphics2D graph);
}
