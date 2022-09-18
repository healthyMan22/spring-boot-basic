package hello.core.order.singleton;

public class SingleTonService {

    private static final SingleTonService instance = new SingleTonService();


    private SingleTonService() {
    }


    public static SingleTonService getInstance() {
        return instance;
    }

}
