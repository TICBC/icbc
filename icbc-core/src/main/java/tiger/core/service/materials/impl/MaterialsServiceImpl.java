/**
 * Gambition Inc.
 * All Right Reserved.
 */
package tiger.core.service.materials.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiger.common.dal.persistence.mapper.EquipmentInfoDOMapper;
import tiger.common.dal.persistence.mapper.MaterialsMapper;
import tiger.common.dal.persistence.materials.Materials;
import tiger.common.dal.persistence.materials.MaterialsExample;
import tiger.core.domain.materials.MaterialsDomain;
import tiger.core.domain.materials.convert.MaterialsConvert;
import tiger.core.service.materials.MaterialsService;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: [mondooo.cgq]
 * @version: [V 0.1.1]
 * @CreateDate: [2016/12/8 06:59]
 */
@Service
public class MaterialsServiceImpl implements MaterialsService {

    @Autowired
    MaterialsMapper materialsMapper;

    @Override
    public List<MaterialsDomain> getAll() {
        List<Materials> DOs = materialsMapper.selectAll();
        return MaterialsConvert.convert2Domains(DOs);
    }

    @Override
    public Boolean insert(Materials materials) {
        return materialsMapper.insert(materials) > 0;
    }

    @Override
    public Boolean update(Materials materials) {
        return materialsMapper.updateByPrimaryKey(materials) > 0;
    }

    @Override
    public List<MaterialsDomain> getSome(String column, String value) {
        String [] values = mySplit(value);

        MaterialsExample example = new MaterialsExample();
        MaterialsExample.Criteria criteria = example.createCriteria();
        switch (column) {
            case "code": {
                for (String str : values) {
                    criteria.andCodeLike(addPercent(str));
                }
                break;
            }
            case "name": {
                for (String str : values) {
                    criteria.andNameLike(addPercent(str));
                }
                break;
            }
            case "description": {
                for (String str : values) {
                    System.out.println(str);
                    criteria.andDescriptionLike(addPercent(str));
                }
                break;
            }
            case "majorcategory": {
                for (String str : values) {
                    criteria.andMajorcategoryLike(addPercent(str));
                }
                break;
            }
            case "subcategory": {
                for (String str : values) {
                    criteria.andSubcategoryLike(addPercent(str));
                }
                break;
            }
            case "detailclass": {
                for (String str : values) {
                    criteria.andDetailclassLike(addPercent(str));
                }
                break;
            }
            case "company": {
                for (String str : values) {
                    criteria.andCompanyLike(addPercent(str));
                }
                break;
            }
            case "productline": {
                for (String str : values) {
                    criteria.andProductlineLike(addPercent(str));
                }
                break;
            }
            case "marketprice":
                double mvalue = Double.parseDouble(value.trim());
                criteria.andMarketpriceEqualTo(mvalue);
                break;
            case "discount":
                double dvalue = Double.parseDouble(value.trim());
                criteria.andDiscountEqualTo(dvalue);
                break;

        }
        return MaterialsConvert.convert2Domains(materialsMapper.selectByExample(example));
    }

    //~ private methods
    private static String[] mySplit(String source) {
        if (source == null) {
            return null;
        }
        source = source.trim();
        Pattern pattern = Pattern.compile("\\s+");
        Matcher matcher = pattern.matcher(source);
        String temp = matcher.replaceAll(" ");

        String[] target = temp.split(" ");
        return target;
    }
    private  static String addPercent(String source) {
        return "%" + source + "%";
    }

    //test
    public static void main(String[] args) {
        String test = " abc 123  ddd lkjhppp    ";

        String [] temp = mySplit(test);
        for (String string : temp) {
            System.out.println(string);
        }
    }

}
