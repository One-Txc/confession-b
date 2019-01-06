package com.didispace.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author: txc
 * @date: 19-1-6 下午7:27
 */
@Entity
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class AuthorizationCode implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String code;
    @Column
    private String codeStatus;//授权码状态(1-可用,0-不可用)
    @Column
    private Integer ableAddCount;
    @Column
    private Integer usedAddCount;
    @Column
    private Integer ableUpdateCount;
    @Column
    private Integer usedUpdateCount;
    @Column
    private Timestamp startTime;
    @Column
    private Timestamp endTime;
    @Column
    private Timestamp createTime;
    @Column
    private Timestamp updateTime;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeStatus() {
        return codeStatus;
    }

    public void setCodeStatus(String codeStatus) {
        this.codeStatus = codeStatus;
    }

    public Integer getAbleAddCount() {
        return ableAddCount;
    }

    public void setAbleAddCount(Integer ableAddCount) {
        this.ableAddCount = ableAddCount;
    }

    public Integer getUsedAddCount() {
        return usedAddCount;
    }

    public void setUsedAddCount(Integer usedAddCount) {
        this.usedAddCount = usedAddCount;
    }

    public Integer getAbleUpdateCount() {
        return ableUpdateCount;
    }

    public void setAbleUpdateCount(Integer ableUpdateCount) {
        this.ableUpdateCount = ableUpdateCount;
    }

    public Integer getUsedUpdateCount() {
        return usedUpdateCount;
    }

    public void setUsedUpdateCount(Integer usedUpdateCount) {
        this.usedUpdateCount = usedUpdateCount;
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
