package gl.linpeng.tools.builder.module;

import java.util.List;

/**
 * Module is a bean of builder process target
 * 
 * @author linpeng
 *
 */
public class Module {

	private String id;
	private String name;
	private String type;
	private List<? extends Module> dependencies;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@SuppressWarnings("rawtypes")
	public List getDependencies() {
		return dependencies;
	}

	public void setDependencies(List<? extends Module> dependencies) {
		this.dependencies = dependencies;
	}

}
