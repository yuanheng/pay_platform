package com.bootdo.system.adptor;

import com.bootdo.common.config.Constant;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Prometheus
 * @date 2021/9/25.
 */
public class UrlTransformer {

    private String sourceUrl;

    private static final String S_BIZ_NO = "{biz_no}";
    private static final String S_SIGN_APP_NAME = "{app_name}";
    private static final String S_SC = "{sc}";
    private static final String S_V = "{v}";
    private static final String S_SIGN = "{sign}";
    private static final String S___WEBVIEW_OPTIONS__ = "{__webview_options__}";

    private static final String TARGET_URL_TEMPLATE = String.format("https://mclient.alipay.com/h5/peerpay.htm?biz_no=%s&app_name=%s&sc=%s&v=%s&sign=%s&__webview_options__=%s",
            S_BIZ_NO, S_SIGN_APP_NAME, S_SC, S_V, S_SIGN, S___WEBVIEW_OPTIONS__);

    public UrlTransformer() {
    }

    public UrlTransformer(String sourceUrl) {
        Assert.isTrue(StringUtils.isNotEmpty(sourceUrl), "无效链接.");
        this.sourceUrl = sourceUrl;
    }

    public String renderTargetUrl() {
        Map<String, String> paramMap = parseParams();
        if (paramMap.isEmpty()) {
            final String errMsg = String.format("无效url -> [%s].", this.sourceUrl);
            throw new IllegalArgumentException(errMsg);
        }
        String targetUrl = TARGET_URL_TEMPLATE;
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            final String key = String.format("%s%s%s", "{", entry.getKey(), "}");
            targetUrl = targetUrl.replace(key, entry.getValue());
        }
        if (targetUrl.contains("{") || targetUrl.contains("}")) {
            final String errMsg = String.format("目标链接[%s]不完整。", targetUrl);
            throw new IllegalArgumentException(errMsg);
        }
        return targetUrl;
    }

    private Map<String, String> parseParams() {
        if (this.sourceUrl.contains(Constant.SYMBOL_Q)) {
            Map<String, String> paramMap = new HashMap<>(8);
            final String paramStr = Splitter.on(Constant.SYMBOL_Q).splitToList(this.sourceUrl).get(1);
            if (StringUtils.isNotEmpty(paramStr)) {
                List<String> arr = Splitter.on(Constant.SYMBOL_AMPERSAND).splitToList(paramStr);
                for (String a : arr) {
                    List<String> m = Splitter.on(Constant.SYMBOL_EQUAL).splitToList(a);
                    paramMap.put(m.get(0), m.get(1));
                }
                return paramMap;
            } else {
                return Maps.newHashMap();
            }
        } else {
            return Maps.newHashMap();
        }
    }

}
