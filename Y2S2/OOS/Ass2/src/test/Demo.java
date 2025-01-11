package test;

class A {
    static {
        System.out.println("D.A");
    }
    public static void dispaly() {
        System.out.println("D.A.display");
    }
}
    
public class Demo {
    public static void main(String[] args) {
        A.dispaly();
    }
}