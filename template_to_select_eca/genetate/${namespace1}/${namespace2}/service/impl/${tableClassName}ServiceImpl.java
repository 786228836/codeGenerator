<#assign className = sql.tableClassName>
<#assign classNameFirstLower = className?uncap_first>

<#include "/java_copyright1.include">
* FileName: ${className}Service.java
<#include "/java_copyright2.include">

package ${basepackage}.${namespace1}.<#if namespace2??>${namespace2}.</#if>service.impl;

<#include "/java_imports.include">
import ${basepackage}.${namespace1}.<#if namespace2??>${namespace2}.</#if>dao.${className}Dao;
import ${basepackage}.${namespace1}.<#if namespace2??>${namespace2}.</#if>dto.${className}Condition;
import ${basepackage}.${namespace1}.<#if namespace2??>${namespace2}.</#if>service.${className}Service;
import ${basepackage}.dao.Page;
import ${basepackage}.framework.service.result.GenericResult;
import ${basepackage}.framework.service.result.Result;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.HashMap;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("${classNameFirstLower}Service")
public class ${className}ServiceImpl  implements ${className}Service {
     /**
      * dao层服务
      */
    @Autowired
    private ${className}Dao ${classNameFirstLower}Dao;

    /*
     * (non-Javadoc)
     * @see ${basepackage}.${classNameFirstLower}.service.${className}Service .${className}Service# queryBy${className}Conditions(${className}Condition ${classNameFirstLower}Conditions, Page page)
     */
    @SuppressWarnings("unchecked")
    @Override
    public GenericResult<List<${className}VO>> queryBy${className}Conditions(${className}Condition conditions, Page page) {
        GenericResult<List<${className}VO>> result = new GenericResult<List<${className}VO>>();
        Map<String, Object> cond = new HashMap<String, Object>();
        if (null != conditions) {
            try {
                cond = BeanUtils.describe(conditions);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        List<${className}VO> list = ${classNameFirstLower}Dao.queryPageByCondition(cond, page);
        result.setObject(list);
        return result;
    }

}
