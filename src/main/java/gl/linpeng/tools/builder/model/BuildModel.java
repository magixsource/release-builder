package gl.linpeng.tools.builder.model;

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
	public List<? extends gl.linpeng.tools.builder.module.Module<?>> getModules();

}
