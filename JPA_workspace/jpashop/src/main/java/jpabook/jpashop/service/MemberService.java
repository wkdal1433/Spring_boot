package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)     //JPA의 모든 로직이나 데이터 변경은 transaction 안에서 실행되어야 함.
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //@Transactional(readOnly = true)는 읽기 전용으로, 쓰기가 안됨.
    //MemberService의 경우 읽기가 많기 때문에 Default로 읽기전용으로 만들어 두고 쓰기만 따로 표기하자.
    /**
    회원 가입
     **/
    @Transactional
    public Long join(Member member){

        validateDuplicateMember(member); // 중복 회원 검증.
        
        memberRepository.save(member);

        return member.getId();
    }

    // 실무에서는 동시에 같은 이름의 멤버가 가입하는 상황이 생길경우 검증이 안될 수 있음.
    // 그래서 멀티스레드에 대한 요청을 처리하기 위해 DB에서 member의 name을 유니크 제약조건을 걸어야함.
    private void validateDuplicateMember(Member member) {
        //EXCEPTION 발생.
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

}
