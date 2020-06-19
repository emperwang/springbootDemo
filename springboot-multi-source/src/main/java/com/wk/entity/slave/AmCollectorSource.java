package com.wk.entity.slave;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.ToString;

import java.util.Date;

@ToString
public class AmCollectorSource {
    private String sourceId;

    private String sourceName;

    private String sourceType;

    private String vendorId;

    private String costClass;

    private Integer collectorId;

    private Integer pmPeriod;

    private Integer cmFmHeartBeat;

    private String domainType;

    private String ftpOrSftp;

    private String ftpIp;

    private Integer ftpPort;

    private String ftpUser;

    private String ftpPassword;

    private String socketIp;

    private Integer socketPort;

    private String socketUser;

    private String socketPassword;

    private String state;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String vnfmEndpointUrl;

    private String vnfmUsername;

    private String vnfmPassword;

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId == null ? null : sourceId.trim();
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName == null ? null : sourceName.trim();
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType == null ? null : sourceType.trim();
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId == null ? null : vendorId.trim();
    }

    public String getCostClass() {
        return costClass;
    }

    public void setCostClass(String costClass) {
        this.costClass = costClass == null ? null : costClass.trim();
    }

    public Integer getCollectorId() {
        return collectorId;
    }

    public void setCollectorId(Integer collectorId) {
        this.collectorId = collectorId;
    }

    public Integer getPmPeriod() {
        return pmPeriod;
    }

    public void setPmPeriod(Integer pmPeriod) {
        this.pmPeriod = pmPeriod;
    }

    public Integer getCmFmHeartBeat() {
        return cmFmHeartBeat;
    }

    public void setCmFmHeartBeat(Integer cmFmHeartBeat) {
        this.cmFmHeartBeat = cmFmHeartBeat;
    }

    public String getDomainType() {
        return domainType;
    }

    public void setDomainType(String domainType) {
        this.domainType = domainType == null ? null : domainType.trim();
    }

    public String getFtpOrSftp() {
        return ftpOrSftp;
    }

    public void setFtpOrSftp(String ftpOrSftp) {
        this.ftpOrSftp = ftpOrSftp == null ? null : ftpOrSftp.trim();
    }

    public String getFtpIp() {
        return ftpIp;
    }

    public void setFtpIp(String ftpIp) {
        this.ftpIp = ftpIp == null ? null : ftpIp.trim();
    }

    public Integer getFtpPort() {
        return ftpPort;
    }

    public void setFtpPort(Integer ftpPort) {
        this.ftpPort = ftpPort;
    }

    public String getFtpUser() {
        return ftpUser;
    }

    public void setFtpUser(String ftpUser) {
        this.ftpUser = ftpUser == null ? null : ftpUser.trim();
    }

    public String getFtpPassword() {
        return ftpPassword;
    }

    public void setFtpPassword(String ftpPassword) {
        this.ftpPassword = ftpPassword == null ? null : ftpPassword.trim();
    }

    public String getSocketIp() {
        return socketIp;
    }

    public void setSocketIp(String socketIp) {
        this.socketIp = socketIp == null ? null : socketIp.trim();
    }

    public Integer getSocketPort() {
        return socketPort;
    }

    public void setSocketPort(Integer socketPort) {
        this.socketPort = socketPort;
    }

    public String getSocketUser() {
        return socketUser;
    }

    public void setSocketUser(String socketUser) {
        this.socketUser = socketUser == null ? null : socketUser.trim();
    }

    public String getSocketPassword() {
        return socketPassword;
    }

    public void setSocketPassword(String socketPassword) {
        this.socketPassword = socketPassword == null ? null : socketPassword.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getVnfmEndpointUrl() {
        return vnfmEndpointUrl;
    }

    public void setVnfmEndpointUrl(String vnfmEndpointUrl) {
        this.vnfmEndpointUrl = vnfmEndpointUrl;
    }

    public String getVnfmUsername() {
        return vnfmUsername;
    }

    public void setVnfmUsername(String vnfmUsername) {
        this.vnfmUsername = vnfmUsername;
    }

    public String getVnfmPassword() {
        return vnfmPassword;
    }

    public void setVnfmPassword(String vnfmPassword) {
        this.vnfmPassword = vnfmPassword;
    }
}