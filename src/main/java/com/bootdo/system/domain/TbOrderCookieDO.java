package com.bootdo.system.domain;

import com.bootdo.app.util.DateUtil;
import com.bootdo.common.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;


/**
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2021-09-08 22:32:27
 */
public class TbOrderCookieDO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * cookie
     */
    private String ck;

    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN, timezone = "GMT+08:00")
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCk() {
        return ck;
    }

    public void setCk(String ck) {
        this.ck = ck;
    }
}
