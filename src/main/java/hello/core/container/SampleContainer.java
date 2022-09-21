package hello.core.container;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class SampleContainer {

    public static <T> T getObject(Class<T> clazz)
    {
        T instance = createInstance(clazz);

        for (Field field : clazz.getDeclaredFields())
        {
            if (field.getDeclaredAnnotation(Inject.class) != null)
            {
                Object filedInstance = createInstance(field.getType());
                try
                {
                    field.setAccessible(true);
                    field.set(instance, filedInstance);
                }
                catch (IllegalAccessException e)
                {
                    throw new RuntimeException("fail to set field", e);
                }
            }
        }

        return instance;
    }

    private static <T> T createInstance(Class<T> clazz)
    {
        try
        {
            return clazz.getConstructor().newInstance();
        }
        catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e)
        {
            throw new RuntimeException("fail to create instance", e);
        }
    }


}
