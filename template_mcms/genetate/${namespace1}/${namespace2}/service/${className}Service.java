<#assign className = table.className>   
<#assign classNameFirstLower = className?uncap_first>
<#include "/java_copyright1.include">
* FileName: I${className}Service.java
<#include "/java_copyright2.include">

package ${basepackage}.${namespace1}.<#if namespace2??>${namespace2}.</#if>service;


<#include "/java_imports.include">
import ${basepackage}.${namespace1}.<#if namespace2??>${namespace2}.</#if>dto.${className}Condition;
import ${basepackage}.dao.Page;
import com.suning.intf.framework.service.result.GenericResult;
import com.suning.intf.framework.service.result.Result;

/**
 *  服务层接口〉<br>
 * 〈功能详细描述〉
 * 
 * @author ${author}
 * @see 
 * @since  ${version}
 */
public interface ${className}Service {
    /**
     * 
     * 功能描述: 根据主键获取实体对象<br>
     * 
     * @param 主键
     * @return 对象实体
     * @see
     * @since  ${version}
     */
    GenericResult<${className}Entity> getBy<#list table.compositeIdColumns as column>${column.columnName}<#if column_has_next>And</#if></#list>(<#list table.compositeIdColumns as column>${column.javaType}  ${column.columnNameFirstLower}<#if column_has_next> , </#if></#list>) ;

    /**
     * 
     * 功能描述: 保存或更新实体 <br>
     * 当主键存在则更新，主键不存在做保存操作
     * 
     * @param ${className}Entity 实体
     * @see 
     * @since  ${version}
     */
    Result saveOrUpdate(${className}Entity ${classNameFirstLower});

    /**
     * 
     * 功能描述: 删除${className}Entity对象 <br>
     * 〈功能详细描述〉
     * 
     * @param 主键
     * @see
     * @since  ${version}
     */
    Result deleteBy<#list table.compositeIdColumns as column>${column.columnName}<#if column_has_next>And</#if></#list>(<#list table.compositeIdColumns as column>${column.javaType}  ${column.columnNameFirstLower}<#if column_has_next> , </#if></#list>) ;

    /**
     * 
     * 功能描述: 分页查询信息<br>
     * 
     * @param ${className}Conditions 查询条件对象
     * @param page 分页对象
     * @return 实体list
     * @see 
     * @since  ${version}
     */
    GenericResult<List<${className}Entity>> queryBy${className}Conditions(${className}Condition conditions, Page page);
}
