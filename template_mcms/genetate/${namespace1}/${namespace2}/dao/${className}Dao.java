<#assign className = table.className>   
<#assign classNameFirstLower = className?uncap_first>
<#include "/java_copyright1.include">
* FileName: ${className}Dao.java
<#include "/java_copyright2.include">

package ${basepackage}.${namespace1}.<#if namespace2??>${namespace2}.</#if>dao;

<#include "/java_imports.include">
import com.suning.mcms.dao.Page;
import java.util.Map;



public interface ${className}Dao {

     /**
     * 
     * 保存对象功能: <br>
     * 支持实体对象的更新
     * 
     * @param ${className}Entity 实体
     * @see 
     * @since ${version}
     */
	long save(${className}Entity  ${classNameFirstLower});

    /**
     * 
     * 功能描述: 修改${className}Entity对象<br>
     * 支持对象的更新〉
     * 
     * @param ${className}Entity 对象
     * @see
     * @since ${version}
     */
    Boolean update(${className}Entity  ${classNameFirstLower});

    /**
     * 功能描述: 根据主键获取实体对象<br>
     * 
     * @param 主键
     * @return 对象实体
     * @see
     * @since ${version}
     */
    ${className}Entity getBy<#list table.compositeIdColumns as column>${column.columnName}<#if column_has_next>And</#if></#list>(<#list table.compositeIdColumns as column>${column.javaType}  ${column.columnNameFirstLower}<#if column_has_next>,</#if></#list>);

    /**
     * 功能描述:根据主键进行物理删除 <br>
     * 〈功能详细描述〉
     * 
     * @param主键
     * @see
     * @since ${version}
     */
    Boolean deleteBy<#list table.compositeIdColumns as column>${column.columnName}<#if column_has_next>And</#if></#list>(<#list table.compositeIdColumns as column>${column.javaType}  ${column.columnNameFirstLower}<#if column_has_next>,</#if></#list>);

    /**
     * 功能描述:分页 ${className}Entity查询 <br>
     * 
     * @param conditions 查询条件Map
     * @param page 分页对象
     * @return 菜单实体list
     * @see 
     * @since ${version}
     */
    List<${className}Entity> queryPageByCondition(Map<String, Object> cond, Page page);

}
