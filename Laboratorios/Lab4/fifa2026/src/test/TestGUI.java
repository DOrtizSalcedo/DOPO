package test;

import presentation.FifaGUI;
import domain.Fifa;

public class TestGUI {
    public static void main(String[] args) {
        Fifa fifa = new Fifa();
        try {
            System.out.println("Testing Search:");
            fifa.search("C");
        } catch (Exception e) {
            System.out.println("Search failed: " + e.toString());
        }
        
        try {
            System.out.println("Testing addPlayer mismatch:");
            fifa.addPlayer("Pele", "A", "100", "1000", "Santos"); // Using GUI order: name, pos, min, manager, val
        } catch (Exception e) {
            System.out.println("addPlayer failed: " + e.toString());
        }
    }
}
