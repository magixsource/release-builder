package gl.linpeng.tools.builder.utils;

import gl.linpeng.tools.builder.resources.LocalStorageResource;
import gl.linpeng.tools.builder.service.ResourceType;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
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

	public static final String ZIP_FILE_SUFFIX = ".zip";

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

		List<LocalStorageResource> resources = new ArrayList<LocalStorageResource>(
				files.size());
		for (File file : files) {
			LocalStorageResource br = new LocalStorageResource();
			if (file.getName().endsWith(".js")) {
				br.setType(ResourceType.JAVASCRIPT);
			} else if (file.getName().endsWith(".css")) {
				br.setType(ResourceType.CSS);
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
		File source = br.getSource();
		String content = null;
		try {
			content = org.apache.commons.io.FileUtils.readFileToString(source,
					"utf-8");
		} catch (IOException e) {
			logger.error("Read file content error. {}", e);
		}
		return content;
	}

	/**
	 * Copy files to directory
	 * 
	 * @param paths
	 *            file paths
	 * @param target
	 *            directory
	 */
	public static void copyFilesToDirectory(String[] paths, File targetDir) {
		try {
			for (String path : paths) {
				org.apache.commons.io.FileUtils.copyFileToDirectory(
						org.apache.commons.io.FileUtils.getFile(path),
						targetDir);
			}
		} catch (IOException e) {
			logger.error("CopyFilesToDirectory error. {}", e);
		}
	}

	/**
	 * Copy whole directory to directory
	 * 
	 * @param paths
	 *            directory paths
	 * @param targetDir
	 *            target directory
	 */
	public static void copyDirectorysToDirectory(String[] paths, File targetDir) {
		try {
			for (String path : paths) {
				org.apache.commons.io.FileUtils.copyDirectoryToDirectory(
						org.apache.commons.io.FileUtils.getFile(path),
						targetDir);
			}
		} catch (IOException e) {
			logger.error("CopyDirectorysToDirectory error. {}", e);
		}
	}

	/**
	 * Zip directory
	 * 
	 * @param path
	 *            directory
	 */
	public static final void zip(String path) {
		File file = org.apache.commons.io.FileUtils.getFile(path);
		if (!file.exists()) {
			return;
		}
		FileOutputStream fileOutputStream;
		try {

			fileOutputStream = new FileOutputStream(file.getPath()
					+ ZIP_FILE_SUFFIX);
			CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream,
					new CRC32());
			ZipOutputStream out = new ZipOutputStream(cos);
			compress(out, file, file.getName());
			IOUtils.closeQuietly(out);
		} catch (FileNotFoundException e) {
			logger.error("FileNotFoundException error. {}", e);
		}

	}

	/**
	 * compress file or directory
	 * 
	 * @param out
	 * @param file
	 * @param parentPath
	 */
	private static final void compress(ZipOutputStream out, File file,
			String parentPath) {
		if (file.isDirectory()) {
			compressDirectory(out, file, parentPath);
		} else {
			compressFile(out, file, parentPath);
		}
	}

	/**
	 * compress directory,list files and compress their
	 * 
	 * @param out
	 * @param dir
	 * @param parentPath
	 */
	private static void compressDirectory(ZipOutputStream out, File dir,
			String parentPath) {
		try {
			if (!dir.exists()) {
				return;
			}
			File[] files = dir.listFiles();
			String thisPath = dir.getName();
			if (!thisPath.equalsIgnoreCase(parentPath)) {
				thisPath = parentPath + File.separator + thisPath;
			}

			for (int i = 0; i < files.length; i++) {
				compress(out, files[i], thisPath);
			}
		} catch (Exception e) {
			logger.error("Compress directory error, directory {}.{}", dir, e);
		}
	}

	private static final void compressFile(ZipOutputStream out, File file,
			String parentPath) {
		if (!file.exists()) {
			return;
		}
		if (file.getName().endsWith(ZIP_FILE_SUFFIX)) {
			return;
		}
		try {
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(file));
			String entryName = parentPath + File.separator + file.getName();
			ZipEntry entry = new ZipEntry(entryName);
			out.putNextEntry(entry);
			IOUtils.copy(bis, out);
			IOUtils.closeQuietly(bis);
		} catch (Exception e) {
			logger.error("Compress file error, file {}.{}", file, e);
		}
	}
}
