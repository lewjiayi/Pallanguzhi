import java.util.InputMismatchException;
import java.util.Scanner;
public class Input {


    public int readInt() {
        Scanner input = new Scanner(System.in);
        boolean continueInput = true;
        int a = 0;
        do {
            try {
                a = input.nextInt();
                continueInput = false;
            } catch (InputMismatchException e) {
                System.out.println("Number please!");
                input.next();
                input.close();
            }
        } while (continueInput);
        return a;
    }

    public String readString(){
        Scanner input = new Scanner(System.in);
        boolean continueInput = true;
        String a = null;
        do {
            try {
                a = input.nextLine();
                continueInput = false;
            } catch (InputMismatchException e) {
                System.out.println("Wrong Input!");
                input.next();
                input.close();
            }
        } while (continueInput);
        return a;
    }

}

