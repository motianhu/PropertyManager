package com.smona.app.propertymanager.data.table;

public class FangwuzulinmianjiTypeTable extends AbstractTable {
    private static final String TABLE_NAME = "fangwuzulinmianjitype";
    public static final String TYPE_ID = "type_id";
    public static final String TYPE_NAME = "type_name";

    private static volatile FangwuzulinmianjiTypeTable mInanstance = null;

    public FangwuzulinmianjiTypeTable(String tableName) {
        super(TABLE_NAME);
    }

    public synchronized static FangwuzulinmianjiTypeTable getInstance() {
        if (mInanstance == null) {
            mInanstance = new FangwuzulinmianjiTypeTable(TABLE_NAME);
        }
        return mInanstance;
    }

    @Override
    public String createTableSql() {
        return "CREATE TABLE " + TABLE_NAME + "(" + _ID
                + " INTEGER PRIMARY KEY, " + TYPE_ID + TEXT + DOUHAO
                + TYPE_NAME + TEXT + ")";
    }

    @Override
    public String updateTableSql() {
        return null;
    }
}
