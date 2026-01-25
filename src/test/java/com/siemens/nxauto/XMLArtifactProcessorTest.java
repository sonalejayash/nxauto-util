package com.siemens.nxauto;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileWriter;

import static org.junit.Assert.assertEquals;

public class XMLArtifactProcessorTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void xmlArtifactsShouldBeDetected() throws Exception {
        File executionDir = tempFolder.newFolder("exec");
        File inputDir = new File(executionDir, Configuration.INPUT_DIR);
        inputDir.mkdir();

        File xmlFile = new File(inputDir, "sample.xml");
        try (FileWriter writer = new FileWriter(xmlFile)) {
            writer.write("<test/>");
        }

        XMLArtifactProcessor processor = new XMLArtifactProcessor(executionDir);
        processor.process();

        assertEquals(1, processor.getArtifacts().size());
    }
}
