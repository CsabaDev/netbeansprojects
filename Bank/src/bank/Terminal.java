package bank;

import java.util.Scanner;

public class Terminal {
    Scanner beOlvas = new Scanner(System.in);
    private int osszeg = beOlvas.nextInt();

    public int getOsszeg() {
        return osszeg;
    }
}
