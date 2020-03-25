package com.supwisdom.datashow.basedata.service.impl;

import com.supwisdom.datashow.basedata.domain.RecordAnalyse;
import com.supwisdom.datashow.basedata.domain.RecordInfo;
import com.supwisdom.datashow.basedata.mapper.RecordMapper;
import com.supwisdom.datashow.basedata.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    RecordMapper recordMapper;

    @Override
    public List<RecordInfo> getRecordList(String transdate,String custname,String stuempno,
                                          String btemp,String etemp,Integer devid,Integer status) {
        return recordMapper.getRecordList(transdate,custname,stuempno,btemp,etemp,devid,status);
    }

    @Override
    public List<RecordAnalyse> getRecordAnalyse(String transdate) {
        return recordMapper.getRecordAnalyse(transdate);
    }
}
