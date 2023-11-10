package jpabook2.jpashop.domain.service;

import jakarta.transaction.Transactional;
import jpabook2.jpashop.domain.Member;
import jpabook2.jpashop.domain.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    @DisplayName("회원가입")
    void join() throws Exception {
        // given :: 이런 게 주어졌을때
        Member member = new Member();
        member.setName("Lee");

        // when :: 이렇게 하면
        Long saveId = memberService.join(member);

        // then :: 이렇게 된다 (검증)
        assertEquals(member, memberRepository.findOne(saveId)); // 같은 트랜잭션안이라 동일함
    }

    @Test
    @DisplayName("중복_회원_예외")
    void validateDuplicateMember() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("Lee");
        Member member2 = new Member();
        member2.setName("Lee");

        // when
        memberService.join(member1);

        // then
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    }
}