package gl.linpeng.tools.builder.filters;

import gl.linpeng.tools.builder.resources.LocalStorageResource;
import gl.linpeng.tools.builder.resources.Resource;
import gl.linpeng.tools.builder.service.ResourceType;
import gl.linpeng.tools.builder.utils.JSMin;
import gl.linpeng.tools.builder.utils.JSMin.UnterminatedCommentException;
import gl.linpeng.tools.builder.utils.JSMin.UnterminatedRegExpLiteralException;
import gl.linpeng.tools.builder.utils.JSMin.UnterminatedStringLiteralException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Minifer,make data as small as it can be
 * 
 * @author linpeng
 *
 */
public class MinifyOperation implements Operation {

	final Logger logger = LoggerFactory.getLogger(MinifyOperation.class);

	private String content = "";

	@Override
	public void onProcess(Resource resource) {
		LocalStorageResource br = (LocalStorageResource) resource;
		logger.info("Processing resource [{}]", br.getSource().getName());
		String original = br.getContent();
		int lengthBegin = original.length();
		try {
			ByteArrayInputStream in = new ByteArrayInputStream(
					original.getBytes());
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			JSMin jsmin = new JSMin(in, out);
			jsmin.jsmin();
			original = out.toString();
			logger.debug("Processing result of [{}], [{}] -> [{}].", br
					.getSource().getName(), lengthBegin, original.length());
			out.close();
			in.close();
		} catch (IOException | UnterminatedRegExpLiteralException
				| UnterminatedCommentException
				| UnterminatedStringLiteralException e) {
			e.printStackTrace();
		}
		br.setContent(original);
		this.content += original;
	}

	@Override
	public boolean isSupported(Resource resource) {
		return resource.getType().equals(ResourceType.JAVASCRIPT)
				|| resource.getType().equals(ResourceType.CSS);
	}

	@Override
	public String toText() {
		return this.content;
	}

}
