import java.util.Random;

public class Hello {
    public static void main(String[] args) {
        Random rnd = new Random();
        System.out.println(new Random().nextInt(100_000_000));
    }
}