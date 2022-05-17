package fileManager;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.zip.ZipEntry;


class ZipManage {

    public static void main(String test[]) {
//        unzip(new File("test/임시.zip"), new File("tset2").toPath());
        unzipsFolder(new File("HW2"));
    }
//    public static String get

    public static void unzipsFolder(File folder) {
        File files[] = folder.listFiles();
        for(File file : files){
            if (file.isDirectory()){
                unzipsFolder(file);
            }else if(FileReader.getExtension(file).equals("zip"))
                unzip(file, FileReader.getFolder(file));
        }

    }
    public static void unzip(File target, String folder) {
        try {
            ZipFile zipFile = new ZipFile(target);
            zipFile.extractAll(folder);
        } catch (ZipException e) {
            e.printStackTrace();
        }


    }
    public static Path zipSlipProtect(ZipEntry zipEntry, Path targetDir)
            throws IOException {

        // test zip slip vulnerability
        Path targetDirResolved = targetDir.resolve(zipEntry.getName());

        // make sure normalized file still has targetDir as its prefix
        // else throws exception
        Path normalizePath = targetDirResolved.normalize();
        if (!normalizePath.startsWith(targetDir)) {
            throw new IOException("Bad zip entry: " + zipEntry.getName());
        }
        return normalizePath;
    }


}
