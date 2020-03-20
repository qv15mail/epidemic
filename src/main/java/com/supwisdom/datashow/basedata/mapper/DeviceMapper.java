package com.supwisdom.datashow.basedata.mapper;

import com.supwisdom.datashow.basedata.domain.DeviceInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DeviceMapper {
    public List<DeviceInfo> getDeviceList();

}
