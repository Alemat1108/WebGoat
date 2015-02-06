package org.owasp.webgoat.plugins;


import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PluginFileUtils {

    public static boolean fileEndsWith(Path p, String s) {
        return p.getFileName().toString().endsWith(s);
    }

    public static boolean hasParentDirectoryWithName(Path p, String s) {
        if (p == null || p.getParent() == null || p.getRoot().equals(p.getParent())) {
            return false;
        }
        if (p.getParent().getFileName().toString().equals(s)) {
            return true;
        }
        return hasParentDirectoryWithName(p.getParent(), s);
    }

    public static Path createDirsIfNotExists(Path p) throws IOException {
        if ( Files.notExists(p)) {
            Files.createDirectories(p);
        }
        return p;
    }
    
    public static List<Path> getFilesInDirectory( Path directory) throws IOException
    {
    	List<Path> files = new ArrayList<>();
    	DirectoryStream<Path> dirStream;
    	dirStream = Files.newDirectoryStream(directory);
    	for (Path entry : dirStream) {
    	    files.add(entry);
    	}
    	dirStream.close();
    	return files;
    }

}