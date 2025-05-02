package io.github.cloudstars.lowcode.controller;

import java.io.File;

public class FileTestUtils {

    private static final String FOLDER = System.getProperty("user.dir") + File.separator + "uploads" + File.separator;

    public static String getUploadFolder() {
        return FOLDER;
    }

}
