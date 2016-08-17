package gl.linpeng.tools.builder.module;

import gl.linpeng.tools.builder.resources.LocalStorageResource;

import java.util.ArrayList;
import java.util.List;

/**
 * A module storage on local hardware
 * 
 * @author linpeng
 *
 */
public class LocalStorageModule extends Module<LocalStorageModule> {

	private List<LocalStorageResource> resources;

	public LocalStorageModule() {

	}

	public LocalStorageModule(String id, String name) {
		this.setId(id);
		this.setName(name);
	}

	public LocalStorageModule(String id, String name, String type) {
		this.setId(id);
		this.setName(name);
		this.setType(type);
	}

	public List<LocalStorageResource> getResources() {
		return resources;
	}

	public void setResources(List<LocalStorageResource> resources) {
		this.resources = resources;
	}

	/**
	 * Add resource
	 * 
	 * @param resource
	 */
	public void addResource(LocalStorageResource resource) {
		if (resources == null) {
			resources = new ArrayList<LocalStorageResource>();
		}
		resources.add(resource);
	}

	/**
	 * add dependency
	 * 
	 * @param base
	 */
	public void addDependency(LocalStorageModule module) {
		List<LocalStorageModule> modules = this.getDependencies();
		if (null == modules) {
			modules = new ArrayList<LocalStorageModule>();
		}
		modules.add(module);
		this.setDependencies(modules);
	}

}
