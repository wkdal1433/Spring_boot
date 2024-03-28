package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_item")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;  // 주문 상품

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order; // 주문

    private int orderPrice; // 주문 당시 가격
    private int count;  //주문 당시 수량


    //== 생성 메소드 ==//
    //이 주문 아이템 필드에서 생성 메소드를 가지는 이유는 이 필드에 주문에 대한 정보와
    //아이템(가격, 수량)에 대한 정보가 모두 있기 때문에 여기서 처리해 주는 것이 좋음.
    public static OrderItem createOrderItem(Item item, int orderPrice, int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);
        item.removeStock(count);// item에 대한 정보도 얘가 담고 있기 때문에 주문에 대한
                                // 정보가 들어오면 재고를 줄여주는 처리도 얘가함.
        return orderItem;
    }
    public void cancel(){
        getItem().addStock(count);
    }
    public int getTotalPrice() {
        return getOrderPrice()*getCount();
    }
}
