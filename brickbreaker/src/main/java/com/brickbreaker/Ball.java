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
        setY(updateY); // y축 위치 업데이트
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

    public void bounce(Shape other) {
        if (checkCollision(other)) {
            if((getX() + getRadius() <= other.getMinX() || getX() - getRadius() >= other.getMaxX())){
                if(getY() <= other.getMaxY() - getRadius() && getY() >= other.getMinY() + getRadius()) {
                    setDx(-dx);
                }
                else {
                    setDx(-dx);
                    setDy(-dy);
                }
            }
            else {
                if(getX() <= other.getMaxX() - getRadius() && getX() >= other.getMinX() + getRadius()) {
                    setDy(-dy);
                }
                else {
                    setDx(-dx);
                    setDy(-dy);
                }
            }
        }
    }

// 충돌 감지 메서드
@Override
public boolean checkCollision(Shape other) {
    return (this.getX() + this.getRadius() >= other.getMinX() &&
            this.getX() - this.getRadius() <= other.getMaxX() &&
            this.getY() + this.getRadius() >= other.getMinY() &&
            this.getY() - this.getRadius() <= other.getMaxY());
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

