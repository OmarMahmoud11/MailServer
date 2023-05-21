package Controller.SingletonClasses;

import Controller.Profile.ProfileI;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private final static String databasePath = "src/main/java/Model/Database/";
    private static Database instance;
    private static int size = 0;
    private static List<ProfileI> dataBaseList;

    private Database() throws Exception{
        dataBaseList = new ArrayList<>();
        setDatabase();
    }

    public static Database getInstance() throws Exception {
        if (instance == null) {
            instance = new Database();
            System.out.println("CREATED DATABASE");
        }
        return instance;
    }


    private static void setDatabase() throws Exception{
        Creator creator = Creator.getInstance();
        File file = new File(databasePath);
        File[] files = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        if(files == null || file == null){
            throw new Exception("NO SUCH DIRECTORY");
        }
        size = files.length;
        for(int i = 0; i < size; i++){
            dataBaseList.add(creator.setProfile(databasePath, files[i].getName()));
        }
        System.out.println(size);
    }

    public int getSize(){
        return size;
    }

    public String getDatabasePath(){
        return databasePath;
    }

    public ProfileI getProfilebyUsername(String encryption, String username){

        if(username == "") username = encryption.substring(0, encryption.indexOf("$"));
        ProfileI profile = null;
        System.out.println(username);
        for(int i = 0; i < size; i++){
            if(username.equals(dataBaseList.get(i).getUsername())){
                System.out.println("INSIDE GET PROFILE BY USERNAME");
                profile = dataBaseList.get(i);
                System.out.println(profile.getUsername());
            }
        }
        return profile;
    }
    public ProfileI getProfilebyEncryption(String encryption)throws Exception{
        ProfileI profile = null;
        for(int i = 0; i < size; i++){
            if(encryption.equals(dataBaseList.get(i).getEncryption())){
                System.out.println("INSIDE GET PROFILE BY USERNAME");
                profile = dataBaseList.get(i);
            }
        }
        if(profile == null){
            throw new Exception("COULD NOT FIND PROFILE BY THIS USERNAME");
        }
        return profile;
    }


    public void addProfile(String encryption) throws Exception{
        Creator creator = Creator.getInstance();
        if(size > 0){
            if (getProfilebyUsername(encryption, "") != null) {
                System.out.println(getProfilebyUsername(encryption, ""));
                throw new Exception("PROFILE WITH SAME NAME EXISTS");
            }
            else{
                System.out.println("AFTER CATCH");
                ProfileI profile = creator.createProfile(databasePath, encryption);
                dataBaseList.add(profile);
                size++;
            }
        }
        else{
            System.out.println(encryption);
            ProfileI profile = creator.createProfile(databasePath, encryption);
            dataBaseList.add(profile);
            size++;

        }
    }
    public void removeProfile(String encryption) throws Exception{
        Deleter deleter = Deleter.getInstance();
        ProfileI profile = getProfilebyUsername(encryption, "");
        if(size > 0){
            if(profile != null){
                deleter.deleteProfile(profile);
                dataBaseList.remove(profile);
                size--;
            }
            else{
                throw new Exception("COULD NOT FIND PROFILE TO DELETE");
            }
        }
        else{
            throw new Exception("DATABASE IS EMPTY");
        }
    }

    public void createDataFile(String encryption) throws Exception{
        Creator creator = Creator.getInstance();
        if(getProfilebyEncryption(encryption) == null){
            throw new Exception("THERE IS NO SUCH PROFILE");
        }
        creator.createDataFile(getProfilebyEncryption(encryption));
    }

    public void printDatabase(){
        for(int i = 0; i < size; i++){
            System.out.println("PROFILE Encyrption ==> ".concat(dataBaseList.get(i).getEncryption()));
            System.out.println("PROFILE UserName ==> ".concat(dataBaseList.get(i).getUsername()));
            System.out.println("PROFILE passWord ==> ".concat(dataBaseList.get(i).getPassWord()));
            System.out.println("PROFILE DataContainer Name ==> ".concat(dataBaseList.get(i).getDataContainer().getDataContainerName()));
            System.out.println("PROFILE DataContainer Path ==> ".concat(dataBaseList.get(i).getDataContainer().getDataContainerPath()));
        }
    }







}
