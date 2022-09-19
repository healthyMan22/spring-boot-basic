package hello.core.discount;

import java.util.NoSuchElementException;
import hello.core.member.Grade;
import hello.core.member.Member;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {DiscountPolicyFactory.class, FixDiscountPolicy.class, RateDiscountPolicy.class})
class DiscountPolicyFactoryTest {

    @Autowired
    DiscountPolicyFactory factory;


    @Test
    @DisplayName("DiscountFactory가 빈을 잘 생성하는가")
    void creatBean() throws Exception {

        // given
        DiscountPolicy fixDiscount = factory.createBean(DiscountPolicyCode.FIX);

        // when
        int discountPrice = fixDiscount.discount(new Member(1L, "test", Grade.VIP), 10000);

        // then
        assertThat(discountPrice).isEqualTo(1000);
    }


    @Test
    @DisplayName("DiscountFactory가 빈을 생성하지 못할 때 NoSuchElementException 발생시키는가")
    void creatBeanError() throws Exception {

        assertThatThrownBy(() -> factory.createBean(DiscountPolicyCode.NULL)).isInstanceOf(
                NoSuchElementException.class);
    }

}