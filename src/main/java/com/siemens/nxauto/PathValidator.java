package com.siemens.nxauto;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.file.Path;

public class PathValidator {

    private PathValidator() {
        // Utility class
    }

    public static void validateExecutionDirectory(Path executionDir) {

        if (executionDir == null) {
            throw new IllegalArgumentException("Execution directory path is null.");
        }

        File executionFolder = executionDir.toFile();

        if (!executionFolder.exists()) {
            throw new IllegalArgumentException(
                    "Execution directory does not exist: " + executionFolder.getAbsolutePath()
            );
        }

        if (!executionFolder.isDirectory()) {
            throw new IllegalArgumentException(
                    "Execution path is not a directory: " + executionFolder.getAbsolutePath()
            );
        }

        File inputDir = new File(executionFolder, Configuration.INPUT_DIR);

        if (!inputDir.exists() || !inputDir.isDirectory()) {
            throw new IllegalArgumentException(
                    "Required input directory missing: " + inputDir.getAbsolutePath()
            );
        }

        try {
            // Verify directory is readable and listable
            FileUtils.listFiles(inputDir, null, false);
        } catch (Exception ex) {
            throw new IllegalArgumentException(
                    "Input directory is not accessible: " + inputDir.getAbsolutePath(), ex
            );
        }
    }
}
