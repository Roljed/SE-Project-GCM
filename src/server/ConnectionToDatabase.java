package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import product.City;
import product.DigitalMap;
import product.Tour;
import product.content.Content;
import product.content.Location;
import product.content.Site;
import product.pricing.MapCost;
import product.pricing.Purchase;
import user.member.MemberCard;


/**
 *
 * @version 1
 * @author Avi Ayeli
 */

public class ConnectionToDatabase
{

    static private String databaseName = "xdhLgvyRnN";
    static private String databaseUsername = "xdhLgvyRnN";
    static private String databasePassword = "uNtE7bXJvV";
    static private final String DB_URL = "jdbc:mysql://remotemysql.com/"+ databaseName + "?useSSL=false";
    static private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    public static String getDatabaseName()
    {
        return databaseName;
    }

    public static void setDatabaseName(String databaseName)
    {
        ConnectionToDatabase.databaseName = databaseName;
    }

    public static String getDatabaseUsername()
    {
        return databaseUsername;
    }

    public static void setDatabaseUsername(String databaseUsername)
    {
        ConnectionToDatabase.databaseUsername = databaseUsername;
    }

    public static String getDatabasePassword()
    {
        return databasePassword;
    }

    public static void setDatabasePassword(String databasePassword)
    {
        ConnectionToDatabase.databasePassword = databasePassword;
    }


    public static Connection connectToDatabase()
    {
        try
        {
            Class.forName(JDBC_DRIVER).newInstance();
        } catch (Exception ex) {}

        Connection con=null;

        try
        {
            con = DriverManager.getConnection(DB_URL, databaseUsername, databasePassword);

        } catch (SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return con;
    }

    public static Object SignIn (String nameUser, String password) throws SQLException {
        Connection conn = connectToDatabase();
        Statement stmt;
        ResultSet rs = null;
        String name = "";
        MemberCard memberCard = null;
        try
        {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery("SELECT Password FROM User_Database WHERE UserName = '" + nameUser + "'");
            if((!rs.next()) || (!rs.getString("Password").equals(password)))
                return ClientServerStatus.WRONG_USERNAME_OR_PASSWORD;
            rs=stmt.executeQuery("SELECT Registered FROM User_Database WHERE UserName = '" + nameUser + "'");
            if ((!rs.next()) || (rs.getString("Registered").equals("YES")))
                return ClientServerStatus.CONNECTED;
            rs=stmt.executeQuery("SELECT ID FROM User_Database WHERE UserName = '" + nameUser + "'");
            if (!rs.next()){return null;}
            String ID=rs.getString("ID");
            rs=stmt.executeQuery("SELECT UserName FROM User_Database WHERE UserName = '" + nameUser + "'");
            if (!rs.next()){return null;}
            String un=rs.getString("UserName");
            rs=stmt.executeQuery("SELECT PersonalName FROM User_Database WHERE UserName = '" + nameUser + "'");
            if (!rs.next()){return null;}
            String pn=rs.getString("PersonalName");
            rs=stmt.executeQuery("SELECT Password FROM User_Database WHERE UserName = '" + nameUser + "'");
            if (!rs.next()){return null;}
            String ps=rs.getString("Password");
            rs=stmt.executeQuery("SELECT PhoneNumber FROM User_Database WHERE UserName = '" + nameUser + "'");
            if (!rs.next()){return null;}
            String phone=rs.getString("PhoneNumber");
            rs=stmt.executeQuery("SELECT Email FROM User_Database WHERE UserName = '" + nameUser + "'");
            if (!rs.next()){return null;}
            String email=rs.getString("Email");
            rs=stmt.executeQuery("SELECT Permission FROM User_Database WHERE UserName = '" + nameUser + "'");
            if (!rs.next()){return null;}
            String per=rs.getString("Permission");
            List<Purchase> purchaseHistory = null;
            rs=stmt.executeQuery("SELECT PurchaseID FROM User_Database WHERE UserName = '" + nameUser + "'");
            if (!rs.next()){return null;}
            String purchaseID=rs.getString("PurchaseID");
            String[] message = purchaseID.split(",");
            for (String s : message) {
                rs=stmt.executeQuery("SELECT ID FROM Purchase_Database WHERE ID = '" + s + "'");
                int id=Integer.parseInt(rs.getString("ID"));
                rs=stmt.executeQuery("SELECT DateOfPurchase FROM Purchase_Database WHERE ID = '" + s + "'");
                Date date=rs.getDate("DateOfPurchase");
                rs=stmt.executeQuery("SELECT Cost FROM Purchase_Database WHERE ID = '" + s + "'");
                int cost=rs.getInt("Cost");
                rs=stmt.executeQuery("SELECT PurchaseType FROM Purchase_Database WHERE ID = '" + s + "'");
                String type=rs.getString("PurchaseType");
                rs=stmt.executeQuery("SELECT PurchaseCities FROM Purchase_Database WHERE ID = '" + s + "'");
                String stringcities=rs.getString("PurchaseCities");
                int[] purchasedCityID = null;
                String[] tmpCity=stringcities.split(",");
                int i=0;
                for (String c : tmpCity)
                    purchasedCityID[i++]=Integer.parseInt(c);
                rs=stmt.executeQuery("SELECT PurchaseMaps FROM Purchase_Database WHERE ID = '" + s + "'");
                String stringmaps=rs.getString("PurchaseMaps");
                int[] purchasedMapID = null;
                String[] tmpMaps=stringmaps.split(",");
                i=0;
                for (String m : tmpMaps)
                    purchasedMapID[i++]=Integer.parseInt(m);
                purchaseHistory.add(new Purchase(id,date,purchasedCityID,purchasedMapID,cost,type));
            }
            memberCard=new MemberCard(ID, pn, un, ps, phone, email, null,per);
            memberCard.setPurchaseHistory(purchaseHistory);
            stmt.executeUpdate("UPDATE User_Database SET Registered = 'YES' WHERE UserName='"+ nameUser +"'");
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {}
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  memberCard;
    }

    public static boolean UpdateClient(String ID, String nameUser, String namePersonal, String password, String phoneNumber, String email, String permission)
    {
        Connection conn= connectToDatabase();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        try
        {
            stmt.executeUpdate("UPDATE User_Database SET UserName = '" + nameUser + "' WHERE ID='"+ ID +"'");
            stmt.executeUpdate("UPDATE User_Database SET PersonalName = '" + namePersonal + "' WHERE ID='"+ ID +"'");
            stmt.executeUpdate("UPDATE User_Database SET Password = '" + password + "' WHERE ID='"+ ID +"'");
            stmt.executeUpdate("UPDATE User_Database SET PhoneNumber = '" + phoneNumber + "' WHERE ID='"+ ID +"'");
            stmt.executeUpdate("UPDATE User_Database SET Email = '" + email + "' WHERE ID='"+ ID +"'");
            stmt.executeUpdate("UPDATE User_Database SET Permission = '" + permission + "' WHERE ID='"+ ID +"'");

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean SignOut (String nameUser)
    {
        Connection conn= connectToDatabase();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        try
        {
            stmt.executeUpdate("UPDATE User_Database SET Registered = 'NO' WHERE UserName='"+ nameUser +"'");
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {}
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean AddClient (String ID,String permission,String nameUser,String password,String namePersonal, String email, String phoneNumber)
    {
        Connection conn = connectToDatabase();
        Statement stmt;
        if (SearchForUserName(nameUser))
            return false; /* User name is taken */
        try
        {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            stmt.executeUpdate("INSERT INTO User_Database VALUES ('" + ID + "','NO','" + permission +"','" + nameUser + "','" + password + "','"
                    + namePersonal + "','" + email + "','" + phoneNumber + "','','')");
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {}
            }
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public static boolean RemoveClient (String nameUser)
    {
        Connection conn= connectToDatabase();
        Statement stmt;
        try
        {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("SELECT UserName FROM User_Database WHERE UserName = '" + nameUser + "'");
            if(!rs.next())
                return false;
            stmt.executeUpdate("DELETE FROM User_Database WHERE UserName = '" + nameUser + "'");
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {}
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean SearchForUserName (String nameUser)
    {
        Connection conn= connectToDatabase();
        Statement stmt;
        try
        {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("SELECT UserName FROM User_Database WHERE UserName = '" + nameUser + "'");
            if(!rs.next())
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void UpdateContent(String ID,String name,String coordinate,String duration,String siteType,String description,boolean accessibility)
    {
        Connection conn= connectToDatabase();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        try
        {
            stmt.executeUpdate("UPDATE Content_Database SET Name = '" + name + "' WHERE ID='"+ ID +"'");
            stmt.executeUpdate("UPDATE Content_Database SET Coordinate = '" + coordinate + "' WHERE ID='"+ ID +"'");
            stmt.executeUpdate("UPDATE Content_Database SET Duration = '" + duration + "' WHERE ID='"+ ID +"'");
            stmt.executeUpdate("UPDATE Content_Database SET Type = '" + siteType + "' WHERE ID='"+ ID +"'");
            stmt.executeUpdate("UPDATE Content_Database SET Description = '" + description + "' WHERE ID='"+ ID +"'");
            if (accessibility)
                stmt.executeUpdate("UPDATE Content_Database SET Accessibility = 1 WHERE ID='"+ ID +"'");
            else
                stmt.executeUpdate("UPDATE Content_Database SET Accessibility = 0 WHERE ID='"+ ID +"'");
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {}
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void AddContentToMap(String ID,String version,String description,HashMap<Integer, Content> digitalMapContents,
                                       String cost,String lastApproved,String lastModifiedDate)
    {
        Connection conn= connectToDatabase();
        Statement stmt = null;
        String temp="";
        int counter=0;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        try
        {
            stmt.executeUpdate("UPDATE Maps_Database SET Version = '" + version + "' WHERE ID='"+ ID +"'");
            stmt.executeUpdate("UPDATE Maps_Database SET Description = '" + description + "' WHERE ID='"+ ID +"'");
            stmt.executeUpdate("UPDATE Maps_Database SET Cost = '" + cost + "' WHERE ID='"+ ID +"'");
            stmt.executeUpdate("UPDATE Maps_Database SET LastApproved = '" + lastApproved + "' WHERE ID='"+ ID +"'");
            stmt.executeUpdate("UPDATE Maps_Database SET LastModifiedDate = '" + lastModifiedDate + "' WHERE ID='"+ ID +"'");
            for (Map.Entry<Integer, Content> content : digitalMapContents.entrySet())
            {
                if (counter != 0)
                    temp+=",";
                temp+=content.getValue().getContendIDToString();
                counter=1;
            }
            stmt.executeUpdate("UPDATE Maps_Database SET Contents = '" + temp + "' WHERE ID='"+ ID +"'");

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {}
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void AddContentToCity(String ID,String name,String price,String version,String lastUpdatedDate, HashMap<Integer, DigitalMap> cityMaps,
                                        HashMap<Integer, Tour> cityTours)
    {
        Connection conn= connectToDatabase();
        Statement stmt = null;
        String temp1="";
        String temp2="";
        int counter=0;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        try
        {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("SELECT ID FROM City_Database WHERE ID = '" + ID + "'");
            if(!rs.next())
            {
                stmt.executeUpdate("UPDATE City_Database SET Name = '" + name + "' WHERE ID='"+ ID +"'");
                stmt.executeUpdate("UPDATE City_Database SET Price = '" + price + "' WHERE ID='"+ ID +"'");
                stmt.executeUpdate("UPDATE City_Database SET Version = '" + version + "' WHERE ID='"+ ID +"'");
                stmt.executeUpdate("UPDATE City_Database SET LastUpdatedDate = '" + lastUpdatedDate + "' WHERE ID='"+ ID +"'");
                for (Map.Entry<Integer, DigitalMap> digitalMap : cityMaps.entrySet())
                {
                    if (counter != 0)
                        temp1+=",";
                    temp1+=digitalMap.getValue().getDigitalMapIDToString();
                    counter=1;
                }
                stmt.executeUpdate("UPDATE City_Database SET Maps = '" + temp1 + "' WHERE ID='"+ ID +"'");
                counter=0;
                temp1="";
                for (Map.Entry<Integer, Tour> tour : cityTours.entrySet())
                {
                    if (counter != 0)
                        temp1+=",";
                    temp1+=tour.getValue().getTourIDToString();
                    counter=1;
                }
                stmt.executeUpdate("UPDATE City_Database SET Tours = '" + temp1 + "' WHERE ID='"+ ID +"'");
            }
            else {
                for (Map.Entry<Integer, DigitalMap> digitalMap : cityMaps.entrySet())
                {
                    if (counter != 0)
                        temp1+=",";
                    temp1+=digitalMap.getValue().getDigitalMapIDToString();
                    counter=1;
                }
                counter=0;
                for (Map.Entry<Integer, Tour> tour : cityTours.entrySet())
                {
                    if (counter != 0)
                        temp2+=",";
                    temp2+=tour.getValue().getTourIDToString();
                    counter=1;
                }
                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                stmt.executeUpdate("INSERT INTO City_Database VALUES ('" +ID +"','" + name + "' , '" + price + "' , '" + version +
                        "' , '"	+ lastUpdatedDate + "', '" + temp1 + "', '" + temp2 + "')");
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {}
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void UpdateTour(String ID,String name,String description,String duration,List<Content> tourSequence)
    {
        Connection conn= connectToDatabase();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        try
        {
            stmt.executeUpdate("UPDATE Tour_Database SET Name = '" + name + "' WHERE ID='"+ ID +"'");
            stmt.executeUpdate("UPDATE Tour_Database SET Description = '" + description + "' WHERE ID='"+ ID +"'");
            stmt.executeUpdate("UPDATE Tour_Database SET Duration = '" + duration + "' WHERE ID='"+ ID +"'");
            String temp = "";
            for(Content content : tourSequence){
                temp+=content.getContendIDToString();
                temp+=",";
            }
            stmt.executeUpdate("UPDATE Tour_Database SET Contents = '" + temp + "' WHERE ID='"+ ID +"'");
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {}
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Object SearchByID(String type, String ID)
    {
        if(type.equals("content"))
            return SiteByID(ID);
        if(type.equals("digital_map"))
            return DigitalMapByID(ID);
        if(type.equals("city"))
            return CityByID(ID);
        return TourByID(ID);
    }

    public static List<City> SearchByCityName(String cityName)
    {
        Connection conn= connectToDatabase();
        Statement stmt;
        try
        {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("SELECT ID FROM City_Database WHERE Name = '" + cityName + "'");
            List<City> cityList = new ArrayList<City>();
            if(rs.next())
            {
                HashMap<Integer, DigitalMap> cityMaps=new HashMap<Integer, DigitalMap>();
                HashMap<Integer, Tour> cityTours=new HashMap<Integer, Tour>();
                String[] mapsString = rs.getString("Maps").split(",");
                String[] toursString = rs.getString("Tours").split(",");
                int i=0;
                for(String m: mapsString)
                {
                    DigitalMap temp=(DigitalMap) DigitalMapByID(mapsString[i]);
                    cityMaps.put(temp.getDigitalMapID(),temp);
                    i++;
                }
                i=0;
                for(String t: toursString)
                {
                    Tour temp=(Tour) TourByID(toursString[i]);
                    cityTours.put(temp.getTourID(),temp);
                    i++;
                }
                cityList.add(new City(rs.getInt("ID"),rs.getString("Name"),cityMaps,cityTours,rs.getDouble("Price"),rs.getInt("Version"),rs.getDate("LastUpdatedDate")));
                return cityList;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object SearchBySite(String contentName)
    {
        return null;
    }

    public static Site SiteByID(String ID)
    {
        Connection conn= connectToDatabase();
        Statement stmt;
        try
        {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("SELECT ID FROM Content_Database WHERE ID = '" + ID + "'");
            if(rs.next())
            {
                String[] coordinate = rs.getString("Coordinate").split(",");
                Location location=new Location(Double.parseDouble(coordinate[0]),Double.parseDouble(coordinate[1]));
                return new Site(rs.getInt("ID"),location,rs.getDouble("Duration"),rs.getString("Name"), rs.getString("Type"),
                        rs.getString("Description"),rs.getInt("Accessibility"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static DigitalMap DigitalMapByID(String ID)
    {
        Connection conn= connectToDatabase();
        Statement stmt;
        try
        {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("SELECT ID FROM Maps_Database WHERE ID = '" + ID + "'");
            if(rs.next())
            {
                HashMap<Integer, Content> digitalMapContents=new HashMap<>();
                String[] contents = rs.getString("Contents").split(",");
                int i=0;
                for(String c: contents)
                {
                    Content temp=(Content) SiteByID(contents[i]);
                    digitalMapContents.put(temp.getContendID(),temp);
                    i++;
                }
                String[] cost = rs.getString("Cost").split(",");
                MapCost mapcost= new MapCost(Double.parseDouble(cost[0]),Integer.parseInt(cost[1]),cost[2]);
                return new DigitalMap(rs.getInt("ID"),rs.getDouble("Version"),rs.getString("Description"),digitalMapContents,mapcost);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static City CityByID(String ID)
    {
        Connection conn= connectToDatabase();
        Statement stmt;
        try
        {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("SELECT ID FROM City_Database WHERE ID = '" + ID + "'");
            if(rs.next())
            {
                HashMap<Integer, DigitalMap> cityMaps=new HashMap<>();
                String[] digitalMap = rs.getString("Maps").split(",");
                int i=0;
                for(String d: digitalMap)
                {
                    DigitalMap temp=(DigitalMap) DigitalMapByID(digitalMap[i]);
                    cityMaps.put(temp.getDigitalMapID(),temp);
                    i++;
                }
                i=0;
                HashMap<Integer, Tour> cityTours=new HashMap<>();
                String[] tour = rs.getString("Tours").split(",");
                for(String t: tour)
                {
                    Tour temp=(Tour) TourByID(tour[i]);
                    cityTours.put(temp.getTourID(),temp);
                    i++;
                }
                return new City(rs.getInt("ID"),rs.getString("Name"),cityMaps, cityTours, rs.getDouble("Price"), rs.getInt("Version"),
                        rs.getDate("LastUpdatedDate"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Tour TourByID(String ID)
    {
        Connection conn= connectToDatabase();
        Statement stmt;
        try
        {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("SELECT ID FROM Content_Database WHERE ID = '" + ID + "'");
            if(rs.next())
            {
                List<Content> tourSequence=new ArrayList<>();
                String[] content = rs.getString("Contents").split(",");
                int i=0;
                for(String c: content)
                {
                    Content temp=(Content) SiteByID(content[i]);
                    tourSequence.add(temp);
                    i++;
                }
                return new Tour(rs.getInt("ID"),rs.getString("Name"), rs.getString("Description"),tourSequence,rs.getDouble("Duration"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Object> SearchByDescription(String description){
        Connection conn= connectToDatabase();
        Statement stmt;
        try
        {
            List<Object> objects = new ArrayList<Object>();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("SELECT ID FROM City_Database WHERE Description CONTAINS '" + description + "'");
            while(true)
            {
                if(rs.next())
                {
                    HashMap<Integer, DigitalMap> cityMaps=new HashMap<>();
                    String[] digitalMap = rs.getString("Maps").split(",");
                    int i=0;
                    for(String d: digitalMap)
                    {
                        DigitalMap temp=(DigitalMap) DigitalMapByID(digitalMap[i]);
                        cityMaps.put(temp.getDigitalMapID(),temp);
                        i++;
                    }
                    i=0;
                    HashMap<Integer, Tour> cityTours=new HashMap<>();
                    String[] tour = rs.getString("Tours").split(",");
                    for(String t: tour)
                    {
                        Tour temp=(Tour) TourByID(tour[i]);
                        cityTours.put(temp.getTourID(),temp);
                        i++;
                    }
                    objects.add(new City(rs.getInt("ID"),rs.getString("Name"),cityMaps, cityTours, rs.getDouble("Price"), rs.getInt("Version"),
                            rs.getDate("LastUpdatedDate")));
                }
                else {
                    break;
                }
            }
            rs = stmt.executeQuery("SELECT ID FROM Site_Database WHERE Description CONTAINS '" + description + "'");
            while(true)
            {
                if(rs.next())
                {
                    String[] coordinates = rs.getString("Coordinate").split(",");
                    Location local = new Location(Double.parseDouble(coordinates[0]),Double.parseDouble(coordinates[1]));
                    objects.add(new Site(rs.getInt("ID"),local,rs.getDouble("Duration"),rs.getString("Name"),rs.getString("Type"),
                            rs.getString("Description"),rs.getInt("Accessibility")));
                }
                else {
                    break;
                }
            }
            return objects;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<City> SearchByCityID(String ID)
    {
        Connection conn= connectToDatabase();
        Statement stmt;
        try
        {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("SELECT ID FROM City_Database WHERE ID = '" + ID + "'");
            List<City> cityList = new ArrayList<City>();
            while(true)
            {
                if(rs.next())
                {
                    HashMap<Integer, DigitalMap> cityMaps=new HashMap<Integer, DigitalMap>();
                    HashMap<Integer, Tour> cityTours=new HashMap<Integer, Tour>();
                    String[] mapsString = rs.getString("Maps").split(",");
                    String[] toursString = rs.getString("Tours").split(",");
                    int i=0;
                    for(String m: mapsString)
                    {
                        DigitalMap temp=(DigitalMap) DigitalMapByID(mapsString[i]);
                        cityMaps.put(temp.getDigitalMapID(),temp);
                        i++;
                    }
                    i=0;
                    for(String t: toursString)
                    {
                        Tour temp=(Tour) TourByID(toursString[i]);
                        cityTours.put(temp.getTourID(),temp);
                        i++;
                    }
                    cityList.add(new City(rs.getInt("ID"),rs.getString("Name"),cityMaps,cityTours,rs.getDouble("Price"),rs.getInt("Version"),rs.getDate("LastUpdatedDate")));
                }
                else{
                    return cityList;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}