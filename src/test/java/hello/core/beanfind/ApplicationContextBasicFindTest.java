package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);


    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() throws Exception {

        // when
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        // then
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }


    @Test
    @DisplayName("이름 없이 타입으로 조회")
    void findBeanByType() throws Exception {

        // when
        MemberService memberService = ac.getBean(MemberService.class);

        // then
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }


    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2() throws Exception {

        // when
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);

        // then
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }


    @Test
    @DisplayName("빈 이름으로 조회가 안된다")
    void findBeanByNameFail() throws Exception {

        assertThrows(NoSuchBeanDefinitionException.class, () -> {
            MemberService memberService = ac.getBean("xxxx", MemberService.class);
        });
    }

}
