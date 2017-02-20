package observerpattern;

import java.util.Random;
import media.BBCNews;
import observatory.Observatory;

public class ObserverPattern {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        Observatory gilice = new Observatory();
        BBCNews news = new BBCNews();
        //itt adom at a news referenciajat (telefonszam)
        gilice.addObserver(news);
        Random rnd = new Random();
        while (true) {
           gilice.change(rnd.nextInt(36), rnd.nextInt(68), rnd.nextInt(20));
           
           Thread.sleep(2000);
        }
    }
}
