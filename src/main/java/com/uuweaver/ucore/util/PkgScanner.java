package com.uuweaver.ucore.util;

import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created with IntelliJ IDEA.
 * User: chenlong828
 * Date: 10/6/12
 * Time: 8:37 PM
 * @deprecated 使用Reflections替代
 */
public class PkgScanner {
    public static List<String> getClassInPackage(String package_name) throws IOException {
        ArrayList<String> classNames = new ArrayList<String>();

        if(null == package_name || "".equals(package_name)){
            return classNames;
        }

        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        String resourceName = package_name.replaceAll("\\.", "/");
        //System.out.println("----+++++----->" + resourceName);

        URL url = loader.getResource(resourceName);
        //System.out.println("----------------->url:" + url);

        if (url.toString().startsWith("file:")) {
            //file:/home/username/workspace/myproject/classes/a/b/c
            try {
                File file = new File(url.toURI());
                File[] files = file.listFiles();

                for (File f : files) {
                    getClassName(package_name, f, classNames);
                }
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

        } else if(url.toString().startsWith("jar:")){
            //jar:file:/home/username/workspace/lib/abc.jar!/a/b/c

            int index = url.toString().indexOf('!');

            String filePath = url.toString().substring(9, index);
            //System.out.println("--------path--------->" + filePath);
            JarFile jarFile = new JarFile(filePath);
            for (Enumeration<JarEntry> files = jarFile.entries(); files.hasMoreElements(); ) {
                JarEntry jarF = files.nextElement();
                if (jarF.getName().startsWith(resourceName)) {
                   // System.out.println("----------------->" + jarF.getName());
                    getClassName(jarF.getName(), classNames);
                }
            }
        }

        return classNames;
    }

    private static void getClassName(String file, List<String> list) {
       // System.out.printf("----------------->%s1$", file);

        if (file.endsWith(".class")) {
            String rpl = StringUtils.replace(file, "/", ".");
            list.add(StringUtils.replace(rpl, ".class", ""));
        }
    }

    private static void getClassName(String packageName, File packageFile, List<String> list) {
        //System.out.printf("----------------->%s1$", packageFile.getAbsoluteFile() + "\n");

        if (packageFile.isFile()) {
            list.add(packageName + "." + StringUtils.replace(packageFile.getName(), ".class", ""));
        } else {
            File[] files = packageFile.listFiles();

            if (files == null) return;

            String tmPackageName = packageName + "." + packageFile.getName();
            for (File f : files) {
                getClassName(tmPackageName, f, list);
            }
        }
    }
}
