package com.supwisdom.datashow.basedata.controller;

import cn.afterturn.easypoi.entity.vo.MapExcelConstants;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.view.PoiBaseView;
import cn.hutool.core.util.RandomUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supwisdom.datashow.basedata.domain.*;
import com.supwisdom.datashow.basedata.service.CustService;
import com.supwisdom.datashow.basedata.service.DeviceService;
import com.supwisdom.datashow.basedata.service.RecordService;
import com.supwisdom.datashow.system.domain.UserRoleBean;
import com.supwisdom.datashow.utils.DateUtil;
import com.supwisdom.datashow.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.PrintWriter;
import java.util.*;

@Controller
public class CustController {
    private static final Logger log = LoggerFactory.getLogger(CustController.class);
    String thisSchoolYear = DateUtil.getTermDate();

    @Autowired
    private CustService custService;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private RecordService recordService;

    @RequestMapping("/yy")
    public String yy(ModelMap model){
        return "basedata/yy";
    }

    @RequestMapping("/basedata/userPortrait")
    public String cts(ModelMap model){
        return "basedata/userPortrait";
    }

    @RequestMapping("/mu_listimport")
    public String listimport(ModelMap model){
        return "basedata/listimport";
    }

    @RequestMapping("/mu_listmgr")
    public String listmgr(ModelMap model){
        return "basedata/custmanager";
    }

    @RequestMapping("/mu_dtlquery")
    public String dtlquery(ModelMap model){
        return "basedata/recorddtl";
    }

    @RequestMapping("/mu_dtlanalyse")
    public String dtlanalyse(ModelMap model){
        return "basedata/recordanalyse";
    }

    @RequestMapping("/acc/curStatu")
    @ResponseBody
    public Map curStatu(){
        Map map = new HashMap();
        return map;
    }

    @RequestMapping(value = "/acc/downImpTemplate")
    public void downImpTemplate(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        try {
            List<ExcelExportEntity> entity = new ArrayList<ExcelExportEntity>();
            /**
             * 1. 设置列信息：name：列标题   key：	列属性
             */
            entity.add(new ExcelExportEntity("学工号", "stuempno"));
            entity.add(new ExcelExportEntity("姓名", "custname"));
            entity.add(new ExcelExportEntity("开始日期", "begindate"));
            entity.add(new ExcelExportEntity("有效期", "expiredate"));
            //entity.add(new ExcelExportEntity("设备名称", "devname"));
            List<Map> personList = new ArrayList<Map>();

            /**
             * 3.设置表格属性: title:标题  sheetName:工作簿名 type:表格类型
             */
            ExportParams params = new ExportParams(null, "sheet1", ExcelType.HSSF);
            map.put(MapExcelConstants.MAP_LIST, personList);
            map.put(MapExcelConstants.ENTITY_LIST, entity);
            map.put(MapExcelConstants.PARAMS, params);
            map.put(MapExcelConstants.FILE_NAME, "名单导入模板");
            map.put("result", "导出excel文件完成");
            PoiBaseView.render(map, request, response, MapExcelConstants.EASYPOI_MAP_EXCEL_VIEW);

        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", "导出excel文件失败");
        }
    }

    @RequestMapping(value = "/acc/getDevList",method = {RequestMethod.GET})
    @ResponseBody
    public Map getFillFormList() throws Exception {
        List<DeviceInfo> devList = deviceService.getDeviceList();
        Map map = new HashMap();
        map.put("devList", devList);
        return map;
    }

    private void rtnInfo(HttpServletResponse response,PrintWriter out,String outinfo){
        try {
            response.setContentType("text/html; charset=utf-8");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("X-Frame-Options", "SAMEORIGIN");
            out = response.getWriter();
            out.write(outinfo);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (out != null){
            out.close();
            }
        }
    }

    @RequestMapping(value = "/acc/impCustList", method = {RequestMethod.POST})
    @ResponseBody
    public Map impDoorCardList(@RequestParam(value = "file", required = false) MultipartFile file,
                               HttpServletRequest request,HttpServletResponse response) {
        Map map = new HashMap();
        PrintWriter out = null;
        try {
            HttpSession session = request.getSession();
            String opercode = (String) session.getAttribute("loginName");
            String devids = request.getParameter("devids");
            String path = request.getSession().getServletContext().getRealPath("upload");
            String fileName = file.getOriginalFilename();
            File targetFile = new File(path,fileName);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            file.transferTo(targetFile);
            int rtn = impCustList(path+"/"+fileName,opercode,devids,map);
            //System.out.println("devids:"+devids);
            //System.out.println(path+"/"+fileName);
            if (rtn<0){
                return map;
            }
            //rtnInfo(response,out,"已经导入成功");
        } catch (Exception e) {
            e.printStackTrace();
            return map;
        }finally {
            if (out != null){
                out.close();
            }

        }
        return map;
    }

    private int impCustList(String fpath,String opercode,String devids,Map map) {
        ImportParams params = new ImportParams();
        params.setTitleRows(0);
        List<CustBean> custList = ExcelImportUtil.importExcel(new File(fpath), CustBean.class, params);
        int totCnt = custList.size();
        if (custList.size() <= 0) {
            return -1;
        }
        map.put("imp_succ", "");
        int tmpi = 0;
        int succCnt = 0;
        int errCnt = 0;
        String[] devList = devids.split(",");
        long startTime = System.currentTimeMillis();
        for (String dev:devList){
            String devid = dev;
            for(CustBean cust:custList){
                boolean bTemp;
                String stuempno = cust.getStuempno();
                if (stuempno==null){
                    continue;
                }
                try {
                    CustInfo custInfo = custService.getCustBy(opercode, devid, stuempno);
                    if (custInfo == null) {
                        custInfo = new CustInfo();
                        //不存在记录
                        bTemp = false;
                    } else {
                        //存在记录
                        bTemp = true;
                    }

                    custInfo.setStuempno(stuempno);
                    custInfo.setCustname(cust.getCustname());
                    custInfo.setAdddelflag("1");
                    if (cust.getBegindate() == null || cust.getBegindate().equals("")) {
                        custInfo.setBegindate(DateUtil.getNow("yyyyMMdd"));
                    } else {
                        custInfo.setBegindate(cust.getBegindate());
                    }
                    if (cust.getExpiredate()==null || cust.getExpiredate().equals("")){
                        custInfo.setExpiredate("20201231");
                    }else{
                        custInfo.setExpiredate(cust.getExpiredate());
                    }

                    custInfo.setOpercode(opercode);
                    custInfo.setSyncflag("0");
                    custInfo.setSynctime("");
                    custInfo.setUpdatetime(DateUtil.getNow());
                    if (bTemp == true) {
                        custService.updateCustInfo(custInfo);
                    } else {
                        custInfo.setIds(UUID.randomUUID().toString().replaceAll("-", ""));
                        custInfo.setImporttime(DateUtil.getNow());
                        custInfo.setDeviceid(devid);
                        custInfo.setVerno(0);
                        custService.saveCustInfo(custInfo);
                    }
                    succCnt++;
                    tmpi++;
                }catch (Exception ex){
                    log.error(ex.getMessage()+",stuempno:"+stuempno);
                    errCnt++;
                }
            }
        }
        //获取结束时间
        long endTime = System.currentTimeMillis();
        log.info("名单导入消耗时间： " + (endTime - startTime) + "ms");
        map.put("imp_totCnt",totCnt);
        map.put("imp_succCnt",succCnt);
        map.put("imp_errCnt",errCnt);
        return 0;
    }

    @ResponseBody
    @RequestMapping("acc/getCustList")
    public Map getUserList(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "custname",required = false)String custname,
            @RequestParam(value = "stuempno",required = false)String stuempno,
            @RequestParam(value = "status",required = false)String status,
            @RequestParam(value = "pageNo",required = false,defaultValue = "1")int pageNo,
            @RequestParam(value = "pageSize",required = false,defaultValue = "10")int pageSize){
        Map map = new HashMap();
        try {
            PageHelper.startPage(pageNo,pageSize);
            HttpSession session = request.getSession();
            String opercode = (String) session.getAttribute("loginName");
            List<CustInfo> getCustList = custService.getCustWithPage(custname,stuempno,opercode,status);
            PageInfo<CustInfo> pageInfo = new PageInfo<>(getCustList);

            map.put("PageResult",pageInfo);
        }catch (Exception e){
            e.printStackTrace();
            log.error("查询用户失败："+e.getMessage());
        }
        return map;
    }

    @ResponseBody
    @RequestMapping("/acc/deleteCust")
    public Map deleteUser(@RequestParam(value = "ids",required = true) String ids,
                          HttpServletRequest request){
        Map map = new HashMap();
        int okFlag = 0;
        String message ="";
        HttpSession session = request.getSession();
        String opercode = (String)session.getAttribute("loginName");
        try{
            CustInfo custInfo = custService.getCustById(opercode,ids);
            if (custInfo == null){
                message = "该用户不存在";
            }else {
                custInfo.setAdddelflag("2");
                custInfo.setUpdatetime(DateUtil.getNow());
                custInfo.setSyncflag("0");
                custInfo.setSynctime("");
                okFlag = 1;
                custService.updateCustInfo(custInfo);
            }
        }catch (Exception e){
            e.printStackTrace();
            message = "删除用户出现异常";
            log.error("删除用户失败："+e.getMessage());
        }
        map.put("okFlag",okFlag);
        map.put("message",message);
        map.put("result",message);
        return map;
    }

    @ResponseBody
    @RequestMapping("/acc/getDtlList")
    public Map getDtlList(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "custname",required = false)String custname,
            @RequestParam(value = "stuempno",required = false)String stuempno,
            @RequestParam(value = "btemp",required = false)String btemp,
            @RequestParam(value = "etemp",required = false)String etemp,
            @RequestParam(value = "bdate",required = false)String bdate,
            @RequestParam(value = "devid",required = false)String devid,
            @RequestParam(value = "status",required = false)String status,
            @RequestParam(value = "pageNo",required = false,defaultValue = "1")int pageNo,
            @RequestParam(value = "pageSize",required = false,defaultValue = "10")int pageSize){
        Map map = new HashMap();
        try {
            PageHelper.startPage(pageNo,pageSize);
            HttpSession session = request.getSession();
            //String opercode = (String) session.getAttribute("loginName");
            List<RecordInfo> getDtlList = recordService.getRecordList(bdate,custname,stuempno,btemp,etemp,Integer.parseInt(devid),Integer.parseInt(status));
            PageInfo<RecordInfo> pageInfo = new PageInfo<>(getDtlList);

            map.put("PageResult",pageInfo);
        }catch (Exception e){
            e.printStackTrace();
            log.error("查询流水失败："+e.getMessage());
        }
        return map;
    }

    /**
     * 预约申请保存
     * @param postData
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/acc/saveAppointment")
    public Map saveAppointment(@RequestBody Appoinment postData,
                          HttpServletRequest request){
        Map map = new HashMap();
        int okFlag = 0;
        String message ="";
        HttpSession session = request.getSession();
        String opercode = (String)session.getAttribute("loginName");
        try{
            CustInfo custInfo = custService.getCustById(opercode,"");
            if (custInfo == null){
                message = "该用户不存在";
            }else {
                custInfo.setAdddelflag("2");
                custInfo.setUpdatetime(DateUtil.getNow());
                custInfo.setSyncflag("0");
                custInfo.setSynctime("");
                okFlag = 1;
                custService.updateCustInfo(custInfo);
            }
        }catch (Exception e){
            e.printStackTrace();
            message = "删除用户出现异常";
            log.error("删除用户失败："+e.getMessage());
        }
        map.put("okFlag",okFlag);
        map.put("message",message);
        map.put("result",message);
        return map;
    }

    @ResponseBody
    @RequestMapping("/acc/getDtlAnalyse")
    public Map getDtlAnalyse(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "bdate",required = false)String bdate,
            @RequestParam(value = "pageNo",required = false,defaultValue = "1")int pageNo,
            @RequestParam(value = "pageSize",required = false,defaultValue = "10")int pageSize){
        Map map = new HashMap();
        try {
            PageHelper.startPage(pageNo,pageSize);
            //HttpSession session = request.getSession();
            //String opercode = (String) session.getAttribute("loginName");
            List<RecordAnalyse> getDtlList = recordService.getRecordAnalyse(bdate);
            PageInfo<RecordAnalyse> pageInfo = new PageInfo<>(getDtlList);

            map.put("PageResult",pageInfo);
        }catch (Exception e){
            e.printStackTrace();
            log.error("流水分析失败："+e.getMessage());
        }
        return map;
    }

}
