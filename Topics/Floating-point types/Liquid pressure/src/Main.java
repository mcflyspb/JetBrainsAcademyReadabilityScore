import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double lp = scanner.nextDouble();
        double hc = scanner.nextDouble();
        double ga = 9.8;
        System.out.println(lp * hc * ga);
    }
}
