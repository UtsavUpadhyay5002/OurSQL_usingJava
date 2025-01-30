public class Query 
{
    public static String curDatabase;
    private String queryType;
    private String command;
    
    //constructor
    public Query(String command)
    {
        queryType = command.substring(0, command.indexOf(' '));
        this.command = command;
        curDatabase = "";
    }
    
    //executor
    public void execute()
    {
        if(queryType.equalsIgnoreCase("show")) {
            DatabaseManager.showDatabases();
        }
        else if (queryType.equalsIgnoreCase("use")) {
            String dbName = command.substring(command.indexOf(" ")+1, command.indexOf(";"));
            DatabaseManager.useDatabase(dbName);
        }
        else if(queryType.equalsIgnoreCase("create") 
            || queryType.equalsIgnoreCase("drop"))
        {
            DDLQuery ddlQuery= new DDLQuery(queryType, command);
            ddlQuery.execute();
        }
        else if(queryType.equalsIgnoreCase("select")
                    || queryType.equalsIgnoreCase("insert")
                    || queryType.equalsIgnoreCase("update")
                    || queryType.equalsIgnoreCase("delete"))
        {
            DMLQuery dmlQuery = new DMLQuery(queryType, command, curDatabase);
            dmlQuery.execute();
        }
        else
        {
            System.out.println(String.format("ERROR: Query type (%s) is not recognized by this replics!!!", queryType));
        }
    }

    //setter for fixed database when selected
    public static void setDatabase(String dbname)
    {
        curDatabase = dbname;
    }

    //method to chck whether database is selected or not
    public static boolean dbSelected()
    {
        if(curDatabase.equals(""))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
