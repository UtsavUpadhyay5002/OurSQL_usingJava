//CREATE DATABASE DATABASE_NAME;
//DROP DATABASE database_name;
//CREATE TABLE TABLE_NAME(...); or CREATE TABLE TABLE_NAME (...);
//DROP TABLE TABLE_NAME;

public class DDLQuery 
{
    String queryType;
    String fileType;
    String fileName;
    String fields;

    public DDLQuery(String queryType, String command)
    {
        this.queryType = queryType;
        String[] parts = command.split(" ");
        fileType = parts[1];
        if(parts[2].contains("(")) {
            fileName = parts[2].substring(0,parts[2].indexOf("("));
        }
        else
        {
            if(parts[2].contains(";")) {
                fileName = parts[2].replace(";", "");
            }
            else {
                fileName = parts[2];
            }
        }
        if(command.contains("(")) {
            fields = command.substring(command.indexOf("(")+1, command.indexOf(")"));
        }
        else {
            fields = "null";
        }
    }

    public void execute()
    {
        if(queryType.equalsIgnoreCase("drop"))
        {
            //try-catch
            if(fileType.equalsIgnoreCase("database")) {
                DatabaseManager.dropDatabase(fileName);
            }
            else if(fileType.equalsIgnoreCase("table")) {
                DatabaseManager.dropTable(Query.getDatabase(), fileName);
            }
            else {
                System.out.println("ERROR: Inappropriate object Type: " + fileType);
            }
        }
        else if(queryType.equalsIgnoreCase("create"))
        {
            //try-catch
            if(fileType.equalsIgnoreCase("database")) {
                DatabaseManager.createDatabase(fileName);
            }
            else if(fileType.equalsIgnoreCase("table")) {
                //DatabaseManager.createTable(fileName); 
                int i = 0;
            }
            else {
                System.out.println("ERROR: Inappropriate object Type: " + fileType);
            }
        }
        //System.out.println("DDL Query object contains:-");
        //System.out.println("queryType: " + queryType);
        //System.out.println("fileType: " + fileType);
        //System.out.println("fileName: " + fileName);
        //System.out.println("fields: " + fields);
    }
}
