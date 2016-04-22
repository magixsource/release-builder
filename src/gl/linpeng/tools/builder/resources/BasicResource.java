package gl.linpeng.tools.builder.resources;

import java.io.File;

import gl.linpeng.tools.builder.service.ResourceType;

/**
 * Basic resources of release builder,particular resource should extend this
 * class
 * 
 * @author linpeng
 *
 */
public class BasicResource implements BuilderResource {

	protected String root;
	protected String path;
	protected File source;
	protected ResourceType type;

	@Override
	public ResourceType getType() {
		return this.type;
	}

	@Override
	public Object getRaw() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String toText() {
		throw new UnsupportedOperationException();
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setType(ResourceType type) {
		this.type = type;
	}

	public File getSource() {
		return source;
	}

	public void setSource(File source) {
		this.source = source;
	}

}
