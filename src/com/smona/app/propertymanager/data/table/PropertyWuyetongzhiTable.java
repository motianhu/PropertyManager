package com.smona.app.propertymanager.data.table;

public class PropertyWuyetongzhiTable extends PropertyAbstractTable {
    private static final String TABLE_NAME = "wuyetongzhi";
    public static final String TIME_FABU = "time_fabu";
    public static final String MINGCHEN = "mingchen";
    public static final String NEIRONG = "neirong";

    private static volatile PropertyWuyetongzhiTable mInanstance = null;

    public PropertyWuyetongzhiTable(String tableName) {
        super(TABLE_NAME);
    }

    public synchronized static PropertyWuyetongzhiTable getInstance() {
        if (mInanstance == null) {
            mInanstance = new PropertyWuyetongzhiTable(TABLE_NAME);
        }
        return mInanstance;
    }

    @Override
    public String createTableSql() {
        return "CREATE TABLE " + TABLE_NAME + "(" + _ID
                + " INTEGER PRIMARY KEY, " + TIME_FABU + TEXT + DOUHAO
                + NEIRONG + TEXT + DOUHAO + MINGCHEN + TEXT + ")";
    }

    @Override
    public String updateTableSql() {
        return null;
    }
}
