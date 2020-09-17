public class Main {


    public static void main(String[] args) throws InterruptedException {
//        play1();   // Wrong, no synchronization at all
//        play2();   // Wrong, no synchronization at all
//        play3();   // Wrong, lost signal example
//        play4();   // Wrong, lost signal example
//        play5();   // Wrong, spurious wakeup example
          play6();  // Correct
          Actor.endPlay();
    }

    public static void play1() {
        Juliette juliette = new Juliette();
        Romeo romeo = new Romeo();
        romeo.play();
        juliette.play();
    }

    public static void play2() throws InterruptedException {
        Juliette juliette = new Juliette();
        Romeo romeo = new Romeo();
        juliette.play();
        romeo.play();
    }

    public static void play3() throws InterruptedException {
        Juliette2 juliette = new Juliette2();
        Romeo2 romeo = new Romeo2();
        juliette.play();
        Thread.sleep(3000);
        romeo.play();
    }

    public static void play4() throws InterruptedException {
        Romeo3 romeo = new Romeo3();
        Juliette3 juliette = new Juliette3(romeo);
        juliette.play();
        romeo.play();
    }

    public static void play5() throws InterruptedException {
        Hamlet hamlet = new Hamlet();
        Romeo3 romeo = new Romeo3();
        Juliette3 juliette = new Juliette3(romeo);
        juliette.play();
        Thread.sleep(2000);
        hamlet.play();
        //romeo.play();
    }

    public static void play6() throws InterruptedException {
        Hamlet hamlet = new Hamlet();
        Romeo3 romeo = new Romeo3();
        Juliette4 juliette = new Juliette4(romeo);
        juliette.play();
        Thread.sleep(2000);
        hamlet.play();
        Thread.sleep(2000);
        romeo.play();
    }
}
