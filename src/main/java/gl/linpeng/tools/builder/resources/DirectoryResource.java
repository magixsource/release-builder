package gl.linpeng.tools.builder.resources;

import gl.linpeng.tools.builder.service.ResourceType;

/**
 * Directory resource mean this is a folder,all files in this dicrectory will be
 * manage as reource by default.
 * 
 * @author linpeng
 *
 */
public class DirectoryResource extends LocalStorageResource {
	public DirectoryResource() {
		this.setType(ResourceType.DIRECTORY);
	}
}
