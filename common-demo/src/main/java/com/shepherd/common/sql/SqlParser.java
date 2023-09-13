package com.shepherd.common.sql;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2023/4/3 17:58
 */
public class SqlParser {
    public static void main(String[] args) throws JSQLParserException {
        Statement stmt = CCJSqlParserUtil.parse("SELECT * FROM tab1");
        int i = 1;
    }
}
