package com.suning.create;

import java.io.IOException;

import cn.org.rapid_framework.generator.GeneratorFacade;
import cn.org.rapid_framework.generator.provider.db.sql.SqlFactory;
import cn.org.rapid_framework.generator.provider.db.sql.model.Sql;

public class Create {
	
	GeneratorFacade g = new GeneratorFacade();
	
	public Create() throws IOException{
     //删除生成器的输出目录
     g.deleteOutRootDir();
	}
	
	//根据表名生成
	public void createByTable(String tableName) throws Exception{
		g.generateByTable(tableName,"template_to_update");
	}
	
    //根据SQL生成
	public void createBySql(String selectSql,String voName,String reMark) throws Exception{
	   Sql sql = new SqlFactory().parseSql(selectSql);  
       sql.setTableSqlName(voName); //所定义的结果VO名
       sql.setMultiplicity("many");  //many or one,用于控制查询结果是one,many
       sql.setOperation("findBy"+voName); //
       sql.setRemarks(reMark);  
       g.generateBySql(sql, "template_to_select");
	}
	
	
}
