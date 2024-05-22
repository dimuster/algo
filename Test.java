import java.util.Scanner;
import static java.lang.System.exit;

public class Test {
    public static void main(String[] args) {

        MySet st = new AVL_Tree();
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
                    System.out.println("exit");
                    exit(0);
                    break;
            }
        }

    }
}
