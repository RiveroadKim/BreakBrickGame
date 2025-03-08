package com.brickbreaker;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Paddle extends Rectangle implements Drawable, Movable {
    private double dx; // 패들의 이동 속도
    private double dy = 0;
    private static double tempdx;
    private static double tempdy;
    private Color color; // 패들의 색상

    // 생성자
    public Paddle(double x, double y, double width, double height, double dx, Color color) {
        super(x, y, width, height);
        this.dx = dx;
        this.color = color;
    }

    // 패들을 그리는 메서드
    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight()); // 중심을 기준으로 사각형 그리기
    }

    // 패들이 화면 경계를 벗어나지 않도록 제한
    public void checkBounds(double canvasWidth) {
        if (getX() - getWidth() / 2 < 0) { // 왼쪽 경계
            setX(getWidth() / 2);
        }
        else if (getX() + getWidth() / 2 > canvasWidth) { // 오른쪽 경계
            setX(canvasWidth - getWidth() / 2);
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

    // Getter, Setter
    @Override
    public double getDx() {
        return dx;
    }
    @Override
    public double getDy() {
        return dy;
    }
    @Override
    public void setDx(double dx) {
        this.dx = dx;
    }
    @Override
    public void setDy(double dy) {
        this.dy = dy;
    }
}
