package com.lqb.thoughtworks;

public class Main {
    public static void main(String[] args) {
        Trains trains = new Trains();
        trains.convertInputToStations("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
        System.out.println(trains.getDistance("A", "B", "C"));
        System.out.println(trains.getDistance("A", "D"));
        System.out.println(trains.getDistance("A", "D", "C"));
        System.out.println(trains.getDistance("A", "E", "B", "C", "D"));

        try {
            System.out.println(trains.getDistance("A", "E", "D"));
        } catch (SystemException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(trains.getLeastStops("C", "C"));
        System.out.println(trains.getLinesNumOfStops("A", "C", 4));
        System.out.println(trains.getLeastDistance("A", "C"));
        System.out.println(trains.getLeastDistance("B", "B"));
        System.out.println(trains.getMostLinesNumInDistance("C", "C", 30));

    }
}
