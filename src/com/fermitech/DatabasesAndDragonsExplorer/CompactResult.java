package com.fermitech.DatabasesAndDragonsExplorer;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
/**
 * This is the CompactResult class, used to unify in a single entity data and metadata of a query..
 */
public class CompactResult {
    public  ResultSet dati;
    public  ResultSetMetaData metaData;
    /**
     * This is the CompactResult constructor.
     *
     * @param dati the data of the query.
     * @param metaData the metadata of the query.
     */
    public CompactResult(ResultSet dati, ResultSetMetaData metaData){
        this.dati = dati;
        this.metaData = metaData;
    }
}
