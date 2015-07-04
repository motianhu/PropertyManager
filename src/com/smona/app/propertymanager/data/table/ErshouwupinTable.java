package com.smona.app.propertymanager.data.table;

public class ErshouwupinTable extends AbstractTable {
    private static final String TABLE_NAME = "ershouwupin";

    public static final String TIME_FABU = "time_fabu";
    public static final String TYPE_WUPIN = "type_wupin_id";
    public static final String TYPE_PINPAI = "type_pinpai_id";
    public static final String TYPE_XINJIU = "type_xinjiu_id";
    public static final String DES_WUPIN = "des_wupin";
    public static final String URL_IMAGE = "url_image";
    public static final String LIANXIREN = "lianxiren";
    public static final String LIANXIDIANHUA = "lianxidianhua";

    private static volatile ErshouwupinTable mInanstance = null;

    public ErshouwupinTable(String tableName) {
        super(TABLE_NAME);
    }

    public synchronized static ErshouwupinTable getInstance() {
        if (mInanstance == null) {
            mInanstance = new ErshouwupinTable(TABLE_NAME);
        }
        return mInanstance;
    }

    @Override
    public String createTableSql() {
        return "CREATE TABLE " + TABLE_NAME + "(" + _ID
                + " INTEGER PRIMARY KEY, " + TIME_FABU + TEXT + DOUHAO
                + TYPE_WUPIN + TEXT + DOUHAO + TYPE_PINPAI + TEXT + DOUHAO
                + TYPE_XINJIU + TEXT + DOUHAO + DES_WUPIN + TEXT + DOUHAO
                + URL_IMAGE + TEXT + DOUHAO + LIANXIREN + TEXT + DOUHAO
                + LIANXIDIANHUA + TEXT + ")";
    }

    @Override
    public String updateTableSql() {
        return null;
    }
}