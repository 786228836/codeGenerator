<#assign className = table.className>   
<#assign classNameLowerCase = className?lower_case> 
${r"<#include '/reference.ftl'/>"}
${r"<#include '/pagination.ftl'/>"}
${r"<#include '/commonTag.ftl'/>"}
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>
<div class="imng-hd">
    <span class="t">${table.tableAlias}</span>
    <a class="r J-drop" href="javascript:;"><span class="bg">查询条件</span><span class="arrow-down"></span></a>
</div>
<div class="mybox-hd J-dropBd hide">
	<form action="${r'${ctx}'}/${namespace1}/<#if namespace2??>${namespace2}/</#if>query.do" method="post" id="query" name="query">
    <table class="search">
        <tr>
        <#list table.compositeIdColumns  as column>    
        <td class="t" width="75">${column.columnAlias}：</td>    
        <td width="220"><input id=" ${column.columnNameFirstLower}" type="text"/></td>
        </#list>
        </tr>
        <tr>
             <td><a title="查询" class="btn_u ml30" href="###"  onclick="javascript:$('#query').submit();">查询</a></td>
            <td><a title="重置" id="resetBtn" class="btn_u ml30 " href="###" >重置</a></td>
        </tr>
    </table>
    </form>
</div>
<div class="mybox-bd">
    <div class="tc mt30 mb30"><a href="#" class="btn btn-sub"><i class="yahei">添加用户</i></a></div>
    <table class="sl-bd">
        <tr>
        <th width="70"><label><input type="checkbox" class="ckbox all" />全选</label></th>   
        <th width="70">序号</th>
        <#list table.columns as column>
        <th width="150">${column.columnAlias}</th>    
        </#list>
        <th width="150">操作</th> 
        </tr>
        ${r"<#if list ??>"}
        <tbody class="interlaceBg">
            ${r"<#list list as item>"}
                <tr>
                    <td><input type="checkbox" class="ckbox item" /></td>
                    <td>${r"${limit.rowStart+item_index+1}"}</td>
                        <#list table.columns as column>
                        <td>${r"${item."}${column.columnNameFirstLower}${r"}"}</td>   
                        </#list>
                    <td><a href="javascript:;" title='编辑' class='l-edit-btn'></a><span class="pipe">|</span><a title='删除' class='l-delete-btn'></a></td> 
                </tr> 
            ${r"</#list>"}
        </tbody>
        ${r"</#if>"}
    </table>
    ${r"<#if limit??>"}
    <form action="${r'${ctx}'}/${namespace1}/<#if namespace2??>${namespace2}/</#if>query.do" method="post" id="gridDemo">
        </@pager pagination=limit formId="gridDemo"/>
    </form>
    ${r"</#if>"}
</div>
</body> 
</html> 