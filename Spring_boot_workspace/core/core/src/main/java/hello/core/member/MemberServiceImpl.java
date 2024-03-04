package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    //회원가입, 회원조회 서비스를 하려면 저장된 정보를 가져오는 곳이 있어야됨.
    //이때 fianl이기 때문에 구현체가 없으면 nullpoint 오류 발생
    //따라서 우리가 구현체로 만들었던 MemoryMemberRepository를 넣어줌
    private final MemberRepository memberRepository;

    @Autowired // 마치 ac.getBean(MemberRepository.class) 를 한것과 같은 효과
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }


    // 테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;

    }
}
