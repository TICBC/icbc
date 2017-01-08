/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.util.component.payment.alipay;

/**
 * 支付宝配置文件
 *
 * @author mi.li
 * @version $ID: v 0.1 下午9:26 mi.li Exp $
 */
public class AlipayConfig {
    /**
     * 支付宝提供给商户的服务接入网关URL(新)
     */
    public static final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?";

    public static final String TIGER_GATEWAY = "https://www.xiaoguotech.com";

    // 合作身份者ID，以2088开头由16位纯数字组成的字符串
    public static String partner = "";

    // 收款支付宝账号
    public static String seller_email = "";

    // 商户的私钥
    public static String key = "";

    // 字符编码格式 目前支持 gbk 或 utf-8
    public static String input_charset = "utf-8";

    // 签名方式 不需修改
    public static String sign_type = "MD5";

    //超时时间
    public static String it_b_pay = "1h";
}
