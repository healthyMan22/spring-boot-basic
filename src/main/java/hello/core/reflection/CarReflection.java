package hello.core.reflection;

import java.lang.reflect.InvocationTargetException;

public class CarReflection {

    public static void main(String[] args) {

        Class<Car> carClass = Car.class;
        try {
            Car car = carClass.getConstructor(String.class).newInstance("gdgd");
            System.out.println(car.getName());
        } catch(InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("오류발생: " + e.getMessage());
        }
    }

}
