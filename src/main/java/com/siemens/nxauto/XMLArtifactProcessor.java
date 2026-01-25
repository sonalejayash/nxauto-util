package com.siemens.nxauto;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class XMLArtifactProcessor {

    private final File executionDir;
    private final List<File> artifacts = new ArrayList<>();

    public XMLArtifactProcessor(File executionDir) {
        this.executionDir = executionDir;
    }

    public XMLArtifactProcessor(java.nio.file.Path executionDir) {
        this.executionDir = executionDir.toFile();
    }

    public void process() {

        File inputDir = new File(executionDir, Configuration.INPUT_DIR);

        if (!inputDir.exists() || !inputDir.isDirectory()) {
            throw new IllegalStateException(
                    "Input directory does not exist or is invalid: " + inputDir.getAbsolutePath()
            );
        }

        Collection<File> xmlFiles = FileUtils.listFiles(inputDir, new String[]{"xml"}, false);

        if (xmlFiles == null || xmlFiles.isEmpty()) {
            throw new IllegalStateException(
                    "No XML artifacts found in input directory: " + inputDir.getAbsolutePath()
            );
        }

        artifacts.addAll(xmlFiles);
    }

    public List<File> getArtifacts() {
        return artifacts;
    }
}
