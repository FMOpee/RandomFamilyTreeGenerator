import java.util.ArrayList;

public class Character{
    public String name;
    public ArrayList<Character> children;
    public Character parent;
    public int traversedIndex = 0;
    public int generation=0;
    public int stringNo;
    public int birthyear;
    public int life;
    public int death;

    public Character(int birthyear) {
        this.birthyear = birthyear;

        int random = (int) (Math.random()*160);
        if(random<75)
            life = 15+random/3;
        else if(random<115)
            life = 40+(random-75)/2;
        else life= 60+random-115;

        death = birthyear+life;

        this.name = "["+birthyear+"-"+death+"]";
        children = new ArrayList<>();
        parent = null;
        if(Driver.highestGeneration <= generation) Driver.highestGeneration = generation;
    }

    public Character(Character parent, int birthyear) {
        this.birthyear = birthyear;

        int random = (int) (Math.random()*160);
        if(random<75)
            life = 15+random/3;
        else if(random<115)
            life = 40+(random-75)/2;
        else life= 60+random-115;

        death = birthyear+life;

        this.name = "["+birthyear+"-"+death+"]";

        this.parent = parent;
        //this.parent.addChild(this);
        children = new ArrayList<>();
        generation = parent.generation+1;
        if(Driver.highestGeneration <= generation) Driver.highestGeneration = generation;
    }

    public Character(int birthyear, ArrayList<Character> children) {
        this.birthyear = birthyear;

        int random = (int) (Math.random()*160);
        if(random<75)
            life = 15+random/3;
        else if(random<115)
            life = 40+(random-75)/2;
        else life= 60+random-115;

        death = birthyear+life;

        this.name = "["+birthyear+"-"+death+"]";
        this.children = children;
        parent = null;
        for (Character n: children) {
            n.parent = this;
            n.generation = generation+1;
            if(Driver.highestGeneration <= n.generation) Driver.highestGeneration = n.generation;
        }
    }

    public Character(Character parent, int birthyear, ArrayList<Character> children) {
        this.birthyear = birthyear;

        int random = (int) (Math.random()*160);
        if(random<75)
            life = 15+random/3;
        else if(random<115)
            life = 40+(random-75)/2;
        else life= 60+random-115;

        death = birthyear+life;

        this.name = "["+birthyear+"-"+death+"]";
        this.parent = parent;
        this.parent.addChild(this);
        this.children = children;
        for (Character n: children) {
            n.parent = this;
            n.generation = generation+1;
            if(Driver.highestGeneration <= n.generation) Driver.highestGeneration = n.generation;
        }
    }

    public void addChild(Character node){
        children.add(node);
        node.parent = this;
        node.generation = generation+1;
        if(Driver.highestGeneration <= node.generation) Driver.highestGeneration = node.generation;
    }
    public Character previousSibling(){
        if(parent == null) return null;
        else if(parent.children.get(0).equals(this)) return null;
        int i =0;
        for (i = 0; i<parent.children.size(); i++){
            if(parent.children.get(i).equals(this)) break;
        }
        return parent.children.get(i-1);
    }

    public int getLevel(){
        int level = 0;
        Character x = this;
        while(x.parent != null){
            level++;
            x = x.parent;
        }
        return level;
    }

    public void generateChild(){
        for (int i =20; i <= 41 && i <= life-3 ;i+=3){
            if(Math.random()>.75){
                int by = birthyear+i;
                if (by > 250) break;
                Character c = new Character(this, by);
                addChild(c);
            }
        }
    }
}
