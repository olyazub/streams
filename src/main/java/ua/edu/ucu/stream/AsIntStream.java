package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

public class AsIntStream implements IntStream {

    public static ArrayList<Integer> stream = new ArrayList<>();

    private AsIntStream() {

    }

    public static IntStream of(int... values) {
        AsIntStream intStream = new AsIntStream();
        for (int value : values) {
            stream.add(value);
        }
        return intStream;
    }


    @Override
    public Double average() {
        if (stream.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            double average = 0;
            for (int value :
                    stream) {
                average = average + value;

            }
            return average / stream.size();
        }
    }

    @Override
    public Integer max() {
        if (stream.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return Collections.max(stream);
    }

    @Override
    public Integer min() {
        if (stream.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return Collections.min(stream);
    }

    @Override
    public long count() {
        return stream.size();
    }

    @Override
    public Integer sum() {
        if (stream.isEmpty()) {
            throw new IllegalArgumentException();
        }
        int sum = 0;
        for (int value :
                stream) {
            sum = sum + value;
        }
        return sum;
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        try {
            for (int j = stream.size() - 1; j >= -1; j--) {
                if (!predicate.test(stream.get(j))) {
                    stream.remove(stream.get(j));
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return this;
        }
        return this;
    }

    @Override
    public void forEach(IntConsumer action)  {
        for (int j = 0; j < stream.size(); j++) {
            action.accept(stream.get(j));
        }

    }


    @Override
    public IntStream map(IntUnaryOperator mapper) {
        try {
            for (int j = stream.size() - 1; j >= -1; j--) {
                stream.set(j,mapper.apply(stream.get(j)));
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            return this;
        }
        return this;
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        int [] arr = this.toArray();
        stream = new ArrayList<>();
        for (int value: arr) {
            func.applyAsIntStream(value);
        }
        return this;
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        int result = identity;
        for (int value : stream) {
            result = op.apply(result, value);
        }
        return result;
    }

    @Override
    public int[] toArray() {
        int[] res = new int[stream.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = stream.get(i);
        }
        return res;
    }

}
