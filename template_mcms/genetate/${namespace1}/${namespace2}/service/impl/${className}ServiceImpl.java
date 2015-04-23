<#assign className = table.className>   
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
import com.suning.intf.framework.service.result.GenericResult;
import com.suning.intf.framework.service.result.Result;

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
     * @see ${basepackage}.${classNameFirstLower}.service.${className}Service#getBy<#list table.compositeIdColumns as column>${column.columnName}<#if column_has_next>And</#if></#list>(<#list table.compositeIdColumns as column>${column.javaType}  ${column.columnNameFirstLower}<#if column_has_next> , </#if></#list>)
     */
    @Override
    public GenericResult<${className}Entity>  getBy<#list table.compositeIdColumns as column>${column.columnName}<#if column_has_next>And</#if></#list>(<#list table.compositeIdColumns as column>${column.javaType}  ${column.columnNameFirstLower}<#if column_has_next> , </#if></#list>){
        GenericResult<${className}Entity> result = new GenericResult<${className}Entity>();
        ${className}Entity entity = ${classNameFirstLower}Dao.getBy<#list table.compositeIdColumns as column>${column.columnName}<#if column_has_next>And</#if></#list>(<#list table.compositeIdColumns as column> ${column.columnNameFirstLower}<#if column_has_next> , </#if></#list>);
        result.setObject(entity);
        return result;
    }

    /*
     * (non-Javadoc)
     * @see  ${basepackage}.${classNameFirstLower}.service.${className}Service#saveOrUpdate(${className}Entity ${classNameLowerCase}) 
     */
    @Override
    public Result saveOrUpdate(${className}Entity ${classNameFirstLower}) {
        Result result = new Result();
        if (<#list table.compositeIdColumns as column>null != ${classNameFirstLower}.get${column.columnName}()   &&  <#if column.javaType =="long"  || column.javaType =="int">0 != ${classNameFirstLower}.get${column.columnName}()  <#else> 0 != ${classNameFirstLower}.get${column.columnName}() </#if> <#if column_has_next> &&</#if></#list>) {
            ${classNameFirstLower}Dao.update(${classNameFirstLower});
        } else {
            ${classNameFirstLower}Dao.save(${classNameFirstLower});
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * @see  ${basepackage}.${classNameFirstLower}.service.${className}Service#deleteBy<#list table.compositeIdColumns as column>${column.columnName}<#if column_has_next>And</#if></#list>(<#list table.compositeIdColumns as column>${column.javaType}  ${column.columnNameFirstLower}<#if column_has_next> , </#if></#list>)
     */
    @Override
    public Result deleteBy<#list table.compositeIdColumns as column>${column.columnName}<#if column_has_next>And</#if></#list>(<#list table.compositeIdColumns as column>${column.javaType}  ${column.columnNameFirstLower}<#if column_has_next> , </#if></#list>){
        Result result = new Result();
        ${classNameFirstLower}Dao.deleteBy<#list table.compositeIdColumns as column>${column.columnName}<#if column_has_next>And</#if></#list>(<#list table.compositeIdColumns as column> ${column.columnNameFirstLower}<#if column_has_next> , </#if></#list>);
        return result;
    }

    /*
     * (non-Javadoc)
     * @see ${basepackage}.${classNameFirstLower}.service.${className}Service .${className}Service# queryBy${className}Conditions(${className}Condition ${classNameLowerCase}Conditions, Page page)
     */
    @SuppressWarnings("unchecked")
    @Override
    public GenericResult<List<${className}Entity>> queryBy${className}Conditions(${className}Condition conditions, Page page) {
        GenericResult<List<${className}Entity>> result = new GenericResult<List<${className}Entity>>();
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
        List<${className}Entity> list = ${classNameFirstLower}Dao.queryPageByCondition(cond, page);
        result.setObject(list);
        return result;
    }

}
