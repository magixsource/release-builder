package gl.linpeng.tools.builder.filters;

import gl.linpeng.tools.builder.resources.BuilderResource;

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
	public void onProcess(BuilderResource resource);

	/**
	 * Check is this resouce supported
	 * 
	 * @param resource
	 * @return
	 */
	public boolean isSupported(BuilderResource resource);

	public String toText();
}
