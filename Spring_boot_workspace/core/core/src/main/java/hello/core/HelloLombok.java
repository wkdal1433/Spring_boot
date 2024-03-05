package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {
    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("ss");
        helloLombok.setAge(12);

        String a = helloLombok.name;
        System.out.println("a = " + a);
        System.out.println("helloLombok = " + helloLombok);
    }


}
