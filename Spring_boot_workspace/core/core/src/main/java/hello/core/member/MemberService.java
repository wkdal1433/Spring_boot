package hello.core.member;

public interface MemberService {// 얘는 두가지 기능이 있어야됨. 회원 가입과 회원 조회

    void join(Member member);

    Member findMember(Long memberId);
}
