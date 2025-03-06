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

    // 공과 충돌 여부 확인
    public boolean checkCollision(Ball ball) {
        double ballX = ball.getX();
        double ballY = ball.getY();
        double ballRadius = ball.getRadius();

        // 공이 벽돌의 경계와 충돌했는지 확인
        boolean collision =
            ballX + ballRadius > getX() - getWidth() / 2 &&
            ballX - ballRadius < getX() + getWidth() / 2 &&
            ballY + ballRadius > getY() - getHeight() / 2 &&
            ballY - ballRadius < getY() + getHeight() / 2;

        return collision;
    }
}
