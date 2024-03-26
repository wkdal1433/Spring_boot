package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
//여기서는 아이템에 대한 실제 DB로의 저장, 삭제, 등의 기능을 em을 통해 진행해야함.

    public final EntityManager em;

    //아이템을 저장을 할 건데, 없으면 새로 저장을 하고, 있으면 merge해서 추가하자.<- 실제 db로의 재고 추가
    public void save(Item item){
        if (item.getId()==null){
            em.persist(item);
        }else {
            em.merge(item);
        }
    }

    //id 값에 맞는 아이템을 만들어주는,
    public Item findOne(Long id){
        return em.find(Item.class,id);//item 클래스의 id로 검색해서 찾아서 해당하는 것 반환.
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

}
