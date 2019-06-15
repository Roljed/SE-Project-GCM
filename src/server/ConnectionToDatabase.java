package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.*;

import command.catalog.Catalog;
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


    // TODO make getVersion

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
                if (!rs.next()){break;}
                int id=Integer.parseInt(rs.getString("ID"));
                rs=stmt.executeQuery("SELECT DateOfPurchase FROM Purchase_Database WHERE ID = '" + s + "'");
                if (!rs.next()){break;}

                // Parse to LocalDate
                Date date=rs.getDate("DateOfPurchase");

                rs=stmt.executeQuery("SELECT Cost FROM Purchase_Database WHERE ID = '" + s + "'");
                if (!rs.next()){break;}
                int cost=rs.getInt("Cost");
                rs=stmt.executeQuery("SELECT PurchaseType FROM Purchase_Database WHERE ID = '" + s + "'");
                if (!rs.next()){break;}
                String type=rs.getString("PurchaseType");
                rs=stmt.executeQuery("SELECT PurchaseCities FROM Purchase_Database WHERE ID = '" + s + "'");
                if (!rs.next()){break;}
                String stringcities=rs.getString("PurchaseCities");
                int purchasedCityID=Integer.parseInt(stringcities);
                rs=stmt.executeQuery("SELECT PurchaseMaps FROM Purchase_Database WHERE ID = '" + s + "'");
                String stringmaps=rs.getString("PurchaseMaps");
                int[] purchasedMapID = null;
                String[] tmpMaps=stringmaps.split(",");
                int i=0;
                for (String m : tmpMaps)
                    purchasedMapID[i++]=Integer.parseInt(m);
                purchaseHistory.add(new Purchase(Integer.parseInt(ID), id, date, purchasedCityID, purchasedMapID, cost, type));
            }
            memberCard = new MemberCard(ID, pn, un, ps, phone, email, null,per);
            memberCard.setPurchaseHistory(purchaseHistory);
            if (pn.equals("Tester") == false)
            {
                stmt.executeUpdate("UPDATE User_Database SET Registered = 'YES' WHERE UserName='"+ nameUser +"'");
            }

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

    public static void UpdateClient(String ID, String nameUser, String namePersonal, String password, String phoneNumber, String email, String permission)
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
                } catch (SQLException e) {}
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public static boolean SignUp (String ID, String permission,String nameUser,String password,String namePersonal, String email, String phoneNumber)
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

    public static void UpdateTour(String ID,String name,String description,String duration,List<Site> tourSequence)
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
            for(Site content : tourSequence){
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

    public static Catalog SearchByCityName(String cityName)
    {
        Connection conn= connectToDatabase();
        Statement stmt;
        try
        {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("SELECT Name FROM City_Database WHERE Name = '" + cityName + "'");
            if(!rs.next())  return null;
            List<City> cityList = new ArrayList<City>();
            HashMap<Integer, DigitalMap> cityMaps=new HashMap<Integer, DigitalMap>();
            HashMap<Integer, Tour> cityTours=new HashMap<Integer, Tour>();
            rs = stmt.executeQuery("SELECT Maps FROM City_Database WHERE Name = '" + cityName + "'");
            if(!rs.next()) return null;
            String[] mapsString = rs.getString("Maps").split(",");
            rs = stmt.executeQuery("SELECT Tours FROM City_Database WHERE Name = '" + cityName + "'");
            if(!rs.next()) return null;
            String[] toursString = rs.getString("Tours").split(",");
            for(String m : mapsString)
            {
                List<DigitalMap> temp_digitalMap_list = DigitalMapByID(m).getDigitalMaps();
                if (temp_digitalMap_list != null)
                {
                    for (DigitalMap temp_map : temp_digitalMap_list)
                    {
                        cityMaps.put(temp_map.getDigitalMapID() ,temp_map);
                    }
                }
                else {
                    cityMaps = null;
                }

            }
            for(String t: toursString)
            {
                List<Tour> temp_tour_list = TourByID(t).getTours();
                for (Tour temp_tour : temp_tour_list)
                {
                    cityTours.put(temp_tour.getTourID(),temp_tour);
                }
            }
            rs = stmt.executeQuery("SELECT ID FROM City_Database WHERE Name = '" + cityName + "'");
            if(!rs.next()) return null;
            int id=rs.getInt("ID");
            rs = stmt.executeQuery("SELECT Name FROM City_Database WHERE Name = '" + cityName + "'");
            if(!rs.next()) return null;
            String name=rs.getString("Name");
            rs = stmt.executeQuery("SELECT Price FROM City_Database WHERE Name = '" + cityName + "'");
            if(!rs.next()) return null;
            double price=rs.getDouble("Price");
            rs = stmt.executeQuery("SELECT Version FROM City_Database WHERE Name = '" + cityName + "'");
            if(!rs.next()) return null;
            int Version=rs.getInt("Version");
            rs = stmt.executeQuery("SELECT LastUpdatedDate FROM City_Database WHERE Name = '" + cityName + "'");
            if(!rs.next()) return null;
            Date date = rs.getDate("LastUpdatedDate");
            cityList.add(new City(id,name,cityMaps,cityTours,price,Version, date));

            if(cityList.isEmpty()) {
                return null;
            }
            Catalog catalog = new Catalog(null,null,null,cityList);
            System.out.println("Done");
            return catalog;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Catalog SearchBySite(String contentName)
    {
        Connection conn= connectToDatabase();
        Statement stmt;
        try
        {
            List<Site> SiteList = new ArrayList<Site>();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("SELECT ID FROM Content_Database WHERE Name = '" + contentName + "'");
            if(!rs.next()) return null;
            int id=rs.getInt("ID");
            rs = stmt.executeQuery("SELECT Name FROM Content_Database WHERE Name = '" + contentName + "'");
            if(!rs.next()) return null;
            String name=rs.getString("Name");
            rs = stmt.executeQuery("SELECT Coordinate FROM Content_Database WHERE Name = '" + contentName + "'");
            if(!rs.next()) return null;
            String[] coordinate = rs.getString("Coordinate").split(",");
            Location LocationCoordinate = new Location(Double.parseDouble(coordinate[0]),Double.parseDouble(coordinate[1]));
            rs = stmt.executeQuery("SELECT Duration FROM Content_Database WHERE Name = '" + contentName + "'");
            if(!rs.next()) return null;
            double duration=rs.getDouble("Duration");
            rs = stmt.executeQuery("SELECT Type FROM Content_Database WHERE Name = '" + contentName + "'");
            if(!rs.next()) return null;
            String type=rs.getString("Type");
            rs = stmt.executeQuery("SELECT Description FROM Content_Database WHERE Name = '" + contentName + "'");
            if(!rs.next()) return null;
            String description=rs.getString("Description");
            rs = stmt.executeQuery("SELECT Accessibility FROM Content_Database WHERE Name = '" + contentName + "'");
            if(!rs.next()) return null;
            boolean accessibility=rs.getBoolean("Accessibility");
            SiteList.add(new Site(id, LocationCoordinate, duration, name, type, description, accessibility));
            if(SiteList.isEmpty()) {
                return null;
            }
            Catalog catalog = new Catalog(SiteList,null,null,null);
            return catalog;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Catalog SiteByID(String ID)
    {
        Connection conn= connectToDatabase();
        Statement stmt;
        try
        {
            List<Site> SiteList = new ArrayList<Site>();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("SELECT ID FROM Content_Database WHERE ID = '" + ID + "'");
            if(!rs.next()) return null;
            int id=rs.getInt("ID");
            rs = stmt.executeQuery("SELECT Name FROM Content_Database WHERE ID = '" + ID + "'");
            if(!rs.next()) return null;
            String name=rs.getString("Name");
            rs = stmt.executeQuery("SELECT Coordinate FROM Content_Database WHERE ID = '" + ID + "'");
            if(!rs.next()) return null;
            String[] coordinate = rs.getString("Coordinate").split(",");
            Location LocationCoordinate = new Location(Double.parseDouble(coordinate[0]),Double.parseDouble(coordinate[1]));
            rs = stmt.executeQuery("SELECT Duration FROM Content_Database WHERE ID = '" + ID + "'");
            if(!rs.next()) return null;
            double duration=rs.getDouble("Duration");
            rs = stmt.executeQuery("SELECT Type FROM Content_Database WHERE ID = '" + ID + "'");
            if(!rs.next()) return null;
            String type=rs.getString("Type");
            rs = stmt.executeQuery("SELECT Description FROM Content_Database WHERE ID = '" + ID + "'");
            if(!rs.next()) return null;
            String description=rs.getString("Description");
            rs = stmt.executeQuery("SELECT Accessibility FROM Content_Database WHERE ID = '" + ID + "'");
            if(!rs.next()) return null;
            boolean accessibility=rs.getBoolean("Accessibility");
            SiteList.add(new Site(id, LocationCoordinate, duration, name, type, description, accessibility));
            if(SiteList.isEmpty()) {
                return null;
            }
            Catalog catalog = new Catalog(SiteList,null,null,null);
            return catalog;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Catalog DigitalMapByID(String ID)
    {
        Connection conn= connectToDatabase();
        Statement stmt;
        try
        {
            List<DigitalMap> DigitalMapList = new ArrayList<DigitalMap>();
            HashMap<Integer, Content> digitalMapContents = new HashMap<>();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("SELECT ID FROM Maps_Database WHERE ID = '" + ID + "'");
            if(!rs.next()) return null;
            int id=rs.getInt("ID");
            rs = stmt.executeQuery("SELECT Version FROM Maps_Database WHERE ID = '" + ID + "'");
            if(!rs.next()) return null;
            double version=rs.getDouble("Version");
            rs = stmt.executeQuery("SELECT Description FROM Maps_Database WHERE ID = '" + ID + "'");
            if(!rs.next()) return null;
            String description = rs.getString("Description");
            rs = stmt.executeQuery("SELECT Contents FROM Maps_Database WHERE ID = '" + ID + "'");
            if(!rs.next()) return null;
            String[] contents=rs.getString("Contents").split(",");

            for(String c: contents)
            {
                List<Site> temp_site_list = (SiteByID(c)).getContents();
               for (Site site : temp_site_list)
               {
                    digitalMapContents.put(site.getContendID(),site);
               }
            }

            rs = stmt.executeQuery("SELECT Cost FROM Maps_Database WHERE ID = '" + ID + "'");
            if(!rs.next()) return null;
            double cost=rs.getDouble("Cost");
            rs = stmt.executeQuery("SELECT LastApproved FROM Maps_Database WHERE ID = '" + ID + "'");
            if(!rs.next()) return null;
            String lastApproved=rs.getString("LastApproved");
            rs = stmt.executeQuery("SELECT LastModifiedDate FROM Maps_Database WHERE ID = '" + ID + "'");
            if(!rs.next()) return null;
            DigitalMapList.add(new DigitalMap(id,version,description,digitalMapContents,new MapCost(cost, id, lastApproved)));
            if(DigitalMapList.isEmpty()) {
                return null;
            }
            Catalog catalog = new Catalog(null,DigitalMapList,null,null);
            return catalog;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Catalog CityByID(String ID)
    {
        Connection conn= connectToDatabase();
        Statement stmt;
        try
        {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("SELECT ID FROM City_Database WHERE ID = '" + ID + "'");
            if(!rs.next()) return null;
            List<City> cityList = new ArrayList<City>();
            HashMap<Integer, DigitalMap> cityMaps=new HashMap<Integer, DigitalMap>();
            HashMap<Integer, Tour> cityTours=new HashMap<Integer, Tour>();
            rs = stmt.executeQuery("SELECT Maps FROM City_Database WHERE ID = '" + ID + "'");
            if(!rs.next()) return null;
            String[] mapsString = rs.getString("Maps").split(",");
            rs = stmt.executeQuery("SELECT Tours FROM City_Database WHERE ID = '" + ID + "'");
            if(!rs.next()) return null;
            String[] toursString = rs.getString("Tours").split(",");
            for(String m : mapsString)
            {
                List<DigitalMap> temp_digitalMap_list = DigitalMapByID(m).getDigitalMaps();
                if (temp_digitalMap_list != null)
                {
                    for (DigitalMap temp_map : temp_digitalMap_list)
                    {
                        cityMaps.put(temp_map.getDigitalMapID() ,temp_map);
                    }
                }
                else {
                    cityMaps = null;
                }

            }

            for(String t: toursString)
            {
                List<Tour> temp_tour_list = TourByID(t).getTours();
                for (Tour temp_tour : temp_tour_list)
                {
                    cityTours.put(temp_tour.getTourID(),temp_tour);
                }
            }
            rs = stmt.executeQuery("SELECT ID FROM City_Database WHERE ID = '" + ID + "'");
            if(!rs.next()) return null;
            int id=rs.getInt("ID");
            System.out.println(id);
            rs = stmt.executeQuery("SELECT Name FROM City_Database WHERE ID = '" + ID + "'");
            if(!rs.next()) return null;
            String name=rs.getString("Name");
            rs = stmt.executeQuery("SELECT Price FROM City_Database WHERE ID = '" + ID + "'");
            if(!rs.next()) return null;
            double price=rs.getDouble("Price");
            rs = stmt.executeQuery("SELECT Version FROM City_Database WHERE ID = '" + ID + "'");
            if(!rs.next()) return null;
            int Version=rs.getInt("Version");
            rs = stmt.executeQuery("SELECT LastUpdatedDate FROM City_Database WHERE ID = '" + ID + "'");
            if(!rs.next()) return null;
            Date date=rs.getDate("LastUpdatedDate");
            cityList.add(new City(id,name,cityMaps,cityTours,price,Version,date));
            if(cityList.isEmpty()) {
                return null;
            }
            Catalog catalog = new Catalog(null,null,null,cityList);
            return catalog;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Catalog TourByID(String ID)
    {
        Connection conn= connectToDatabase();
        Statement stmt;
        try
        {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            List<Tour> tourList = new ArrayList<Tour>();
            ResultSet rs = stmt.executeQuery("SELECT ID FROM Tour_Database WHERE ID = '" + ID + "'");
            if(!rs.next()) return null;
            int id=rs.getInt("ID");
            rs = stmt.executeQuery("SELECT Name FROM Tour_Database WHERE ID = '" + ID + "'");
            if(!rs.next()) return null;
            String name=rs.getString("Name");
            rs = stmt.executeQuery("SELECT Description FROM Tour_Database WHERE ID = '" + ID + "'");
            if(!rs.next()) return null;
            String description=rs.getString("Description");
            rs = stmt.executeQuery("SELECT Duration FROM Tour_Database WHERE ID = '" + ID + "'");
            if(!rs.next()) return null;
            double duration=rs.getDouble("Duration");
            rs = stmt.executeQuery("SELECT Contents FROM Tour_Database WHERE ID = '" + ID + "'");
            if(!rs.next()) return null;
            String[] contents=rs.getString("Contents").split(",");
            List<Site> tourSequence = new ArrayList();
            for(String c: contents)
            {
                List<Site> temp_site_list = (SiteByID(c)).getContents();
                if (temp_site_list != null)
                {
                    tourSequence.addAll(temp_site_list);
                }
                else {
                    tourSequence = null;
                }
            }

            tourList.add(new Tour(id,name,description,tourSequence,duration));
            if(tourList.isEmpty()) {
                return null;
            }
            Catalog catalog = new Catalog(null,null,tourList,null);
            return catalog;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    static Catalog SearchByDescription(String description){
        Connection conn= connectToDatabase();
        List<City> cityList = new ArrayList<City>();
        List<Site> siteList = new ArrayList<Site>();
        Statement stmt;
        try
        {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("SELECT ID FROM City_Database WHERE Description CONTAINS '" + description + "'");
            while(true)
            {
                if(rs.next())
                {
                    String id=rs.getString("ID");
                    HashMap<Integer, DigitalMap> cityMaps=new HashMap<>();
                    String[] digitalMap = rs.getString("Maps").split(",");

                    for(String m : digitalMap)
                    {
                        List<DigitalMap> temp_digitalMap_list = DigitalMapByID(m).getDigitalMaps();
                        if (temp_digitalMap_list != null)
                        {
                            for (DigitalMap temp_map : temp_digitalMap_list)
                            {
                                cityMaps.put(temp_map.getDigitalMapID() ,temp_map);
                            }
                        }
                        else {
                            cityMaps = null;
                        }

                    }

                    HashMap<Integer, Tour> cityTours=new HashMap<>();
                    String[] toursString = rs.getString("Tours").split(",");
                    for(String t: toursString)
                    {
                        List<Tour> temp_tour_list = TourByID(t).getTours();
                        for (Tour temp_tour : temp_tour_list)
                        {
                            cityTours.put(temp_tour.getTourID(),temp_tour);
                        }
                    }

                    ResultSet rs2 = stmt.executeQuery("SELECT Name FROM City_Database WHERE ID = '" + id + "'");
                    if(!rs2.next()) return null;
                    String name=rs2.getString("Name");
                    rs2 = stmt.executeQuery("SELECT Price FROM City_Database WHERE ID = '" + id + "'");
                    if(!rs2.next()) return null;
                    double price=rs2.getDouble("Price");
                    rs2 = stmt.executeQuery("SELECT Version FROM City_Database WHERE ID = '" + id + "'");
                    if(!rs2.next()) return null;
                    int version=rs2.getInt("Version");
                    rs2 = stmt.executeQuery("SELECT LastUpdatedDate FROM City_Database WHERE ID = '" + id + "'");
                    if(!rs2.next()) return null;
                    Date date = rs2.getDate("LastUpdatedDate");
                    cityList.add(new City(Integer.parseInt(id),name,cityMaps, cityTours,price, version,date));
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
                    String id=rs.getString("ID");
                    ResultSet rs2 = stmt.executeQuery("SELECT Coordinate FROM Site_Database WHERE ID = '" + id + "'");
                    if(!rs2.next()) return null;
                    String[] coordinates = rs.getString("Coordinate").split(",");
                    Location local = new Location(Double.parseDouble(coordinates[0]),Double.parseDouble(coordinates[1]));
                    rs2 = stmt.executeQuery("SELECT Duration FROM Site_Database WHERE ID = '" + id + "'");
                    if(!rs2.next()) return null;
                    double duration=rs2.getDouble("Duration");
                    rs2 = stmt.executeQuery("SELECT Name FROM Site_Database WHERE ID = '" + id + "'");
                    if(!rs2.next()) return null;
                    String name=rs2.getString("Name");
                    rs2 = stmt.executeQuery("SELECT Type FROM Site_Database WHERE ID = '" + id + "'");
                    if(!rs2.next()) return null;
                    String type=rs2.getString("Type");
                    rs2 = stmt.executeQuery("SELECT Description FROM Site_Database WHERE ID = '" + id + "'");
                    if(!rs2.next()) return null;
                    String description2=rs2.getString("Description");
                    rs2 = stmt.executeQuery("SELECT Accessibility FROM Site_Database WHERE ID = '" + id + "'");
                    if(!rs2.next()) return null;
                    boolean accessibility=rs2.getBoolean("Accessibility");
                    siteList.add(new Site(Integer.parseInt(id),local,duration,name,type,description2,accessibility));
                }
                else {
                    break;
                }
            }
            return new Catalog(siteList,null,null,cityList);
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

                    for(String m : mapsString)
                    {
                        List<DigitalMap> temp_digitalMap_list = DigitalMapByID(m).getDigitalMaps();
                        if (temp_digitalMap_list != null)
                        {
                            for (DigitalMap temp_map : temp_digitalMap_list)
                            {
                                cityMaps.put(temp_map.getDigitalMapID() ,temp_map);
                            }
                        }
                        else {
                            cityMaps = null;
                        }

                    }

                    for(String t : toursString)
                    {
                        List<Tour> temp_tour_list = TourByID(t).getTours();
                        for (Tour temp_tour : temp_tour_list)
                        {
                            cityTours.put(temp_tour.getTourID(),temp_tour);
                        }
                    }
                    Date date = rs.getDate("LastUpdatedDate");
                    cityList.add(new City(rs.getInt("ID"),rs.getString("Name"),cityMaps,cityTours,rs.getDouble("Price"),rs.getInt("Version"),date));
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

    public static boolean AddPurchase (String ID, String date,String cost,String purchasedType,String purchasedCities, String purchasedMaps,String userID)
    {
        Connection conn = connectToDatabase();
        Statement stmt;
        try
        {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            stmt.executeUpdate("INSERT INTO Purchase_Database VALUES ('" + ID +"','" + date + "','" + cost + "','" + purchasedType + "','" +
                    purchasedCities +"','" + purchasedMaps + "')");
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {}
            }
        } catch (SQLException e) {
            return false;
        }
        try
        {

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("SELECT PurchaseID FROM User_Database WHERE ID = '" + userID + "'");
            String purchaseID=rs.getString("PurchaseID");
            purchaseID=purchaseID+","+ID;
            stmt.executeUpdate("UPDATE User_Database SET PurchaseID = '" + purchaseID + "' WHERE ID='"+ userID +"'");
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {}
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static Object ReturnMemberCardByID(String ID)
    {
        Connection conn = connectToDatabase();
        Statement stmt;
        ResultSet rs = null;
        String name = "";
        MemberCard memberCard = null;
        try
        {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery("SELECT Password FROM User_Database WHERE ID = '" + ID + "'");
            if(!rs.next()){return null;}
            String password=rs.getString("Password");
            rs = stmt.executeQuery("SELECT UserName FROM User_Database WHERE ID = '" + ID + "'");
            if(!rs.next()){return null;}
            String userName=rs.getString("UserName");
            rs = stmt.executeQuery("SELECT PhoneNumber FROM User_Database WHERE ID = '" + ID + "'");
            if(!rs.next()){return null;}
            String phone=rs.getString("PhoneNumber");
            rs = stmt.executeQuery("SELECT Email FROM User_Database WHERE ID = '" + ID + "'");
            if(!rs.next()){return null;}
            String email=rs.getString("Email");
            memberCard = new MemberCard(ID, null, userName, password, phone, email, null,null);
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
}