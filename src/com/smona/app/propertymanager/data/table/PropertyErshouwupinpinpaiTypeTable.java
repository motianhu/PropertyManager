package com.smona.app.propertymanager.data.table;

public class PropertyErshouwupinpinpaiTypeTable extends PropertyAbstractTable {
    private static final String TABLE_NAME = "ershouwupinpinpaitype";
    public static final String TYPE_ID = "type_id";
    public static final String TYPE_NAME = "type_name";

    private static volatile PropertyErshouwupinpinpaiTypeTable mInanstance = null;

    public PropertyErshouwupinpinpaiTypeTable(String tableName) {
        super(TABLE_NAME);
    }

    public synchronized static PropertyErshouwupinpinpaiTypeTable getInstance() {
        if (mInanstance == null) {
            mInanstance = new PropertyErshouwupinpinpaiTypeTable(TABLE_NAME);
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
