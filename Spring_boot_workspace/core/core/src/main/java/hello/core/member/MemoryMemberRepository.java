package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{//얘는 MemberRepository의 구현체로
    // 우선은 메모리에 저장해서 조회해보는 간단한 로직만,, 서버 꺼지면 저장 안됨
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
