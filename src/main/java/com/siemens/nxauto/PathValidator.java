package com.siemens.nxauto;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathValidator {

    public static Path validateExecutionDirectory(String workspace, String executionId) {

        if (workspace == null || workspace.isEmpty()) {
            throw new IllegalArgumentException("Workspace path is empty");
        }

        if (executionId == null || executionId.isEmpty()) {
            throw new IllegalArgumentException("Execution ID is empty");
        }

        Path executionDir =
                Paths.get(workspace, "nxutil", executionId);

        if (!Files.exists(executionDir)) {
            throw new IllegalArgumentException(
                    "Execution directory does not exist: " + executionDir
            );
        }

        Path inputDir = executionDir.resolve("input");

        if (!Files.exists(inputDir)) {
            throw new IllegalArgumentException(
                    "Required input directory missing: " + inputDir
            );
        }

        return inputDir;
    }
}
