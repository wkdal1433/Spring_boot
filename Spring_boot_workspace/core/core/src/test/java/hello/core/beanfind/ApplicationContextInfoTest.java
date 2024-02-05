package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName+" object = " + bean);
        
        }

    }
    @Test
    @DisplayName("애플리케이션 빈만 출력하기")
    void findApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {

            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            //
            /*
            여기서의 ROLE_APPLICATION은 스프링에서 자동으로 등록해주는 빈들이 아닌 내가 개발을 위해 만든 APPLICATION
            들을 의미함.
            ROLE_INFRASTRUCTURE는 스프링이 내부에서 사용하는 빈들

            즉 ROLE_APPLICATION : 직접 등록한 애플리케이션 빈
            ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈들
             */

            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName+" object = " + bean);
            }


        }

    }
}
