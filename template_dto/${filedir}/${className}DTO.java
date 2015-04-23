<#macro mapperE value>${"#{"}${value}}</#macro>
/*
 * Copyright (C), 2002-2013, 苏宁易购电子商务有限公司
 * FileName: ${className}DTO.java
 * Author:   ${author}
 * Date:     ${.now?string("yyyy-MM-dd a HH:mm:ss")}
 * Description: ${remark}DTO
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "${className}DTO")
public class ${className}DTO  implements Serializable{

	 /**
     * 序列号
     */
    private static final long serialVersionUID = 0L;
		<#list list as map>
		/**
		  *${map["columnAlias"]}
		  */	
		
		@XStreamAlias("${map["columnName"]}")
		private  String   ${map["columnName"]?uncap_first};  

		</#list>	
		
		<#if anotherClassName??>
		private  ${anotherClassName}DTO[]   ${anotherClassName?uncap_first}DTO; 
		</#if>
		
		<#list list as map>
		public void  set${map["columnName"]}  ( String ${map["columnName"]?uncap_first}){
			 this.${map["columnName"]?uncap_first} = ${map["columnName"]?uncap_first};	
		}	
		
		public String get${map["columnName"]}(){
			 return   ${map["columnName"]?uncap_first} ;
		}
		
		</#list>
		
		<#if anotherClassName??>
		public void  set${anotherClassName}DTO(${anotherClassName}DTO[]  ${anotherClassName?uncap_first}DTO){
			 this.${anotherClassName?uncap_first}DTO = ${anotherClassName?uncap_first}DTO;	
		}	
		
		public   ${anotherClassName}DTO[] get${anotherClassName}DTO(){
			 return   ${anotherClassName?uncap_first}DTO;
		}
		</#if>
		
	
}
