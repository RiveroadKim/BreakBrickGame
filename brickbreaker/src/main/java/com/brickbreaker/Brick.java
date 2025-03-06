package com.brickbreaker;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Brick extends Rectangle implements Drawable, Breakable {
    private Color color; // 벽돌의 색상
    private static boolean isDestroyed; // 벽돌이 파괴되었는지 여부
    
        // 생성자
        public Brick(double x, double y, double width, double height, Color color) {
            super(x, y, width, height);
            this.color = color;
            Brick.isDestroyed = false; // 초기 상태는 파괴되지 않음
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
        public boolean checkCollision(Ball ball) {
            double ballX = ball.getX();
            double ballY = ball.getY();
            double ballRadius = ball.getRadius();
    
            // 공이 벽돌의 경계와 충돌했는지 확인
            boolean collision =
                ballX + ballRadius > getX() &&
                ballX - ballRadius < getX() + getWidth() &&
                ballY + ballRadius > getY() &&
                ballY - ballRadius < getY() + getHeight();
    
            if (collision) {
                isDestroyed = true; // 벽돌 파괴
            }
    
            return collision;
        }
    
        // Getter와 Setter (필요 시 사용)
        public static boolean isDestroyed() {
            return isDestroyed;
    }
}
