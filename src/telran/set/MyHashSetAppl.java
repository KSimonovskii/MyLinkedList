package telran.set;

import telran.set.interfaces.ISet;
import telran.set.model.MyHashSet;

public class MyHashSetAppl {
    public static void main(String[] args) {
        ISet<String> mySet = new MyHashSet<>();
        System.out.println(mySet.size());

        mySet.add("Boston");
        mySet.add("Atlanta");
        System.out.println(mySet.add("Chicago"));
        mySet.add("New York");
        mySet.add("Detroit");
        System.out.println(mySet.size());
        System.out.println(mySet.add("Chicago"));
        System.out.println(mySet.size());

        System.out.println(mySet.contains("Chicago"));
        System.out.println(mySet.contains("Los Angeles"));

        System.out.println("**** Print ****");
        for (String set: mySet) {
            System.out.println(set);
        }

        System.out.println("**** Remove ****");
        System.out.println(mySet.remove("Chicago"));

        System.out.println("**** Print after remove ****");
        for (String set: mySet) {
            System.out.println(set);
        }


    }
}
