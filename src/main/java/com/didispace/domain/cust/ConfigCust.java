package com.didispace.domain.cust;

import com.didispace.domain.MainConfig;
import com.didispace.domain.PopupConfig;

import java.io.Serializable;
import java.util.List;

/**
 * @author: txc
 * @date: 18-8-4 下午10:47
 */
public class ConfigCust implements Serializable {
    private static final long serialVersionUID = 1L;

    public static String failStatus = "9999";

    private String resultStatus;
    private String errorMsg;

    private MainConfig mainConfig;
    private List<PopupConfig> leftButtonPopupCofigList;
    private List<PopupConfig> rightButtonPopupCofigList;

    public MainConfig getMainConfig() {
        return mainConfig;
    }

    public void setMainConfig(MainConfig mainConfig) {
        this.mainConfig = mainConfig;
    }

    public List<PopupConfig> getLeftButtonPopupCofigList() {
        return leftButtonPopupCofigList;
    }

    public void setLeftButtonPopupCofigList(List<PopupConfig> leftButtonPopupCofigList) {
        this.leftButtonPopupCofigList = leftButtonPopupCofigList;
    }

    public List<PopupConfig> getRightButtonPopupCofigList() {
        return rightButtonPopupCofigList;
    }

    public void setRightButtonPopupCofigList(List<PopupConfig> rightButtonPopupCofigList) {
        this.rightButtonPopupCofigList = rightButtonPopupCofigList;
    }

    public String getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
