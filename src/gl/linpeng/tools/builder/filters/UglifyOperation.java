package gl.linpeng.tools.builder.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gl.linpeng.tools.builder.resources.BasicResource;
import gl.linpeng.tools.builder.resources.Resource;
import gl.linpeng.tools.builder.service.ResourceType;

/**
 * Uglify resource
 * 
 * @author linpeng
 *
 */
public class UglifyOperation implements BuilderOperation {
	final Logger logger = LoggerFactory.getLogger(UglifyOperation.class);
	private String content = "";

	@Override
	public void onProcess(Resource resource) {
		BasicResource br = (BasicResource) resource;
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
