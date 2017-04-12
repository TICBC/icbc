package tiger.core.domain.TransactionInfo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Jian on 2017/1/10.
 */
public class TransactionInfoCountDomain {

    /**
     * 最后一条记录的ID
     * @mbggenerated
     */
    private Integer lastId;

    /**
     * 总交易额
     * @mbggenerated
     */
    private BigDecimal txAmtCount;

    /**
     * 设备拦截总数
     * @mbggenerated
     */
    private Integer equSignCount;

    /**
     * 行为拦截总数
     * @mbggenerated
     */
    private Integer actSignCount;

    /**
     * 信任拦截总数
     * @mbggenerated
     */
    private Integer truSignCount;


    public void setLastId(Integer lastId) {
        this.lastId = lastId;
    }

    public void setTxAmtCount(BigDecimal txAmtCount) {
        this.txAmtCount = txAmtCount;
    }

    public void setEquSignCount(Integer equSignCount) {
        this.equSignCount = equSignCount;
    }

    public void setActSignCount(Integer actSignCount) {
        this.actSignCount = actSignCount;
    }

    public void setTruSignCount(Integer truSignCount) {
        this.truSignCount = truSignCount;
    }

    public Integer getLastId() {
        return lastId;
    }

    public BigDecimal getTxAmtCount() {
        return txAmtCount;
    }

    public Integer getEquSignCount() {
        return equSignCount;
    }

    public Integer getActSignCount() {
        return actSignCount;
    }

    public Integer getTruSignCount() {
        return truSignCount;
    }


}
