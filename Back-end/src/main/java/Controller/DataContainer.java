package Controller;

import java.io.File;

public class DataContainer implements DataContainerI {
    private String dataContainerPath;
    private String dataContainerName;
    private File file;


    public DataContainer(){}

    public DataContainer(String dataContainerPath, String dataContainerName, File file) {
        this.dataContainerPath = dataContainerPath;
        this.dataContainerName = dataContainerName;
        this.file = file;
    }


    public String getDataContainerPath() {
        return dataContainerPath;
    }


    public String getDataContainerName() {
        return dataContainerName;
    }


    public File getFile() {
        return file;
    }


    @Override
    public void setDataContainerPath(String dataContainerPath) {
        this.dataContainerPath = dataContainerPath;
    }


    @Override
    public void setDataContainerName(String dataContainerName) {
        this.dataContainerName = dataContainerName;
    }


    public void setFile(File file) {
        this.file = file;
    }



}
