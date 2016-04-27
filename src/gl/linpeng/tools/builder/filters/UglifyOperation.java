package gl.linpeng.tools.builder.filters;

import gl.linpeng.tools.builder.resources.LocalStorageResource;
import gl.linpeng.tools.builder.resources.Resource;
import gl.linpeng.tools.builder.service.ResourceType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Uglify resource
 * 
 * @author linpeng
 *
 */
public class UglifyOperation implements Operation {
	final Logger logger = LoggerFactory.getLogger(UglifyOperation.class);
	private String content = "";

	@Override
	public void onProcess(Resource resource) {
		LocalStorageResource br = (LocalStorageResource) resource;
		logger.info("Processing resource [{}]", br.getSource().getName());
		String original = br.getContent();

		// begin process

		br.setContent(original);
		content += original;
	}

	@Override
	public boolean isSupported(Resource resource) {
		return resource.getType().equals(ResourceType.JavaScript)
				|| resource.getType().equals(ResourceType.Css);
	}

	@Override
	public String toText() {
		return this.content;
	}

}
