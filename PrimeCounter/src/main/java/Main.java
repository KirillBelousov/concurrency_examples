import java.util.Scanner;

// Counting prime numbers: serially and in parallel
public class Main {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Enter start range: ");
        Scanner sc = new Scanner(System.in);
        int start = sc.nextInt();

        System.out.println("Enter end range: ");
        int end = sc.nextInt();

        PrimeCounter counter = new PrimeCounterSerial();
        int result = counter.countPrimes(start, end);
        System.out.println("Primes in the range: " + result);

    }
}
