package gl.linpeng.tools.builder.resources;

import gl.linpeng.tools.builder.service.ResourceType;

import java.io.File;

/**
 * Javascript Resource class
 * 
 * @author linpeng
 *
 */
public class JavascriptResource extends FileResource {

	public JavascriptResource() {
		this.setType(ResourceType.JAVASCRIPT);
	}

	public JavascriptResource(String source) {
		this();
		this.setSource(new File(source));
	}
}
