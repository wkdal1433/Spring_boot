package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Test
    public void 상품주문() throws Exception {
        //given
        Member member = new Member();
        member.setName("회원1");
        member.setAddress("");
        Item item = new Book();

        //when

        //then

    }

    @Test
    public void 주문취소() throws Exception{
        //given

        //when

        //then

      }
    @Test
    public void 상품주문_재고수량초과() throws Exception{
        //given

        //when

        //then

      }

}