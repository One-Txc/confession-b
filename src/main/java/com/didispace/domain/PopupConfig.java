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
 * @date: 18-7-28 下午9:41
 */
@Entity
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class PopupConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long popupConfigId;
    @Column
    private Long mainConfigId;
    @Column
    private String groupType;  //分组[ok-ok按钮的配置,no按钮的配置]
    @Column
    private Integer orderIndex;
    @Column
    private String type;    //type(msg-消息提示,html-弹框)
    @Column
    private Integer icon;
    @Column
    private String title;
    @Column
    private String path;
    @Column
    private String content;
    @Column
    private Timestamp createTime;
    @Column
    private Timestamp updateTime;

    public Long getPopupConfigId() {
        return popupConfigId;
    }

    public void setPopupConfigId(Long popupConfigId) {
        this.popupConfigId = popupConfigId;
    }

    public Long getMainConfigId() {
        return mainConfigId;
    }

    public void setMainConfigId(Long mainConfigId) {
        this.mainConfigId = mainConfigId;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getIcon() {
        return icon;
    }

    public void setIcon(Integer icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
