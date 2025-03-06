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
        double updateX = getX() + dx;
        setX(updateX); // x축 위치 업데이트
        double updateY = getY() + dy;
        setX(updateY); // y축 위치 업데이트
    }

    // 튕기는 함수
    public void bounce(Shape other) {
        if(isCollisionDetected(other)) {
            if((getX() + getRadius() == other.getMinX() || getX() - getRadius() == other.getMaxX())){
                if(getY() < other.getMaxY() - getRadius() && getY() > other.getMinY() + getRadius()) {
                    setDx(-dx);
                }
                else {
                    setDx(-dx);
                    setDy(-dy);
                }
            }
            else {
                if(getX() < other.getMaxX() - getRadius() && getX() > other.getMinX() + getRadius()) {
                    setDy(-dy);
                }
                else {
                    setDx(-dx);
                    setDy(-dy);
                }
            }
        }
    }

    // 이동 함수
    @Override
    public void move() {
        setX(getX() + dx);
        setY(getY() + dy);
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
    @Override
    public boolean isCollisionDetected(Shape other) {
        double otherMaxX= other.getMaxX();
        double otherMaxY = other.getMaxY();
        double otherMinX = other.getMinX();
        double otherMinY = other.getMinY();

        // 공이 벽돌의 경계와 충돌했는지 확인
        boolean collision =
            otherMaxX > getX() - getRadius() &&
            otherMinX < getX() + getRadius() &&
            otherMaxY > getY() - getRadius() &&
            otherMinY < getY() + getRadius();

        if(collision) {
            setX(otherMaxX - getRadius());
            setY(otherMaxY - getRadius());
        }
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

