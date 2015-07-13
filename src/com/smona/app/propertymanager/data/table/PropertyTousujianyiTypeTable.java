package com.smona.app.propertymanager.data.table;

public class PropertyTousujianyiTypeTable extends PropertyAbstractTable {
    private static final String TABLE_NAME = "tousujianyitype";
    public static final String TYPE_ID = "type_id";
    public static final String TYPE_NAME = "type_name";

    private static volatile PropertyTousujianyiTypeTable mInanstance = null;

    public PropertyTousujianyiTypeTable(String tableName) {
        super(TABLE_NAME);
    }

    public synchronized static PropertyTousujianyiTypeTable getInstance() {
        if (mInanstance == null) {
            mInanstance = new PropertyTousujianyiTypeTable(TABLE_NAME);
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
