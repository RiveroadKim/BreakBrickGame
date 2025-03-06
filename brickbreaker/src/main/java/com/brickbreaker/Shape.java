package com.brickbreaker;

public abstract class Shape {
    private double x;
    private double y;

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }

    abstract public double getMaxX();
    abstract public double getMaxY();
    abstract public double getMinX();
    abstract public double getMinY();
}

class Circle extends Shape {
    private double radius;

    Circle(double x, double y, double r) {
        setX(x);
        setY(y);
        this.radius = r;
    }

    public double getRadius() {
        return radius;
    }
    public void setRadius(double radius) {
        this.radius = radius;
    }
    @Override
    public double getMinX() {
        return getX() - radius;
    }
    @Override
    public double getMinY() {
        return getY() + radius;
    }
    @Override
    public double getMaxX() {
        return getX() + radius;
    }
    @Override
    public double getMaxY() {
        return getY() - radius;
    }
}

class Rectangle extends Shape {
    private double width;
    private double height;

    Rectangle(double x, double y, double width, double height) {
        setX(x);
        setY(y);
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }
    public double getHeight() {
        return height;
    }
    public void setWidth(double width) {
        this.width = width;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    @Override
    public double getMinX() {
        return getX() - width / 2;
    }
    @Override
    public double getMinY() {
        return getY() + height / 2;
    }
    @Override
    public double getMaxX() {
        return getX() + width / 2;
    }
    @Override
    public double getMaxY() {
        return getY() - height / 2;
    }
}
