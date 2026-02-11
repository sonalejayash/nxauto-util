package com.siemens.nxauto;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertTrue;

public class PathValidatorTest {

    @Rule
    public TemporaryFolder temp = new TemporaryFolder();

    @Test
    public void validExecutionDirectory() throws Exception {

        Path workspace = temp.newFolder("workspace").toPath();
        Path executionDir = workspace.resolve("nxutil/run1/input");

        Files.createDirectories(executionDir);

        Path inputDir =
                PathValidator.validateExecutionDirectory(
                        workspace.toString(),
                        "run1"
                );

        assertTrue(Files.exists(inputDir));
    }
}
