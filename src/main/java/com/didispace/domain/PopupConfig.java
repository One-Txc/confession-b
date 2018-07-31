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
    private Long popup_config_id;
    @Column
    private Long main_config_id;
    @Column
    private String group_type;
    @Column
    private Integer order;
    @Column
    private String type;
    @Column
    private Integer icon;
    @Column
    private String title;
    @Column
    private String path;
    @Column
    private String content;
    @Column
    private Timestamp create_time;
    @Column
    private Timestamp update_time;

    public Long getPopup_config_id() {
        return popup_config_id;
    }

    public void setPopup_config_id(Long popup_config_id) {
        this.popup_config_id = popup_config_id;
    }

    public Long getMain_config_id() {
        return main_config_id;
    }

    public void setMain_config_id(Long main_config_id) {
        this.main_config_id = main_config_id;
    }

    public String getGroup_type() {
        return group_type;
    }

    public void setGroup_type(String group_type) {
        this.group_type = group_type;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
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

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public Timestamp getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Timestamp update_time) {
        this.update_time = update_time;
    }
}
