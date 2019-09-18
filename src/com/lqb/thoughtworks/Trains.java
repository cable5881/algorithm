package com.lqb.thoughtworks;

import java.util.*;

public class Trains {

    private static final int NO_STOP_FOUND = -1;

    private Map<String, Station> stations;

    public Trains(String linesStr) {
        convertInputToStations(linesStr);
    }

    public Trains() {
    }

    public void convertInputToStations(String linesStr) {
        String[] lines = linesStr.split(", ");

        if (stations == null) {
            stations = new HashMap<>(lines.length);
        } else {
            stations.clear();
        }

        for (String lineStr : lines) {
            String startNo = lineStr.charAt(0) + "";
            String endNo = lineStr.charAt(1) + "";
            int distance = Integer.valueOf(lineStr.substring(2));

            Station startStation = stations.getOrDefault(startNo, new Station(startNo));
            Station endStation = stations.getOrDefault(endNo, new Station(endNo));

            Line line = new Line(endStation, distance);
            startStation.getLines().add(line);

            stations.put(startNo, startStation);
            stations.put(endNo, endStation);
        }
    }

    /**
     * 指定站点，计算站点间的距离
     * @param nos 多个站点
     * @return 距离
     */
    public int getDistance(String... nos) {
        int distance = 0;

        for (int i = 1; i < nos.length; i++) {
            Station start = getStation(nos[i - 1]);
            Station end = getStation(nos[i]);

            Line curLine = null;
            boolean hasLine = false;
            List<Line> lines = start.getLines();
            for (Line line : lines) {
                if (line.getEnd() == end) {
                    hasLine = true;
                    curLine = line;
                    break;
                }
            }

            if (!hasLine) {
                throw new SystemException("NO SUCH ROUTE");
            }

            if (curLine != null) {
                distance += curLine.getDistance();
            }
        }

        return distance;
    }

    /**
     * 获取两点间的经过的站点（最少）
     * @param startNo 起点
     * @param endNo 终点
     * @return 最少站的数量
     */
    public int getLeastStops(String startNo, String endNo) {
        Station start = getStation(startNo);
        Station end = getStation(endNo);

        if (start == end) {
            return 0;
        }

        int leastStops = getLeastStops(start, end, 0);
        if (leastStops == NO_STOP_FOUND) {

        }

        return leastStops;
    }

    private int getLeastStops(Station start, Station end, int baseStops) {
        List<Line> lines = start.getLines();

        if (lines == null || lines.size() <= 0) {
            return NO_STOP_FOUND;
        }

        int leastStops = Integer.MAX_VALUE;
        for (Line line : lines) {
            if (line.getEnd() == end) {
                return baseStops + 1;
            }

            int stops = getLeastStops(line.getEnd(), end, baseStops + 1);
            if (stops > 0 && stops < leastStops) {
                leastStops = stops;
            }
        }

        return leastStops == Integer.MAX_VALUE ? NO_STOP_FOUND : leastStops;
    }

    /**
     * 获取两点间最短距离
     * @param startNo 起点
     * @param endNo 终点
     * @return 最短距离
     */
    public int getLeastDistance(String startNo, String endNo) {
        Station start = getStation(startNo);
        Station end = getStation(endNo);

        int distance = getLeastDistance(start, end, 0);
        if (distance == NO_STOP_FOUND) {

        }

        return distance;
    }

    private int getLeastDistance(Station start, Station end, int distanceSum) {
        if (start.getLines() == null || start.getLines().size() == 0) {
            return NO_STOP_FOUND;
        }

        int leastDistance = Integer.MAX_VALUE;
        for (Line line : start.getLines()) {
            if (line.getEnd() == end) {
                return line.getDistance() + distanceSum;
            }

            int distance = getLeastDistance(line.getEnd(), end, line.getDistance() + distanceSum);
            if (distance > 0 && distance < leastDistance) {
                leastDistance = distance;
            }
        }

        return leastDistance == Integer.MAX_VALUE ? NO_STOP_FOUND : leastDistance;
    }

    /**
     * 在给定的距离限制下，获取两点间最多有多少条路线
     * @param startNo 起点
     * @param endNo 终点
     * @param distance 距离限制
     * @return 路线数量
     */
    public int getMostLinesNumInDistance(String startNo, String endNo, int distance) {
        Station start = getStation(startNo);
        Station end = getStation(endNo);

        if (distance <= 0) {

        }

        return getMostLinesNumInDistance(start, end, 0);
    }

    private int getMostLinesNumInDistance(Station start, Station end, int distanceLimit) {
        if (start.getLines() == null || start.getLines().size() == 0 || distanceLimit <= 0) {
            return 0;
        }

        int linesNum = 0;
        for (Line line : start.getLines()) {
            if (line.getEnd() == end && line.getDistance() <= distanceLimit) {
                linesNum++;
            }

            linesNum += getMostLinesNumInDistance(line.getEnd(), end, distanceLimit - line.getDistance());
        }

        return linesNum;
    }

    /**
     * 在给点的站点数量下，找出两点间有多少种可达的路线
     * @param startNo 起点
     * @param endNo 终点
     * @param stops 站点数量
     * @return 可达的路线数量
     */
    public int getLinesNumOfStops(String startNo, String endNo, int stops) {
        Station start = getStation(startNo);
        Station end = getStation(endNo);

        if (stops <= 0) {

        }

        int linesNum = 0;
        for (Line line : start.getLines()) {
            if (validLinesNumOfStops(line.getEnd(), end, stops - 1)) {
                linesNum++;
            }
        }

        return linesNum;
    }

    private boolean validLinesNumOfStops(Station start, Station end, int stops) {
        if (start.getLines() == null || start.getLines().size() <= 0 || stops < 0) {
            return false;
        }

        for (Line line : start.getLines()) {
            if ((line.getEnd() == end && stops == 0)) {
                return true;
            }

            if (validLinesNumOfStops(line.getEnd(), end, stops - 1)) {
                return true;
            }
        }

        return false;
    }

    private Station getStation(String startNo) {
        return stations.get(startNo);
    }
}

class Station {
    private String no;
    private List<Line> lines;

    public Station(String no) {
        lines = new ArrayList<>();
        this.no = no;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }
}

class Line {
    private Station end;
    private int distance;

    public Line(Station end, int distance) {
        this.end = end;
        this.distance = distance;
    }

    public Line() {
    }

    public Station getEnd() {
        return end;
    }

    public void setEnd(Station end) {
        this.end = end;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

}

