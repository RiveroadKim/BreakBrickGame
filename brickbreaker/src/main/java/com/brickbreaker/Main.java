package com.brickbreaker;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {

    private boolean moveLeft = false;
    private boolean moveRight = false;
    private boolean gameFinished = false;

    @Override
    public void start(Stage primaryStage) {
        // Canvas 생성
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Shape 객체 리스트 생성
        List<Shape> shapes = new ArrayList<>();
        // Ball 생성
        shapes.add(new Ball(canvas.getWidth() / 2, canvas.getHeight() / 2, 10, 1, 1, Color.RED));
        // Paddle 생성
        shapes.add(new Paddle(canvas.getWidth() / 2, canvas.getHeight() - 100, 100, 20, 5, Color.BLUE));
        // Wall 생성
        double wallWidth = 20;
        double wallHeight = 20;
        shapes.add(new Wall(0, 0, wallWidth, canvas.getHeight(), Color.BLUE));
        shapes.add(new Wall(canvas.getWidth() - wallWidth, 0, wallWidth, canvas.getHeight(), Color.BLUE));
        shapes.add(new Wall(wallWidth, 0, canvas.getWidth() - wallWidth * 2, wallHeight, Color.BLUE));
        shapes.add(new Wall(wallWidth, canvas.getHeight() - wallHeight, canvas.getWidth() - wallWidth * 2, wallHeight, Color.RED));
        // 벽돌 생성
        int rows = 5;
        int cols = 8;
        double brickWidth = 70;
        double brickHeight = 20;
        double padding = 5;
        double startX = 100;
        double startY = 100;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                double x = startX + col * (brickWidth + padding);
                double y = startY + row * (brickHeight + padding);
                shapes.add(new Brick(x, y, brickWidth, brickHeight, Color.BLUE));
            }
        }

        // 게임 루프
        AnimationTimer gameLoop = new AnimationTimer() {

            @Override
            public void handle(long now) {
                if (gameFinished) {
                    return;
                }

                // 화면 초기화
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

                // Drawable한 객체 draw
                for(Shape shape : shapes) {
                    // Drawable
                    if(shape instanceof Drawable) {
                        Drawable drawable = (Drawable) shape;
                        drawable.draw(gc);
                    }
                    // Movable
                    if(shape instanceof Movable) {
                        Movable movable = (Movable) shape;
                        // Ball
                        if(shape instanceof Bounceable) {
                            Bounceable bounceable = (Bounceable) shape;
                            movable.move();
                            for(Shape checkShape : shapes) {
                                if(checkShape == shape) {
                                    continue;
                                }
                                bounceable.bounce(checkShape);
                            }
                            
                            if(shape.getY() > canvas.getHeight()) {
                                gameFinished = true;
                                showGameOverPopup();
                            }
                        }
                        // Paddle
                        else{
                            if(moveLeft == true) {
                                movable.setDx(-movable.getDx());
                                movable.move();
                                movable.setDx(-movable.getDx());
                            }
                            if(moveRight == true) {
                                movable.setDx(movable.getDx());
                                movable.move();
                            }
                        }
                    }
                    // Brick
                    if(shape instanceof Breakable) {
                        Breakable breakable = (Breakable) shape;
                        
                        boolean isDestroyed = false;
                        if(breakable.checkCollision(shapes.get(0))) {
                            isDestroyed = true;
                        }
                        if(isDestroyed) {
                            shapes.remove(shape);
                        }
                    }         
                }
            }
        };
        gameLoop.start();

        // 레이아웃 설정
        StackPane root = new StackPane();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root, 800, 600);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                moveLeft = true;
            }
            else if (event.getCode() == KeyCode.RIGHT) {
                moveRight = true;
            }
        });

        scene.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                moveLeft = false;
            }
            else if (event.getCode() == KeyCode.RIGHT) {
                moveRight = false;
            }
        });

        // Stage 설정
        primaryStage.setTitle("Brick Breaker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showGameOverPopup() {
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.INFORMATION, "Game Over! Thank you for playing.", ButtonType.OK);
            alert.setTitle("Game Over");
            alert.setHeaderText(null);

            // 팝업 닫기 후 게임 종료
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    Platform.exit(); // 게임 종료
                }
            });
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
