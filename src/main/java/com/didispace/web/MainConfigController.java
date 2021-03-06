package com.didispace.web;

import com.alibaba.fastjson.JSON;
import com.didispace.domain.AuthorizationCode;
import com.didispace.domain.MainConfig;
import com.didispace.domain.PopupConfig;
import com.didispace.domain.cust.ConfigCust;
import com.didispace.exception.MyException;
import com.didispace.repository.AuthorizationCodeRepository;
import com.didispace.repository.MainConfigRepository;
import com.didispace.repository.PopupConfigRespository;
import com.didispace.util.MD5Util;
import com.didispace.util.model.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: txc
 * @date: 18-7-28 下午6:33
 */
@RequestMapping(value="confession")
@RestController
public class MainConfigController {
    @Autowired
    MainConfigRepository mainConfigRepository;
    @Autowired
    PopupConfigRespository popupConfigRespository;
    @Autowired
    AuthorizationCodeRepository authorizationCodeRepository;

    @RequestMapping(value="/mc/{id}", method=RequestMethod.GET)
    public ConfigCust getMc(@PathVariable Long id) {
        ConfigCust result = new ConfigCust();

        MainConfig mc = null;
        try {
            mc = mainConfigRepository.getOne(id);
        }catch (Exception e){
            result.setResultStatus(ConfigCust.failStatus);
            result.setErrorMsg(e.getMessage());
            return result;
        }

        //校验
        if("0".equals(mc.getStatus())){
            result.setResultStatus(ConfigCust.failStatus);
            result.setErrorMsg("配置过期了");
            return result;
        }
        Timestamp now = new Timestamp(System.currentTimeMillis());
        if(mc.getEndTime()!=null && mc.getEndTime().before(now)){
            result.setResultStatus(ConfigCust.failStatus);
            result.setErrorMsg("配置过期了");
            return result;
        }

        Sort sort = new Sort("orderIndex");
        List<PopupConfig> leftList = popupConfigRespository.findAllByMainConfigIdAndAndGroupType(id,"left",sort);
        List<PopupConfig> rightList = popupConfigRespository.findAllByMainConfigIdAndAndGroupType(id,"right",sort);
        result.setMainConfig(mc);
        result.setLeftButtonPopupCofigList(leftList);
        result.setRightButtonPopupCofigList(rightList);
        //mc.getTitile();
        //System.out.println(mc.toString());
        return result;
    }



    @RequestMapping(value="/authorizationCode/creat/{count}", method=RequestMethod.GET)
    public List<String> creatAuthorizationCode(@PathVariable Integer count) {
        List<String> codeList = new ArrayList<>(count);
        for (int i=0;i<=count;i++){
            AuthorizationCode authorizationCode = new AuthorizationCode();
            //String
            authorizationCode.setCode(MD5Util.MD5Encode(System.currentTimeMillis()+"","UTF-8"));
            authorizationCode.setAbleAddCount(1);
            authorizationCode.setUsedAddCount(0);
            authorizationCode.setAbleUpdateCount(1);
            authorizationCode.setUsedUpdateCount(0);
            authorizationCode.setCodeStatus("1");
            try {
                authorizationCodeRepository.save(authorizationCode);
                codeList.add(authorizationCode.getCode());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return codeList;
    }



    private Boolean autoAuthorizationCode = true;

    @RequestMapping(value="/mc/save")
    public ResultData save(@RequestBody Map map) {
        MainConfig mainConfig = JSON.parseObject(JSON.toJSONString(map.get("mainConfig")),MainConfig.class);
        List<PopupConfig> leftButtonPopupCofigList = JSON.parseArray(JSON.toJSONString(map.get("leftButtonPopupCofigList")),PopupConfig.class);
        List<PopupConfig> rightButtonPopupCofigList = JSON.parseArray(JSON.toJSONString(map.get("rightButtonPopupCofigList")),PopupConfig.class);

        //authorizationCode校验
        String authorizationCode = mainConfig.getAuthorizationCode();

        if("9999".equals(authorizationCode) && autoAuthorizationCode){
            authorizationCode = creatAuthorizationCode(0).get(0);
            mainConfig.setAuthorizationCode(authorizationCode);
        }

        try {
            addCheckAuthorizationCode(authorizationCode);
        }catch (MyException e){
            return ResultData.fail(e.getMessage());
        }

        //code无效化
        AuthorizationCode codeInfo = authorizationCodeRepository.getOne(authorizationCode);
        codeInfo.setUsedAddCount(codeInfo.getUsedAddCount()+1);
        if(codeInfo.getUsedAddCount()>=codeInfo.getAbleAddCount()){
            codeInfo.setCodeStatus("0");
        }
        authorizationCodeRepository.save(codeInfo);

        //数据填充
        mainConfig.setStartTime(new Timestamp(System.currentTimeMillis()));
        //30天后不可用
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

    /**
     * authorizationCode校验
     * @return
     */
    private boolean addCheckAuthorizationCode(String authorizationCode) throws MyException {
        if(authorizationCode == null){
            throw new MyException("授权码为空");
        }
        AuthorizationCode codeInfo = authorizationCodeRepository.findOne(authorizationCode);

        if(codeInfo == null){
            throw new MyException("授权码不存在");
        }
        if(!"1".equals(codeInfo.getCodeStatus())){
            throw new MyException("授权码已不可用");
        }
        if(codeInfo.getAbleAddCount() <= codeInfo.getUsedAddCount()){
            throw new MyException("授权码已使用");
        }
//        Timestamp now = new Timestamp(System.currentTimeMillis());
//        if(codeInfo.getStartTime()!=null && codeInfo.getStartTime().before(now)){
//            return false;
//        }
//        if(codeInfo.getEndTime()!=null && codeInfo.getEndTime().after(now)){
//            return false;
//        }
        return true;
    }



}
