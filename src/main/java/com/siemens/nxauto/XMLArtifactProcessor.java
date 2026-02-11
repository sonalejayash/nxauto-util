package com.siemens.nxauto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class XMLArtifactProcessor {

    public void process(Path inputDir) {

        try {
            List<Path> xmlFiles = Files.list(inputDir)
                    .filter(p -> p.toString().endsWith(".xml"))
                    .collect(Collectors.toList());

            if (xmlFiles.isEmpty()) {
                throw new IllegalStateException(
                        "No XML artifacts found in input directory: " + inputDir
                );
            }

            for (Path xml : xmlFiles) {
                System.out.println("Processing XML: " + xml.getFileName());
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to read input directory", e);
        }
    }
}
