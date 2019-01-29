package _1_Fundamentals._1_1_Programming_Model;

import java.util.ArrayList;
import java.util.List;

public class A {


    public static void main(String[] args) {
        final A a = new A();
        final List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
//        a.m(list);
        A.mm(list);
//        Math.PI
        list.forEach(System.out::println);
    }


    void m(List<Integer> list) {
        list.add(111);
    }

    static void mm(List<Integer> list) {
        list.add(111);
    }

//    public static void main(String[] args) {
//        Integer _ = 1;
//
//        System.out.println(_);
//        System.out.println(true ^ false);
//
//        int[] a = {1, 2, 3} ;
//        int[] b = {3, 4, 5};
//
//        System.out.println();
//
//
//    }



//    public static void main(String[] args) {
//        Arrays.asList(1, 2, 3, 3).stream().distinct().collect(Collectors.toList())
//                .forEach(System.out::println);
//    }

}
