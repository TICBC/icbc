/**
 * Gambition Inc.
 * All Right Reserved.
 */
package tiger.biz.materials.support;

import tiger.core.basic.PageResult;
import tiger.core.domain.materials.MaterialsDomain;

import java.util.List;

/**
 * @Author: [mondooo.cgq]
 * @version: [V 0.1.1]
 * @CreateDate: [2016/12/8 07:05]
 */
public interface MaterialsManager {

    PageResult<List<MaterialsDomain>> getAll();

    Boolean insert(String [][] materials);

    Boolean update(MaterialsDomain materialsDomain);

    PageResult<List<MaterialsDomain>> getSome(String column, String value);
}
