package gl.linpeng.tools.builder.resources;

import java.io.File;

import gl.linpeng.tools.builder.service.ResourceType;

/**
 * Css resources class
 * 
 * @author linpeng
 *
 */
public class CssResource extends FileResource {

	public CssResource() {
		this.setType(ResourceType.CSS);
	}

	public CssResource(String source) {
		this();
		this.setSource(new File(source));
	}
}
