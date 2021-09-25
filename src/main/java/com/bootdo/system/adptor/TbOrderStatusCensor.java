package com.bootdo.system.adptor;

import com.bootdo.app.util.RedisUtils;
import com.bootdo.common.utils.JSONUtils;
import com.bootdo.system.utils.DomUtil;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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

    private static final String STATUS_KEY = "peerPayNoticeInfo";

    /**
     * 付款成功标识
     */
    private static final String SUCCEED_MARK = "已有人帮TA付款";

    /**
     * 订单已取消标识
     */
    private static final String CANCELLED_MARK = "订单已取消";

    /**
     * 登录用户的COOKIE
     */
    private String cookie;

    /**
     * 付款码子链接
     */
    private String tbPayUrl;

    private RedisUtils redisUtils;

    public TbOrderStatusCensor() {
    }

    public TbOrderStatusCensor(String tbPayUrl, String cookie) {
        Assert.isTrue(StringUtils.isNotEmpty(tbPayUrl), "无效链接。");
        Assert.isTrue(StringUtils.isNotEmpty(cookie), "无效cookie。");
        this.tbPayUrl = tbPayUrl;
        this.cookie = cookie;
    }

    public boolean renderStatus(RedisUtils redisUtils) {
        try {
            Assert.isTrue(redisUtils != null, "redisUtils句柄空了.");
            Document pageDoc = DomUtil.getDoc(this.tbPayUrl, formHeaders(this.cookie));
            Elements scriptEles = pageDoc.getElementsByTag("script");
            if (scriptEles.isEmpty()) {
                final String errMsg = "登录异常，未能正确获取代付单页，请确认.";
                throw new IllegalArgumentException(errMsg);
            }
            Element element = scriptEles.get(5);
            if (element == null) {
                final String errMsg = "登录异常，未能正确获取代付单状态，请确认.";
                throw new IllegalArgumentException(errMsg);
            }
            final String text = element.html();
            final String mark = "var _data = ";
            if (!text.contains(mark)) {
                final String errMsg = "登录异常，未能正确获取代付单状态，请确认.";
                throw new IllegalArgumentException(errMsg);
            }
            final String textMiddle = Splitter.on(mark).splitToList(text).get(1);
            final String jsonStr = Splitter.on("||").splitToList(textMiddle).get(0);
            final Map<String, Object> properties = JSONUtils.jsonToMap(jsonStr);
            final String payStatus = String.valueOf(properties.getOrDefault(STATUS_KEY, "unknown"));
            if (payStatus.equals(SUCCEED_MARK)) {
                LOGGER.info("已付款成功");
                return true;
            } else {
                if (payStatus.equals(CANCELLED_MARK)) {
                    LOGGER.info("订单已取消");
                } else {
                    LOGGER.info("等待付款");
                }
                return false;
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
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
