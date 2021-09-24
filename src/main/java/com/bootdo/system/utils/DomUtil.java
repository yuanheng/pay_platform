package com.bootdo.system.utils;

import com.bootdo.common.config.Constant;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Map;

/**
 * @author Prometheus
 * @date 2021/7/6.
 */
public class DomUtil {

    public static Document getDoc(final String url, Map<String, String> headers) {
        ConnectUtil.trustEveryone();
        // 获取文档对象
        Document doc = null;
        try {
            Connection con = Jsoup.connect(url).userAgent(Constant.UA[ConnectUtil.RDM.nextInt(Constant.UA.length - 1)])
                    .timeout(30000); // 设置连接超时时间

            con.headers(headers);

            Connection.Response response = con.execute();

            if (response.statusCode() == 200) {
                doc = con.get();
                if (null != doc) {
                    return doc;
                }
            } else {
                System.out.println(response.statusCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
