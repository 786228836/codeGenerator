<#assign className = sql.tableClassName>
<#assign classNameFirstLower = className?uncap_first>
<#include "/java_copyright1.include">
* FileName: ${className}Condition.java
<#include "/java_copyright2.include">

package ${basepackage}.${namespace1}.<#if namespace2??>${namespace2}.</#if>dto;

import java.util.Date;


/**
 * ${sql.remarks}条件查询类<br> 
 *
 * @author ${author}
 * @see 
 * @since ${version}
 */
public class ${className}Condition {


    <#list sql.columns as column>
	/**
	  *${column.columnAlias}
	  */
	private  ${column.javaType}  ${column.columnNameFirstLower};
	
	
	</#list>
		
	<#list sql.columns as column>
	/**
      * @param  ${column.columnNameFirstLower} the ${column.columnNameFirstLower} to set
      */
	public void  set${column.columnName}(${column.javaType}  ${column.columnNameFirstLower}){
		 this.${column.columnNameFirstLower} = ${column.columnNameFirstLower};	
	}
	
	/**
      * @return the  ${column.columnNameFirstLower}
      */
	public ${column.javaType} get${column.columnName}(){
		 return   ${column.columnNameFirstLower};
	}

	</#list> 
	
}
