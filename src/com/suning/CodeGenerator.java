package com.suning;

import cn.org.rapid_framework.generator.GeneratorFacade;

import com.suning.create.CreateDto;

public class CodeGenerator {

	public static void main(String[] args) throws Exception {

		// Map<String, String> map = new HashMap<String, String>();
		// Properties prop = new Properties();
		// InputStream in =
		// CodeGenerator.class.getResourceAsStream("config.properties");
		// prop.load(in);
		//
		// Set<Object> set = prop.keySet();
		// for(Object o : set){
		// map.put(o.toString(), prop.getProperty(o.toString()).trim());
		// }

		/**
		 * 按照表生成
		 */
		// LayoutTable layout = new LayoutTable(map);
		// layout.setVisible(true);

		/**
		 * 按照SQL语句生成
		 */
		// LayoutSql layout= new LayoutSql(map);
		// layout.setVisible(true);

		GeneratorFacade g = new GeneratorFacade();
		g.deleteOutRootDir();

		/**
		 * 指定表生成代码
		 */

		 //g.generateByTable("TM_LOAN_FMS","template_eca");

		/**
		 * 根据sql查询生成代码
		 */
		// Sql sql = new
		// SqlFactory().parseSql("select * from TM_RECEIPT_CONTRACT_COMMON as a left join TM_RECEIPT_CONTRACT_ITEM as b on a.contract_no=b.contract_no left join TM_SETTLEMENT_LIST as c on a.contract_no=c.contract_no");
		// sql.setTableSqlName("CommCredenceInfo"); //所定义的结果VO名
		// sql.setMultiplicity("many"); //many or one,用于控制查询结果是one,many
		// sql.setOperation("findByCommCredenceInfo"); //
		// sql.setRemarks("根据用户查询");
		// //g.generateBySql(sql, "template_to_select_eca");
		// g.generateBySql(sql, "template_to_select_mcms");
		//

		
		//根据word生成dto	
		CreateDto c = new CreateDto();
		c.CreateDtos(g, "D://接口文档", "template_dto");
		

	

		/**
		 * 生成所有的表的代码
		 */
		// g.generateByAllTable("template_to_update");

		/**
		 * 根据类生成代码
		 */
		// g.generateByClass(Blog.class,"template_clazz");

		/**
		 * 根据表名删除对应代码
		 */
		// g.deleteByTable("LOGIN_LOG", "template_to_update");

	}

}
