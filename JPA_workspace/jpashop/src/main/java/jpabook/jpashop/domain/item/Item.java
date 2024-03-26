package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<Category>();



    //==비즈니스 로직 ==//
    // 회원이 아이템에서 실행하는 것은 무엇?
    //-> 상품 주문 시 아이템 재고 변경. 정도가 있음.
    //근데 우리가 구현하려는 "아이템에 대해 유저가 주문을 하고 그 주문에 따라 재고 수량을 변경"
    //하는 로직이 각 아이템마다 다름?-> ㄴㄴ 그래서 그러한 정보를 다 가지고 있는 여기 이 추상 클래스에서
    //로직 구현을 하는 것이 객체 지향에 더 맞음.

    //재고를 추가하는 로직의 필요 이유는 우리가 만드는 프로그램에서는 주문을 취소했을 때 재고를 늘려주는..
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if (restStock<0){
            throw new NotEnoughStockException("need more stock. 재고가 없어서 stock을 뺄 수 없습니다.");
        }
        stockQuantity = restStock;
    }


}
