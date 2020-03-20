package com.supwisdom.datashow.basedata.service;


import com.supwisdom.datashow.basedata.domain.CustInfo;
import com.supwisdom.datashow.basedata.domain.DeviceInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeviceService {
    public List<DeviceInfo> getDeviceList();
}
