package hello.core.discount;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class DiscountPolicyFactory {

    private final Map<DiscountPolicyCode, DiscountPolicy> discountPolicyMap;


    public DiscountPolicyFactory(List<DiscountPolicy> discountPolicies) {
        this.discountPolicyMap = discountPolicies.stream()
                .collect(Collectors.toMap(DiscountPolicy::code, discountPolicy -> discountPolicy));
    }

    public DiscountPolicy createBean(DiscountPolicyCode code) {
        return FactoryUtils.getBean(discountPolicyMap.get(code));
    }
}
