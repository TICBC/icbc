/**
 * 404 Team
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.basic;

import tiger.common.util.Paginator;

/**
 * @author HuPeng
 * @version v 0.1 2015年10月20日 下午8:17:31 HuPeng Exp $
 */
public class DataTable<T> extends BaseResult<T> {
    /**  */
    private static final long serialVersionUID = -7880508585764912700L;
    private int recordsTotal;
    private int recordsFiltered;

    public DataTable() {
        super();
    }

    public DataTable(T data, Paginator paginator) {
        super(data);
        this.recordsFiltered = paginator.getLength();
        this.recordsTotal = paginator.getItems();
    }

    /**
     * Getter method for property <tt>recordsTotal</tt>.
     *
     * @return property value of recordsTotal
     */
    public int getRecordsTotal() {
        return recordsTotal;
    }

    /**
     * Setter method for property <tt>recordsTotal</tt>.
     *
     * @param recordsTotal value to be assigned to property recordsTotal
     */
    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    /**
     * Getter method for property <tt>recordsFiltered</tt>.
     *
     * @return property value of recordsFiltered
     */
    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    /**
     * Setter method for property <tt>recordsFiltered</tt>.
     *
     * @param recordsFiltered value to be assigned to property recordsFiltered
     */
    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

}
