package commands;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utils.Catalog;
import utils.Item;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ReportCommand implements Command<Catalog>{
	
	private Catalog catalog;
	private String path;
	
	@Override
	public Catalog execute() {
		if(catalog == null) {
			return null;
		}

		Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
		
		cfg.setClassForTemplateLoading(Catalog.class, "/");
		cfg.setDefaultEncoding("UTF-8");
		
		Template template = null;
		
		Map<String, Object> templateData = new HashMap<>();
		templateData.put("catalog_name", catalog.getName());
		List<Map<String, Object>> items = new LinkedList<>();
		Map<String, Object> item = null;
		for(Item i : catalog.getItems()) {
			item = new HashMap<>();
			item.put("author", i.getAuthor());
			item.put("id", i.getId());
			item.put("location", i.getLocation());
			item.put("title", i.getTitle());
			item.put("type", i.getType());
			item.put("year", i.getYear());
			item.put("keywords", i.getKeywords());
			items.add(item);
		}

		templateData.put("catalog_name", catalog.getName());
		templateData.put("catalog_items", items);
		try {
			FileTemplateLoader ftl = new FileTemplateLoader(new File("./templates"));
			cfg.setTemplateLoader(ftl);
			template = cfg.getTemplate("catalog_test.ftl");
			StringWriter out = new StringWriter();
		    template.process(templateData, out);
		    
		    File file = new File(path);
		    file.createNewFile();
		    FileWriter writer = new FileWriter(file);
		    writer.write(out.getBuffer().toString());
		    writer.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
				
	}

}
