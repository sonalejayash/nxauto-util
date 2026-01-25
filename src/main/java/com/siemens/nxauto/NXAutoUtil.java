package com.siemens.nxauto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NXAutoUtil {

    private static final Logger LOG = LoggerFactory.getLogger(NXAutoUtil.class);

    public static void main(String[] args) {

        if (args == null || args.length != 2) {
            LOG.error("Invalid arguments.");
            LOG.error("Usage: java -jar nxauto-util.jar <workspace_path> <execution_id>");
            System.exit(ExitCode.INVALID_ARGUMENTS.getCode());
        }

        String workspacePath = args[0];
        String executionId = args[1];

        LOG.info("NX Auto Utility started");
        LOG.info("Workspace Path : {}", workspacePath);
        LOG.info("Execution ID   : {}", executionId);

        try {
            // Build execution directory path
            Path executionDirPath = Paths.get(
                    workspacePath,
                    Configuration.NXUTIL_DIR,
                    executionId
            );

            // Validate directory structure
            PathValidator.validateExecutionDirectory(executionDirPath);

            // Convert Path -> File for downstream processing
            File executionDir = executionDirPath.toFile();

            // Process XML artifacts
            XMLArtifactProcessor processor = new XMLArtifactProcessor(executionDir);
            processor.process();

            // Generate manifest
            ManifestGenerator generator =
                    new ManifestGenerator(executionDir, processor.getArtifacts());
            generator.generate();

            LOG.info("STATUS: SUCCESS");
            System.exit(ExitCode.SUCCESS.getCode());

        } catch (Exception ex) {
            LOG.error("STATUS: FAILED");
            LOG.error("Reason: {}", ex.getMessage(), ex);
            System.exit(ExitCode.PROCESSING_ERROR.getCode());
        }
    }
}
