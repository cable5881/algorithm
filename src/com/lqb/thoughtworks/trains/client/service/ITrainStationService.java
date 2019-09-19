package com.lqb.thoughtworks.trains.client.service;

import java.io.InputStream;

/**
 * @description 站点对外交互接口
 * @author liqibo
 * @date 2019/9/16 19:58
 **/
public interface ITrainStationService {

    /**
     * @description 读取用户输入的站点信息
     * @author liqibo
     * @date 2019/9/16 20:25
     * @param linesStr 用户输入的站点信息
     **/
    void load(String linesStr);

    /**
     * @description 读取文件的站点信息
     * @author liqibo
     * @date 2019/9/16 20:25
     * @param in 文件的站点信息
     **/
    void load(InputStream in);

    /**
     * 指定站点，计算站点间的距离
     * @param nos 多个站点
     * @return 距离
     */
    int getDistance(String... nos);

    /**
     * 获取两点间的最少需要经过几个站点
     * @param startNo 起点
     * @param endNo 终点
     * @return 最少站的数量
     */
    int getLeastStops(String startNo, String endNo);

    /**
     * 获取两点间最短距离
     * @param startNo 起点
     * @param endNo 终点
     * @return 最短距离
     */
    int getLeastDistance(String startNo, String endNo);


    /**
     * 在给定的距离限制下，获取两点间最多有多少条路线
     * @param startNo 起点
     * @param endNo 终点
     * @param distanceLimit 距离限制
     * @return 路线数量
     */
    int getMostLinesNumInDistance(String startNo, String endNo, int distanceLimit);

    /**
     * 在给点的站点数量下，找出两点间有多少种可达的路线
     * @param startNo 起点
     * @param endNo 终点
     * @param stops 站点数量
     * @return 可达的路线数量
     */
    int getLinesNumOfStops(String startNo, String endNo, int stops);

}
