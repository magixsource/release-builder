package gl.linpeng.tools.builder.filters;

import gl.linpeng.tools.builder.resources.BasicResource;
import gl.linpeng.tools.builder.resources.BuilderResource;
import gl.linpeng.tools.builder.service.ResourceType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Minifer,make data as small as it can be
 * 
 * @author linpeng
 *
 */
public class MinifyOperation implements BuilderOperation {

	final Logger logger = LoggerFactory.getLogger(MinifyOperation.class);

	private String content = "";

	@Override
	public void onProcess(BuilderResource resource) {
		BasicResource br = (BasicResource) resource;
		logger.info("Processing resource [{}]", br.getSource().getName());
		String original = br.getContent();
		int lengthBegin = original.length();

		// remove comment style //
		original = original.replaceAll("\\/\\/.*", "");
		// remove comment style /** */
		original = original.replaceAll("/\\*(?:[^*]|\\*+[^*/])*\\*+/", "");
		// remove spaces {it's so stupid,cant remove var+space at least}
		original = original.replaceAll("\\s+", "");

		logger.debug("Processing result of [{}], [{}] -> [{}].", br.getSource()
				.getName(), lengthBegin, original.length());
		br.setContent(original);
		this.content += original;
	}

	@Override
	public boolean isSupported(BuilderResource resource) {
		return resource.getType().equals(ResourceType.JavaScript)
				|| resource.getType().equals(ResourceType.Css);
	}

	@Override
	public String toText() {
		return this.content;
	}

}
