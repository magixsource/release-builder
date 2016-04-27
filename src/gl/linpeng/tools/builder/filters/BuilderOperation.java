package gl.linpeng.tools.builder.filters;

import gl.linpeng.tools.builder.resources.Resource;

/**
 * Builder Operation
 * 
 * @author linpeng
 *
 */
public interface BuilderOperation {

	/**
	 * internal opeartion
	 * 
	 * @param resource
	 */
	public void onProcess(Resource resource);

	/**
	 * Check is this resouce supported
	 * 
	 * @param resource
	 * @return
	 */
	public boolean isSupported(Resource resource);

	public String toText();
}
