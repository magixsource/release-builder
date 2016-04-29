package gl.linpeng.tools.builder.module;

import gl.linpeng.tools.builder.resources.LocalStorageResource;

import java.util.List;

/**
 * A module storage on local hardware
 * 
 * @author linpeng
 *
 */
public class LocalStorageModule extends Module {

	private List<LocalStorageResource> resources;

	public List<LocalStorageResource> getResources() {
		return resources;
	}

	public void setResources(List<LocalStorageResource> resources) {
		this.resources = resources;
	}

}
