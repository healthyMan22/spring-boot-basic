package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService {

    /*
        private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

        [ DIP: 추상화에 의존해야 하며, 구체화에 의존하면 안된다. ]

        지금 위의 경우는 DIP 를 위반한 경우이다.
        왜냐하면 인터페이스 뿐만 아니라 구체 클래스도 함께 의존하고 있기 떄문이다.

        [ OCP : 확장에 대해서는 개방(OPEN) 되어야 하지만 변경에 대해서는 폐쇄(CLOSE) 되어야 한다.
        -> 기존의 코드를 변경하지 않으면서 기능을 추가할 수 있다. ]

        또한 할인 정책을 바꾸면 OrderServiceImpl 도 바꿔줘야 하므로 OCP 원칙에도 위반된다.

        DIP, OCP 모두 SOLID 원칙 중 하나이다.
     */

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;


    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
