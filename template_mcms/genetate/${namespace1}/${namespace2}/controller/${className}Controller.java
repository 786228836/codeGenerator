<#assign className = table.className>   
<#assign classNameFirstLower = className?uncap_first>
<#include "/java_copyright1.include">
* FileName: ${className}Controller.java
<#include "/java_copyright2.include">
 
package ${basepackage}.web.${namespace1}<#if namespace2??>.${namespace2}</#if>;

<#include "/java_imports.include">

import com.suning.eca.dao.Page;
import com.suning.eca.framework.service.result.GenericResult;
import com.suning.eca.framework.service.result.Result;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ${basepackage}.${namespace1}.<#if namespace2??>${namespace2}.</#if>dto.${className}Condition;
import ${basepackage}.${namespace1}.<#if namespace2??>${namespace2}.</#if>service.${className}Service;


/**
 *  ${className} controller 层服务逻辑<br>
 * 〈功能详细描述〉
 * 
 * @author ${author}
 * @see 
 * @since  ${version}
 */
@Controller

<#if namespace2??>
@RequestMapping("/${namespace1}/${namespace2}/")
<#else>
@RequestMapping("/${namespace1}/")
</#if>
public class ${className}Controller {

    
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 查询页面
     */
    <#if namespace2??>
    private static final String INPUT = "${namespace1}/${namespace2}/${classNameFirstLower}_input";
    <#else>
    private static final String INPUT = "${namespace1}/${classNameFirstLower}_input";
    </#if>
    /**
     * 添加页面
     */
    <#if namespace2??>
    private static final String MAIN = "${namespace1}/${namespace2}/${classNameFirstLower}_main";
    <#else>
    private static final String MAIN = "${namespace1}/${classNameFirstLower}_main";
    </#if>
    /**
     * service 服务
     */
    @Autowired
    private ${className}Service ${classNameFirstLower}Service;
    


    /**
     * 
     * 功能描述: 进入添加或者修改页面<br>
     * 〈功能详细描述〉
     * 
     * @return
     * @see 
     * @since ${version}
     */
    @RequestMapping("input.do")
    public ModelAndView input(<#list table.compositeIdColumns as column>${column.javaType}  ${column.columnNameFirstLower}<#if column_has_next> , </#if></#list>) {
        ModelAndView mv = new ModelAndView();
        if (<#list table.compositeIdColumns as column>null == ${column.columnNameFirstLower}   ||  <#if column.javaType =="long"  || column.javaType =="int">0 == ${column.columnNameFirstLower}  <#else>0 == ${column.columnNameFirstLower} </#if> <#if column_has_next> || </#if></#list>) {
               mv.setViewName(INPUT);
            GenericResult<${className}Entity> result = ${classNameFirstLower}Service.getBy<#list table.compositeIdColumns as column>${column.columnName}<#if column_has_next>And</#if></#list>(<#list table.compositeIdColumns as column>${column.columnNameFirstLower}<#if column_has_next> , </#if></#list>);
            if (result.isSuccess()) {
                mv.addObject("${className}", result.getObject());
            }
        } else {
            mv.setViewName(INPUT);
        }
        return mv;
    }

    /**
     * 
     * 功能描述: 保存或者修改提交服务<br>
     * 〈功能详细描述〉
     * 
     * @param ${className} 实体
     * @param errors validation 校验结果
     * @return
     * @see 
     * @since ${version}
     */
    @RequestMapping("submit.do")
    public ModelAndView submit(@Valid @ModelAttribute("${className}") ${className}Entity ${classNameFirstLower}, Errors errors) {
        ModelAndView mv = new ModelAndView();
        // 主键
        <#list table.compositeIdColumns as column>
        ${column.javaType}  ${column.columnNameFirstLower}  =  ${classNameFirstLower}.get${column.columnName}();
        </#list>
        
        // 后台entity 校验结果
        if (errors.hasErrors()) {
            mv.addObject("flag", false);
            mv.addObject("message", "请检查你的输入项，输入有误！");
            mv.setViewName(INPUT);
            return mv;
        }
        // 进行修改或新添
        Result result = ${classNameFirstLower}Service.saveOrUpdate(${classNameFirstLower});

        if (result.isSuccess()) {
            mv.addObject("flag", true);
            if (<#list table.compositeIdColumns as column>null == ${column.columnNameFirstLower}   ||  <#if column.javaType =="long"  || column.javaType =="int">0 == ${column.columnNameFirstLower}  <#else>0 == ${column.columnNameFirstLower} </#if> <#if column_has_next> || </#if></#list>) {
                mv.addObject("message", "编辑成功");
                logger.debug("编辑成功：${className}");
            } else {
                mv.addObject("message", "添加成功");
                logger.debug("添加成功：${className}");
            }
        }
        mv.setViewName(INPUT);
        return mv;
    }

    /**
     * 
     * 功能描述:进入维护页面 <br>
     * 〈功能详细描述〉
     *
     * @param page 分页对象
     * @return
     * @see 
     * @since ${version}
     */
    @RequestMapping("main.do")
    public ModelAndView main(Page page) {
        ModelAndView mv = new ModelAndView();
        if (null == page) {
            page = new Page();
        }
        mv.addObject("limit", page);
        mv.setViewName(MAIN);
        return mv;
    }

    /**
     * 
     * 功能描述: 根据条件查询实体信息<br>
     * 〈功能详细描述〉
     *
     * @param custMainConditions
     * @param page
     * @return
     * @see 
     * @since ${version}
     */
    @RequestMapping("query.do")
    public ModelAndView query(${className}Condition ${className}Conditions, Page page) {
        ModelAndView mv = new ModelAndView();
        if (null == page) {
            page = new Page();
        }
        // 分页查询
        GenericResult<List<${className}Entity>> result =  ${classNameFirstLower}Service
                .queryBy${className}Conditions(${className}Conditions, page);
        if (result.isSuccess()) {
            mv.addObject("list", result.getObject());
        }
        mv.addObject("limit", page);
        mv.setViewName(MAIN);
        return mv;
    }

    /**
     * 
     * 功能描述: 删除操作<br>
     * 〈功能详细描述〉
     * 
     * @return
     * @see 
     * @since ${version}
     */
    @RequestMapping("remove.do")
    public ModelAndView remove(<#list table.compositeIdColumns as column>${column.javaType}  ${column.columnNameFirstLower}<#if column_has_next> , </#if></#list>) {
        ModelAndView mv = new ModelAndView();
        // 主键为空则返回主页面
        if (<#list table.compositeIdColumns as column>null == ${column.columnNameFirstLower}   ||  <#if column.javaType =="long"  || column.javaType =="int">0 == ${column.columnNameFirstLower}  <#else>0 == ${column.columnNameFirstLower} </#if> <#if column_has_next> || </#if></#list>) {
            mv.setViewName(MAIN);
            return mv;
        }
        ${classNameFirstLower}Service.deleteBy<#list table.compositeIdColumns as column>${column.columnName}<#if column_has_next>And</#if></#list>(<#list table.compositeIdColumns as column>${column.columnNameFirstLower}<#if column_has_next>,</#if></#list>);
        logger.debug("删除${className}");
        return this.query(null, null);
    } 
    
}
