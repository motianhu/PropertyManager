package com.smona.app.propertymanager.data.table;

public class FangwuzulinfangyuanTable extends AbstractTable {
    private static final String TABLE_NAME = "fangwuzulinfangyuan";
    public static final String TIME_FABU = "time_fabu";
    public static final String TYPE_YEWU = "type_yewu_id";
    public static final String TYPE_HUXING = "type_huxing_id";
    public static final String TYPE_MIANJI = "type_mianji_id";
    public static final String DES_PEITAO = "des_peitao";
    public static final String WEIZHI = "weizhi";
    public static final String URL_IMAGE = "url_image";
    public static final String LIANXIREN = "lianxiren";
    public static final String LIANXIDIANHUA = "lianxidianhua";

    private static volatile FangwuzulinfangyuanTable mInanstance = null;

    public FangwuzulinfangyuanTable(String tableName) {
        super(TABLE_NAME);
    }

    public synchronized static FangwuzulinfangyuanTable getInstance() {
        if (mInanstance == null) {
            mInanstance = new FangwuzulinfangyuanTable(TABLE_NAME);
        }
        return mInanstance;
    }

    @Override
    public String createTableSql() {
        return "CREATE TABLE " + TABLE_NAME + "(" + _ID
                + " INTEGER PRIMARY KEY, " + TIME_FABU + TEXT + DOUHAO
                + TYPE_YEWU + TEXT + DOUHAO + TYPE_HUXING + TEXT + DOUHAO
                + TYPE_MIANJI + TEXT + DOUHAO + DES_PEITAO + TEXT + DOUHAO
                + WEIZHI + TEXT + DOUHAO + URL_IMAGE + TEXT + DOUHAO
                + LIANXIREN + TEXT + DOUHAO + LIANXIDIANHUA + TEXT + ")";
    }

    @Override
    public String updateTableSql() {
        return null;
    }
}
