package gl.linpeng.tools.builder.resources;

import gl.linpeng.tools.builder.service.ResourceType;

/**
 * Basic resources of release builder,particular resource should extend this
 * 
 * @author linpeng
 *
 */
public class BaseResource implements Resource {

	private ResourceType type;

	public void setType(ResourceType type) {
		this.type = type;
	}

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

}
