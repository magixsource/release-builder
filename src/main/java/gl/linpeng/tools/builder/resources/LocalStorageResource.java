package gl.linpeng.tools.builder.resources;

import gl.linpeng.tools.builder.service.ResourceType;
import gl.linpeng.tools.builder.utils.FileUtils;

import java.io.File;

/**
 * Local resource
 * 
 * @author linpeng
 *
 */
public class LocalStorageResource extends BaseResource {
	private String root;
	private String path;
	private File source;
	private String content;

	public LocalStorageResource() {
	}

	public LocalStorageResource(File file) {
		this.source = file;
		this.path = file.getPath();
		this.root = file.getParent();
		this.content = FileUtils.readContent(this);
		
		if (file.getName().endsWith(".js")) {
			this.setType(ResourceType.JAVASCRIPT);
		} else if (file.getName().endsWith(".css")) {
			this.setType(ResourceType.CSS);
		}
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

	public File getSource() {
		return source;
	}

	public void setSource(File source) {
		this.source = source;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		if (null == content) {
			content = FileUtils.readContent(this);
		}
		return this.content;
	}

	@Override
	public String toText() {
		return this.getContent();
	}
}
