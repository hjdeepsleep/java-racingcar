package racinggame.domain.car;

import racinggame.util.NumberUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Cars {

    private static final int RANDOM_BOUND = 10;

    private final List<Car> cars;

    public Cars(List<Car> cars) {
        this.cars = Collections.unmodifiableList(new ArrayList<>(cars));
    }

    public static Cars newRacingCarsFrom(List<String> carNames) {
        List<Car> cars = carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());
        return new Cars(cars);
    }

    public Cars moveCarAll() {
        List<Car> cars = this.cars.stream()
                .map(car -> car.moveCar(NumberUtil.createRandomInt(RANDOM_BOUND)))
                .collect(Collectors.toList());
        return new Cars(cars);
    }

    /**
     * 0은 크기를 비교하기 위해 의미 없이 임시로 사용하는 값인데
     * 이런 매직 넘버까지 위에서 상수로 선언 해야 할까요?
     */
    private int getMaxMoveRange() {
        return cars.stream()
                .mapToInt(Car::getPosition)
                .filter(car -> car >= 0)
                .max()
                .orElse(0);
    }

    public List<String> findWinner() {
        int range = getMaxMoveRange();
        return cars.stream()
                .filter(car -> car.isMovedFarThan(range))
                .map(Car::getCarName)
                .collect(Collectors.toList());
    }

    public List<Car> getCars() {
        return new ArrayList<>(this.cars);
    }
}
