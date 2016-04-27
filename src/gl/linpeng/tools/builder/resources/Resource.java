package gl.linpeng.tools.builder.resources;

import gl.linpeng.tools.builder.service.ResourceType;

/**
 * Builder resource interface
 * 
 * @author linpeng
 *
 */
public interface Resource {

	public ResourceType getType();

	public Object getRaw();

	public String toText();
}
