package com.didispace.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author: txc
 * @date: 18-7-28 下午6:05
 *
 * JsonIgnoreProperties  因为hibernate懒加载机制会导致给bean中多加一个handler属性导致序列号抛异常,所以加这个注解
 */
@Entity
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class MainConfig implements Serializable{
    private static final long serialVersionUID = 1L;

    /**
     * 配置id
     */
    @Id
    @GeneratedValue
    private Long mainConfigId;
    /**
     * 标题
     */
    @Column
    private String title;
    /**
     * 左边问题
     */
    @Column
    private String question;
    /**
     * 左边按钮文字
     */
    @Column
    private String leftButtonText;
    /**
     * 右边按钮文字
     */
    @Column
    private String rightButtonText;
    /**
     * 用户手机号
     */
    @Column
    private String tel;
    /**
     * 用户淘宝账号
     */
    @Column
    private String taobaoAccount;
    /**
     * 授权码
     */
    @Column
    private String authorizationCode;
    /**
     * 状态:0-不可用,1-可用
     */
    @Column
    private String status;
    /**
     * 生效时间
     */
    @Column
    private Timestamp startTime;
    /**
     * 失效时间
     */
    @Column
    private Timestamp endTime;
    /**
     * 创建
     */
    @Column
    private Timestamp createTime;
    /**
     * 更新
     */
    @Column
    private Timestamp updateTime;

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getMainConfigId() {
        return mainConfigId;
    }

    public void setMainConfigId(Long mainConfigId) {
        this.mainConfigId = mainConfigId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getLeftButtonText() {
        return leftButtonText;
    }

    public void setLeftButtonText(String leftButtonText) {
        this.leftButtonText = leftButtonText;
    }

    public String getRightButtonText() {
        return rightButtonText;
    }

    public void setRightButtonText(String rightButtonText) {
        this.rightButtonText = rightButtonText;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTaobaoAccount() {
        return taobaoAccount;
    }

    public void setTaobaoAccount(String taobaoAccount) {
        this.taobaoAccount = taobaoAccount;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
