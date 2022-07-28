package cn.night.demo3;

public class Dog implements Pet{

    public Dog(){
        System.out.println("无参构造");
    }

    @Override
    public void hello() {
        System.out.println("汪汪汪！！！");
    }
}
