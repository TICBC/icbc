/**
 * Gambition Inc.
 * All Right Reserved.
 */
package tiger.core.service.materials;

import tiger.common.dal.persistence.materials.Materials;
import tiger.core.domain.materials.MaterialsDomain;

import java.util.List;

/**
 * @Author: [mondooo.cgq]
 * @version: [V 0.1.1]
 * @CreateDate: [2016/12/8 06:54]
 */
public interface MaterialsService  {

    /**
     * 获取所有的物料条目
     *
     * @return
     */
    List<MaterialsDomain> getAll();

    /**
     * 插入一条物料信息
     *
     * @param materials
     * @return
     */
    Boolean insert(Materials materials);

    /**
     * 更新一条物料信息
     *
     * @param materials
     * @return
     */
    Boolean update(Materials materials);

    /**
     * 根据条件获取物料条目
     *
     * @param column
     * @param value
     * @return
     */
    List<MaterialsDomain> getSome(String column, String value);

}
