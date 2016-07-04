package gl.linpeng.tools.builder.model;

import gl.linpeng.tools.builder.module.Module;

import java.util.List;

/**
 * Build model
 * 
 * @author linpeng
 * 
 */
public interface BuildModel {

	/**
	 * get modules
	 * 
	 * @return
	 */
	public List<? extends Module<?>> getModules();

}
