/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.util.component.payment.Base;

/**
 * @author mi.li
 * @version v 0.1 15/10/27 下午9:04 mi.li Exp $
 */
public abstract class Payment {
    private Long orderId;

    private Double totalFee;

    private String subject;

    public abstract void pay();

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Double totalFee) {
        this.totalFee = totalFee;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
