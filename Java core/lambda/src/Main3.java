public class Main3 {
    public static void main(String[] args) {
        // // functional  interface: la interface chi co 1 phuwong thuc truu tuong
//        Calculator calculator = new CalculatorImpl();
        Calculator calculator = (a, b) -> a + b;


        System.out.println(calculator.sum(1, 2));
    }
}
