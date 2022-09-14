package hello.core.order.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingleTonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() throws Exception {

        // given
        AppConfig appConfig = new AppConfig();

        // when
        MemberService memberService1 = appConfig.memberService(); // 조회: 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService(); // 조회: 호출할 때 마다 객체를 생성

        // then
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isNotSameAs(memberService2);
    }


    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() throws Exception {

        // when
        SingleTonService singleTonService1 = SingleTonService.getInstance();
        SingleTonService singleTonService2 = SingleTonService.getInstance();

        // then
        System.out.println("singleTonService1 = " + singleTonService1);
        System.out.println("singleTonService2 = " + singleTonService2);

        // same ==
        // equalTo equals 메소드 실행
        assertThat(singleTonService1).isSameAs(singleTonService2);
    }


    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() throws Exception {

        // given
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // when
        MemberService memberService1 = ac.getBean("memberService", MemberService.class); // 조회: 호출할 때 마다 객체를 생성
        MemberService memberService2 = ac.getBean("memberService", MemberService.class); // 조회: 호출할 때 마다 객체를 생성

        // then
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isSameAs(memberService2);
    }



}
