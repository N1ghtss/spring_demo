package cn.night.demo3;

public class NewDog {

    public static void main(String[] args) throws Exception {
        Dog dog=(Dog) Class.forName("cn.night.demo3.Dog").newInstance();
        dog.hello();

    }
}
