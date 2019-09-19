package com.lqb.thoughtworks.trains.server.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liqibo
 * @description 站点
 * @date 2019/9/16 9:49
 **/
public class Station {

    /**站点序号,由用户定义*/
    private String no;

    /**站点能到达的路线*/
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

    @Override
    public String toString() {
        return "Station{" +
                "no='" + no + '\'' +
                '}';
    }

    /**
     * @author liqibo
     * @description 路线
     * @date 2019/9/16 9:49
     **/
    public static class Line {

        /**终点*/
        private Station end;

        /**距离*/
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
}
