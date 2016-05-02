package gl.linpeng.tools.builder.model;

import gl.linpeng.tools.builder.module.LocalStorageModule;
import gl.linpeng.tools.builder.module.Module;

import java.util.List;

/**
 * Local storage build model
 * 
 * @author linpeng
 * 
 */
public class LocalStorageBuildModel implements BuildModel {
	/**
	 * target build modules
	 */
	private List<LocalStorageModule> modules;

	@Override
	public List<? extends Module> getModules() {
		return modules;
	}

	public void setModules(List<LocalStorageModule> modules) {
		this.modules = modules;
	}

}
