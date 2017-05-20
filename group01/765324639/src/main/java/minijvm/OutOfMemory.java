package minijvm;

import java.util.ArrayList;
import java.util.List;

public class OutOfMemory {
    
    public static void main(String[] args) {
        List<OutOfMemory> list = new ArrayList<>();
        while (true) {
            list.add(new OutOfMemory());
        }
    }
}
