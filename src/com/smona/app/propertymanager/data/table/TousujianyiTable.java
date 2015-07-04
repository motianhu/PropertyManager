package com.smona.app.propertymanager.data.table;

public class TousujianyiTable extends AbstractTable {
    private static final String TABLE_NAME = "tousujianyi";
    public static final String TIME_TOUSU = "time_tousu";
    public static final String TYPE = "type_id";
    public static final String DES_PROBLEM = "des_problem";
    public static final String URL_IMAGE = "url_image";
    public static final String TIME_FANKUI = "time_fankui";
    public static final String PINGJIA_TAIDU = "pingjia_taidu";
    public static final String PINGJIA_JISHIXING = "pingjia_jishixing";

    private static volatile TousujianyiTable mInanstance = null;

    public TousujianyiTable(String tableName) {
        super(TABLE_NAME);
    }

    public synchronized static TousujianyiTable getInstance() {
        if (mInanstance == null) {
            mInanstance = new TousujianyiTable(TABLE_NAME);
        }
        return mInanstance;
    }

    @Override
    public String createTableSql() {
        return "CREATE TABLE " + TABLE_NAME + "(" + _ID
                + " INTEGER PRIMARY KEY, " + TIME_TOUSU + TEXT + DOUHAO + TYPE
                + TEXT + DOUHAO + DES_PROBLEM + TEXT + DOUHAO + URL_IMAGE
                + TEXT + DOUHAO + TIME_FANKUI + TEXT + DOUHAO + PINGJIA_TAIDU
                + TEXT + DOUHAO + PINGJIA_JISHIXING + TEXT + ")";
    }

    @Override
    public String updateTableSql() {
        return null;
    }
}
