package com.bootdo.system.adptor;

import com.bootdo.system.utils.DomUtil;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * @author Prometheus
 * @date 2021/9/24.
 */
public class TbOrderStatusCensor {

    private static final Logger LOGGER = LoggerFactory.getLogger(TbOrderStatusCensor.class);

    /**
     * 淘宝代付单页面状态获取标识
     */
    private static final String STATUS_MARK = "\"peerPayNoticeInfo\"";

    /**
     * 付款成功标识
     */
    private static final String SUCCEED_MARK = "已有人帮TA付款";

    /**
     * 登录用户的COOKIE
     */
    private String cookie;

    /**
     * 付款码子链接
     */
    private String tbPayUrl;

    public TbOrderStatusCensor() {
    }

    public TbOrderStatusCensor(String tbPayUrl, String cookie) {
        Assert.isTrue(StringUtils.isNotEmpty(tbPayUrl), "无效链接。");
        Assert.isTrue(StringUtils.isNotEmpty(cookie), "无效cookie。");
        this.tbPayUrl = tbPayUrl;
        this.cookie = cookie;
    }

    public boolean renderStatus() {
        Document pageDoc = DomUtil.getDoc(this.tbPayUrl, formHeaders(this.cookie));
        final String docStr = pageDoc.toString();
        final int firstIndex = docStr.indexOf(STATUS_MARK);
        if (firstIndex == -1) {
            final String errMsg = "登录异常，未能正确获取代付单页，请确认.";
            LOGGER.error(errMsg);
            throw new IllegalArgumentException(errMsg);
        }
        int indent = firstIndex + STATUS_MARK.length();
        final int offset = 10;
        final String cutoff = docStr.substring(indent + 2, indent + offset);
        if (cutoff.contains(SUCCEED_MARK)) {
            LOGGER.info("已付款成功");
            return true;
        } else {
            LOGGER.info("未付款或订单已取消");
            return false;
        }
    }

    /**
     * 请求头信息，包含cookie（必填）
     *
     * @return
     */
    private static Map<String, String> formHeaders(final String cookie) {
        Map<String, String> headers = Maps.newHashMap();
        headers.put("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        headers.put("cookie", cookie);
        headers.put("x-alipay-client-session", "check");
        headers.put("accept-language", "en-us");
        headers.put("ts", "1632494028330");
        headers.put("user-agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 13_6 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/17G68 Ariver/1.1.0 AliApp(AP/10.2.30.6000) Nebula WK RVKType(1) AlipayDefined(nt:WIFI,ws:414|672|3.0) AlipayClient/10.2.30.6000 Alipay Language/en Region/CN NebulaX/1.0.0");
        headers.put("sign", "c26bc0ab153bb210473fbd528d6fcee6");
        headers.put("accept-encoding", "gzip, deflate, br");
        return headers;
    }

}
