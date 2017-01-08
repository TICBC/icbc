/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.util.component.payment.alipay;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import tiger.common.util.component.payment.Base.Refund;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mi.li
 * @version v 0.1 15/10/30 下午11:22 mi.li Exp $
 */
public class RefundAlipay extends Refund {
    /**
     * 生成签名结果
     *
     * @param sPara 要签名的数组
     * @return 签名结果字符串
     */
    public static String buildRequestMysign(Map<String, String> sPara) {
        String prestr = AlipayCore.createLinkString(sPara); //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String mysign = "";
        if (AlipayConfig.sign_type.equals("MD5")) {
            mysign = MD5.sign(prestr, AlipayConfig.key, AlipayConfig.input_charset);
        }
        return mysign;
    }

    /**
     * MAP类型数组转换成NameValuePair类型
     *
     * @param properties MAP类型数组
     * @return NameValuePair类型List
     */
    private static List<NameValuePair> generatNameValuePair(Map<String, String> properties) {

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return params;
    }

    @Override
    public void refund() {
        Map<String, String> sPara = buildRequestPara();

        try {
            //创建HttpClientBuilder
            HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
            //HttpClient
            CloseableHttpClient httpClient = httpClientBuilder.build();

            HttpPost httpPost = new HttpPost(AlipayConfig.ALIPAY_GATEWAY_NEW);
            List<NameValuePair> params = generatNameValuePair(sPara);
            httpPost.setEntity(new UrlEncodedFormEntity(params));

            HttpResponse response = httpClient.execute(httpPost);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private Map<String, String> buildRequestPara() {
        Map<String, String> sPara = AlipayCore.paraFilter(buildRequestParaTemp());
        //生成签名结果
        String mysign = buildRequestMysign(sPara);
        //签名结果与签名方式加入请求提交参数组中
        sPara.put("sign", mysign);
        sPara.put("sign_type", AlipayConfig.sign_type);
        return sPara;
    }

    private Map<String, String> buildRequestParaTemp() {
        Map<String, String> sParaTemp = new HashMap<>();
        sParaTemp.put("service", "refund_fastpay_by_platform_pwd");
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("seller_email", AlipayConfig.seller_email);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        sParaTemp.put("notify_url", AlipayConfig.TIGER_GATEWAY + "/payment/refund/" + getRefundId() + "/notify");
        sParaTemp.put("refund_date", AlipayDateUtil.getDateFormatter());
        sParaTemp.put("batch_no", getBatchNo() + "");
        sParaTemp.put("batch_num", getBatchNum() + "");
        sParaTemp.put("detail_data", getDetailData());
        return sParaTemp;
    }

}
