package src.TierCraft.Minigame.SkyGiant.plugin.utils;

import java.util.*;
import org.bukkit.entity.*;
import java.sql.*;

public class MySQL
{
    private String host;
    private int port;
    private String database;
    private String user;
    private String password;
    private Connection conn;
    
    private String getHost() {
        return this.host;
    }
    
    private int getPort() {
        return this.port;
    }
    
    private String getDataBase() {
        return this.database;
    }
    
    private String getUser() {
        return this.user;
    }
    
    private String getPassword() {
        return this.password;
    }
    
    private Connection getConnection() {
        return this.conn;
    }
    
    private void setHost(final String host) {
        this.host = host;
    }
    
    private void setPort(final int port) {
        this.port = port;
    }
    
    private void setDataBase(final String database) {
        this.database = database;
    }
    
    private void setUser(final String user) {
        this.user = user;
    }
    
    private void setPassword(final String password) {
        this.password = password;
    }
    
    private void setConnection(final Connection conn) {
        this.conn = conn;
    }
    
    public MySQL(final String host, final int port, final String dataBase, final String user, final String password) {
        this.setHost(host);
        this.setPort(port);
        this.setDataBase(dataBase);
        this.setUser(user);
        this.setPassword(password);
    }
    
    public boolean createTable(final String s, final ArrayList<String> list) {
        this.openConnection();
        if (this.isConnected()) {
            try {
                int n = 1;
                String s2 = "";
                for (final String s3 : list) {
                    if (list.size() == 1) {
                        s2 += s3;
                    }
                    else if (n != 0) {
                        s2 += s3;
                        n = 0;
                    }
                    else {
                        s2 = s2 + ", " + s3;
                    }
                }
                this.getConnection().createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS `" + s + "` (" + s2 + ");");
                return true;
            }
            catch (SQLException ex) {
                this.printError(ex.getMessage());
            }
        }
        this.closeConnection();
        return false;
    }
    
    public Object getScore(final String s, final String s2, final Player player) {
        if (!this.isConnected()) {
            this.connect();
        }
        if (this.isConnected()) {
            try {
                final ResultSet executeQuery = this.getConnection().createStatement().executeQuery("SELECT * FROM `" + s + "` WHERE `" + "UUID" + "`='" + player.getUniqueId().toString() + "';");
                if (executeQuery.next()) {
                    return executeQuery.getObject(s2);
                }
            }
            catch (SQLException ex) {}
            try {
                final ResultSet executeQuery2 = this.getConnection().createStatement().executeQuery("SELECT * FROM `" + s + "` WHERE `" + "Player" + "`='" + player.getName() + "';");
                if (executeQuery2.next()) {
                    return executeQuery2.getObject(s2);
                }
                return 0;
            }
            catch (SQLException ex2) {
                return 0;
            }
        }
        return 0;
    }
    
    public boolean deleteData(final String s, final String s2, final String s3, final String s4) {
        this.openConnection();
        if (this.isConnected()) {
            try {
                this.getConnection().createStatement().executeUpdate("DELETE FROM `" + s + "` WHERE `" + s2 + "`" + s3 + "'" + s4 + "';");
                return true;
            }
            catch (SQLException ex) {
                this.printError(ex.getMessage());
            }
        }
        this.closeConnection();
        return false;
    }
    
    public boolean isDataExists(final String s, final String s2, final String s3) {
        this.openConnection();
        if (this.isConnected()) {
            try {
                if (this.getConnection().createStatement().executeQuery("SELECT * FROM `" + s + "` WHERE `" + s2 + "`='" + s3 + "';").next()) {
                    return true;
                }
            }
            catch (SQLException ex) {
                this.printError(ex.getMessage());
            }
        }
        this.closeConnection();
        return false;
    }
    
    public void connect() {
        this.openConnection();
    }
    
    public void close() {
        this.closeConnection();
    }
    
    public boolean setData(final String s, final ArrayList<String> list, final ArrayList<String> list2) {
        this.openConnection();
        if (this.isConnected() && list.size() == list2.size()) {
            try {
                int n = 1;
                int n2 = 0;
                String s2 = "";
                for (final String s3 : list) {
                    if (n != 0) {
                        s2 = s2 + "`" + s3 + "`='" + list2.get(n2) + "'";
                        n = 0;
                    }
                    else {
                        s2 = s2 + ", `" + s3 + "`='" + list2.get(n2) + "'";
                    }
                    ++n2;
                }
                this.getConnection().createStatement().executeUpdate("UPDATE `" + s + "` SET " + s2 + ";");
                return true;
            }
            catch (SQLException ex) {
                this.printError(ex.getMessage());
            }
        }
        this.closeConnection();
        return false;
    }
    
    public boolean createDate(final String s, final ArrayList<String> list, final ArrayList<String> list2) {
        this.openConnection();
        if (this.isConnected()) {
            try {
                int n = 1;
                int n2 = 1;
                String s2 = "";
                String s3 = "";
                for (final String s4 : list) {
                    if (list.size() == 1) {
                        s3 = s3 + "`" + s4 + "`";
                    }
                    else if (n != 0) {
                        s3 = s3 + "`" + s4 + "`";
                        n = 0;
                    }
                    else {
                        s3 = s3 + ", `" + s4 + "`";
                    }
                }
                for (final String s5 : list2) {
                    if (list2.size() == 1) {
                        s2 = s2 + "'" + s5 + "'";
                    }
                    else if (n2 != 0) {
                        s2 = s2 + "'" + s5 + "'";
                        n2 = 0;
                    }
                    else {
                        s2 = s2 + ", '" + s5 + "'";
                    }
                }
                this.getConnection().createStatement().executeUpdate("INSERT INTO `" + s + "` (" + s3 + ") VALUES (" + s2 + ");");
                return true;
            }
            catch (SQLException ex) {
                this.printError(ex.getMessage());
            }
        }
        this.closeConnection();
        return false;
    }
    
    public boolean setData(final String s, final ArrayList<String> list, final ArrayList<String> list2, final String s2, final String s3, final String s4) {
        this.openConnection();
        if (this.isConnected() && list.size() == list2.size()) {
            try {
                int n = 1;
                int n2 = 0;
                String s5 = "";
                for (final String s6 : list) {
                    if (n != 0) {
                        s5 = s5 + "`" + s6 + "`='" + list2.get(n2) + "'";
                        n = 0;
                    }
                    else {
                        s5 = s5 + ", `" + s6 + "`='" + list2.get(n2) + "'";
                    }
                    ++n2;
                }
                this.getConnection().createStatement().executeUpdate("UPDATE `" + s + "` SET " + s5 + " WHERE `" + s2 + "`" + s3 + "'" + s4 + "';");
                return true;
            }
            catch (SQLException ex) {
                this.printError(ex.getMessage());
            }
        }
        this.closeConnection();
        return false;
    }
    
    public boolean execute(final String s) {
        this.openConnection();
        if (this.isConnected()) {
            try {
                this.getConnection().createStatement().executeUpdate(s);
                return true;
            }
            catch (SQLException ex) {
                this.printError(ex.getMessage());
            }
        }
        this.closeConnection();
        return false;
    }
    
    private void printError(final String s) {
        System.out.println(s);
    }
    
    private boolean openConnection() {
        if (!this.isConnected()) {
            try {
                this.setConnection(DriverManager.getConnection("jdbc:mysql://" + this.getHost() + ":" + this.getPort() + "/" + this.getDataBase() + "?user=" + this.getUser() + "&password=" + this.getPassword()));
            }
            catch (SQLException ex) {
                this.printError(ex.getMessage());
                return false;
            }
        }
        return true;
    }
    
    private boolean closeConnection() {
        if (this.isConnected()) {
            try {
                this.getConnection().close();
            }
            catch (SQLException ex) {
                this.printError(ex.getMessage());
                return false;
            }
        }
        return true;
    }
    
    public boolean isConnected() {
        try {
            if (this.getConnection() == null) {
                return false;
            }
            if (this.getConnection().isClosed()) {
                return false;
            }
        }
        catch (SQLException ex) {
            this.printError(ex.getMessage());
        }
        return true;
    }
}
