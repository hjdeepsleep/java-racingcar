package racinggame.domain.car;

/**
 * 1. 움직이는 거리 필드
 * 1. 자동차 이름 필드
 * 1. 게임 진행시 자동차 이동
 * 1. 정렬
 */
public class Car implements Comparable<Car> {

    private static final int RACING_CAR_MOVING_BOUNDARY = 4;

    private final String carName;
    private final int moveRange;

    public Car(String carName) {
        this(carName, 0);
    }

    public Car(String carName, int moveRange) {
        this.carName = carName;
        this.moveRange = moveRange;
    }

    //객체 복사
    public Car(Car car) {
        this.carName = car.carName;
        this.moveRange = car.moveRange;
    }

    public Car moveCar(int num) {
        if (num >= RACING_CAR_MOVING_BOUNDARY) {
            return new Car(this.carName, this.moveRange + 1);
        }
        return this;
    }

    public int compareMoveRange(int range) {
        if (this.moveRange < range) {
            return range;
        }

        return this.moveRange;
    }

    public boolean isMovedFarThan(int compare) {
        return this.moveRange >= compare;
    }

    @Override
    public int compareTo(Car o) {
        return Integer.compare(this.moveRange, o.getMoveRange());
    }

    public int getMoveRange() {
        return this.moveRange;
    }

    public String getCarName() {
        return this.carName;
    }
}
