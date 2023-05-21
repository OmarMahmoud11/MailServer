package Controller;

import java.io.File;

public interface DataContainerI {
    String dataContainerPath = null;
    String dataContainerName = null;
    File file = null;

    String getDataContainerPath();
    String getDataContainerName();
    File getFile();

    void setDataContainerPath(String dataContainerPath);
    void setDataContainerName(String dataContainerName);
    void setFile(File file);
}
