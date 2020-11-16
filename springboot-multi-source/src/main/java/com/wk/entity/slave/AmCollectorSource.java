package com.wk.entity.slave;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@Setter
@Getter
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

}