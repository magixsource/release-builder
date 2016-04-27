package gl.linpeng.tools.builder;

import gl.linpeng.tools.builder.filters.MinifyOperation;
import gl.linpeng.tools.builder.filters.UglifyOperation;
import gl.linpeng.tools.builder.module.LocalStorageModule;
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

	public static void main(String[] args) {

		Builder builder = new Builder();

		// register operations
		builder.registerOperation(new MinifyOperation());
		builder.registerOperation(new UglifyOperation());

		// register modules
		JavascriptResource js = new JavascriptResource();
		js.setType(ResourceType.JavaScript);
		js.setSource(new File(
				"E:\\林鹏\\f\\GitHub\\node_workspace\\frontend\\JS控件\\js\\app\\sinobest.image.js"));
		LocalStorageModule module = new LocalStorageModule();
		module.setId("image");
		module.setName("image");
		module.setType("file");
		List<LocalStorageResource> resources = new ArrayList<LocalStorageResource>();
		resources.add(js);
		module.setResources(resources);

		builder.registerModule(module);

		builder.build();
	}

}
