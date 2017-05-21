package jvm;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Haochen on 2017/5/21.
 * TODO:
 */
public class OutOfMemory {
    public static void main(String[] args) {
        List<OutOfMemory[]> list = new LinkedList<>();
        while (true) {
            list.add(new OutOfMemory[Integer.MAX_VALUE]);
        }
    }
}
