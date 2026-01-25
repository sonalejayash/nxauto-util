package com.siemens.nxauto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManifestGenerator {

    private final File executionDir;
    private final List<File> artifacts;

    public ManifestGenerator(File executionDir, List<File> artifacts) {
        this.executionDir = executionDir;
        this.artifacts = artifacts;
    }

    public void generate() throws IOException {

        File outputDir = new File(executionDir, Configuration.OUTPUT_DIR);

        if (!outputDir.exists()) {
            Files.createDirectories(outputDir.toPath());
        }

        Map<String, Object> manifest = new HashMap<>();
        manifest.put("artifactCount", artifacts.size());

        Map<String, Object> artifactDetails = new HashMap<>();
        for (File artifact : artifacts) {
            Map<String, Object> details = new HashMap<>();
            details.put("size", artifact.length());
            details.put("path", artifact.getAbsolutePath());
            artifactDetails.put(artifact.getName(), details);
        }

        manifest.put("artifacts", artifactDetails);

        File manifestFile = new File(outputDir, Configuration.MANIFEST_FILE);

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(manifestFile, manifest);
    }
}
