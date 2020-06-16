package PaooGame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Levels {

    public enum Level{
        level_1, level_2, level_3, level_4, level_5
    }


    public static String GetLevelWorld(Level level){
        switch(level){
            case level_1:
                return  getLevelPath(1);
            case level_2:
                return getLevelPath(2);
            case level_3:
                return getLevelPath(3);
            case level_4:
                return getLevelPath(4);
            case level_5:
            default:
                return getLevelPath(5);
        }
    }
    public static String GetLevelMusic(Level level){
        switch(level){
            case level_1:
                return  getAudioPath(1);
            case level_2:
                return getAudioPath(2);
            case level_3:
                return getAudioPath(3);
            case level_4:
                return getAudioPath(4);
            case level_5:
            default:
                return getAudioPath(5);
        }
    }



    public static String getLevelPath(int level){
        Connection c = null;
        Statement stmt = null;
        String sql = null;
        String path = "";
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:data.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
//            sql = "CREATE TABLE LevelPath " +
//                    "(Level INT," +
//                    " Background TEXT )";
//            stmt.executeUpdate(sql);
//            sql = "INSERT INTO LevelPath (Level, Background) " +
//                    "VALUES (1, '/level/level.png');";
//            stmt.executeUpdate(sql);

//           sql = "ALTER TABLE LevelPath" +" ADD COLUMN Sound TXT; ";
//            stmt.executeUpdate(sql);
//
//            sql = "INSERT INTO LevelPath (Sound) " +
//                    "VALUES ('/sound/platformer_level03_loop.wav');";
//            stmt.executeUpdate(sql);

            ResultSet rs = stmt.executeQuery( "SELECT * FROM LevelPath WHERE Level=" + level + ";" );
            path = rs.getString("Background");

            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return path;
    }
    public static String getAudioPath(int level){
        Connection c = null;
        Statement stmt = null;
        String path = "";
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:data.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM LevelPath WHERE Level=" + level + ";" );
            path = rs.getString("Sound");
            System.out.println(path);

            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return path;
    }

}
