package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {
    @Test
    public void lifeCycleTest(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient3 client = ac.getBean(NetworkClient3.class);
        ac.close();


    }

    @Configuration
    static class LifeCycleConfig{
//        @Bean(initMethod = "init",destroyMethod = "close") <- 얘는 2번 방식
//        결론은 코드를 고칠 수 없는 외부 라이브러리의 경우는 2번 형식을 사용하고 아닌 경우는 3번을 사용하자.
        @Bean
        public NetworkClient3 networkClient3(){
            NetworkClient3 networkClient3 = new NetworkClient3();
            networkClient3.setUrl("http://hello-spring.dev");
            return networkClient3;
        }
    }


}
