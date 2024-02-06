package HomeTask_240206;

/**
 * return <T>
 */
public class ABC1 {
    public int sum(int a, int b) {
        return a + b;
    }

    public int divide(int a, int b) {
        if(b == 0) {
            throw new ArithmeticException();
        }
        return a / b;
    }

}
