package gl.linpeng.tools.builder;

import gl.linpeng.tools.builder.filters.CopyOperation;
import gl.linpeng.tools.builder.filters.MinifyOperation;
import gl.linpeng.tools.builder.filters.UglifyOperation;
import gl.linpeng.tools.builder.model.LocalStorageBuildModel;
import gl.linpeng.tools.builder.module.LocalStorageModule;
import gl.linpeng.tools.builder.resources.CssResource;
import gl.linpeng.tools.builder.resources.DirectoryResource;
import gl.linpeng.tools.builder.resources.FileResource;
import gl.linpeng.tools.builder.resources.JavascriptResource;
import gl.linpeng.tools.builder.resources.LocalStorageResource;
import gl.linpeng.tools.builder.service.Builder;
import gl.linpeng.tools.builder.service.ResourceType;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Test enter of program
 * 
 * @author linpeng
 * 
 */
public class RunService {

	private RunService() {
	}

	public static void main(String[] args) {

		Builder builder = new Builder();

		String basePath = "E:\\林鹏\\f\\GitHub\\node_workspace\\frontend\\JS控件\\";

		// register operations
		builder.registerOperation(new MinifyOperation());
		builder.registerOperation(new UglifyOperation());
		builder.registerOperation(new CopyOperation());

		// register modules
		JavascriptResource js = new JavascriptResource();
		js.setType(ResourceType.JAVASCRIPT);
		js.setSource(new File(basePath + "js\\app\\sinobest.image.js"));
		LocalStorageModule module = new LocalStorageModule();
		module.setId("image");
		module.setName("image");
		module.setType("file");
		List<LocalStorageResource> resources = new ArrayList<LocalStorageResource>();
		resources.add(js);
		CssResource css = new CssResource();
		css.setType(ResourceType.CSS);
		css.setSource(new File(basePath + "css\\sinobest.image.css"));
		resources.add(css);

		FileResource img1 = new FileResource();
		img1.setType(ResourceType.IMAGE);
		img1.setSource(new File(basePath + "img\\zoomin.cur"));
		resources.add(img1);

		FileResource img2 = new FileResource();
		img2.setType(ResourceType.IMAGE);
		img2.setSource(new File(basePath + "img\\photoTool.gif"));
		resources.add(img2);

		module.setResources(resources);

		// dependencies
		LocalStorageModule dependency = new LocalStorageModule();
		dependency.setId("date");
		dependency.setName("date");
		dependency.setType("dir");

		List<LocalStorageResource> resources2 = new ArrayList<LocalStorageResource>();
		DirectoryResource dir = new DirectoryResource();
		dir.setSource(new File(basePath + "js\\lib\\My97DatePicker"));
		resources2.add(dir);

		dependency.setResources(resources2);
		List<LocalStorageModule> dependencies = new ArrayList<LocalStorageModule>();
		dependencies.add(dependency);
		module.setDependencies(dependencies);

		// builder.registerModule(module);
		List<LocalStorageModule> modules = new ArrayList<LocalStorageModule>();
		modules.add(module);
		LocalStorageBuildModel model = new LocalStorageBuildModel();
		model.setModules(modules);

		builder.build(model);
	}

}
