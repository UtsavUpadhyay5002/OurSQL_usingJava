import java.util.Scanner;

public class Main {
    public static void main(String[] args) 
    {
        Scanner scan = new Scanner(System.in);
        boolean sqlOn = true;
        System.out.println("Welcome to the MySQL Replica! Type your queries below.");

        while (sqlOn) {
            System.out.print(">>> ");
            StringBuilder query = new StringBuilder();
            boolean queryComplete = false;

            while (!queryComplete) 
            {
                String line = scan.nextLine();
                query.append(line);

                if (line.trim().endsWith(";")) 
                {
                    queryComplete = true;
                    System.out.format("\n");
                } else 
                {
                    System.out.format("  > ");
                    query.append(" ");
                }
            }

            String fullQuery = query.toString().trim();

            if (fullQuery.equalsIgnoreCase("exit;")) 
            {
                System.out.println("Exiting the MySQL Replica. Goodbye!");
                sqlOn = false;
            }
            else
            {
                Query fullQuery2 = new Query(fullQuery);
                fullQuery2.execute();
            }
        }
        scan.close();
    }
}
