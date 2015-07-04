package com.smona.app.propertymanager.data.table;

public class ErshouwupinpinpaiTypeTable extends AbstractTable {
    private static final String TABLE_NAME = "ershouwupinpinpaitype";
    public static final String TYPE_ID = "type_id";
    public static final String TYPE_NAME = "type_name";

    private static volatile ErshouwupinpinpaiTypeTable mInanstance = null;

    public ErshouwupinpinpaiTypeTable(String tableName) {
        super(TABLE_NAME);
    }

    public synchronized static ErshouwupinpinpaiTypeTable getInstance() {
        if (mInanstance == null) {
            mInanstance = new ErshouwupinpinpaiTypeTable(TABLE_NAME);
        }
        return mInanstance;
    }

    @Override
    public String createTableSql() {
        return "CREATE TABLE " + TABLE_NAME + "(" + _ID
                + " INTEGER PRIMARY KEY, " + TYPE_ID + " INTEGER , "
                + TYPE_NAME + " TEXT " + ")";
    }

    @Override
    public String updateTableSql() {
        return null;
    }
}
