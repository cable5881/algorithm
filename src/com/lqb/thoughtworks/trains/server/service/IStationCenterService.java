package com.lqb.thoughtworks.trains.server.service;

import java.io.InputStream;

/**
 * @author liqibo
 * @description 站点中心接口，负责提供对外信息和列车调度
 * @date 2019/9/16 20:11
 **/
public interface IStationCenterService {

    /**
     * @description 从用户输入中重新加载所有站点信息
     * @author liqibo
     * @date 2019/9/16 15:29
     * @param linesStr 用户输入
     * @param clear 是否清空原有站点信息
     **/
    public void reload(String linesStr, boolean clear);

    /**
     * @description 从用户输入中重新加载所有站点信息
     * @author liqibo
     * @date 2019/9/16 15:29
     * @param in 文件输入
     * @param clear 是否清空原有站点信息
     **/
    public void reload(InputStream in, boolean clear);

    /**
     * 指定站点，计算站点间的距离
     * @param nos 多个站点
     * @return 距离
     */
    public int getDistance(String... nos);

    /**
     * 获取两点间的最少需要经过几个站点
     * @param startNo 起点
     * @param endNo 终点
     * @return 最少站的数量
     */
    public int getLeastStops(String startNo, String endNo);

    /**
     * 获取两点间最短距离
     * @param startNo 起点
     * @param endNo 终点
     * @return 最短距离
     */
    public int getLeastDistance(String startNo, String endNo);

    /**
     * 在给定的距离限制下，获取两点间最多有多少条路线
     * @param startNo 起点
     * @param endNo 终点
     * @param distanceLimit 距离限制
     * @return 路线数量
     */
    public int getMostLinesNumInDistance(String startNo, String endNo, int distanceLimit);


    /**
     * 在给点的站点数量下，找出两点间有多少种可达的路线
     * @param startNo 起点
     * @param endNo 终点
     * @param stops 站点数量
     * @return 可达的路线数量
     */
    public int getLinesNumOfStops(String startNo, String endNo, int stops);

}
