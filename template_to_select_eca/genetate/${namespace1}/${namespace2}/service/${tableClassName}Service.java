<#assign className = sql.tableClassName>
<#assign classNameFirstLower = className?uncap_first>
<#include "/java_copyright1.include">
* FileName: I${className}Service.java
<#include "/java_copyright2.include">

package ${basepackage}.${namespace1}.<#if namespace2??>${namespace2}.</#if>service;


<#include "/java_imports.include">
import ${basepackage}.${namespace1}.<#if namespace2??>${namespace2}.</#if>dto.${className}Condition;
import ${basepackage}.dao.Page;
import ${basepackage}.framework.service.result.GenericResult;
import ${basepackage}.framework.service.result.Result;

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
     * 功能描述: 分页查询信息<br>
     * 
     * @param ${className}Conditions 查询条件对象
     * @param page 分页对象
     * @return 实体list
     * @see 
     * @since  ${version}
     */
    GenericResult<List<${className}VO>> queryBy${className}Conditions(${className}Condition conditions, Page page);
}
