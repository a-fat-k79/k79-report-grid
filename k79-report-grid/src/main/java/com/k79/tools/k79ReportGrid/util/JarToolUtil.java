package com.k79.tools.k79ReportGrid.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JarToolUtil {
    public static void main(String[] args) {
        System.err.println(getFile().getAbsolutePath());
    }

    /**
     * 获取jar绝对路径
     *
     * @return
     */
    public static String getJarPath() {
        File file = getFile();
        if (file == null)
            return null;
        return file.getAbsolutePath();
    }

    /**
     * 获取jar目录
     *
     * @return
     */
    public static String getJarDir() {
        File file = getFile();
        if (file == null)
            return null;
        return getFile().getParent();
    }

    /**
     * 获取jar目录
     *
     * @return
     */
    public static String getSpringBootJarDir() {
        File file = getFile();
        if (file == null)
            return null;
        String dir = getFile().getParent();
        if (dir.contains(".jar")) {
            dir = dir.split("\\.jar")[0];
            dir = dir.substring(0, dir.lastIndexOf("\\"));
        }
        if (dir.startsWith("file:\\")) {
            dir = dir.replace("file:\\", "");
        }
        return dir;
    }

    /**
     * 获取jar包名
     *
     * @return
     */
    public static String getJarName() {
        File file = getFile();
        if (file == null)
            return null;
        return getFile().getName();
    }

    /**
     * 获取当前Jar文件
     *
     * @return
     */
    public static File getFile() {
        // 关键是这行...
        String path = JarToolUtil.class.getProtectionDomain().getCodeSource().getLocation().getFile();
        try {
            path = java.net.URLDecoder.decode(path, "UTF-8"); // 转换处理中文及空格
        } catch (java.io.UnsupportedEncodingException e) {
            return null;
        }
        return new File(path);
    }

    /**
     * 获取当前jar包目录下，指定后缀名的文件
     *
     * @param pathSuffix
     * @return
     */
    public static List<File> getFile(String pathSuffix) {
        List<File> result = new ArrayList<>();
        File dir = new File(getJarDir());
        for (File temp : dir.listFiles()) {
            if (temp.getAbsolutePath().endsWith(pathSuffix)) {
                result.add(temp);
            }
        }
        return result;
    }
}