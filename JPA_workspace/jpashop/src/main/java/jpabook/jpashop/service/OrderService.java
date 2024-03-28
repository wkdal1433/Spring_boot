package jpabook.jpashop.service;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;

    /**주문*/
    @Transactional
    public Long order(Long memberId, Long itemId, int count){

        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송 정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setStatus(DeliveryStatus.READY);

        //주문 상품 생성

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        //주문 저장
        //이때 cascade의 범위에 대해서 고민을 해봐야함. -> 딱 이런 경우에만 사용해야함.
        // Order가 OrderItem과 delivery를 관리하기 때문에 이 상황에서만 사용하는 것이 좋음. 아닐땐 쓰지말자.
        // 이 상황이란? -> Order가 OrderItem, delivery를 참조하고, 관리까지 하기 때문에.
        // 그런데 OrderItem이나 delivery를 다른데서도 참조하고 있다면 cascade를 이렇게 사용하면 안됨.
        // OrderItem을 다른데서 쓰고있는데 얘가 영속성 주입을 통해 삭제해버리면 못쓰는 거니깐..
        orderRepository.save(order);


        return order.getId();
    }

    /**주문 취소*/
    @Transactional
    public void cancelOrder(Long orderId){
        //주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }

    /**주문 검색*/
/*
    public List<Order> findOrders(OrderSearch orderSearch){
        return orderRepository.findAll(orderSearch);
    }
*/



}
