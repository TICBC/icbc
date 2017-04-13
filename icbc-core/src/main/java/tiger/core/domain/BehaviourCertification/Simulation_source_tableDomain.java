package tiger.core.domain.BehaviourCertification;

import tiger.core.domain.BaseDomain;

import java.math.BigDecimal;
import java.util.Date;

//<<<<<<< Updated upstream
//=======
//>>>>>>> Stashed changes

/**
 * Created by archer on 2017/1/9.
 */
public class Simulation_source_tableDomain extends BaseDomain {
    private String tranOutCardNum;
    private Date timeStamp;
    private Integer user;
    private BigDecimal txAmt;
    private String tranInAcctNum;
    private String jnlSeqNum;
    private Date eventDt;
    private Boolean lable;
    private String zoneNum;
    private Boolean predicationResult;

    public String getTranOutCardNum() {
        return tranOutCardNum;
    }

    public void setTranOutCardNum(String tranOutCardNum) {
        this.tranOutCardNum = tranOutCardNum;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public BigDecimal getTxAmt() {
        return txAmt;
    }

    public void setTxAmt(BigDecimal txAmt) {
        this.txAmt = txAmt;
    }

    public String getTranInAcctNum() {
        return tranInAcctNum;
    }

    public void setTranInAcctNum(String tranInAcctNum) {
        this.tranInAcctNum = tranInAcctNum;
    }

    public String getJnlSeqNum() {
        return jnlSeqNum;
    }

    public void setJnlSeqNum(String jnlSeqNum) {
        this.jnlSeqNum = jnlSeqNum;
    }

    public Date getEventDt() {
        return eventDt;
    }

    public void setEventDt(Date eventDt) {
        this.eventDt = eventDt;
    }

    public Boolean getLable() {
        return lable;
    }

    public void setLable(Boolean lable) {
        this.lable = lable;
    }

    public String getZoneNum() {
        return zoneNum;
    }

    public void setZoneNum(String zoneNum) {
        this.zoneNum = zoneNum;
    }

    public Boolean getPredicationResult() {
        return predicationResult;
    }

    public void setPredicationResult(Boolean predicationResult) {
        this.predicationResult = predicationResult;
    }
}
