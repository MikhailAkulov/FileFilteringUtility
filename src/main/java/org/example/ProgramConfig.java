package org.example;

import java.nio.file.Path;
import java.util.List;

public class ProgramConfig {
    public Path outputPath;
    public String prefix;
    public boolean appendOption;
    public boolean fullStatistics;
    public List<Path> fileList;

    public ProgramConfig(Path outputPath, String prefix, boolean appendOption, boolean fullStatistics, List<Path> fileList) {
        this.outputPath = outputPath;
        this.prefix = prefix;
        this.appendOption = appendOption;
        this.fullStatistics = fullStatistics;
        this.fileList = fileList;
    }
}
