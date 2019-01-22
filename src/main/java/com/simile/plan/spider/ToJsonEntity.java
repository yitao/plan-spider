package com.simile.plan.spider;

import com.alibaba.fastjson.JSON;

/**
 * Created by yitao on 2019/1/8.
 */
public class ToJsonEntity {
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
