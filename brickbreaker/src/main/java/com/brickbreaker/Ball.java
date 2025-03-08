package com.brickbreaker;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ball extends Circle implements Drawable, Movable, Bounceable {
    private double dx; // 공의 x축 속도 (단위: 픽셀/프레임)
    private double dy; // 공의 y축 속도 (단위: 픽셀/프레임)
    private double tempdx;
    private double tempdy;
    private Color color; // 공의 색상

    // 생성자
    public Ball(double x, double y, double radius, double dx, double dy, Color color) {
        super(x, y, radius);
        this.dx = dx;
        this.dy = dy;
        this.color = color;
    }

    // 공을 그리는 메서드
    @Override
    public void draw(GraphicsContext gc) {
        update();
        gc.setFill(color);
        gc.fillOval(getX() - getRadius(), getY() - getRadius(), getRadius() * 2, getRadius() * 2); // 중심을 기준으로 원 그리기
    }
    // 공의 위치를 업데이트하는 메서드
    public void update() {
        double updateX = getX() + getDx();
        setX(updateX); // x축 위치 업데이트
        double updateY = getY() + getDy();
        setX(updateY); // y축 위치 업데이트
    }

    // 이동 메서드
    @Override
    public void move() {
        setX(getX() + getDx());
        setY(getY() + getDy());
    }
    @Override
    public void pause() {
        tempdx = getDx();
        tempdy = getDy();
        setDx(0);
        setDy(0);
    }
    @Override
    public void resume() {
        setDx(tempdx);
        setDy(tempdy);
    }

    // 튕기는 메서드
    public void bounce(Shape other) {
        if(checkCollision(other)) {
            boolean hitLeft = getX() - getRadius() < other.getMinX() && getX() + getRadius() > other.getMinX();
            boolean hitRight = getX() + getRadius() > other.getMaxX() && getX() - getRadius() < other.getMaxX();
            boolean hitTop = getY() - getRadius() < other.getMinY() && getY() + getRadius() > other.getMinY();
            boolean hitBottom = getY() + getRadius() > other.getMaxY() && getY() - getRadius() < other.getMaxY();

            System.out.println("Collision detected!");
            System.out.println("hitLeft: " + hitLeft + ", hitRight: " + hitRight + ", hitTop: " + hitTop + ", hitBottom: " + hitBottom);


            // 모서리 충돌
            if ((hitLeft || hitRight) && (hitTop || hitBottom)) {
                setDx(-getDx());
                setDy(-getDy());
            }
            // 수직 충돌
            else if (hitTop || hitBottom) {
                setDy(-getDy());
            }
            // 수평 충돌
            else if (hitLeft || hitRight) {
                setDx(-getDx());
            }
        }
    }
    // 충돌 감지 메서드
    @Override
    public boolean checkCollision(Shape other) {
        // 공이 벽돌의 경계와 충돌했는지 확인
        boolean collision =
            other.getMaxX() >= getX() - getRadius() &&
            other.getMinX() <= getX() + getRadius() &&
            other.getMaxY() >= getY() - getRadius() &&
            other.getMinY() <= getY() + getRadius();
        return collision;
    }

    // Getter와 Setter (필요 시 사용)
    public double getDx() {
        return dx;
    }
    public double getDy() {
        return dy;
    }
    public void setDx(double dx) {
        this.dx = dx;
    }
    public void setDy(double dy) {
        this.dy = dy;
    }
}

