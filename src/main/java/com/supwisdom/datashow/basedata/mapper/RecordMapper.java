package com.supwisdom.datashow.basedata.mapper;

import com.supwisdom.datashow.basedata.domain.RecordAnalyse;
import com.supwisdom.datashow.basedata.domain.RecordInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RecordMapper {
    public List<RecordInfo> getRecordList(@Param("transdate") String transdate,
                                          @Param("custname") String custname,
                                          @Param("stuempno") String stuempno,
                                          @Param("btemp") String btemp,
                                          @Param("etemp") String etemp,
                                          @Param("devid") Integer devid,
                                          @Param("status") Integer status);

    public List<RecordAnalyse> getRecordAnalyse(@Param("transdate") String transdate);
}
