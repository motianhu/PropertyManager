package com.smona.app.propertymanager.data.table;

public class PropertyYezhuxinxiTable extends PropertyAbstractTable {
    private static final String TABLE_NAME = "yezhuxinxi";
    public static final String YEZHU_NAME = "name";
    public static final String YEZHU_TEL = "tel";
    public static final String YEZHU_SHEQU_URL = "shequ_url";
    public static final String YEZHU_ADDRESS = "address";

    private static volatile PropertyYezhuxinxiTable mInanstance = null;

    public PropertyYezhuxinxiTable(String tableName) {
        super(TABLE_NAME);
    }

    public synchronized static PropertyYezhuxinxiTable getInstance() {
        if (mInanstance == null) {
            mInanstance = new PropertyYezhuxinxiTable(TABLE_NAME);
        }
        return mInanstance;
    }

    @Override
    public String createTableSql() {
        return "CREATE TABLE " + TABLE_NAME + "(" + _ID
                + " INTEGER PRIMARY KEY, " + YEZHU_NAME + TEXT + DOUHAO
                + YEZHU_SHEQU_URL + TEXT + DOUHAO + YEZHU_ADDRESS + TEXT
                + DOUHAO + YEZHU_TEL + TEXT + ")";
    }

    @Override
    public String updateTableSql() {
        return null;
    }
}