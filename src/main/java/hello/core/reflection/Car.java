package hello.core.reflection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Car {

    private String name;


    public Car(String name) {
        System.out.println("car 생성자 실행됨");
        this.name = name;
    }
}
