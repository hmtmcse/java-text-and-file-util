package com.hmtmcse.jtfutil.parser;

import java.net.URI;

public class JDBCUrlParser {

    static class DatabaseConnectionData {
        public String databaseName;
        public String username;
        public String password;
        public String host;
        public String database;
        public Integer port;
    }


    public static DatabaseConnectionData parse(String url) {
        DatabaseConnectionData databaseConnectionData = new DatabaseConnectionData();
        String cleanURI = url.substring(5);
        URI uri = URI.create(cleanURI);
        databaseConnectionData.databaseName = uri.getScheme();
        databaseConnectionData.host = uri.getHost();
        databaseConnectionData.port = uri.getPort() > 1 ? uri.getPort() : null;
        if (uri.getPath() != null){
            String[] paths = uri.getPath().split("/");
            if (paths.length >= 2){
                databaseConnectionData.database =   paths[1].trim();
            }
        }
        return databaseConnectionData;
    }

    public static void main(String[] args) {
        DatabaseConnectionData databaseConnectionData = parse("jdbc:mysql://localhost/touhid?useUnicode=yes&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        System.out.println("Test");
    }

}
