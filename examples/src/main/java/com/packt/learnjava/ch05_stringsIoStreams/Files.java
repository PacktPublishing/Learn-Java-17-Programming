package com.packt.learnjava.ch05_stringsIoStreams;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.net.URI;

public class Files {
    public static void main(String... args) throws Exception {
        createFile1();
        createFile2();
        list1();
    }

    private static void createFile1() {
        File f = new File("NameOnly.txt");
        try {
            f.createNewFile();
            f.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createFile2() {
        String path = "demo1" + File.separator + "demo2" + File.separator;
        String fileName = "FileName.txt";
        File f = new File(path + fileName);
        f = new File(path, fileName);
        f = new File(new File(path), fileName);
        URI uri = new File(path + fileName).toURI();
        f = new File(uri);
        try {
            new File(path).mkdirs();
            f.createNewFile();
            f.delete();
            path = StringUtils.substringBeforeLast(path, File.separator);
            while (new File(path).delete()) {
                path = StringUtils.substringBeforeLast(path, File.separator);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void list1() {
        String path1 = "demo1" + File.separator;
        String path2 = "demo2" + File.separator;
        String path = path1 + path2;
        File f1 = new File(path + "file1.txt");
        File f2 = new File(path + "file2.txt");
        File dir1 = new File(path1);
        File dir = new File(path);
        try {
            dir.mkdirs();
            f1.createNewFile();
            f2.createNewFile();

            System.out.print("\ndir1.list(): ");
            for(String d: dir1.list()){
                System.out.print(d + " ");
            }
            System.out.print("\ndir1.listFiles(): ");
            for(File f: dir1.listFiles()){
                System.out.print(f + " ");
            }
            System.out.print("\ndir.list(): ");
            for(String d: dir.list()){
                System.out.print(d + " ");
            }
            System.out.print("\ndir.listFiles(): ");
            for(File f: dir.listFiles()){
                System.out.print(f + " ");
            }
            System.out.print("\nFile.listRoots(): ");
            for(File f: File.listRoots()){
                System.out.print(f + " ");
            }
            System.out.println();
            System.out.println();
            f1.delete();
            f2.delete();
            path = StringUtils.substringBeforeLast(path, File.separator);
            while (new File(path).delete()) {
                path = StringUtils.substringBeforeLast(path, File.separator);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

