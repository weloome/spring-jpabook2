package jpabook2.jpashop;

import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class) // 스프링과 관련된걸 테스트할거야
@SpringBootTest
@Transactional // testCase에 있으면 test 종료 후 roll back
@Rollback(value = false)
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    void testMember() throws Exception {
        // given
        Member member = new Member();
        member.setUsername("danbi");

        // when
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.find(saveId);

        // then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId()); // Assertions는 core.api꺼
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());

    }
}