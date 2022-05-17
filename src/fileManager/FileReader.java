package fileManager;

import java.io.File;
import java.util.ArrayList;



public class FileReader {
    static String findExtend[] = {"csv", "swvs"};

    public static String getFolder(File file){
        String path = file.getAbsolutePath();
        return path.substring(0, path.lastIndexOf('\\'));

    }

    //static ArrayList<File> fileList;
    public static File[] read(String path) {
        ArrayList<File> fileList = new ArrayList<File>();
        File file = new File(path);
        if ( file.isDirectory() )
            searchFolder(file, fileList);
        return  castArray(fileList);
    }
    private static File[] castArray(ArrayList<File> arrayList)
    {
        File list[] = new File[arrayList.size()];
        for(int i = 0; i<list.length; i++) {
            list[i] = arrayList.get(i);
        }
        return list;
    }
    private static void searchFolder(File folder, ArrayList<File> fileList) {
        for(File file : folder.listFiles()) {
            if(file.isDirectory())
            {
                searchFolder(file, fileList);
            }else if(checkExtend(file)){
                fileList.add(file);
            }
        }
    }
    private static boolean checkExtend(File file) {
        String targetExtend = getExtension(file);
        for(String extend : findExtend) {
            if(extend.equals(targetExtend))
                return true;
            else
                continue;
        }
        return false;
    }

    /**
     * .을 제외한 확장자 명 혹은 ""
     * @param target
     * @return "csv", "java", ""etc ....
     */
    public static String getExtension(File target) {
        String extend = "";
        String name = target.getName();
        int pointIndex = name.lastIndexOf('.');
        if(-1 < pointIndex)
        {
            extend = name.substring(pointIndex+1);
        }
        return extend;

    }
    public static void main(String []args) {
//        System.out.println(getExtension(new File("UtillProject.iml")));
        System.out.println(getFolder(new File("test")));
    }
}