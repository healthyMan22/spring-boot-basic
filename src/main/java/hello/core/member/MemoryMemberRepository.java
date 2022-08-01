package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{

    // 콘크루트 해쉬맵에 대해서 따로 정리
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }


    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
