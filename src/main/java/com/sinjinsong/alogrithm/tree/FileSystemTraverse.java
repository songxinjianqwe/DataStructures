package com.sinjinsong.alogrithm.tree;

import java.io.File;

/**
 * @author sinjinsong
 * @date 2018/3/1
 */
public class FileSystemTraverse {
    public static void traverse(String path) {
        traverse(new File(path), 0);
    }

    private static void traverse(File root, int level) {
        if (root.isDirectory()) {
            print(root, level);
            File[] files = root.listFiles();
            for (File f : files) {
                traverse(f, level + 1);
            }
        } else {
            print(root,level);   
        }
    }

    private static void print(File file, int level) {
        for (int i = 0; i < level * 4; i++) {
            System.out.print(" ");
        }
        System.out.print(file.isDirectory() ? "Dir: " : "File: ");
        System.out.println(file.getAbsolutePath());
    }

    public static void main(String[] args) {
        traverse("D:/Download");
    }
}
