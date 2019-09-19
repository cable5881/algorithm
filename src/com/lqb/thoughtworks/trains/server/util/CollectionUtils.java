package com.lqb.thoughtworks.trains.server.util;

import java.util.Collection;

/**
 * @author liqibo
 * @description 集合工具类
 * @date 2019/9/16 10:17
 **/
public class CollectionUtils {

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.size() == 0;
    }

}
