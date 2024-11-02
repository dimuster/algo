import treeCollections.*;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {

        MySet st = new AVLTree();
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            String command = in.next();
            switch (command) {
                case "+": {
                    int x = in.nextInt();
                    st.add(x);
                    break;
                }
                case "-": {
                    int x = in.nextInt();
                    st.remove(x);
                    break;
                }
                case "f": {
                    int x = in.nextInt();
                    System.out.println(st.contains(x));
                    break;
                }
                case "p":
                    st.printTree();
                    break;
                case "q":
                    System.out.println("exit!");
                    System.exit(0);
                    break;
            }
        }

    }
}
