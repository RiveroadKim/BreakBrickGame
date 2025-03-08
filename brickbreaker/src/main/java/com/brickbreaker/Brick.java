package com.brickbreaker;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Brick extends Rectangle implements Drawable, Breakable {
    private Color color; // 벽돌의 색상
    private boolean isDestroyed; // 벽돌이 파괴되었는지 여부
    
    // 생성자
    public Brick(double x, double y, double width, double height, Color color) {
        super(x, y, width, height);
        this.color = color;
        isDestroyed = false; // 초기 상태는 파괴되지 않음
    }
    
    // 벽돌을 그리는 메서드
    @Override
    public void draw(GraphicsContext gc) {
        if (!isDestroyed) {
            gc.setFill(color);
            gc.fillRect(getX(), getY(), getWidth(), getHeight()); // 벽돌 그리기
        }
    }
    
    // 공과 충돌 여부 확인
    @Override
    public boolean checkCollision(Shape other) {
        // 공이 벽돌의 경계와 충돌했는지 확인
        boolean collision =
            other.getMaxX() >= getX() - getWidth() &&
            other.getMinX() <= getX() + getWidth() &&
            other.getMaxY() >= getY() - getHeight() &&
            other.getMinY() <= getY() + getHeight();

        return collision;
    }
    
    // Getter와 Setter (필요 시 사용)
    public boolean getisDestroyed() {
        return isDestroyed;
    }
}
