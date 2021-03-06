package com.supwisdom.datashow.basedata.mapper;

import com.supwisdom.datashow.basedata.domain.CustInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CustInfoMapper {
    public int saveCustInfo(CustInfo custInfo);

    public void updateCustInfo(CustInfo custInfo);

    public List<CustInfo> getCustWithPage(@Param("custname") String custname, @Param("stuempno") String stuempno,
                                          @Param("opercode") String opercode,@Param("status") String status);

    public CustInfo getCustBy(@Param("opercode") String opercode,@Param("deviceid") String deviceid, @Param("stuempno") String stuempno);

    public CustInfo getCustById(@Param("opercode") String opercode,@Param("ids") String ids);
}
