import java.io.IOException;
import java.util.ArrayList;

public class TreeVertical {
    public Character root;
    private ArrayList<String> tree = new ArrayList<>();
    int width = 1;
    int level = 0;

    public TreeVertical(Character root){
        this.root = root;
    }

    private void fillGapLater(int index, Character node){
        int gap = width-tree.get(index).length();
        String add ="";
        String add2 = "";
        for(int i = 0; i<gap; i++){
            add+=" ";

            if(node.parent!=null) if(!node.equals(node.parent.children.get(node.parent.children.size()-1)))
                add2+="—";
            else add2+=" ";
        }
        tree.set(index, tree.get(index)+add);
        tree.set(index-1, tree.get(index-1)+add2);
    }

    private void fillGap(int index, Character node){
        int gap = width - tree.get(index).length();
        String add ="";
        String add2 = "";
        for(int i = 0; i<gap; i++){
            add+=" ";
            if(node.parent!=null) if(!node.equals(node.parent.children.get(0)))
                add2+="—";
            else add2+=" ";
        }
        int wordlength = node.name.length()+4;
        for(int i = 0; i<(wordlength-1)/2+1;i++){
            if(node.parent != null) if(!node.equals(node.parent.children.get(0))){
                add2+="—";
            }
            else add2+=" ";
        }
        if (node.parent!=null){
            if(node.parent.children.get(0).equals(node) && node.parent.children.size()!=1)
                add2 += '├';
            else if(node.parent.children.size() == 1)
                add2 += '│';
            else if(node.parent.children.get(node.parent.children.size()-1).equals(node))
                add2 += '┐';
            else
                add2+= '┬';
        }
        for(int i = (wordlength-1)/2+2; i<wordlength;i++){
            if(node.parent != null) if(!node.equals(node.parent.children.get(node.parent.children.size()-1))){
                add2+="—";
            }
            else add2+=" ";
        }


        add+=" ["+node.name+"] ";
        tree.set(index, tree.get(index)+add);
        tree.set(index-1, tree.get(index-1)+add2);
    }

    private void totalLevel(Character start){
        if(start.getLevel() >= level)
            level++;

        for (Character child : start.children) {
            totalLevel(child);
        }
    }

    public void print() {

        totalLevel(root);
        for (int i=0; i<level;i++) {
            tree.add("");
            tree.add("");
        }

        fillGap(1,root);

        for (Character child : root.children) {
            traverse(child);
        }

        if(tree.get(1).length() > width){
            width=tree.get(1).length();
        }
        else if(tree.get(1).length() < width){
            fillGapLater(1, root);
        }

        tree.remove(0);
        for (String s:tree) {
            System.out.println(s);
        }
    }

    public void traverse(Character start){
        fillGap(2*start.getLevel()+1,start);

        for (Character child : start.children) {
            traverse(child);
        }

        if(tree.get( 2*start.getLevel()+1 ).length() > width)
            width = tree.get( 2*start.getLevel()+1 ).length();
        else if(tree.get( 2*start.getLevel()+1 ).length() < width){
            fillGapLater( 2*start.getLevel()+1 , start );
        }
    }
}

