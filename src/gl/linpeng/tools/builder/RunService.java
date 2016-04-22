package gl.linpeng.tools.builder;

import gl.linpeng.tools.builder.service.Builder;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Test enter of program
 * 
 * @author linpeng
 *
 */
public class RunService {

	public static void main(String[] args) {

		File root = new File(
				"E:\\林鹏\\f\\GitHub\\node_workspace\\frontend\\JS控件");

		File target = new File("E:\\林鹏\\f\\GitHub\\temp");

		List<String> jsIncluder = Arrays.asList(new String[] {
				"sinobest.bigselect.js", "sinobest.image.js" });

		Builder builder = new Builder();
		builder.setSource(root);
		builder.setTarget(target);
		builder.setIncludes(jsIncluder);

		builder.build();
	}

}
