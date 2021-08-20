package com.bootdo.app.qapple.util;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;


public class RsaUtil {

    public static final String RSA_ALGORITHM = "RSA";

    /**
     * 签名算法
     */
    public static final String SIGN_ALGORITHMS = "SHA1withRSA";
    public static final int keySize = 512;

    /**
     * RSA签名
     * @param content 待签名数据
     * @param privateKey 私钥
     * @return 签名值
     */
    public static String sign(String content, String privateKey)
    {
        try
        {
            byte[] bytes = Base64.getDecoder().decode(privateKey);
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(bytes  );
            KeyFactory keyf = KeyFactory.getInstance(RSA_ALGORITHM);
            PrivateKey priKey = keyf.generatePrivate(priPKCS8);
            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
            signature.initSign(priKey);
            signature.update( content.getBytes("UTF-8"));
            byte[] signed = signature.sign();
            return Base64.getEncoder().encodeToString(signed);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * RSA验签名检查
     * @param content 待签名数据
     * @param sign 签名值
     * @param publicKey
     * @return 布尔值
     */
    public static boolean doCheck(String content, String sign, String publicKey)
    {
        System.out.println("content :"+content);
        try
        {
            KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
            byte[] encodedKey = Base64.getDecoder().decode(publicKey);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

            Signature signature = Signature
                    .getInstance(SIGN_ALGORITHMS);

            signature.initVerify(pubKey);
            signature.update( content.getBytes("UTF-8") );

            boolean bverify = signature.verify( Base64.getDecoder().decode(sign) );
            return bverify;

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

}
