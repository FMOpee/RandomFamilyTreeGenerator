import java.util.ArrayList;

public class Driver {
    public static int highestGeneration = 0;
    public static ArrayList<Character> cs = new ArrayList<>();

    public static void main(String[] args) {
        Character adam = new Character(0);
        adam.generateChild();
        cs.addAll(adam.children);
        for (int i =0;i<cs.size();i++) {
            cs.get(i).generateChild();
            cs.addAll(cs.get(i).children);
        }
        TreeHorizontal tree = new TreeHorizontal(adam, 12);
        tree.print();
//        System.out.println("\n\n\n\n\n\n");
//        TreeVertical tree2 = new TreeVertical(adam);
//        tree2.print();
    }
}
