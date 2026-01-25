package com.siemens.nxauto;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;

public class PathValidatorTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void validExecutionDirectory_shouldPass() throws Exception {
        File executionDir = tempFolder.newFolder("exec");
        File inputDir = new File(executionDir, Configuration.INPUT_DIR);
        inputDir.mkdir();

        PathValidator.validateExecutionDirectory(executionDir.toPath());
    }

    @Test(expected = IllegalArgumentException.class)
    public void missingInputDirectory_shouldFail() throws Exception {
        File executionDir = tempFolder.newFolder("exec");
        PathValidator.validateExecutionDirectory(executionDir.toPath());
    }
}
