
class Container {
    public int m_var = 0;

    public void change() {
        m_var +=5;
    }

    public int value(){
        return --m_var;
    }
}

class A {
    public static Container m_counter = new Container();

    public A() {
        m_counter.change();
    }

    public static void printCounter() {
        System.out.println(m_counter.value() + ";");
    }
}




public class zad22 {
    public static void main(String[] args) {
        A.printCounter();
        A objA = new A();
        A.printCounter();
    }
}