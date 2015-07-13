package com.smona.app.propertymanager.data.table;

public class PropertyErshouwupinwupinTypeTable extends PropertyAbstractTable {
    private static final String TABLE_NAME = "ershouwupinwupintype";
    public static final String TYPE_ID = "type_id";
    public static final String TYPE_NAME = "type_name";

    private static volatile PropertyErshouwupinwupinTypeTable mInanstance = null;

    public PropertyErshouwupinwupinTypeTable(String tableName) {
        super(TABLE_NAME);
    }

    public synchronized static PropertyErshouwupinwupinTypeTable getInstance() {
        if (mInanstance == null) {
            mInanstance = new PropertyErshouwupinwupinTypeTable(TABLE_NAME);
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
