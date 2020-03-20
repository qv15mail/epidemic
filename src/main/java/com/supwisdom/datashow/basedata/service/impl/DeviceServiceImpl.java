package com.supwisdom.datashow.basedata.service.impl;

import com.supwisdom.datashow.basedata.domain.CustInfo;
import com.supwisdom.datashow.basedata.domain.DeviceInfo;
import com.supwisdom.datashow.basedata.mapper.CustInfoMapper;
import com.supwisdom.datashow.basedata.mapper.DeviceMapper;
import com.supwisdom.datashow.basedata.service.CustService;
import com.supwisdom.datashow.basedata.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    DeviceMapper devMapper;

    @Override
    public List<DeviceInfo> getDeviceList() {
        return devMapper.getDeviceList();
    }
}
