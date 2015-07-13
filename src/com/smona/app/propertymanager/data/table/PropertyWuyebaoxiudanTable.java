package com.smona.app.propertymanager.data.table;

public class PropertyWuyebaoxiudanTable extends PropertyAbstractTable {
    private static final String TABLE_NAME = "wuyebaoxiudan";
    public static final String TIME_BAOXIU = "time_baoxiu";
    public static final String TYPE = "type_id";
    public static final String DES_PROBLEM = "des_problem";
    public static final String URL_IMAGE = "url_image";
    public static final String TIME_PAIDAN = "time_paidan";
    public static final String WEIXIUREN = "weixiuren";
    public static final String TIME_WANCHENG = "time_wancheng";
    public static final String WEIXIUJIEGUO = "weixiujieguo";
    public static final String WEIXIUFEIYONG = "weixiufeiyong";
    public static final String PINGJIA_ZHILIANG = "pingjia_zhiliang";
    public static final String PINGJIA_TAIDU = "pingjia_taidu";
    public static final String PINGJIA_JISHIXING = "pingjia_jishixing";
    public static final String PINGJIA_JIAGE = "pingjia_jiage";

    private static volatile PropertyWuyebaoxiudanTable mInanstance = null;

    public PropertyWuyebaoxiudanTable(String tableName) {
        super(TABLE_NAME);
    }

    public synchronized static PropertyWuyebaoxiudanTable getInstance() {
        if (mInanstance == null) {
            mInanstance = new PropertyWuyebaoxiudanTable(TABLE_NAME);
        }
        return mInanstance;
    }

    @Override
    public String createTableSql() {
        return "CREATE TABLE " + TABLE_NAME + "(" + _ID
                + " INTEGER PRIMARY KEY, " + TIME_BAOXIU + TEXT + DOUHAO + TYPE
                + TEXT + DOUHAO + DES_PROBLEM + TEXT + DOUHAO + URL_IMAGE
                + TEXT + DOUHAO + TIME_PAIDAN + TEXT + DOUHAO + WEIXIUREN
                + TEXT + DOUHAO + TIME_WANCHENG + TEXT + DOUHAO + WEIXIUJIEGUO
                + TEXT + DOUHAO + PINGJIA_ZHILIANG + TEXT + DOUHAO
                + PINGJIA_TAIDU + TEXT + DOUHAO + PINGJIA_JISHIXING + TEXT
                + DOUHAO + PINGJIA_JIAGE + TEXT + DOUHAO + WEIXIUFEIYONG + TEXT
                + ")";
    }

    @Override
    public String updateTableSql() {
        return null;
    }
}
