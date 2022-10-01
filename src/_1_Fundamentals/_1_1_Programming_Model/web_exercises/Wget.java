package _1_Fundamentals._1_1_Programming_Model.web_exercises;

import common.In;
import common.Out;

public class Wget {

    public static void main(String[] args) {
        String url = "https://introcs.cs.princeton.edu/java/data/codes.csv";
        In in = new In(url);
        String data = in.readAll();

        // write data to a file
        String filename = url.substring(url.lastIndexOf('/') + 1);
        Out out = new Out(filename);
        out.println(data);
        out.close();
    }

}
