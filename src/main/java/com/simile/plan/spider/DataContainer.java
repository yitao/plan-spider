package com.simile.plan.spider;

import java.io.File;
import java.net.URL;

/**
 * @author yitao
 * update date 2019-02-22
 */
public class DataContainer {

    public static File getLocalDataContainerDirPath(){
        File file = new File(DataContainer.class.getClassLoader().getResource("./").getPath());
        File dir = new File(file.getParentFile().getParentFile(),"/src/data/");
        if(!dir.exists()){
            dir.mkdirs();
        }
        return dir;
    }


    public static File get(){
        File file = new File(DataContainer.class.getClassLoader().getResource("seed").getPath());
        return file;
    }

    public static void main(String[] args) {
        System.out.println(DataContainer.getLocalDataContainerDirPath());
    }

}
