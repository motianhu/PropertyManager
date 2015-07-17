package com.smona.app.propertymanager.data.table;

public class PropertyCustomerTable extends PropertyAbstractTable {
    private static final String TABLE_NAME = "customer";
    public static final String CUSTOMER_NAME = "username";
    public static final String CUSTOMER_PHONE = "userphone";
    public static final String CUSTOMER_PICTURE = "pictureurl";
    public static final String CUSTOMER_ADDRESS = "useraddress";
    public static final String PROPERTY_PHONE = "propertyphone";

    private static volatile PropertyCustomerTable mInanstance = null;

    public PropertyCustomerTable(String tableName) {
        super(TABLE_NAME);
    }

    public synchronized static PropertyCustomerTable getInstance() {
        if (mInanstance == null) {
            mInanstance = new PropertyCustomerTable(TABLE_NAME);
        }
        return mInanstance;
    }

    @Override
    public String createTableSql() {
        return "CREATE TABLE " + TABLE_NAME + "(" + _ID
                + " INTEGER PRIMARY KEY, " + CUSTOMER_NAME + TEXT + DOUHAO
                + CUSTOMER_PHONE + TEXT + DOUHAO + CUSTOMER_PICTURE + TEXT
                + DOUHAO + PROPERTY_PHONE + TEXT + DOUHAO + CUSTOMER_ADDRESS
                + TEXT + ")";
    }

    @Override
    public String updateTableSql() {
        return null;
    }
}
