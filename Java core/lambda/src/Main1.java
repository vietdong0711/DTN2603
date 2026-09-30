import java.util.Arrays;
import java.util.List;

public class Main1 {
    public static void main(String[] args) {
//        List<String> names = Arrays.asList("An", "Dung", "Minh");
        List<String> names = List.of("An", "Dung", "Minh");

        for(String name : names) {
            in(name);// view
        }

        // cte
        names.forEach(name -> System.out.println(name));
    }

    public static void in(String s) {
        // xus ly
        System.out.println(s);
    }

    // functional  interface
}
