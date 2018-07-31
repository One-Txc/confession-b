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

    @Id
    @GeneratedValue
    private Long main_config_id;
    @Column
    private String titile;
    @Column
    private String question;
    @Column
    private String tel;
    @Column
    private String taobao_account;
    @Column
    private Timestamp start_time;
    @Column
    private Timestamp end_time;
    @Column
    private Timestamp create_time;
    @Column
    private Timestamp update_time;

    public Long getMain_config_id() {
        return main_config_id;
    }

    public void setMain_config_id(Long main_config_id) {
        this.main_config_id = main_config_id;
    }

    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTaobao_account() {
        return taobao_account;
    }

    public void setTaobao_account(String taobao_account) {
        this.taobao_account = taobao_account;
    }

    public Timestamp getStart_time() {
        return start_time;
    }

    public void setStart_time(Timestamp start_time) {
        this.start_time = start_time;
    }

    public Timestamp getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Timestamp end_time) {
        this.end_time = end_time;
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

    @Override
    public String toString() {
        return "MainConfig{" +
                "main_config_id=" + main_config_id +
                ", titile='" + titile + '\'' +
                ", question='" + question + '\'' +
                ", tel='" + tel + '\'' +
                ", taobao_account='" + taobao_account + '\'' +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                '}';
    }
}
