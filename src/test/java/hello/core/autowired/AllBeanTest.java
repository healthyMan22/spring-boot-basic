package hello.core.autowired;

import java.util.List;
import java.util.Map;
import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AllBeanTest {

    // https://velog.io/@taeni-develop/Spring-%ED%8C%A9%ED%86%A0%EB%A6%AC%ED%8C%A8%ED%84%B4%EC%9D%84-%ED%99%9C%EC%9A%A9%ED%95%9C-%EC%84%9C%EB%B9%84%EC%8A%A4-%EA%B4%80%EB%A6%AC-8mvd0mqy

    @Test
    void findAllBean() {

        // given
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        // when
        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        int discountFixPrice = discountService.discount(member, 1000, "fixDiscountPolicy");
        int discountRatePrice = discountService.discount(member, 1000, "rateDiscountPolicy");

        // then
        Assertions.assertThat(discountFixPrice).isEqualTo(1000);
        Assertions.assertThat(discountRatePrice).isEqualTo(100);
    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> discountPolicies;


        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> discountPolicies) {
            this.policyMap = policyMap;
            this.discountPolicies = discountPolicies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("discountPolicies = " + discountPolicies);
        }


        public int discount(Member member, int price, String code) {
            DiscountPolicy discountPolicy = policyMap.get(code);
            return discountPolicy.discount(member, price);
        }
    }

}
