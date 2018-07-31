package com.didispace.web;

import com.alibaba.fastjson.JSON;
import com.didispace.domain.MainConfig;
import com.didispace.domain.PopupConfig;
import com.didispace.repository.MainConfigRepository;
import com.didispace.repository.PopupConfigRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value="/confession/mc/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public MainConfig gerMc(@PathVariable Long id) {
        MainConfig mc = mainConfigRepository.getOne(id);
        //mc.getTitile();
        //System.out.println(mc.toString());
        return mc;
    }

    @RequestMapping(value="/confession/mcm/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String ss(@PathVariable Long id) {
        return "qqqq";
    }





    @RequestMapping(value="/confession/mc/save")
    public String save(@RequestBody Map map) {
        MainConfig mainConfig = JSON.parseObject(JSON.toJSONString(map.get("mainConfig")),MainConfig.class);
        List<PopupConfig> popupConfigsList = JSON.parseArray(JSON.toJSONString(map.get("popupConfigsList")),PopupConfig.class);
        MainConfig dbMainConfig = mainConfigRepository.save(mainConfig);
        System.out.println(mainConfig == dbMainConfig);

        List<PopupConfig> dbPopupConfigsList = popupConfigRespository.save(popupConfigsList);
        System.out.println(popupConfigsList == dbPopupConfigsList);

        return "success";
    }



}
