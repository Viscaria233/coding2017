package minijvm;

public class StackOverflowError {

    private static void execute() {
        execute();
    }
    
    public static void main(String[] args) {
        execute();
    }
}
