package com.smona.app.propertymanager.data.table;

public class PropertyWuyebaoxiuTypeTable extends PropertyAbstractTable {

    private static final String TABLE_NAME = "wuyebaoxiu_type";
    public static final String TYPE_ID = "repair_id";
    public static final String TYPE_NAME = "repair_name";

    private static volatile PropertyWuyebaoxiuTypeTable mInanstance = null;

    public PropertyWuyebaoxiuTypeTable(String tableName) {
        super(TABLE_NAME);
    }

    public synchronized static PropertyWuyebaoxiuTypeTable getInstance() {
        if (mInanstance == null) {
            mInanstance = new PropertyWuyebaoxiuTypeTable(TABLE_NAME);
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
