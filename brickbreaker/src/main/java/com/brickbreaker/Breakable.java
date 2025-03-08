package com.brickbreaker;

interface Breakable {
    boolean isDestroyed = false;

    boolean getisDestroyed();
    boolean checkCollision(Shape shape);
}
