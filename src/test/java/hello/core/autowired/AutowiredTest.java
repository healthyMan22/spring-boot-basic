package hello.core.autowired;

import java.util.Optional;
import hello.core.member.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }
    
    @Test
    void autowiredNullable() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean2.class);

        TestBean2 testBean2 = ac.getBean(TestBean2.class);
        System.out.println("testBean2.getMember() = " + testBean2.getMember());
    }
    

    static class TestBean {

        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired
        public void SetNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }

    }
    
    @Getter
    @RequiredArgsConstructor
    static class TestBean2 {
        
        @Nullable
        private final Member member;
    }

}
