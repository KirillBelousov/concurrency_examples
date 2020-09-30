import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * A simple example of a priority queue with custom priority.
 * People are sorted following a "ladies first" then "elders first" criteria.
 */
class Person implements Comparable {
    private final String name;
    boolean isFemale;
    int age;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", isFemale=" + isFemale +
                ", age=" + age +
                '}';
    }

    public Person(String name, int age, boolean isFemale) {
        this.name = name;
        this.age = age;
        this.isFemale = isFemale;
    }

    @Override
    public int compareTo(Object o) {
        Person other = (Person) o;
        if (other.isFemale && !isFemale) return 1;
        if (age < other.age) return 1;
        if (age > other.age) return -1;
        return 0;
    }
}

public class Main {


    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Person> que = new PriorityBlockingQueue<>();
        que.put(new Person("Lena", 20, true));
        que.put(new Person("Misha", 40, false));
        que.put(new Person("Tanya", 30, true));
        que.put(new Person("Kolya", 10, false));

        System.out.println(que.take());
        System.out.println(que.take());
        System.out.println(que.take());
        System.out.println(que.take());
    }
}
