/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 9:47 AM yiliang.gyl Exp $
 */
public class SerialNumberUtil {

    private static final String SERIAL_NUMBER = "XXXXXXXX"; // 流水号格式
    private static SerialNumberUtil serialNumberUtil = null;

    private SerialNumberUtil() {
    }

    /**
     * 取得 SerialNumberUtil 的单例实现
     *
     * @return
     */
    public static SerialNumberUtil getInstance() {
        if (serialNumberUtil == null) {
            synchronized (SerialNumberUtil.class) {
                if (serialNumberUtil == null) {
                    serialNumberUtil = new SerialNumberUtil();
                }
            }
        }
        return serialNumberUtil;
    }

    /**
     * 生成下一个编号
     */
    public synchronized String generaterNextNumber(String sno) {
        String id = null;
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        if (sno == null) {
            id = formatter.format(date) + "0001";
        } else {
            int count = SERIAL_NUMBER.length();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < count; i++) {
                sb.append("0");
            }
            DecimalFormat df = new DecimalFormat("00000000");
            id = formatter.format(date)
                    + df.format(1 + Integer.parseInt(sno.substring(8, 16)));
        }
        return id;
    }


}
