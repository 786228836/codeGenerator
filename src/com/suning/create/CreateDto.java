package com.suning.create;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hwpf.extractor.WordExtractor;

import cn.org.rapid_framework.generator.GeneratorFacade;

import com.suning.util.FirstUp;

public class CreateDto {

	public void CreateDtos(GeneratorFacade g, String filepath, String template)
			throws Exception {
		String className = null;
		String anotherClassName = null;
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, String>> ls = new ArrayList<Map<String, String>>();
		Map<String, String> detaimap = null;
		String remark = null;

		File srcFile = new File(filepath);
		String[] filelist = srcFile.list();
		for (int i = 0; i < filelist.length; i++) {
			File readfile = new File(filepath + "//" + filelist[i]);
			if (!readfile.getName().startsWith("~$")) {
				FileInputStream fis = new FileInputStream(readfile);
				@SuppressWarnings("resource")
				WordExtractor wordExtractor = new WordExtractor(fis);

				String text = wordExtractor.getText();

				String regex = "\\操作简码01\\s+[a-zA-Z]+";
				Pattern p = Pattern.compile(regex);
				Matcher m = p.matcher(text);
				String[] k = null;
				while (m.find()) {
					k = m.group().split("\\s+");
					className = k[1];
					map.put("className", FirstUp.toUpperCaseFirstOne(className));
				}

				regex = "[0-9]+\\s[a-zA-Z\u4E00-\u9FA5]*\\s[a-zA-Z]+";

				p = Pattern.compile(regex);
				m = p.matcher(text);

				int index = 0;
				while (m.find()) {
					detaimap = new HashMap<String, String>();

					k = m.group().split("\\s+");
					int in = 0;
					for (int j = 0; j < k.length; j++) {
						in = Integer.parseInt(k[0]);
						if (in < index) {
							break;
						}
						index = in;

						if (j == 1) {
							detaimap.put("columnAlias", k[1]);
						}
						if (j == 2) {
							detaimap.put("columnName", FirstUp.toUpperCaseFirstOne(k[2]));
						}
					}
					if (detaimap.size() != 0) {
						ls.add(detaimap);
					}

				}

				map.put("list", ls);

				map.put("filedir", readfile.getName());
				remark = readfile.getName();

				remark = remark.substring(14);
				String[] s = remark.split("-");
				remark = s[0].trim();

				map.put("remark", remark);

				regex = "[0-9]+([.]{1}[0-9]+){0,1}+(\\s{2}[a-zA-Z\u4E00-\u9FA5]*\\s[a-zA-Z]+|\\s[a-zA-Z\u4E00-\u9FA5]*\\s{2}[a-zA-Z]+)";
				p = Pattern.compile(regex);
				m = p.matcher(text);
				while (m.find()) {
					k = m.group().split("\\s+");
					remark = k[1];
					anotherClassName = k[2];
					map.put("anotherClassName",
							FirstUp.toUpperCaseFirstOne(anotherClassName));
					break;
				}

				g.generateByMap(map, template);

				map = new HashMap<String, Object>();
				map.put("className",
						FirstUp.toUpperCaseFirstOne(anotherClassName));
				ls = new ArrayList<Map<String, String>>();
				while (m.find()) {
					detaimap = new HashMap<String, String>();

					k = m.group().split("\\s+");
					for (int l = 1; l < k.length; l++) {
						if (l == 1) {
							detaimap.put("columnAlias", k[1]);
						}
						if (l == 2) {
							detaimap.put("columnName", k[2]);
						}
					}

					if (detaimap.size() != 0) {
						ls.add(detaimap);
					}
				}
				map.put("list", ls);
				map.put("filedir", readfile.getName());

				map.put("remark", remark);
				g.generateByMap(map, template);
			}
		}
	}
}
