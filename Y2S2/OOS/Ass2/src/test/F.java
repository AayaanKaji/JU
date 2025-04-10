package test;

class B {
    static {
        System.out.println("F.A");
    }
    public static void dispaly() {
        System.out.println("F.A.display");
    }
}

public class F {
    public static void main(String[] args) {
        B.dispaly();
    }
}