package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient implements InitializingBean , DisposableBean {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);

//        disconnect();
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작 시 호출
    public void connect(){
        System.out.println("connect: " + url);
    }

    public void call(String message){
        System.out.println("call : " + url+" message : " + message);
    }

    //서비스 종료 시 호출
    public void disconnect(){
        System.out.println("disconnect : " + url);
    }


    //초기화 콜백이 오면 실행 되는 메소드 -> 스프링 자체 지원
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메세지");
    }
    //마찬가지로 소멸전 콜백이 오면 실행 되는 메소드 -> 스프링 자체 지원
    @Override
    public void destroy() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
}

//근데 이건 옛날 방식이라 스프링에 의존적이고, 인터페이스를 받아오는데 부담이 있는데다가
// 고칠 수 없는 외부 라이브러리를 받아 쓸 때 같은 상황에서는 사용할 수 없기 때문에 잘 사용하지 않는다.
// 그래서 NetworkClient2 방식이 요즘 방식임

