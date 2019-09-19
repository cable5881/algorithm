package com.lqb.thoughtworks.trains.client;

import com.lqb.thoughtworks.trains.client.service.ITrainStationService;
import com.lqb.thoughtworks.trains.client.service.TrainStationServiceImpl;

import java.io.IOException;

/**
 * @description 程序运行客户端
 * @author liqibo
 * @date 2019/9/16 17:52
 **/
public class Main {
    public static void main(String[] args) throws IOException {

        ITrainStationService trainStationService = init("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");

        getDistance(trainStationService, "A", "B", "C");
        getDistance(trainStationService, "A", "D");
        getDistance(trainStationService, "A", "D", "C");
        getDistance(trainStationService, "A", "E", "B", "C", "D");
        getDistance(trainStationService, "A", "E", "D");
        getLeastStops(trainStationService, "C", "C");
        getLinesNumOfStops(trainStationService, 4, "A", "C");
        getLeastDistance(trainStationService, "A", "C");
        getLeastDistance(trainStationService, "B", "B");
        getMostLinesNumInDistance(trainStationService, "C", "C", 30);

    }

    private static ITrainStationService init(String lineStr) throws IOException {
        ITrainStationService trainStationService = new TrainStationServiceImpl();
        trainStationService.load(lineStr);

        //文件读取
        //trainStationService.load(new FileInputStream("/test.txt"));
        return trainStationService;
    }

    private static void getDistance(ITrainStationService trainStationService, String... no) {
        try {
            System.out.println(trainStationService.getDistance(no));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void getLeastStops(ITrainStationService trainStationService, String start, String end) {
        try {
            System.out.println(trainStationService.getLeastStops(start, end));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void getLinesNumOfStops(ITrainStationService trainStationService, int stops, String start, String end) {
        try {
            System.out.println(trainStationService.getLinesNumOfStops(start, end, stops));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void getLeastDistance(ITrainStationService trainStationService, String start, String end) {
        try {
            System.out.println(trainStationService.getLeastDistance(start, end));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void getMostLinesNumInDistance(ITrainStationService trainStationService, String start, String end, int distanceLimit) {
        try {
            System.out.println(trainStationService.getMostLinesNumInDistance(start, end, distanceLimit));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
