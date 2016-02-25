package com.example;

public class Demo {
    public static void main(String[] args) {
        System.out.println("Labukas");

        double myDouble = 1235.67894;
        double mySecondDouble = 3.554478554878548;
        System.out.println(myDouble + mySecondDouble);

        Player vygis = new Player("Vygis");
        System.out.println(vygis.getName() + " has lives: " + vygis.getLives());
    }
}
