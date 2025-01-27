import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class DatabaseManager 
{
    //dropTable
    public static void showDatabases()
    {
        //fetching current ("oursql folder") directory path
        Path currRelativePath = Paths.get("");
        String currAbsolutePathString = currRelativePath.toAbsolutePath().toString();

        File directory = new File(currAbsolutePathString + "\\Databases");
        File[] files = directory.listFiles();

        //Printing all existing databases
        if (files != null) {
            for (File file : files) {
                System.out.println(file.getName());
            }
            System.out.println("\n");
        }
        else {
            System.out.println("No Databases Exists!!!\n");
        }
    }

    //dropDatabase
    public static void useDatabase(String folderName) 
    {
        if(databaseExists(folderName))
        {
            System.out.println("using " + folderName + "\n");
            Query.setDatabase(folderName);
        }
        else
        {
            System.out.println("Database " + folderName + " does not exist\n");
        }
    }

    public static boolean databaseExists(String databaseName) {
        return fileExists(databaseName, "notForTable");
    }

    public static boolean tableExists(String databaseName, String tableName) {
        return fileExists(databaseName, tableName);
    }

    public static boolean fileExists(String databaseName, String fileName)
    {
        //fetching current ("oursql folder") directory path
        Path currRelativePath = Paths.get("");
        String currAbsolutePathString = currRelativePath.toAbsolutePath().toString();
        String targetFile;
        File targetDirectory;

        if(fileName.equals("notForTable"))
        {   
            //Appending "Databases" to path
            targetDirectory = new File(currAbsolutePathString + "\\Databases");
            targetFile = databaseName;
        }
        else
        {
            //Appending "Databases\DatabaseName" to path
            targetDirectory = new File(currAbsolutePathString + "\\Databases\\" + databaseName);
            targetFile = fileName;
        }
        
        File[] files = targetDirectory.listFiles();
        int flg = 0;
        if (files != null) {
            for (File file : files) 
            {
                if(file.getName().equalsIgnoreCase(targetFile))
                {
                    flg+=1;
                }
            }
        }

        if(flg == 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
