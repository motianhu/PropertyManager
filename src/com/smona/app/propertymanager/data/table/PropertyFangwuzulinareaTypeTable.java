package com.smona.app.propertymanager.data.table;

public class PropertyFangwuzulinareaTypeTable extends PropertyAbstractTable {
    private static final String TABLE_NAME = "fangwuzulinmianjitype";
    public static final String TYPE_ID = "area_code";
    public static final String TYPE_NAME = "area_name";

    private static volatile PropertyFangwuzulinareaTypeTable mInanstance = null;

    public PropertyFangwuzulinareaTypeTable(String tableName) {
        super(TABLE_NAME);
    }

    public synchronized static PropertyFangwuzulinareaTypeTable getInstance() {
        if (mInanstance == null) {
            mInanstance = new PropertyFangwuzulinareaTypeTable(TABLE_NAME);
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
