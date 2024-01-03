package ru.gb.Homeworks.Homework4;

import java.lang.reflect.*;
import java.sql.*;

public class ProviderJDBC {
    private String url;
    private String user;
    private String password;

    public static String typeToSQLtype(Object o) {
        String result = null;
        if (o.equals(Boolean.class)) result = "BIT(1)";
        if (o.equals(Byte.class)) result = "TINYINT";
        if (o.equals(Short.class)) result = "SMALLINT";
        if (o.equals(Integer.class)) result = "INT";
        if (o.equals(Long.class)) result = "BIGINT";
        if (o.equals(Float.class)) result = "FLOAT";
        if (o.equals(Double.class)) result = "DOUBLE";
        if (o.equals(String.class)) result = "VARCHAR(255)";
        return result;
    }


    private Connection _connection;

    public ProviderJDBC(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public void connect() {
        try {
            _connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection created " + url + "/" + user + "/");
        } catch (SQLException e) {
            System.out.println("Connection fault: " + e.getMessage());
        }
    }

    public void disconnect() {
        if (_connection != null) {
            try {
                _connection.close();
                System.out.println("Disconnection " + url);
            } catch (SQLException e) {
                System.out.println("Disconnection fault: " + e.getMessage());
            }
        }
    }


    public void createDB(String name) {
        if (_connection != null && name != null) {
            try {
                Statement tempStatement = _connection.createStatement();
                tempStatement.execute("DROP SCHEMA IF EXISTS " + name);
                tempStatement.execute("CREATE SCHEMA " + name);
                System.out.println("DB create: " + name + " created");
            } catch (SQLException e) {
                System.out.println("DB create: " + e.getMessage());
            }
        }
    }

    public void deleteDB(String name) {
        if (_connection != null && name != null) {
            try {
                Statement tempStatement = _connection.createStatement();
                tempStatement.execute("DROP SCHEMA IF EXISTS " + name);
                System.out.println("DB delete: " + name + " deleted");
            } catch (SQLException e) {
                System.out.println("DB delete: " + e.getMessage());
            }
        }
    }

    public void createTable(String DB, String table, Class object) {
        if (_connection != null) {
            if (table == null) table = object.getName();
            try {
                Statement tempStatement = _connection.createStatement();
                tempStatement.execute("USE " + DB);
                tempStatement.execute("DROP TABLE IF EXISTS " + table);
                StringBuilder tempExecute = new StringBuilder();
                tempExecute.append("CREATE TABLE " + table + "(");
                tempExecute.append("id INT NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT");
                Field[] fields = object.getDeclaredFields();
                for (Field field : fields) {
                    if (typeToSQLtype(field.getAnnotatedType().getType()) != null) {
                        tempExecute.append("," + field.getName() + " " + typeToSQLtype(field.getAnnotatedType().getType()));
                    }
                }
                tempExecute.append(", objectSignature VARCHAR(30) NOT NULL UNIQUE");
                tempExecute.append(")");
                tempStatement.execute(tempExecute.toString());
                System.out.println("Table create: " + table + " created");
            } catch (SQLException e) {
                System.out.println("Table create: " + e.getMessage());
            }
        }
    }

    public void deleteTable(String DB, String table) {
        if (_connection != null && table != null) {
            try {
                Statement tempStatement = _connection.createStatement();
                tempStatement.execute("USE " + DB);
                tempStatement.execute("DROP TABLE IF EXISTS " + table);
                System.out.println("Table drop: " + table + " deleted");
            } catch (SQLException e) {
                System.out.println("Table drop: " + e.getMessage());
            }
        }
    }

    public void insertIntoTable(String DB, String table, Object values) {
        if (_connection != null && table != null) {
            try {
                Statement tempStatement = _connection.createStatement();
                tempStatement.execute("USE " + DB);
                StringBuilder tempExecute = new StringBuilder();
                tempExecute.append("INSERT INTO " + table + "(");
                Field[] fields = values.getClass().getDeclaredFields();
                for (Field field : fields) {
                    if (typeToSQLtype(field.getAnnotatedType().getType()) != null) {
                        tempExecute.append(field.getName() + ", ");
                    }
                }
                tempExecute.delete(tempExecute.length() - 2, tempExecute.length());
                tempExecute.append(", objectSignature)");
                tempExecute.append(" VALUES (");
                for (Field field : fields) {

                    if (typeToSQLtype(field.getAnnotatedType().getType()) != null) {
                        tempExecute.append("'" + field.get(values).toString() + "'" + ", ");
                    }
                }
                tempExecute.append("'" + values.toString() + "')");
                tempStatement.execute(tempExecute.toString());
                System.out.println("Table insert: " + table + " new row inserted");
            } catch (SQLException | IllegalAccessException e) {
                System.out.println("Table insert: " + e.getMessage());
            }
        }
    }

    public void deleteIntoTable(String DB, String table, Object values) {
        if (_connection != null && table != null) {
            try {
                Statement tempStatement = _connection.createStatement();
                tempStatement.execute("USE " + DB);
                StringBuilder tempExecute = new StringBuilder();
                tempExecute.append("DELETE FROM " + table + " WHERE objectSignature = '" + values.toString() + "'");
                tempStatement.execute(tempExecute.toString());
                System.out.println("Table delete: " + table + " row deleted");
            } catch (SQLException e) {
                System.out.println("Table delete: " + e.getMessage());
            }
        }
    }

    public void updateIntoTable(String DB, String table, Object values) {
        if (_connection != null && table != null) {
            try {
                Statement tempStatement = _connection.createStatement();
                tempStatement.execute("USE " + DB);
                StringBuilder tempExecute = new StringBuilder();
                tempExecute.append("UPDATE " + table + " SET ");

                Field[] fields = values.getClass().getDeclaredFields();

                for (Field field : fields) {

                    if (typeToSQLtype(field.getAnnotatedType().getType()) != null) {
                        tempExecute.append(field.getName() + " = " + "'" + field.get(values).toString() + "'" + ", ");
                    }
                }
                tempExecute.delete(tempExecute.length() - 2, tempExecute.length());

                tempExecute.append(" WHERE objectSignature = '" + values.toString() + "'");
                tempStatement.execute(tempExecute.toString());
                System.out.println("Table update: " + table + " row updated");
            } catch (SQLException | IllegalAccessException e) {
                System.out.println("Table update: " + e.getMessage());
            }
        }
    }

    public void uploadFromTable(String DB, String table, Object values) {
        if (_connection != null && table != null) {
            try {
                Statement tempStatement = _connection.createStatement();
                tempStatement.execute("USE " + DB);
                StringBuilder tempExecute = new StringBuilder();
                tempExecute.append("SELECT * FROM " + table + " WHERE objectSignature = '" + values.toString() + "'");
                ResultSet set = tempStatement.executeQuery(tempExecute.toString());
                Method[] methods = values.getClass().getMethods();

                for (Method method : methods) {
                    for (int i = 1; i <= set.getMetaData().getColumnCount(); i++) {
                        if (method.getName().toLowerCase().equals("set" + set.getMetaData().getColumnName(i))) {
                            if (set.getMetaData().getColumnClassName(i).getClass().equals(Boolean.class)) {
                                if (set.next()) {
                                    method.invoke(values, set.getBoolean(i));
                                }
                            }
                            if (set.getMetaData().getColumnClassName(i).getClass().equals(Byte.class)) {
                                if (set.next()) {
                                    method.invoke(values, set.getByte(i));
                                }
                            }
                            if (set.getMetaData().getColumnClassName(i).getClass().equals(Short.class)) {
                                if (set.next()) {
                                    method.invoke(values, set.getShort(i));
                                }
                            }
                            if (set.getMetaData().getColumnClassName(i).getClass().equals(Integer.class)) {
                                if (set.next()) {
                                    method.invoke(values, set.getInt(i));
                                }
                            }
                            if (set.getMetaData().getColumnClassName(i).getClass().equals(Long.class)) {
                                if (set.next()) {
                                    method.invoke(values, set.getLong(i));
                                }
                            }
                            if (set.getMetaData().getColumnClassName(i).getClass().equals(Float.class)) {
                                if (set.next()) {
                                    method.invoke(values, set.getFloat(i));
                                }
                            }
                            if (set.getMetaData().getColumnClassName(i).getClass().equals(Double.class)) {
                                if (set.next()) {
                                    method.invoke(values, set.getDouble(i));
                                }
                            }
                            if (set.getMetaData().getColumnClassName(i).getClass().equals(String.class)) {
                                if (set.next()) {
                                    method.invoke(values, set.getString(i));
                                }

                            }

                            System.out.println("Table find: " + table + " row found");
                        }
                    }
                }
            } catch (SQLException | IllegalAccessException | InvocationTargetException e) {
                System.out.println("Table find: " + e.getMessage());
            }
        }
    }

    public Object findIntoTable(String DB, String table, Object values) {
        if (_connection != null && table != null) {
            try {
                Statement tempStatement = _connection.createStatement();
                tempStatement.execute("USE " + DB);
                StringBuilder tempExecute = new StringBuilder();
                tempExecute.append("SELECT * FROM " + table + " WHERE objectSignature = '" + values.toString() + "'");
                ResultSet set = tempStatement.executeQuery(tempExecute.toString());
                Method[] methods = values.getClass().getMethods();

                for (Method method : methods) {
                    for (int i = 1; i <= set.getMetaData().getColumnCount(); i++) {
                        if (method.getName().toLowerCase().equals("set" + set.getMetaData().getColumnName(i))) {
                            if (set.getMetaData().getColumnClassName(i).getClass().equals(Boolean.class)) {
                                if (set.next()) {
                                    method.invoke(values, set.getBoolean(i));
                                }
                            }
                            if (set.getMetaData().getColumnClassName(i).getClass().equals(Byte.class)) {
                                if (set.next()) {
                                    method.invoke(values, set.getByte(i));
                                }
                            }
                            if (set.getMetaData().getColumnClassName(i).getClass().equals(Short.class)) {
                                if (set.next()) {
                                    method.invoke(values, set.getShort(i));
                                }
                            }
                            if (set.getMetaData().getColumnClassName(i).getClass().equals(Integer.class)) {
                                if (set.next()) {
                                    method.invoke(values, set.getInt(i));
                                }
                            }
                            if (set.getMetaData().getColumnClassName(i).getClass().equals(Long.class)) {
                                if (set.next()) {
                                    method.invoke(values, set.getLong(i));
                                }
                            }
                            if (set.getMetaData().getColumnClassName(i).getClass().equals(Float.class)) {
                                if (set.next()) {
                                    method.invoke(values, set.getFloat(i));
                                }
                            }
                            if (set.getMetaData().getColumnClassName(i).getClass().equals(Double.class)) {
                                if (set.next()) {
                                    method.invoke(values, set.getDouble(i));
                                }
                            }
                            if (set.getMetaData().getColumnClassName(i).getClass().equals(String.class)) {
                                if (set.next()) {
                                    method.invoke(values, set.getString(i));
                                }

                            }

                            System.out.println("Table find: " + table + " row found");
                        }
                    }
                }
            } catch (SQLException | IllegalAccessException | InvocationTargetException e) {
                System.out.println("Table find: " + e.getMessage());
            }
        }
        return values;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public Connection get_connection() {
        return _connection;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
