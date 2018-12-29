package com.didispace.web;

import com.alibaba.fastjson.JSON;
import com.didispace.domain.MainConfig;
import com.didispace.domain.PopupConfig;
import com.didispace.domain.cust.ConfigCust;
import com.didispace.repository.MainConfigRepository;
import com.didispace.repository.PopupConfigRespository;
import com.didispace.util.model.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @author: txc
 * @date: 18-7-28 下午6:33
 */
@RestController
public class MainConfigController {
    @Autowired
    MainConfigRepository mainConfigRepository;
    @Autowired
    PopupConfigRespository popupConfigRespository;

    @RequestMapping(value="/mc/{id}", method=RequestMethod.GET)
    public ConfigCust gerMc(@PathVariable Long id) {
        MainConfig mc = mainConfigRepository.getOne(id);
        Sort sort = new Sort("orderIndex");
        List<PopupConfig> leftList = popupConfigRespository.findAllByMainConfigIdAndAndGroupType(id,"left",sort);
        List<PopupConfig> rightList = popupConfigRespository.findAllByMainConfigIdAndAndGroupType(id,"right",sort);
        ConfigCust result = new ConfigCust();
        result.setMainConfig(mc);
        result.setLeftButtonPopupCofigList(leftList);
        result.setRightButtonPopupCofigList(rightList);

        //mc.getTitile();
        //System.out.println(mc.toString());
        return result;
    }

    @RequestMapping(value="/mcm/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String ss(@PathVariable Long id) {
        return "qqqq";
    }





    @RequestMapping(value="/mc/save")
    public ResultData save(@RequestBody Map map) {
        MainConfig mainConfig = JSON.parseObject(JSON.toJSONString(map.get("mainConfig")),MainConfig.class);
        List<PopupConfig> leftButtonPopupCofigList = JSON.parseArray(JSON.toJSONString(map.get("leftButtonPopupCofigList")),PopupConfig.class);
        List<PopupConfig> rightButtonPopupCofigList = JSON.parseArray(JSON.toJSONString(map.get("rightButtonPopupCofigList")),PopupConfig.class);

        //数据填充
        mainConfig.setStartTime(new Timestamp(System.currentTimeMillis()));
        mainConfig.setEndTime(new Timestamp(System.currentTimeMillis()+30*24*60*60*1000L));

        MainConfig dbMainConfig = mainConfigRepository.save(mainConfig);
        System.out.println(mainConfig == dbMainConfig);

        for (int i=0;i<leftButtonPopupCofigList.size();i++){
            PopupConfig popupConfig = leftButtonPopupCofigList.get(i);
            popupConfig.setMainConfigId(dbMainConfig.getMainConfigId());
            popupConfig.setOrderIndex(i);
            popupConfig.setGroupType("left");
        }
        List<PopupConfig> dbLeftButtonPopupConfigsList = popupConfigRespository.save(leftButtonPopupCofigList);
        System.out.println(dbLeftButtonPopupConfigsList == leftButtonPopupCofigList);

        for (int i=0;i<rightButtonPopupCofigList.size();i++){
            PopupConfig popupConfig = rightButtonPopupCofigList.get(i);
            popupConfig.setMainConfigId(dbMainConfig.getMainConfigId());
            popupConfig.setOrderIndex(i);
            popupConfig.setGroupType("right");
        }
        popupConfigRespository.save(rightButtonPopupCofigList);

        return ResultData.successWhitResp(mainConfig.getMainConfigId()+"");
    }



}
