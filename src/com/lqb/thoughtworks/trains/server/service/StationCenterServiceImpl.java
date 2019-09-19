package com.lqb.thoughtworks.trains.server.service;

import com.lqb.thoughtworks.trains.server.entity.Station;
import com.lqb.thoughtworks.trains.server.exception.SystemException;
import com.lqb.thoughtworks.trains.server.util.CollectionUtils;
import com.lqb.thoughtworks.trains.server.util.TrainUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liqibo
 * @description 站点中心服务接口实现类，负责提供对外信息和列车调度
 * @date 2019/9/16 20:11
 **/
public class StationCenterServiceImpl implements IStationCenterService{

    /**标记未找到目的站点*/
    private static final int NO_STOP_FOUND = -1;

    /**保存所有站点信息*/
    private Map<String, Station> stations;

    public StationCenterServiceImpl() {
        stations = new ConcurrentHashMap<>();
    }

    /**
     * @description 从用户输入中重新加载所有站点信息
     * @author liqibo
     * @date 2019/9/16 15:29
     * @param linesStr 用户输入
     * @param clear 是否清空原有站点信息
     **/
    @Override
    public void reload(String linesStr, boolean clear) {
        if (clear) {
            stations.clear();
        }
        TrainUtil.convertInputToStations(stations, linesStr);
    }

    /**
     * @description 从用户输入中重新加载所有站点信息
     * @author liqibo
     * @date 2019/9/16 15:29
     * @param in 文件输入
     * @param clear 是否清空原有站点信息
     **/
    @Override
    public void reload(InputStream in, boolean clear) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = br.readLine()) != null) {
                reload(line, clear);
            }
        } catch (IOException e) {
            throw new SystemException("Loading inputStream error", e);
        } finally {

        }

    }

    /**
     * 指定站点，计算站点间的距离
     * @param nos 多个站点
     * @return 距离
     */
    @Override
    public int getDistance(String... nos) {
        int distance = 0;

        for (int i = 1; i < nos.length; i++) {
            Station start = getStation(nos[i - 1]);
            Station end = getStation(nos[i]);

            Station.Line curLine = null;
            boolean hasLine = false;
            List<Station.Line> lines = start.getLines();
            for (Station.Line line : lines) {
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
     * 获取两点间的最少需要经过几个站点
     * @param startNo 起点
     * @param endNo 终点
     * @return 最少站的数量
     */
    @Override
    public int getLeastStops(String startNo, String endNo) {
        Station start = getStation(startNo);
        Station end = getStation(endNo);

        int leastStops = getLeastStops(start, end, 0, new HashSet<>());
        if (leastStops == NO_STOP_FOUND) {
            throw new SystemException(String.format("NO SUCH ROUTE BETWEEN %s AND %s", startNo, endNo));
        }

        return leastStops;
    }

    /**
     * @description getLeastStops的核心递归方法, passes用于避免遍历重复站点
     **/
    private int getLeastStops(Station start, Station end, int baseStops, Set<Station> passes) {
        List<Station.Line> lines = start.getLines();

        if (CollectionUtils.isEmpty(start.getLines()) || passes.contains(start)) {
            return NO_STOP_FOUND;
        }

        int leastStops = Integer.MAX_VALUE;
        passes.add(start);
        for (Station.Line line : lines) {
            if (line.getEnd() == end) {
                passes.remove(start);
                return baseStops + 1;
            }

            int stops = getLeastStops(line.getEnd(), end, baseStops + 1, passes);
            if (stops > 0 && stops < leastStops) {
                leastStops = stops;
            }
        }
        passes.remove(start);

        return leastStops == Integer.MAX_VALUE ? NO_STOP_FOUND : leastStops;
    }

    /**
     * 获取两点间最短距离
     * @param startNo 起点
     * @param endNo 终点
     * @return 最短距离
     */
    @Override
    public int getLeastDistance(String startNo, String endNo) {
        Station start = getStation(startNo);
        Station end = getStation(endNo);

        int distance = getLeastDistance(start, end, 0, new HashSet<>(stations.size()));
        if (distance == NO_STOP_FOUND) {
            throw new SystemException(String.format("NO SUCH ROUTE BETWEEN %s AND %s", startNo, endNo));
        }

        return distance;
    }

    /**
     * 获取两点间最短距离核心递归方法
     * @param start 起点
     * @param end 终点
     * @param distanceSum 累计的距离
     * @param stops 存储本次路线经过的站点，用于防止再次经过重复站点而导致死循环
     * @return 最短距离
     */
    private int getLeastDistance(Station start, Station end, int distanceSum, Set<Station> stops) {
        if (CollectionUtils.isEmpty(start.getLines()) || stops.contains(start)) {
            return NO_STOP_FOUND;
        }

        int leastDistance = Integer.MAX_VALUE;
        stops.add(start);
        for (Station.Line line : start.getLines()) {
            if (line.getEnd() == end) {
                stops.remove(start);
                return line.getDistance() + distanceSum;
            }

            int distance = getLeastDistance(line.getEnd(), end, line.getDistance() + distanceSum, stops);
            if (distance > 0 && distance < leastDistance) {
                leastDistance = distance;
            }
        }
        stops.remove(start);

        return leastDistance == Integer.MAX_VALUE ? NO_STOP_FOUND : leastDistance;
    }

    /**
     * 在给定的距离限制下，获取两点间最多有多少条路线
     * @param startNo 起点
     * @param endNo 终点
     * @param distanceLimit 距离限制
     * @return 路线数量
     */
    @Override
    public int getMostLinesNumInDistance(String startNo, String endNo, int distanceLimit) {
        Station start = getStation(startNo);
        Station end = getStation(endNo);

        return getMostLinesNumInDistance(start, end, distanceLimit);
    }

    /**
     * @description getMostLinesNumInDistance的核心递归方法
     **/
    private int getMostLinesNumInDistance(Station start, Station end, int distanceLimit) {
        if (CollectionUtils.isEmpty(start.getLines()) || distanceLimit <= 0) {
            return 0;
        }

        int linesNum = 0;
        for (Station.Line line : start.getLines()) {
            int tempLimit = distanceLimit - line.getDistance();
            if (line.getEnd() == end && tempLimit > 0) {
                linesNum++;
            }

            linesNum += getMostLinesNumInDistance(line.getEnd(), end, tempLimit);
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
    @Override
    public int getLinesNumOfStops(String startNo, String endNo, int stops) {
        Station start = getStation(startNo);
        Station end = getStation(endNo);

        if (stops <= 0) {

        }

        int linesNum = 0;
        for (Station.Line line : start.getLines()) {
            linesNum += getLinesNumOfStops(line.getEnd(), end, stops - 1);
        }

        return linesNum;
    }

    /**
     * @description getLinesNumOfStops的递归核心方法
     */
    private int getLinesNumOfStops(Station start, Station end, int stops) {
        if (CollectionUtils.isEmpty(start.getLines()) || stops < 0) {
            return 0;
        }

        int linesNum = 0;
        for (Station.Line line : start.getLines()) {
            int nextStops = stops - 1;

            if (nextStops < 0) {
                return 0;
            }

            if ((line.getEnd() == end && nextStops == 0)) {
                return 1;
            }

            linesNum += getLinesNumOfStops(line.getEnd(), end, nextStops);
        }

        return linesNum;
    }

    /**
     * @description 根据站点序号获取站点实体
     * @author liqibo
     * @date 2019/9/16 17:56
     * @param startNo 站点序号
     * @return 站点实体
     **/
    private Station getStation(String startNo) {
        Station station = stations.get(startNo);
        if (station == null) {
            throw new SystemException("NO SUCH STATION: " + startNo);
        }
        return station;
    }
}
