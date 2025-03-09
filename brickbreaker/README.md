# BrickBreaker 게임 제작

## 구성 파일

### Main
 - 작동 함수
 - ArrayList로 각 도형 생성 및 관리

### Shape
 - 각 모형의 상위 클래스

#### Rectangle, Circle
 - 사각형 모형, 원 모형 클래스
 - x, y, width, height, radius 의 getter, setter 구현

#### Ball, Brick, Paddle, Wall
 - 게임의 구성요소
 - 공 - Drawable, Movable, Bouncealbe
 - 벽돌 - Drawable, Breakable
 - 패들 - Drawable, Movable
 - 벽 - Drawable

### Drawable, Movable, Bounceable, Breakable 인터페이스
 - 각 도형의 특징을 구현하는 인터페이스
 - Drawable : draw() : 그리기
 - Movable : move()-이동, pause()-일시정지, resume()-계속하기
 - Bounceable : bounce()-튕기기기
 - Breakable : getisDestroyed()-파괴 확인