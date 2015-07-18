package com.smona.app.propertymanager.data.table;

public class PropertyFangwuzulinyewuTypeTable extends PropertyAbstractTable {
    private static final String TABLE_NAME = "fangwuzulinyewutype";
    public static final String TYPE_ID = "type_code";
    public static final String TYPE_NAME = "type_name";

    private static volatile PropertyFangwuzulinyewuTypeTable mInanstance = null;

    public PropertyFangwuzulinyewuTypeTable(String tableName) {
        super(TABLE_NAME);
    }

    public synchronized static PropertyFangwuzulinyewuTypeTable getInstance() {
        if (mInanstance == null) {
            mInanstance = new PropertyFangwuzulinyewuTypeTable(TABLE_NAME);
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
