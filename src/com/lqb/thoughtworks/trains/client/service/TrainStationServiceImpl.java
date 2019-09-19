package com.lqb.thoughtworks.trains.client.service;

import com.lqb.thoughtworks.trains.server.service.IStationCenterService;
import com.lqb.thoughtworks.trains.server.service.StationCenterServiceImpl;

import java.io.InputStream;

/**
 * @description 站点对外交互接口实现
 * @author liqibo
 * @date 2019/9/16 19:58
 **/
public class TrainStationServiceImpl implements ITrainStationService {

    private IStationCenterService stationCenter;

    public TrainStationServiceImpl() {
        //默认实现
        stationCenter = new StationCenterServiceImpl();
    }

    public TrainStationServiceImpl(IStationCenterService stationCenter) {
        this.stationCenter = stationCenter;
    }

    @Override
    public void load(String linesStr) {
        stationCenter.reload(linesStr, true);
    }

    @Override
    public void load(InputStream in) {
        stationCenter.reload(in, true);
    }

    @Override
    public int getDistance(String... nos) {
        return stationCenter.getDistance(nos);
    }

    @Override
    public int getLeastStops(String startNo, String endNo) {
        return stationCenter.getLeastStops(startNo, endNo);
    }

    @Override
    public int getLeastDistance(String startNo, String endNo) {
        return stationCenter.getLeastStops(startNo, endNo);
    }

    @Override
    public int getMostLinesNumInDistance(String startNo, String endNo, int distanceLimit) {
        return stationCenter.getMostLinesNumInDistance(startNo, endNo, distanceLimit);
    }

    @Override
    public int getLinesNumOfStops(String startNo, String endNo, int stops) {
        return stationCenter.getLinesNumOfStops(startNo, endNo, stops);
    }

}

