<#assign className = sql.tableClassName>
<#assign classNameFirstLower = className?uncap_first>
<#include "/java_copyright1.include">
* FileName: ${className}Dao.java
<#include "/java_copyright2.include">

package ${basepackage}.${namespace1}.<#if namespace2??>${namespace2}.</#if>dao;

<#include "/java_imports.include">
import com.suning.eca.dao.Page;
import java.util.Map;



public interface ${className}Dao {

     
    /**
     * 功能描述:分页 ${className}VO查询 <br>
     * 
     * @param conditions 查询条件Map
     * @param page 分页对象
     * @return 菜单实体list
     * @see 
     * @since ${version}
     */
    List<${className}VO> queryPageByCondition(Map<String, Object> cond, Page page);

}
