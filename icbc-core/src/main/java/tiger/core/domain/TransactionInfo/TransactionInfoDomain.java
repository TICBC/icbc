package tiger.core.domain.TransactionInfo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Jian on 2017/1/10.
 */
public class TransactionInfoDomain {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactioninfo.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactioninfo.Jnl_Seq_Num
     *
     * @mbggenerated
     */
    private String jnlSeqNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactioninfo.Event_Dt
     *
     * @mbggenerated
     */
    private Date eventDt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactioninfo.Chan_Idtfy_Id
     *
     * @mbggenerated
     */
    private String chanIdtfyId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactioninfo.Webbank_Tx_Cd
     *
     * @mbggenerated
     */
    private String webbankTxCd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactioninfo.Dttm
     *
     * @mbggenerated
     */
    private String dttm;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactioninfo.Cust_Num
     *
     * @mbggenerated
     */
    private String custNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactioninfo.Cust_Name
     *
     * @mbggenerated
     */
    private String custName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactioninfo.Webbank_Cust_Attn_Mode_Cd
     *
     * @mbggenerated
     */
    private String webbankCustAttnModeCd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactioninfo.Tran_In_Card_Num
     *
     * @mbggenerated
     */
    private String tranInCardNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactioninfo.Tran_In_Acct_Saving_Type_Cd
     *
     * @mbggenerated
     */
    private String tranInAcctSavingTypeCd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactioninfo.Tran_In_Acct_Num
     *
     * @mbggenerated
     */
    private String tranInAcctNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactioninfo.Tran_Out_Card_Num
     *
     * @mbggenerated
     */
    private String tranOutCardNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactioninfo.Tran_Out_Acct_Saving_Type_Cd
     *
     * @mbggenerated
     */
    private String tranOutAcctSavingTypeCd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactioninfo.Tran_Out_Acct_Num
     *
     * @mbggenerated
     */
    private String tranOutAcctNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactioninfo.Tx_Amt
     *
     * @mbggenerated
     */
    private BigDecimal txAmt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactioninfo.Comm_Fee
     *
     * @mbggenerated
     */
    private BigDecimal commFee;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactioninfo.Logon_Card_Num
     *
     * @mbggenerated
     */
    private String logonCardNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactioninfo.Host_Tx_Dt
     *
     * @mbggenerated
     */
    private Date hostTxDt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactioninfo.Tran_Out_Acct_Zone_Num
     *
     * @mbggenerated
     */
    private String tranOutAcctZoneNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactioninfo.Tran_In_Acct_Zone_Num
     *
     * @mbggenerated
     */
    private String tranInAcctZoneNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactioninfo.Curr_Cd
     *
     * @mbggenerated
     */
    private String currCd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactioninfo.Tran_Out_Acct_Open_Org_Num
     *
     * @mbggenerated
     */
    private String tranOutAcctOpenOrgNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactioninfo.Tran_In_Acct_Open_Org_Num
     *
     * @mbggenerated
     */
    private String tranInAcctOpenOrgNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactioninfo.Tx_Err_Cd
     *
     * @mbggenerated
     */
    private String txErrCd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactioninfo.Indv_Webbank_Resp_Cd
     *
     * @mbggenerated
     */
    private String indvWebbankRespCd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactioninfo.Host_Online_Resp_Cd
     *
     * @mbggenerated
     */
    private String hostOnlineRespCd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactioninfo.Zone_Num
     *
     * @mbggenerated
     */
    private String zoneNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactioninfo.Ipad_Ind
     *
     * @mbggenerated
     */
    private String ipadInd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactioninfo.Cos_Bank_Exch_Comm_Fee
     *
     * @mbggenerated
     */
    private BigDecimal cosBankExchCommFee;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactioninfo.Webbank_Cust_Level_Cd
     *
     * @mbggenerated
     */
    private String webbankCustLevelCd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactioninfo.Cust_Ip_Addr
     *
     * @mbggenerated
     */
    private String custIpAddr;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactioninfo.Cust_Mac_Addr
     *
     * @mbggenerated
     */
    private String custMacAddr;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactioninfo.Android_Sys_Pad_Ind
     *
     * @mbggenerated
     */
    private String androidSysPadInd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactioninfo.Remit_Msg_Advise_Mobile_Num
     *
     * @mbggenerated
     */
    private String remitMsgAdviseMobileNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactioninfo.Equ_Sign
     *
     * @mbggenerated
     */
    private Integer equSign;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactioninfo.Act_Sign
     *
     * @mbggenerated
     */
    private Integer actSign;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactioninfo.Tru_Sign
     *
     * @mbggenerated
     */
    private Integer truSign;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transactioninfo.Data_Sign
     *
     * @mbggenerated
     */
    private Integer dataSign;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactioninfo.id
     *
     * @return the value of transactioninfo.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactioninfo.id
     *
     * @param id the value for transactioninfo.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactioninfo.Jnl_Seq_Num
     *
     * @return the value of transactioninfo.Jnl_Seq_Num
     *
     * @mbggenerated
     */
    public String getJnlSeqNum() {
        return jnlSeqNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactioninfo.Jnl_Seq_Num
     *
     * @param jnlSeqNum the value for transactioninfo.Jnl_Seq_Num
     *
     * @mbggenerated
     */
    public void setJnlSeqNum(String jnlSeqNum) {
        this.jnlSeqNum = jnlSeqNum == null ? null : jnlSeqNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactioninfo.Event_Dt
     *
     * @return the value of transactioninfo.Event_Dt
     *
     * @mbggenerated
     */
    public Date getEventDt() {
        return eventDt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactioninfo.Event_Dt
     *
     * @param eventDt the value for transactioninfo.Event_Dt
     *
     * @mbggenerated
     */
    public void setEventDt(Date eventDt) {
        this.eventDt = eventDt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactioninfo.Chan_Idtfy_Id
     *
     * @return the value of transactioninfo.Chan_Idtfy_Id
     *
     * @mbggenerated
     */
    public String getChanIdtfyId() {
        return chanIdtfyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactioninfo.Chan_Idtfy_Id
     *
     * @param chanIdtfyId the value for transactioninfo.Chan_Idtfy_Id
     *
     * @mbggenerated
     */
    public void setChanIdtfyId(String chanIdtfyId) {
        this.chanIdtfyId = chanIdtfyId == null ? null : chanIdtfyId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactioninfo.Webbank_Tx_Cd
     *
     * @return the value of transactioninfo.Webbank_Tx_Cd
     *
     * @mbggenerated
     */
    public String getWebbankTxCd() {
        return webbankTxCd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactioninfo.Webbank_Tx_Cd
     *
     * @param webbankTxCd the value for transactioninfo.Webbank_Tx_Cd
     *
     * @mbggenerated
     */
    public void setWebbankTxCd(String webbankTxCd) {
        this.webbankTxCd = webbankTxCd == null ? null : webbankTxCd.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactioninfo.Dttm
     *
     * @return the value of transactioninfo.Dttm
     *
     * @mbggenerated
     */
    public String getDttm() {
        return dttm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactioninfo.Dttm
     *
     * @param dttm the value for transactioninfo.Dttm
     *
     * @mbggenerated
     */
    public void setDttm(String dttm) {
        this.dttm = dttm == null ? null : dttm.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactioninfo.Cust_Num
     *
     * @return the value of transactioninfo.Cust_Num
     *
     * @mbggenerated
     */
    public String getCustNum() {
        return custNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactioninfo.Cust_Num
     *
     * @param custNum the value for transactioninfo.Cust_Num
     *
     * @mbggenerated
     */
    public void setCustNum(String custNum) {
        this.custNum = custNum == null ? null : custNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactioninfo.Cust_Name
     *
     * @return the value of transactioninfo.Cust_Name
     *
     * @mbggenerated
     */
    public String getCustName() {
        return custName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactioninfo.Cust_Name
     *
     * @param custName the value for transactioninfo.Cust_Name
     *
     * @mbggenerated
     */
    public void setCustName(String custName) {
        this.custName = custName == null ? null : custName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactioninfo.Webbank_Cust_Attn_Mode_Cd
     *
     * @return the value of transactioninfo.Webbank_Cust_Attn_Mode_Cd
     *
     * @mbggenerated
     */
    public String getWebbankCustAttnModeCd() {
        return webbankCustAttnModeCd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactioninfo.Webbank_Cust_Attn_Mode_Cd
     *
     * @param webbankCustAttnModeCd the value for transactioninfo.Webbank_Cust_Attn_Mode_Cd
     *
     * @mbggenerated
     */
    public void setWebbankCustAttnModeCd(String webbankCustAttnModeCd) {
        this.webbankCustAttnModeCd = webbankCustAttnModeCd == null ? null : webbankCustAttnModeCd.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactioninfo.Tran_In_Card_Num
     *
     * @return the value of transactioninfo.Tran_In_Card_Num
     *
     * @mbggenerated
     */
    public String getTranInCardNum() {
        return tranInCardNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactioninfo.Tran_In_Card_Num
     *
     * @param tranInCardNum the value for transactioninfo.Tran_In_Card_Num
     *
     * @mbggenerated
     */
    public void setTranInCardNum(String tranInCardNum) {
        this.tranInCardNum = tranInCardNum == null ? null : tranInCardNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactioninfo.Tran_In_Acct_Saving_Type_Cd
     *
     * @return the value of transactioninfo.Tran_In_Acct_Saving_Type_Cd
     *
     * @mbggenerated
     */
    public String getTranInAcctSavingTypeCd() {
        return tranInAcctSavingTypeCd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactioninfo.Tran_In_Acct_Saving_Type_Cd
     *
     * @param tranInAcctSavingTypeCd the value for transactioninfo.Tran_In_Acct_Saving_Type_Cd
     *
     * @mbggenerated
     */
    public void setTranInAcctSavingTypeCd(String tranInAcctSavingTypeCd) {
        this.tranInAcctSavingTypeCd = tranInAcctSavingTypeCd == null ? null : tranInAcctSavingTypeCd.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactioninfo.Tran_In_Acct_Num
     *
     * @return the value of transactioninfo.Tran_In_Acct_Num
     *
     * @mbggenerated
     */
    public String getTranInAcctNum() {
        return tranInAcctNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactioninfo.Tran_In_Acct_Num
     *
     * @param tranInAcctNum the value for transactioninfo.Tran_In_Acct_Num
     *
     * @mbggenerated
     */
    public void setTranInAcctNum(String tranInAcctNum) {
        this.tranInAcctNum = tranInAcctNum == null ? null : tranInAcctNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactioninfo.Tran_Out_Card_Num
     *
     * @return the value of transactioninfo.Tran_Out_Card_Num
     *
     * @mbggenerated
     */
    public String getTranOutCardNum() {
        return tranOutCardNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactioninfo.Tran_Out_Card_Num
     *
     * @param tranOutCardNum the value for transactioninfo.Tran_Out_Card_Num
     *
     * @mbggenerated
     */
    public void setTranOutCardNum(String tranOutCardNum) {
        this.tranOutCardNum = tranOutCardNum == null ? null : tranOutCardNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactioninfo.Tran_Out_Acct_Saving_Type_Cd
     *
     * @return the value of transactioninfo.Tran_Out_Acct_Saving_Type_Cd
     *
     * @mbggenerated
     */
    public String getTranOutAcctSavingTypeCd() {
        return tranOutAcctSavingTypeCd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactioninfo.Tran_Out_Acct_Saving_Type_Cd
     *
     * @param tranOutAcctSavingTypeCd the value for transactioninfo.Tran_Out_Acct_Saving_Type_Cd
     *
     * @mbggenerated
     */
    public void setTranOutAcctSavingTypeCd(String tranOutAcctSavingTypeCd) {
        this.tranOutAcctSavingTypeCd = tranOutAcctSavingTypeCd == null ? null : tranOutAcctSavingTypeCd.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactioninfo.Tran_Out_Acct_Num
     *
     * @return the value of transactioninfo.Tran_Out_Acct_Num
     *
     * @mbggenerated
     */
    public String getTranOutAcctNum() {
        return tranOutAcctNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactioninfo.Tran_Out_Acct_Num
     *
     * @param tranOutAcctNum the value for transactioninfo.Tran_Out_Acct_Num
     *
     * @mbggenerated
     */
    public void setTranOutAcctNum(String tranOutAcctNum) {
        this.tranOutAcctNum = tranOutAcctNum == null ? null : tranOutAcctNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactioninfo.Tx_Amt
     *
     * @return the value of transactioninfo.Tx_Amt
     *
     * @mbggenerated
     */
    public BigDecimal getTxAmt() {
        return txAmt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactioninfo.Tx_Amt
     *
     * @param txAmt the value for transactioninfo.Tx_Amt
     *
     * @mbggenerated
     */
    public void setTxAmt(BigDecimal txAmt) {
        this.txAmt = txAmt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactioninfo.Comm_Fee
     *
     * @return the value of transactioninfo.Comm_Fee
     *
     * @mbggenerated
     */
    public BigDecimal getCommFee() {
        return commFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactioninfo.Comm_Fee
     *
     * @param commFee the value for transactioninfo.Comm_Fee
     *
     * @mbggenerated
     */
    public void setCommFee(BigDecimal commFee) {
        this.commFee = commFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactioninfo.Logon_Card_Num
     *
     * @return the value of transactioninfo.Logon_Card_Num
     *
     * @mbggenerated
     */
    public String getLogonCardNum() {
        return logonCardNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactioninfo.Logon_Card_Num
     *
     * @param logonCardNum the value for transactioninfo.Logon_Card_Num
     *
     * @mbggenerated
     */
    public void setLogonCardNum(String logonCardNum) {
        this.logonCardNum = logonCardNum == null ? null : logonCardNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactioninfo.Host_Tx_Dt
     *
     * @return the value of transactioninfo.Host_Tx_Dt
     *
     * @mbggenerated
     */
    public Date getHostTxDt() {
        return hostTxDt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactioninfo.Host_Tx_Dt
     *
     * @param hostTxDt the value for transactioninfo.Host_Tx_Dt
     *
     * @mbggenerated
     */
    public void setHostTxDt(Date hostTxDt) {
        this.hostTxDt = hostTxDt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactioninfo.Tran_Out_Acct_Zone_Num
     *
     * @return the value of transactioninfo.Tran_Out_Acct_Zone_Num
     *
     * @mbggenerated
     */
    public String getTranOutAcctZoneNum() {
        return tranOutAcctZoneNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactioninfo.Tran_Out_Acct_Zone_Num
     *
     * @param tranOutAcctZoneNum the value for transactioninfo.Tran_Out_Acct_Zone_Num
     *
     * @mbggenerated
     */
    public void setTranOutAcctZoneNum(String tranOutAcctZoneNum) {
        this.tranOutAcctZoneNum = tranOutAcctZoneNum == null ? null : tranOutAcctZoneNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactioninfo.Tran_In_Acct_Zone_Num
     *
     * @return the value of transactioninfo.Tran_In_Acct_Zone_Num
     *
     * @mbggenerated
     */
    public String getTranInAcctZoneNum() {
        return tranInAcctZoneNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactioninfo.Tran_In_Acct_Zone_Num
     *
     * @param tranInAcctZoneNum the value for transactioninfo.Tran_In_Acct_Zone_Num
     *
     * @mbggenerated
     */
    public void setTranInAcctZoneNum(String tranInAcctZoneNum) {
        this.tranInAcctZoneNum = tranInAcctZoneNum == null ? null : tranInAcctZoneNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactioninfo.Curr_Cd
     *
     * @return the value of transactioninfo.Curr_Cd
     *
     * @mbggenerated
     */
    public String getCurrCd() {
        return currCd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactioninfo.Curr_Cd
     *
     * @param currCd the value for transactioninfo.Curr_Cd
     *
     * @mbggenerated
     */
    public void setCurrCd(String currCd) {
        this.currCd = currCd == null ? null : currCd.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactioninfo.Tran_Out_Acct_Open_Org_Num
     *
     * @return the value of transactioninfo.Tran_Out_Acct_Open_Org_Num
     *
     * @mbggenerated
     */
    public String getTranOutAcctOpenOrgNum() {
        return tranOutAcctOpenOrgNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactioninfo.Tran_Out_Acct_Open_Org_Num
     *
     * @param tranOutAcctOpenOrgNum the value for transactioninfo.Tran_Out_Acct_Open_Org_Num
     *
     * @mbggenerated
     */
    public void setTranOutAcctOpenOrgNum(String tranOutAcctOpenOrgNum) {
        this.tranOutAcctOpenOrgNum = tranOutAcctOpenOrgNum == null ? null : tranOutAcctOpenOrgNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactioninfo.Tran_In_Acct_Open_Org_Num
     *
     * @return the value of transactioninfo.Tran_In_Acct_Open_Org_Num
     *
     * @mbggenerated
     */
    public String getTranInAcctOpenOrgNum() {
        return tranInAcctOpenOrgNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactioninfo.Tran_In_Acct_Open_Org_Num
     *
     * @param tranInAcctOpenOrgNum the value for transactioninfo.Tran_In_Acct_Open_Org_Num
     *
     * @mbggenerated
     */
    public void setTranInAcctOpenOrgNum(String tranInAcctOpenOrgNum) {
        this.tranInAcctOpenOrgNum = tranInAcctOpenOrgNum == null ? null : tranInAcctOpenOrgNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactioninfo.Tx_Err_Cd
     *
     * @return the value of transactioninfo.Tx_Err_Cd
     *
     * @mbggenerated
     */
    public String getTxErrCd() {
        return txErrCd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactioninfo.Tx_Err_Cd
     *
     * @param txErrCd the value for transactioninfo.Tx_Err_Cd
     *
     * @mbggenerated
     */
    public void setTxErrCd(String txErrCd) {
        this.txErrCd = txErrCd == null ? null : txErrCd.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactioninfo.Indv_Webbank_Resp_Cd
     *
     * @return the value of transactioninfo.Indv_Webbank_Resp_Cd
     *
     * @mbggenerated
     */
    public String getIndvWebbankRespCd() {
        return indvWebbankRespCd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactioninfo.Indv_Webbank_Resp_Cd
     *
     * @param indvWebbankRespCd the value for transactioninfo.Indv_Webbank_Resp_Cd
     *
     * @mbggenerated
     */
    public void setIndvWebbankRespCd(String indvWebbankRespCd) {
        this.indvWebbankRespCd = indvWebbankRespCd == null ? null : indvWebbankRespCd.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactioninfo.Host_Online_Resp_Cd
     *
     * @return the value of transactioninfo.Host_Online_Resp_Cd
     *
     * @mbggenerated
     */
    public String getHostOnlineRespCd() {
        return hostOnlineRespCd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactioninfo.Host_Online_Resp_Cd
     *
     * @param hostOnlineRespCd the value for transactioninfo.Host_Online_Resp_Cd
     *
     * @mbggenerated
     */
    public void setHostOnlineRespCd(String hostOnlineRespCd) {
        this.hostOnlineRespCd = hostOnlineRespCd == null ? null : hostOnlineRespCd.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactioninfo.Zone_Num
     *
     * @return the value of transactioninfo.Zone_Num
     *
     * @mbggenerated
     */
    public String getZoneNum() {
        return zoneNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactioninfo.Zone_Num
     *
     * @param zoneNum the value for transactioninfo.Zone_Num
     *
     * @mbggenerated
     */
    public void setZoneNum(String zoneNum) {
        this.zoneNum = zoneNum == null ? null : zoneNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactioninfo.Ipad_Ind
     *
     * @return the value of transactioninfo.Ipad_Ind
     *
     * @mbggenerated
     */
    public String getIpadInd() {
        return ipadInd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactioninfo.Ipad_Ind
     *
     * @param ipadInd the value for transactioninfo.Ipad_Ind
     *
     * @mbggenerated
     */
    public void setIpadInd(String ipadInd) {
        this.ipadInd = ipadInd == null ? null : ipadInd.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactioninfo.Cos_Bank_Exch_Comm_Fee
     *
     * @return the value of transactioninfo.Cos_Bank_Exch_Comm_Fee
     *
     * @mbggenerated
     */
    public BigDecimal getCosBankExchCommFee() {
        return cosBankExchCommFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactioninfo.Cos_Bank_Exch_Comm_Fee
     *
     * @param cosBankExchCommFee the value for transactioninfo.Cos_Bank_Exch_Comm_Fee
     *
     * @mbggenerated
     */
    public void setCosBankExchCommFee(BigDecimal cosBankExchCommFee) {
        this.cosBankExchCommFee = cosBankExchCommFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactioninfo.Webbank_Cust_Level_Cd
     *
     * @return the value of transactioninfo.Webbank_Cust_Level_Cd
     *
     * @mbggenerated
     */
    public String getWebbankCustLevelCd() {
        return webbankCustLevelCd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactioninfo.Webbank_Cust_Level_Cd
     *
     * @param webbankCustLevelCd the value for transactioninfo.Webbank_Cust_Level_Cd
     *
     * @mbggenerated
     */
    public void setWebbankCustLevelCd(String webbankCustLevelCd) {
        this.webbankCustLevelCd = webbankCustLevelCd == null ? null : webbankCustLevelCd.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactioninfo.Cust_Ip_Addr
     *
     * @return the value of transactioninfo.Cust_Ip_Addr
     *
     * @mbggenerated
     */
    public String getCustIpAddr() {
        return custIpAddr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactioninfo.Cust_Ip_Addr
     *
     * @param custIpAddr the value for transactioninfo.Cust_Ip_Addr
     *
     * @mbggenerated
     */
    public void setCustIpAddr(String custIpAddr) {
        this.custIpAddr = custIpAddr == null ? null : custIpAddr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactioninfo.Cust_Mac_Addr
     *
     * @return the value of transactioninfo.Cust_Mac_Addr
     *
     * @mbggenerated
     */
    public String getCustMacAddr() {
        return custMacAddr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactioninfo.Cust_Mac_Addr
     *
     * @param custMacAddr the value for transactioninfo.Cust_Mac_Addr
     *
     * @mbggenerated
     */
    public void setCustMacAddr(String custMacAddr) {
        this.custMacAddr = custMacAddr == null ? null : custMacAddr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactioninfo.Android_Sys_Pad_Ind
     *
     * @return the value of transactioninfo.Android_Sys_Pad_Ind
     *
     * @mbggenerated
     */
    public String getAndroidSysPadInd() {
        return androidSysPadInd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactioninfo.Android_Sys_Pad_Ind
     *
     * @param androidSysPadInd the value for transactioninfo.Android_Sys_Pad_Ind
     *
     * @mbggenerated
     */
    public void setAndroidSysPadInd(String androidSysPadInd) {
        this.androidSysPadInd = androidSysPadInd == null ? null : androidSysPadInd.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactioninfo.Remit_Msg_Advise_Mobile_Num
     *
     * @return the value of transactioninfo.Remit_Msg_Advise_Mobile_Num
     *
     * @mbggenerated
     */
    public String getRemitMsgAdviseMobileNum() {
        return remitMsgAdviseMobileNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactioninfo.Remit_Msg_Advise_Mobile_Num
     *
     * @param remitMsgAdviseMobileNum the value for transactioninfo.Remit_Msg_Advise_Mobile_Num
     *
     * @mbggenerated
     */
    public void setRemitMsgAdviseMobileNum(String remitMsgAdviseMobileNum) {
        this.remitMsgAdviseMobileNum = remitMsgAdviseMobileNum == null ? null : remitMsgAdviseMobileNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactioninfo.Equ_Sign
     *
     * @return the value of transactioninfo.Equ_Sign
     *
     * @mbggenerated
     */
    public Integer getEquSign() {
        return equSign;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactioninfo.Equ_Sign
     *
     * @param equSign the value for transactioninfo.Equ_Sign
     *
     * @mbggenerated
     */
    public void setEquSign(Integer equSign) {
        this.equSign = equSign;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactioninfo.Act_Sign
     *
     * @return the value of transactioninfo.Act_Sign
     *
     * @mbggenerated
     */
    public Integer getActSign() {
        return actSign;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactioninfo.Act_Sign
     *
     * @param actSign the value for transactioninfo.Act_Sign
     *
     * @mbggenerated
     */
    public void setActSign(Integer actSign) {
        this.actSign = actSign;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactioninfo.Tru_Sign
     *
     * @return the value of transactioninfo.Tru_Sign
     *
     * @mbggenerated
     */
    public Integer getTruSign() {
        return truSign;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactioninfo.Tru_Sign
     *
     * @param truSign the value for transactioninfo.Tru_Sign
     *
     * @mbggenerated
     */
    public void setTruSign(Integer truSign) {
        this.truSign = truSign;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column transactioninfo.Data_Sign
     *
     * @return the value of transactioninfo.Data_Sign
     *
     * @mbggenerated
     */
    public Integer getDataSign() {
        return dataSign;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column transactioninfo.Data_Sign
     *
     * @param dataSign the value for transactioninfo.Data_Sign
     *
     * @mbggenerated
     */
    public void setDataSign(Integer dataSign) {
        this.dataSign = dataSign;
    }

}
