package _1_Fundamentals._1_1_Programming_Model;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Consumer {

    public static void main(String[] args) throws IOException {
        final Scanner scanner = new Scanner(new BufferedInputStream(System.in), StandardCharsets.UTF_8);
        while (scanner.hasNext()) {
            System.out.println(scanner.nextDouble());
        }
    }

}
