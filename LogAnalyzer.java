/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;
    
    private int[] dayCounts;
    
    private int[] monthCounts;
    
    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer(String web)
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        dayCounts = new int[32];
        monthCounts = new int[13];
        // Create the reader to obtain the data.
        reader = new LogfileReader(web);
    }
    //added the method to find the number of accesses
    public void numberOfAccesses()
    {
        int Access = 0;
        
        for(int hour = 0; hour < hourCounts.length; hour++) {
            Access = Access + hourCounts[hour];
        }
        System.out.print("The number of total accesses: "); 
        System.out.println(Access);
    }
    //added the method to find the busiest hour
    public void buisestHour()
    {
        int busiest = 0;
        int busiestHour = 0;
        
        for(int hour = 0; hour < hourCounts.length; hour++) {
            if (busiest < hourCounts[hour])
            {
                busiest = hourCounts[hour];
                busiestHour= hour;
            }
        }
        System.out.print("The busiest hour was: "); 
        System.out.println(busiestHour);
    }
    //added the method to find the quiest hour
    public void quietestHour()
    {
        int quietest = hourCounts[0];
        int quietestHour = 0;
        
        for(int hour = 0; hour < hourCounts.length; hour++) {
            if (quietest > hourCounts[hour])
            {
                quietest = hourCounts[hour];
                quietestHour= hour;
            }
        }
        System.out.print("The quietest hour was: "); 
        System.out.println(quietestHour);
    }
    //added the method to find the busiest two hours
    public void buisestTwoHour()
    {
        int busiest = 0;
        int busiestHour = 0;
        
        for(int hour = 0; hour < hourCounts.length -1; hour++) {
            if (busiest < (hourCounts[hour] + hourCounts[hour+1]))
            {
                busiest = hourCounts[hour] + hourCounts[hour+1];
                busiestHour= hour;
            }
        }
        System.out.print("The busiest two hours were: "); 
        System.out.print(busiestHour);
        System.out.print(" - ");
        System.out.println(busiestHour +1);
    }
    //added the method to find the busiest day
    public void buisestday()
    {
        int busiest = 0;
        int busiestday = 1;
        
        for(int day = 1; day < dayCounts.length; day++) {
            if (busiest < dayCounts[day])
            {
                busiest = dayCounts[day];
                busiestday= day;
            }
        }
        System.out.print("The busiest day was: "); 
        System.out.println(busiestday);
    }
    //added the method to find the quietest day
    public void quietestday()
    {
        int quietest = dayCounts[1];
        int quietestday = 1;
        
        for(int day = 1; day < dayCounts.length; day++) {
            if (quietest > dayCounts[day])
            {
                quietest = dayCounts[day];
                quietestday= day;
            }
        }
        System.out.print("The quietest day was: "); 
        System.out.println(quietestday);
    }
    //added the method to find the busiest month
    public void buisestmonth()
    {
        int busiest = 0;
        int busiestmonth = 1;
        
        for(int month = 1; month < monthCounts.length; month++) {
            if (busiest < monthCounts[month])
            {
                busiest = monthCounts[month];
                busiestmonth= month;
            }
        }
        System.out.print("The busiest month was: "); 
        System.out.println(busiestmonth);
    }
    //added the method to find the quietest month
    public void quietestmonth()
    {
        int quietest = monthCounts[1];
        int quietestmonth = 1;
        
        for(int month = 1; month < monthCounts.length; month++) {
            if (quietest > monthCounts[month])
            {
                quietest = monthCounts[month];
                quietestmonth= month;
            }
        }
        System.out.print("The quietest month was: "); 
        System.out.println(quietestmonth);
    }
    //added the method to find the average number of accesses per month
    public void averageAccessmonth()
    {
        int average = 0;
        
        for(int month = 1; month < monthCounts.length; month++) {
            average = average + monthCounts[month];
        }
        
        average = average / 12;
        
        System.out.print("The average number of access per month: "); 
        System.out.println(average);
    }

    /**
     * Analyze the hourly, dayly and monthly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            int day = entry.getDay();
            int month = entry.getMonth();
            hourCounts[hour]++;
            dayCounts[day]++;
            monthCounts[month]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
}
