package com.siemens.nxauto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

public class NXAutoUtil {

    private static final Logger log =
            LoggerFactory.getLogger(NXAutoUtil.class);

    public static void main(String[] args) {

        if (args.length < 2) {
            System.err.println("Usage: nxauto-util <workspace> <executionId>");
            System.exit(1);
        }

        String workspace = args[0];
        String executionId = args[1];

        log.info("NX Auto Utility started");
        log.info("Workspace Path : {}", workspace);
        log.info("Execution ID   : {}", executionId);

        try {
            Path inputDir =
                    PathValidator.validateExecutionDirectory(workspace, executionId);

            XMLArtifactProcessor processor = new XMLArtifactProcessor();
            processor.process(inputDir);

            log.info("STATUS: SUCCESS");

        } catch (Exception e) {
            log.error("STATUS: FAILED");
            log.error("Reason: {}", e.getMessage(), e);
            System.exit(1);
        }
    }
}
