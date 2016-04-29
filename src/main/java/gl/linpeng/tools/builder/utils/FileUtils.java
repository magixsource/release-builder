package gl.linpeng.tools.builder.utils;

import gl.linpeng.tools.builder.resources.LocalStorageResource;
import gl.linpeng.tools.builder.service.ResourceType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.filefilter.IOFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Wrapper of FileUtils
 * 
 * @author linpeng
 *
 */
public class FileUtils {
	static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

	private FileUtils() {
	}

	/**
	 * List files from root where file name in includes
	 * 
	 * @param root
	 *            where files store
	 * @param includes
	 *            name filter files
	 * @param dirFilter
	 *            dir filter
	 * @return result list
	 */
	public static List<LocalStorageResource> getFiles(File root,
			final List<String> includes, IOFileFilter dirFilter) {
		Collection<File> files = org.apache.commons.io.FileUtils.listFiles(
				root, new IOFileFilter() {

					@Override
					public boolean accept(File dir, String name) {
						return true;
					}

					@Override
					public boolean accept(File file) {
						for (String str : includes) {
							if (file.getName().equalsIgnoreCase(str)) {
								return true;
							}
						}
						return false;
					}
				}, dirFilter);

		List<LocalStorageResource> resources = new ArrayList<>(files.size());
		for (File file : files) {
			LocalStorageResource br = new LocalStorageResource();
			if (file.getName().endsWith(".js")) {
				br.setType(ResourceType.JavaScript);
			} else if (file.getName().endsWith(".css")) {
				br.setType(ResourceType.Css);
			}
			br.setPath(file.getPath());
			br.setSource(file);

			resources.add(br);
		}
		return resources;
	}

	/**
	 * Read resource content as string object
	 * 
	 * @param br
	 *            BasicResource
	 * @return BasicResource content
	 */
	public static String readContent(LocalStorageResource br) {
		File source = (File) br.getSource();
		String content = null;
		try {
			content = org.apache.commons.io.FileUtils.readFileToString(source,
					"utf-8");
		} catch (IOException e) {
			logger.error("Read file content error. {}", e);
		}
		return content;
	}
}
