package gl.linpeng.tools.builder.module;

import gl.linpeng.tools.builder.resources.BasicResource;

import java.util.List;

/**
 * A module storage on local hardware
 * 
 * @author linpeng
 *
 */
public class LocalStorageModule extends Module {

	private List<BasicResource> resources;

	public List<BasicResource> getResources() {
		return resources;
	}

	public void setResources(List<BasicResource> resources) {
		this.resources = resources;
	}

}
