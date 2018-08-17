import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String str = in.next();
            int result = 0;
            if(isPalin(str)) {
                result = 1;
                for (int i = 1; i < str.length(); i++) {
                }
            }
            
            
            System.out.println(result);
        }
    }

    private static boolean isPalin(String str) {
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }
}