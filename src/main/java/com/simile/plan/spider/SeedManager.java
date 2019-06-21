package com.simile.plan.spider;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * @author yitao
 * update date 2019-02-28
 */
public class SeedManager {

    public static List<String> readSeed() throws IOException {
        FileReader fr = new FileReader(new File(DataContainer.get(),"seed0.txt"));
        List<String> result = IOUtils.readLines(fr);
        IOUtils.closeQuietly(fr);
        return result;
    }

}
