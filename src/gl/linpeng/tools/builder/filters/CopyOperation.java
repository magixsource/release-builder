package gl.linpeng.tools.builder.filters;

import gl.linpeng.tools.builder.resources.Resource;
import gl.linpeng.tools.builder.service.ResourceType;

/**
 * Copy operation,nothing to do but copy resource
 * 
 * @author linpeng
 *
 */
public class CopyOperation implements Operation {

	@Override
	public boolean isSupported(Resource resource) {
		return resource.getType().equals(ResourceType.Image)
				|| resource.getType().equals(ResourceType.Directory);
	}

	@Override
	public String toText() {
		return null;
	}

	@Override
	public void onProcess(Resource resource) {

	}

}
