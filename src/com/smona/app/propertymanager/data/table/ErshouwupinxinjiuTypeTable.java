package com.smona.app.propertymanager.data.table;

public class ErshouwupinxinjiuTypeTable extends AbstractTable {
    private static final String TABLE_NAME = "ershouwupinxinjiutype";
    public static final String TYPE_ID = "type_id";
    public static final String TYPE_NAME = "type_name";

    private static volatile ErshouwupinxinjiuTypeTable mInanstance = null;

    public ErshouwupinxinjiuTypeTable(String tableName) {
        super(TABLE_NAME);
    }

    public synchronized static ErshouwupinxinjiuTypeTable getInstance() {
        if (mInanstance == null) {
            mInanstance = new ErshouwupinxinjiuTypeTable(TABLE_NAME);
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