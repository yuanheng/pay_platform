package com.bootdo.system.utils;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author Prometheus
 * @date 2021/7/5.
 */
public class ConnectUtil {


    public static final Random RDM = new Random();

    public static Map<String, String> formMap() {
        final String mainId = "shop-all-list";
        Map<String, String> map = new HashMap<>();
        map.put("fspop", "test");
        map.put("cy", "2");
        map.put("cye", "beijing");
        map.put("_lxsdk_cuid", "179cd4419a373-0efbf88ac4c796-37607201-1ea000-179cd4419a4c8");
        map.put("_lxsdk", "179cd4419a373-0efbf88ac4c796-37607201-1ea000-179cd4419a4c8");
        map.put("Hm_lvt_602b80cf8079ae6591966cc70a3940e7", "1622646463");
        map.put("_hc.v", "0b1d0980-3023-c06f-c6ac-554508f8636f.1622646463");
        map.put("s_ViewType", "10");
        map.put("ctu", "71c44bc600e08c6e00749a4172c416d6bdb532c6c0f7d139315c8a8fbe7d5f17");
        map.put("_lx_utm", "utm_source%3DBaidu%26utm_medium%3Dorganic");
        map.put("_lxsdk_s", "179cd4419a4-6e9-63d-613%7C%7C276");
        map.put("Hm_lpvt_602b80cf8079ae6591966cc70a3940e7", "1622651405");
        map.put("lgtoken", "02795b778-c45e-4404-87a4-6a230e399b42");
        map.put("dplet", "19263a5b7080ab29b9bf31c8acf693f3");
        map.put("dper", "8f19a2978671e6041cf439a7a06598547f57ab23a7a047c45077a3338f5c11d6c529b48ffe9fe1aea51b49c0c1f73be6030fa7ba7ed9b81c6e15bf5c282ec0cbb9b051f256328220ccecb6046abd000ed8ac792ca305c576c22745cd26344d4d");
        map.put("ll", "7fd06e815b796be3df069dec7836c3df");
        map.put("ua", "dpuser_9151525947");
        return map;
    }

    /**
     * 信任任何站点，实现https页面的正常访问
     */
    public static void trustEveryone() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[]{new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class InnerWebClient {
        private static final WebClient webClient = new WebClient();
    }

    /**
     * 获取指定网页实体
     *
     * @param url
     * @return
     */
    public static HtmlPage getHtmlPage(String url, Map<String, String> headers) {
        final String cookieKey = "cookie";
        //调用此方法时加载WebClient
        WebClient webClient = InnerWebClient.webClient;

        HtmlPage page = null;
        try {
            if (!headers.isEmpty()) {
                headers.entrySet().forEach(e -> {
                    if (!cookieKey.equalsIgnoreCase(e.getKey())) {
                        webClient.addRequestHeader(e.getKey(), e.getValue());
                    }
                });
                webClient.addCookie(headers.get(cookieKey), new URL(url), null);
            }
            // 取消 JS 支持
            webClient.getOptions().setJavaScriptEnabled(true);
            // 取消 CSS 支持
            webClient.getOptions().setCssEnabled(false);

            // 获取指定网页实体
            page = (HtmlPage) webClient.getPage(url);
            webClient.waitForBackgroundJavaScript(10 * 1000);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return page;
    }


}
