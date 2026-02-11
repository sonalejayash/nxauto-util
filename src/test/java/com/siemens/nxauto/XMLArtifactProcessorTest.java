package com.siemens.nxauto;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.nio.file.Files;
import java.nio.file.Path;

public class XMLArtifactProcessorTest {

    @Rule
    public TemporaryFolder temp = new TemporaryFolder();

    @Test
    public void processXmlSuccessfully() throws Exception {

        Path inputDir = temp.newFolder("input").toPath();
        Files.createFile(inputDir.resolve("testcase.xml"));

        XMLArtifactProcessor processor = new XMLArtifactProcessor();
        processor.process(inputDir);
    }
}
