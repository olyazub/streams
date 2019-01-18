package ua.edu.ucu;

import ua.edu.ucu.stream.AsIntStream;
import ua.edu.ucu.stream.IntStream;

import java.util.Arrays;

public class test {


    public static void main(String[] args) {
        IntStream intStream = AsIntStream.of(-1, 0, 1, 2, 3);
        intStream.filter(x -> x > 0);
       System.out.println(AsIntStream.stream);
        intStream.map(x -> x * x);
       System.out.println(AsIntStream.stream);
        intStream.flatMap(x -> AsIntStream.of(x - 1, x, x + 1));
       System.out.println(AsIntStream.stream);
       int res = intStream.reduce(0, (sum, x) -> sum += x);
        System.out.println(res);
        System.out.println(Arrays.toString(StreamApp.streamToArray(intStream)));



    }

}
