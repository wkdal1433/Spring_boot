package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;

public class NetworkClient2  {

    private String url;

    public NetworkClient2() {
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
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메세지");
    }

    //마찬가지로 소멸전 콜백이 오면 실행 되는 메소드 -> 스프링 자체 지원
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
    //근데 이거 조차도 그냥 NetworkClient3 방식을 사용하는 것이 가장 좋다. 스프링에서도 권장하고 있음.
}
