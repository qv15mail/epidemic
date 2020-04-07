package com.supwisdom.datashow.basedata.service.impl;

import com.supwisdom.datashow.basedata.domain.CustInfo;
import com.supwisdom.datashow.basedata.mapper.CustInfoMapper;
import com.supwisdom.datashow.basedata.service.CustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustServiceImpl implements CustService {

    @Autowired
    CustInfoMapper custMapper;

    @Override
    public int saveCustInfo(CustInfo custInfo) {
        custMapper.saveCustInfo(custInfo);
        return 0;
    }

    @Override
    public void updateCustInfo(CustInfo custInfo) {
        custMapper.updateCustInfo(custInfo);
    }

    @Override
    public List<CustInfo> getCustWithPage(String custname, String stuempno,String opercode,String status) {
        return custMapper.getCustWithPage(custname,stuempno,opercode,status);
    }

    @Override
    public CustInfo getCustBy(String opercode, String deviceid, String stuempno) {
        return custMapper.getCustBy(opercode,deviceid,stuempno);
    }

    @Override
    public CustInfo getCustById(String opercode, String ids) {
        return custMapper.getCustById(opercode,ids);
    }
}
