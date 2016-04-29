package gl.linpeng.tools.builder.service;

import gl.linpeng.tools.builder.module.LocalStorageModule;

import java.util.List;

/**
 * A build service for local storage scene
 * 
 * @author linpeng
 *
 */
public interface LocalStorageBuildService extends BuildService {

	/**
	 * load localstorage modules
	 * 
	 * @return LocalStorageModule collections
	 */
	public List<LocalStorageModule> loadLocalStorageModules();

}
