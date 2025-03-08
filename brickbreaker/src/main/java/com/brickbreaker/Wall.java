package com.brickbreaker;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Wall extends Rectangle implements Drawable {
    private Color color;
    // 생성자
    public Wall(double x, double y, double width, double height, Color color) {
        super(x, y, width, height);
        this.color = color;
    }

    // 벽을 그리는 메서드
    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(getX(), getY(), getWidth(), getHeight()); // 중심을 기준으로 사각형 그리기
    }
}
