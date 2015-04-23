<#assign className = table.className>   
<#assign classNameLowerCase = className?lower_case>
<#include "/java_copyright1.include">
* FileName: ${className}Entity.java
<#include "/java_copyright2.include">

package ${basepackage}.${namespace1}.<#if namespace2??>${namespace2}.</#if>dmo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

/**
 * ${table.tableAlias}<br> 
 *
 * @author ${author}
 * @see 
 * @since ${version}
 */
@Entity(name = "${table.sqlName}")
public class ${className}Entity implements Serializable{

    private static final long serialVersionUID =0L;
    
    <#list table.columns as column>
    /**
      *${column.columnAlias}
      */
    private  ${column.javaType}   ${column.columnNameFirstLower};
    
    </#list>
        
    <#list table.columns as column>
    /**
     * @param  ${column.columnNameFirstLower} the  ${column.columnNameFirstLower} to set
     */
    public void  set${column.columnName}(${column.javaType}  ${column.columnNameFirstLower}){
         this.${column.columnNameFirstLower} = ${column.columnNameFirstLower};    
    }    
    </#list>    
    
    <#list table.compositeIdColumns as column>
    
    /**
     * @return the ${column.columnNameFirstLower}
     */
     @Id
     <#if sequenceName??>
     @SequenceGenerator(name = "${sequenceName}", sequenceName = "${sequenceName}", catalog = "${databaseType}")
     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "${sequenceName}")
     </#if>
     @Column(name = "${column.columnName}", table = "${table.sqlName}")
    public ${column.javaType} get${column.columnName}(){
         return   ${column.columnNameFirstLower};
    }
    
    </#list>    
    
    <#list table.notPkColumns as column>    
    /**
     * @return the ${column.columnNameFirstLower}
     */
     @Column(name = "${column.sqlName}")
    public ${column.javaType} get${column.columnName}(){
         return   ${column.columnNameFirstLower};
    }
    </#list> 
}
