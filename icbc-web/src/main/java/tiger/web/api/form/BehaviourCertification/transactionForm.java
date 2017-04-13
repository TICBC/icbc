package tiger.web.api.form.BehaviourCertification;

import tiger.common.util.BeanUtil;
import tiger.core.domain.BehaviourCertification.transactioninfoDomain;
import tiger.web.api.form.FormInterface;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 王硕买个大葱头 on 2017/1/13.
 */
public class transactionForm implements FormInterface {


    private Integer id;

    private String jnlSeqNum;

    private String eventDt;

    private String chanIdtfyId;

    private String webbankTxCd;

    private String dttm;

    private String custNum;

    private String custName;

    private String webbankCustAttnModeCd;

    private String tranInCardNum;

    private String tranInAcctSavingTypeCd;

    private String tranInAcctNum;

    private String tranOutCardNum;

    private String tranOutAcctSavingTypeCd;

    private String tranOutAcctNum;

    private BigDecimal txAmt;

    private BigDecimal commFee;

    private String logonCardNum;

    private Date hostTxDt;

    private String tranOutAcctZoneNum;

    private String tranInAcctZoneNum;

    private String currCd;

    private String tranOutAcctOpenOrgNum;

    private String tranInAcctOpenOrgNum;

    private String txErrCd;

    private String indvWebbankRespCd;

    private String hostOnlineRespCd;

    private String zoneNum;

    private String ipadInd;

    private BigDecimal cosBankExchCommFee;

    private String webbankCustLevelCd;

    private String custIpAddr;

    private String custMacAddr;

    private String androidSysPadInd;

    private String remitMsgAdviseMobileNum;

    private Integer equSign;

    private Integer actSign;

    private Integer truSign;

    private Integer dataSign;

    public Integer getid() {
        return id;
    }

    public String getJnlSeqNum() {
        return jnlSeqNum;
    }

    public String getEventDt() {
        return eventDt;
    }

    public String getChanIdtfyId() {
        return chanIdtfyId;
    }

    public String getWebbankTxCd() {
        return webbankTxCd;
    }

    public String getDttm() {
        return dttm;
    }

    public String getCustNum() {
        return custNum;
    }

    public String getCustName() {
        return custName;
    }

    public String getWebbankCustAttnModeCd() {
        return webbankCustAttnModeCd;
    }

    public String getTranInCardNum() {
        return tranInCardNum;
    }

    public String getTranInAcctSavingTypeCd() {
        return tranInAcctSavingTypeCd;
    }

    public String getTranInAcctNum() {
        return tranInAcctNum;
    }

    public String getTranOutCardNum() {
        return tranOutCardNum;
    }

    public String getTranOutAcctSavingTypeCd() {
        return tranOutAcctSavingTypeCd;
    }

    public String getTranOutAcctNum() {
        return tranOutAcctNum;
    }

    public BigDecimal getTxAmt() {
        return txAmt;
    }

    public BigDecimal getCommFee() {
        return commFee;
    }

    public String getLogonCardNum() {
        return logonCardNum;
    }

    public Date getHostTxDt() {
        return hostTxDt;
    }

    public String getTranOutAcctZoneNum() {
        return tranOutAcctZoneNum;
    }

    public String getTranInAcctZoneNum() {
        return tranInAcctZoneNum;
    }

    public String getCurrCd() {
        return currCd;
    }

    public String getTranOutAcctOpenOrgNum() {
        return tranOutAcctOpenOrgNum;
    }

    public String getTranInAcctOpenOrgNum() {
        return tranInAcctOpenOrgNum;
    }

    public String getTxErrCd() {
        return txErrCd;
    }

    public String getIndvWebbankRespCd() {
        return indvWebbankRespCd;
    }

    public String getHostOnlineRespCd() {
        return hostOnlineRespCd;
    }

    public String getZoneNum() {
        return zoneNum;
    }

    public String getIpadInd() {
        return ipadInd;
    }

    public BigDecimal getCosBankExchCommFee() {
        return cosBankExchCommFee;
    }

    public String getWebbankCustLevelCd() {
        return webbankCustLevelCd;
    }

    public String getCustIpAddr() {
        return custIpAddr;
    }

    public String getCustMacAddr() {
        return custMacAddr;
    }

    public String getAndroidSysPadInd() {
        return androidSysPadInd;
    }

    public String getRemitMsgAdviseMobileNum() {
        return remitMsgAdviseMobileNum;
    }

    public Integer getEquSign() {
        return equSign;
    }

    public Integer getActSign() {
        return actSign;
    }

    public Integer getTruSign() {
        return truSign;
    }

    public Integer getDataSign() {
        return dataSign;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setJnlSeqNum(String jnlSeqNum) {
        this.jnlSeqNum = jnlSeqNum;
    }

    public void setEventDt(String eventDt) {
        this.eventDt = eventDt;
    }

    public void setChanIdtfyId(String chanIdtfyId) {
        this.chanIdtfyId = chanIdtfyId;
    }

    public void setWebbankTxCd(String webbankTxCd) {
        this.webbankTxCd = webbankTxCd;
    }

    public void setDttm(String dttm) {
        this.dttm = dttm;
    }

    public void setCustNum(String custNum) {
        this.custNum = custNum;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public void setWebbankCustAttnModeCd(String webbankCustAttnModeCd) {
        this.webbankCustAttnModeCd = webbankCustAttnModeCd;
    }

    public void setTranInCardNum(String tranInCardNum) {
        this.tranInCardNum = tranInCardNum;
    }

    public void setTranInAcctSavingTypeCd(String tranInAcctSavingTypeCd) {
        this.tranInAcctSavingTypeCd = tranInAcctSavingTypeCd;
    }

    public void setTranInAcctNum(String tranInAcctNum) {
        this.tranInAcctNum = tranInAcctNum;
    }

    public void setTranOutCardNum(String tranOutCardNum) {
        this.tranOutCardNum = tranOutCardNum;
    }

    public void setTranOutAcctSavingTypeCd(String tranOutAcctSavingTypeCd) {
        this.tranOutAcctSavingTypeCd = tranOutAcctSavingTypeCd;
    }

    public void setTranOutAcctNum(String tranOutAcctNum) {
        this.tranOutAcctNum = tranOutAcctNum;
    }

    public void setTxAmt(BigDecimal txAmt) {
        this.txAmt = txAmt;
    }

    public void setCommFee(BigDecimal commFee) {
        this.commFee = commFee;
    }

    public void setLogonCardNum(String logonCardNum) {
        this.logonCardNum = logonCardNum;
    }

    public void setHostTxDt(Date hostTxDt) {
        this.hostTxDt = hostTxDt;
    }

    public void setTranOutAcctZoneNum(String tranOutAcctZoneNum) {
        this.tranOutAcctZoneNum = tranOutAcctZoneNum;
    }

    public void setTranInAcctZoneNum(String tranInAcctZoneNum) {
        this.tranInAcctZoneNum = tranInAcctZoneNum;
    }

    public void setCurrCd(String currCd) {
        this.currCd = currCd;
    }

    public void setTranOutAcctOpenOrgNum(String tranOutAcctOpenOrgNum) {
        this.tranOutAcctOpenOrgNum = tranOutAcctOpenOrgNum;
    }

    public void setTranInAcctOpenOrgNum(String tranInAcctOpenOrgNum) {
        this.tranInAcctOpenOrgNum = tranInAcctOpenOrgNum;
    }

    public void setTxErrCd(String txErrCd) {
        this.txErrCd = txErrCd;
    }

    public void setIndvWebbankRespCd(String indvWebbankRespCd) {
        this.indvWebbankRespCd = indvWebbankRespCd;
    }

    public void setHostOnlineRespCd(String hostOnlineRespCd) {
        this.hostOnlineRespCd = hostOnlineRespCd;
    }

    public void setZoneNum(String zoneNum) {
        this.zoneNum = zoneNum;
    }

    public void setIpadInd(String ipadInd) {
        this.ipadInd = ipadInd;
    }

    public void setCosBankExchCommFee(BigDecimal cosBankExchCommFee) {
        this.cosBankExchCommFee = cosBankExchCommFee;
    }

    public void setWebbankCustLevelCd(String webbankCustLevelCd) {
        this.webbankCustLevelCd = webbankCustLevelCd;
    }

    public void setCustIpAddr(String custIpAddr) {
        this.custIpAddr = custIpAddr;
    }

    public void setCustMacAddr(String custMacAddr) {
        this.custMacAddr = custMacAddr;
    }

    public void setAndroidSysPadInd(String androidSysPadInd) {
        this.androidSysPadInd = androidSysPadInd;
    }

    public void setRemitMsgAdviseMobileNum(String remitMsgAdviseMobileNum) {
        this.remitMsgAdviseMobileNum = remitMsgAdviseMobileNum;
    }

    public void setEquSign(Integer equSign) {
        this.equSign = equSign;
    }

    public void setActSign(Integer actSign) {
        this.actSign = actSign;
    }

    public void setTruSign(Integer truSign) {
        this.truSign = truSign;
    }

    public void setDataSign(Integer dataSign) {
        this.dataSign = dataSign;
    }

    @Override
        public transactioninfoDomain convert2Domain() {

        transactioninfoDomain target = new transactioninfoDomain();
            BeanUtil.copyPropertiesWithIgnores(this, target);

            return target;
        }

}
