package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : A 사용자 10000원 주문
//        statefulService1.order("userA",10000);
        int userAPrice = statefulService1.order("userA", 10000);

        //ThreadB : B 사용자 20000원 주문
//        statefulService2.order("userB",20000);
        int userBPrice = statefulService2.order("userB", 20000);
        // 사용자 A 가 10000원을 주문하고 조회를 하려는데 그 사이에 사용자 B의 주문이 등록되어 객체가 불러진 상황이라면
        // 아래 금액 조회에서 얼마가 나와야 할까? -> 당연히 20000원

        // A가 만원어치 옷을 사고 조회하려는데 끼어들어서 조회된 값이 2만원이 나와버리면 심각해짐.

        // 따라서 공유 필드를 클라이언트가 값을 변경하도록 설계해선 절대 안된다. 돈과 관련되면 진짜 심각해짐.
        // 이게 진짜 중요한게 이걸 잘못해서 망가진 로그들을 수정하려면 몇달이 걸림.


        //ThreadA : 사용자 A 주문 금액 조회
//        int price = statefulService1.getPrice();
        System.out.println("price = " + userAPrice);


    }

    static class TestConfig{

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();

        }
    }

}