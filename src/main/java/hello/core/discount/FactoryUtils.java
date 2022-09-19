package hello.core.discount;

import java.util.NoSuchElementException;
import org.springframework.util.ObjectUtils;

public class FactoryUtils {

    public static <T> T getBean(T bean) {

        if(ObjectUtils.isEmpty(bean)) {
            throw new NoSuchElementException();
        }

        return bean;
    }

}
