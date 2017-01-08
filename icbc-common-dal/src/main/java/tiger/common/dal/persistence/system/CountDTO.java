/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package tiger.common.dal.persistence.system;

/**
 * 临时存储,用于存放计数统计
 * 格式为 id -> count
 *
 * @author alfred_yuan
 * @version v 0.1 20:10 alfred_yuan Exp $
 */
public class CountDTO {

    private Long id;

    private Integer count;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
