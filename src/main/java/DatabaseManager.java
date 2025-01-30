import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class DatabaseManager 
{
    //showDatabases
    public static void showDatabases()
    {
        //fetching current ("oursql folder") directory path
        Path currRelativePath = Paths.get("");
        String currAbsolutePathString = currRelativePath.toAbsolutePath().toString();

        File directory = new File((currAbsolutePathString + "\\Databases\\csv").replace("\\src\\main\\java", ""));
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

    //useDatabase
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
        
    //dropDatabase
    public static void dropDatabase(String folderName)
    {
    	if(databaseExists(folderName))
    	{
    		if(Query.getDatabase().equals(folderName))
    		{
    			Query.setDatabase("");
    		}
    		//fetching current ("Oursql folder") directory path
            Path currRelativePath = Paths.get("");
            String currAbsolutePathString = currRelativePath.toAbsolutePath().toString();

            File targetDirectory1 = new File((currAbsolutePathString + "\\Databases\\csv\\" + folderName).replace("\\src\\main\\java", ""));
            File targetDirectory2 = new File((currAbsolutePathString + "\\Databases\\Outline\\" + folderName).replace("\\src\\main\\java", ""));
            if(targetDirectory1.delete() && targetDirectory2.delete())
            {
            	System.out.println("Database " + folderName + " dropped\n");
            }
            else
            {
            	System.out.println("ERROR: Unknown error occured while deleting the file\n");
            }
    	}
    	else
    	{
    		System.out.println("Database " + folderName + " does not EXIST!!");
    	}
    }
    
    //dropTable
    public static void dropTable(String folderName, String fileName)
    {
    	if(tableExists(folderName, fileName))
    	{
    		//fetching current ("Oursql folder") directory path
            Path currRelativePath = Paths.get("");
            String currAbsolutePathString = currRelativePath.toAbsolutePath().toString();

            File targetDirectory1 = new File((currAbsolutePathString + "\\Databases\\csv\\" + folderName + "\\" +fileName+".csv").replace("\\src\\main\\java", ""));
            File targetDirectory2 = new File((currAbsolutePathString + "\\Databases\\Outline\\" + folderName + "\\" +fileName+".uru").replace("\\src\\main\\java", ""));
            if(targetDirectory1.delete() && targetDirectory2.delete())
            {
            	System.out.println("Table " + fileName + " dropped from " + folderName + "\n");
            }
            else
            {
            	System.out.println("ERROR: Unknown error occured while deleting the file\n");
            }
    	}
    	else
    	{
    		System.out.println("table " + fileName + " does not EXIST!!");
    	}
    }
    
    //create database
    public static void createDatabase(String folderName)
    {
    	if(databaseExists(folderName))
    	{
    		System.out.println("Database " + folderName + " already exists!!\n");
    	}
    	else
    	{
    		//fetching current ("Oursql folder") directory path
            Path currRelativePath = Paths.get("");
            String currAbsolutePathString = currRelativePath.toAbsolutePath().toString();

            File targetDirectory1 = new File((currAbsolutePathString + "\\Databases\\csv\\" + folderName).replace("\\src\\main\\java", ""));
            File targetDirectory2 = new File((currAbsolutePathString + "\\Databases\\Outline\\" + folderName).replace("\\src\\main\\java", ""));
            
            boolean b1 = targetDirectory1.mkdir();
            boolean b2 = targetDirectory2.mkdir();
            
            if(b1 && b2)
            {
            	System.out.println("Database " + folderName + " Successfully created!\n");
            }
            else
            {
            	System.out.println("ERROR: Unknown error occured while creating the database\n");
            }
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
        int flg = 0;
        
        if(fileName.equals("notForTable"))
        {   
            //Appending "Databases" to path
            targetDirectory = new File((currAbsolutePathString + "\\Databases\\csv").replace("\\src\\main\\java", ""));
            targetFile = databaseName;
        }
        else
        {
        	if(databaseExists(databaseName))
        	{
	            //Appending "Databases\DatabaseName" to path
	            targetDirectory = new File((currAbsolutePathString + "\\Databases\\csv" + databaseName).replace("\\src\\main\\java", ""));
	            targetFile = fileName;
        	}
        	else
        	{
        		targetDirectory = new File((currAbsolutePathString + "\\Databases\\csv" + databaseName));
        		targetFile = fileName;
        		flg = 2;
        	}
        }
        
        File[] files = targetDirectory.listFiles();
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
