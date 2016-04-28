package gl.linpeng.tools.builder.filters;

import gl.linpeng.tools.builder.resources.LocalStorageResource;
import gl.linpeng.tools.builder.resources.Resource;
import gl.linpeng.tools.builder.service.ResourceType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Copy operation,nothing to do but copy resource
 * 
 * @author linpeng
 *
 */
public class CopyOperation implements Operation {
	final Logger logger = LoggerFactory.getLogger(CopyOperation.class);

	private String content = "";

	@Override
	public boolean isSupported(Resource resource) {
		return resource.getType().equals(ResourceType.Image)
				|| resource.getType().equals(ResourceType.Directory);
	}

	@Override
	public String toText() {
		return this.content;
	}

	@Override
	public void onProcess(Resource resource) {
		LocalStorageResource br = (LocalStorageResource) resource;
		logger.info("Processing resource [{}]", br.getSource().getName());

		String path = br.getSource().getPath();
		br.setPath(path);
		this.content += path + ";";
	}
}
