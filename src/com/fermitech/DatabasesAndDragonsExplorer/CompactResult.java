package com.fermitech.DatabasesAndDragonsExplorer;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class CompactResult {
    public  ResultSet dati;
    public  ResultSetMetaData metaData;
    public CompactResult(ResultSet dati, ResultSetMetaData metaData){
        this.dati = dati;
        this.metaData = metaData;
    }
}
