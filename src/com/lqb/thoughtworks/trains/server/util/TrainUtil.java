package com.lqb.thoughtworks.trains.server.util;

import com.lqb.thoughtworks.trains.server.entity.Station;
import com.lqb.thoughtworks.trains.server.exception.SystemException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liqibo
 * @description 工具类，主要做用户输入的解析
 * @date 2019/9/16 14:43
 **/
public class TrainUtil {

    /**路线输入分隔符*/
    public static final String LINE_SEPARATOR = ", ";

    /**
     * @description 解析用户的输入，并转化为有效站点
     * @author liqibo
     * @date 2019/9/16 17:50
     * @param stations  站点存储位置
     * @param linesStr 用户输入
     **/
    public synchronized static void convertInputToStations(Map<String, Station> stations, String linesStr) {
        String[] lines = linesStr.split(LINE_SEPARATOR);

        try {
            Map<String, Station> temp = new HashMap<>(lines.length + 1);
            for (String lineStr : lines) {
                String startNo = getStartNo(lineStr);
                String endNo = getEndNo(lineStr);
                int distance = getDistance(lineStr);

                Station startStation = temp.getOrDefault(startNo, new Station(startNo));
                Station endStation = temp.getOrDefault(endNo, new Station(endNo));

                Station.Line line = new Station.Line(endStation, distance);
                startStation.getLines().add(line);

                temp.put(startNo, startStation);
                temp.put(endNo, endStation);
            }
            stations.putAll(temp);
        } catch (Exception e) {
            throw new SystemException("Resolve input error.Please check your input and make sure every row looks " +
                    "like: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7", e);
        }

    }

    /**
     * @description 从给定的字符串中提取获取终点站
     * @author liqibo
     * @date 2019/9/16 15:22
     * @param lineStr 给定的字符串
     * @return 终点站
     **/
    private static String getEndNo(String lineStr) {
        return lineStr.charAt(1) + "";
    }

    /**
     * @description 从给定的字符串中提取获取起点站
     * @author liqibo
     * @date 2019/9/16 15:22
     * @param lineStr 给定的字符串
     * @return 起点站
     **/
    private static String getStartNo(String lineStr) {
        return lineStr.charAt(0) + "";
    }

    /**
     * @description 从给定的字符串中获取两点间的举例
     * @author liqibo
     * @date 2019/9/16 15:23
     * @param line 给定的字符串
     * @return 距离
     **/
    private static int getDistance(String line) {
        return Integer.valueOf(line.substring(2));
    }

}
