package com.jp.base.dubbo.service;

import com.jp.base.dubbo.bean.DubboInArgsBean;
import com.jp.base.dubbo.bean.DubboOutArgsBean;

public abstract interface DubboService {

    public abstract DubboOutArgsBean doPost(DubboInArgsBean inArgsBean);

}
