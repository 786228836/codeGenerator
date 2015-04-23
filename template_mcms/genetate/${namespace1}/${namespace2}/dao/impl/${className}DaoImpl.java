<#assign className = table.className>   
<#assign classNameFirstLower = className?uncap_first>
<#include "/java_copyright1.include">
* FileName: ${className}DaoImpl.java
<#include "/java_copyright2.include">

package ${basepackage}.${namespace1}.<#if namespace2??>${namespace2}.</#if>dao.impl;

<#include "/java_imports.include">
import ${basepackage}.dao.Dao;
import ${basepackage}.${namespace1}.<#if namespace2??>${namespace2}.</#if>dao.${className}Dao;

import com.suning.mcms.system.commons.dao.impl.PaginationImpl;
import com.suning.mcms.dao.Page;
import java.util.Map;


/**
 * 实体dao层实现<br>
 * 〈功能详细描述〉
 * 
 * @author ${author}
 * @see 
 * @since ${version}
 */
@Dao("${classNameFirstLower}Dao")
public class ${className}DaoImpl extends PaginationImpl implements ${className}Dao {
    
    /**
     * 命名空间
     */
    private static final String NAMESPACE="${classNameLowerCase}.";
 
    /*
     * (non-Javadoc)
     * @see ${basepackage}.${classNameLowerCase}.dao.${className}Dao#save(${basepackage}.dmo.${className}Entity)
     */
    @Override
    public long save(${className}Entity ${classNameLowerCase}) {
    	return dalClient.persist(${classNameLowerCase}).longValue();
    }

    /*
     * (non-Javadoc)
     * @see ${basepackage}.${classNameLowerCase}.dao.${className}Dao#update(${basepackage}.dmo.${className}Entity)
     */
    @Override
    public Boolean update(${className}Entity ${classNameLowerCase}) {
    	return super.dalClient.merge(${classNameLowerCase})>0?true:false;
    }

    /*
     * (non-Javadoc)
     * @see ${basepackage}.${classNameLowerCase}.dao.${className}Dao#getBy<#list table.compositeIdColumns as column>${column.columnName}<#if column_has_next>And</#if></#list>(<#list table.compositeIdColumns as column>${column.columnNameFirstLower}<#if column_has_next>,</#if></#list>)
     */
    @Override
    public ${className}Entity  getBy<#list table.compositeIdColumns as column>${column.columnName}<#if column_has_next>And</#if></#list>(<#list table.compositeIdColumns as column>${column.javaType}  ${column.columnNameFirstLower}<#if column_has_next> , </#if></#list>) {
        ${className}Entity  ${classNameLowerCase} = new  ${className}Entity();
        <#list table.compositeIdColumns as column>
        ${classNameLowerCase}.set${column.columnName}(${column.columnNameFirstLower});
        </#list>
        
        return dalClient.find(${className}Entity.class, ${classNameLowerCase});
    }

    /*
     * (non-Javadoc)
     * @see ${basepackage}.${classNameLowerCase}.dao.${className}Dao#deleteBy<#list table.compositeIdColumns as column>${column.columnName}<#if column_has_next>And</#if></#list>(<#list table.compositeIdColumns as column>${column.columnNameFirstLower}<#if column_has_next>,</#if></#list>)
     */
    @Override
    public Boolean deleteBy<#list table.compositeIdColumns as column>${column.columnName}<#if column_has_next>And</#if></#list>(<#list table.compositeIdColumns as column>${column.javaType}  ${column.columnNameFirstLower}<#if column_has_next> , </#if></#list>) {
        ${className}Entity  ${classNameLowerCase} = new ${className}Entity();
        <#list table.compositeIdColumns as column>
        ${classNameLowerCase}.set${column.columnName}(${column.columnNameFirstLower});
        </#list>
        
        return dalClient.remove(${classNameLowerCase})>0?true:false;
    }

    /*
     * (non-Javadoc)
     * @see  ${basepackage}.${classNameLowerCase}.dao.${className}Dao#queryUserByUserConditions(java.util.Map, ${basepackage}.dao.Page)
     */
    public List< ${className}Entity> queryPageByCondition(Map<String, Object> cond, Page page) {
        List< ${className}Entity> list = super.queryByPage(NAMESPACE+"queryPageByCondition", cond, page,  ${className}Entity.class);
        return list;
    }
}
