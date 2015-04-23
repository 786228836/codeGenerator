<#assign className = sql.tableClassName>
<#assign classNameFirstLower = className?uncap_first>
<#include "/java_copyright1.include">
* FileName: ${className}DaoImpl.java
<#include "/java_copyright2.include">

package ${basepackage}.${namespace1}.<#if namespace2??>${namespace2}.</#if>dao.impl;

<#include "/java_imports.include">
import ${basepackage}.dao.Dao;
import ${basepackage}.${namespace1}.<#if namespace2??>${namespace2}.</#if>dao.${className}Dao;

import com.suning.eca.system.commons.dao.impl.PaginationImpl;
import com.suning.eca.dao.Page;
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
     * @see  ${basepackage}.${classNameLowerCase}.dao.${className}Dao#queryUserByUserConditions(java.util.Map, ${basepackage}.dao.Page)
     */
    public List< ${className}VO> queryPageByCondition(Map<String, Object> cond, Page page) {
        List< ${className}VO> list = super.queryByPage(NAMESPACE+"queryPageByCondition", cond, page,  ${className}VO.class);
        return list;
    }
}
