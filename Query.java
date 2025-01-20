
public class Query 
{
    String queryType;
    String command;

    public Query(String command)
    {
        queryType = command.substring(0, command.indexOf(' '));
        this.command = command;
    }
    
    public void execute()
    {
        if(queryType.equalsIgnoreCase("create") 
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
            DMLQuery dmlQuery = new DMLQuery(queryType, command);
            dmlQuery.execute();
        }
        else
        {
            System.out.println(String.format("ERROR: Query type (%s) is not recognized by this replics!!!", queryType));
        }
    }
}
