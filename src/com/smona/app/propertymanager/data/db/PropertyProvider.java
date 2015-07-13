package com.smona.app.propertymanager.data.db;

import java.util.ArrayList;
import java.util.HashMap;

import com.smona.app.propertymanager.data.table.AbstractTable;
import com.smona.app.propertymanager.data.table.ErshouwupinTable;
import com.smona.app.propertymanager.data.table.ErshouwupinpinpaiTypeTable;
import com.smona.app.propertymanager.data.table.ErshouwupinwupinTypeTable;
import com.smona.app.propertymanager.data.table.ErshouwupinxinjiuTypeTable;
import com.smona.app.propertymanager.data.table.FangwuzulinfangyuanTable;
import com.smona.app.propertymanager.data.table.FangwuzulinhuxingTypeTable;
import com.smona.app.propertymanager.data.table.FangwuzulinmianjiTypeTable;
import com.smona.app.propertymanager.data.table.TousujianyiTable;
import com.smona.app.propertymanager.data.table.TousujianyiTypeTable;
import com.smona.app.propertymanager.data.table.WuyebaoxiuTypeTable;
import com.smona.app.propertymanager.data.table.WuyebaoxiudanTable;
import com.smona.app.propertymanager.data.table.WuyetongzhiTable;
import com.smona.app.propertymanager.data.table.YezhuxinxiTable;
import com.smona.app.propertymanager.util.PropertyLogUtil;

import android.annotation.SuppressLint;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class PropertyProvider extends ContentProvider {
    private static final String TAG = "PropertyProvider";
    private DatabaseHelper mDataHelper;

    // ywzhuxinxi
    private static final int CODE_BASE_YEZHUXINXI = 0;
    private static final int CODE_YEZHUXINXI = CODE_BASE_YEZHUXINXI + 1;

    // ershouwupin
    private static final int CODE_BASE_ERSHOUWUPIN = 10;
    private static final int CODE_ERSHOUWUPINPINPAITYPE = CODE_BASE_ERSHOUWUPIN + 1;
    private static final int CODE_ERSHOUWUPIN = CODE_BASE_ERSHOUWUPIN + 2;
    private static final int CODE_ERSHOUWUPINWUPINTYPE = CODE_BASE_ERSHOUWUPIN + 3;
    private static final int CODE_ERSHOUWUPINXINJIUTYPE = CODE_BASE_ERSHOUWUPIN + 4;

    // fangwuzulin
    private static final int CODE_BASE_FANGWUZULIN = 20;
    private static final int CODE_FANGWUZULINFANGYUAN = CODE_BASE_FANGWUZULIN + 1;
    private static final int CODE_FANGWUZULINHUXING = CODE_BASE_FANGWUZULIN + 2;
    private static final int CODE_FANGWUZULINMIANJI = CODE_BASE_FANGWUZULIN + 3;

    // tousujianyi
    private static final int CODE_BASE_TOUSU = 30;
    private static final int CODE_TOUSUJIANYI = CODE_BASE_TOUSU + 1;
    private static final int CODE_TOUSUJIANYITYPE = CODE_BASE_TOUSU + 2;

    // wuyebaoxiu
    private static final int CODE_BASE_WUYEBAOXIU = 40;
    private static final int CODE_WUYEBAOXIUDAN = CODE_BASE_WUYEBAOXIU + 1;
    private static final int CODE_WUYEBAOXIUTYPE = CODE_BASE_WUYEBAOXIU + 2;

    // tongzhi
    private static final int CODE_BASE_TONGZHI = 50;
    private static final int CODE_TONGZHI = CODE_BASE_TONGZHI + 1;

    private static final UriMatcher URI_MATCH = new UriMatcher(
            UriMatcher.NO_MATCH);

    @SuppressLint("UseSparseArrays")
    private static HashMap<Integer, String> TABLE_MATCH = new HashMap<Integer, String>();

    static {
        // yezhuxinxi
        URI_MATCH.addURI(AbstractTable.AUTHORITY,
                YezhuxinxiTable.getInstance().mTableName, CODE_YEZHUXINXI);
        TABLE_MATCH.put(CODE_YEZHUXINXI,
                YezhuxinxiTable.getInstance().mTableName);

        // ershouwupin
        URI_MATCH.addURI(AbstractTable.AUTHORITY,
                ErshouwupinpinpaiTypeTable.getInstance().mTableName,
                CODE_ERSHOUWUPINPINPAITYPE);
        URI_MATCH.addURI(AbstractTable.AUTHORITY,
                ErshouwupinTable.getInstance().mTableName, CODE_ERSHOUWUPIN);
        URI_MATCH.addURI(AbstractTable.AUTHORITY,
                ErshouwupinwupinTypeTable.getInstance().mTableName,
                CODE_ERSHOUWUPINWUPINTYPE);
        URI_MATCH.addURI(AbstractTable.AUTHORITY,
                ErshouwupinxinjiuTypeTable.getInstance().mTableName,
                CODE_ERSHOUWUPINXINJIUTYPE);

        TABLE_MATCH.put(CODE_ERSHOUWUPINPINPAITYPE,
                ErshouwupinpinpaiTypeTable.getInstance().mTableName);
        TABLE_MATCH.put(CODE_ERSHOUWUPIN,
                ErshouwupinTable.getInstance().mTableName);
        TABLE_MATCH.put(CODE_ERSHOUWUPINWUPINTYPE,
                ErshouwupinwupinTypeTable.getInstance().mTableName);
        TABLE_MATCH.put(CODE_ERSHOUWUPINXINJIUTYPE,
                ErshouwupinxinjiuTypeTable.getInstance().mTableName);

        // fangwuzulin
        URI_MATCH.addURI(AbstractTable.AUTHORITY,
                FangwuzulinfangyuanTable.getInstance().mTableName,
                CODE_FANGWUZULINFANGYUAN);
        URI_MATCH.addURI(AbstractTable.AUTHORITY,
                FangwuzulinhuxingTypeTable.getInstance().mTableName,
                CODE_FANGWUZULINHUXING);
        URI_MATCH.addURI(AbstractTable.AUTHORITY,
                FangwuzulinmianjiTypeTable.getInstance().mTableName,
                CODE_FANGWUZULINMIANJI);

        TABLE_MATCH.put(CODE_FANGWUZULINFANGYUAN,
                FangwuzulinfangyuanTable.getInstance().mTableName);
        TABLE_MATCH.put(CODE_FANGWUZULINHUXING,
                FangwuzulinhuxingTypeTable.getInstance().mTableName);
        TABLE_MATCH.put(CODE_FANGWUZULINMIANJI,
                FangwuzulinmianjiTypeTable.getInstance().mTableName);

        // tousujianyi
        URI_MATCH.addURI(AbstractTable.AUTHORITY,
                TousujianyiTable.getInstance().mTableName, CODE_TOUSUJIANYI);
        URI_MATCH.addURI(AbstractTable.AUTHORITY,
                TousujianyiTypeTable.getInstance().mTableName,
                CODE_TOUSUJIANYITYPE);

        TABLE_MATCH.put(CODE_TOUSUJIANYI,
                TousujianyiTable.getInstance().mTableName);
        TABLE_MATCH.put(CODE_TOUSUJIANYITYPE,
                TousujianyiTypeTable.getInstance().mTableName);

        // wuyebaoxiu
        URI_MATCH
                .addURI(AbstractTable.AUTHORITY,
                        WuyebaoxiudanTable.getInstance().mTableName,
                        CODE_WUYEBAOXIUDAN);
        URI_MATCH.addURI(AbstractTable.AUTHORITY,
                WuyebaoxiuTypeTable.getInstance().mTableName,
                CODE_WUYEBAOXIUTYPE);

        TABLE_MATCH.put(CODE_WUYEBAOXIUDAN,
                WuyebaoxiudanTable.getInstance().mTableName);
        TABLE_MATCH.put(CODE_WUYEBAOXIUTYPE,
                WuyebaoxiuTypeTable.getInstance().mTableName);

        // wuyetongzhi
        URI_MATCH.addURI(AbstractTable.AUTHORITY,
                WuyetongzhiTable.getInstance().mTableName, CODE_TONGZHI);

        TABLE_MATCH
                .put(CODE_TONGZHI, WuyetongzhiTable.getInstance().mTableName);
    }

    @Override
    public boolean onCreate() {
        PropertyLogUtil.d(TAG, "onCreate");
        mDataHelper = new DatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
            String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        int match = URI_MATCH.match(uri);
        String tableName = TABLE_MATCH.get(match);
        qb.setTables(tableName);

        SQLiteDatabase db = mDataHelper.getReadableDatabase();
        return qb.query(db, projection, selection, selectionArgs, null, null,
                sortOrder);
    }

    @Override
    public String getType(Uri uri) {
        int match = URI_MATCH.match(uri);
        String tableName = TABLE_MATCH.get(match);
        PropertyLogUtil.d(TAG, "getType uri: " + uri + ";tableName: " + tableName);
        if (tableName != null) {
            return "vnd.android.cursor.dir/wallpaper";
        } else {
            throw new IllegalArgumentException("Unknown URL");
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int match = URI_MATCH.match(uri);
        SQLiteDatabase db = mDataHelper.getWritableDatabase();
        String tableName = TABLE_MATCH.get(match);
        long rowId = db.insert(tableName, null, values);
        Uri newUrl = null;
        if (rowId != -1) {
            newUrl = ContentUris.withAppendedId(uri, rowId);
            getContext().getContentResolver().notifyChange(newUrl, null);
        }
        return newUrl;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mDataHelper.getWritableDatabase();

        int count = -1;
        int matchValue = URI_MATCH.match(uri);
        String tableName = TABLE_MATCH.get(matchValue);
        count = db.delete(tableName, selection, selectionArgs);

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    public void batchInserts(Uri uri, ArrayList<ContentValues> list) {
        int matchValue = URI_MATCH.match(uri);
        String tableName = TABLE_MATCH.get(matchValue);
        SQLiteDatabase db = mDataHelper.getWritableDatabase();
        db.beginTransaction();
        for (ContentValues v : list) {
            db.insert(tableName, null, v);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void batchUpdatesOrInserts(Uri uri, ArrayList<ContentValues> list,
            String whereColumn) {
        int matchValue = URI_MATCH.match(uri);
        String tableName = TABLE_MATCH.get(matchValue);
        SQLiteDatabase db = mDataHelper.getWritableDatabase();
        db.beginTransaction();
        for (ContentValues v : list) {
            String whereClause = whereColumn + "=?";
            String[] whereArgs = new String[] { ""
                    + v.getAsInteger(whereColumn) };
            int count = db.update(tableName, v, whereClause, whereArgs);
            if (count <= 0) {
                db.insert(tableName, null, v);
            }
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void batchUpdateLocals(Uri uri, ArrayList<ContentValues> list,
            String whereColumn) {
        int matchValue = URI_MATCH.match(uri);
        String tableName = TABLE_MATCH.get(matchValue);
        SQLiteDatabase db = mDataHelper.getWritableDatabase();
        db.beginTransaction();
        String[] whereArgs;
        for (ContentValues v : list) {
            String whereClause = whereColumn + "=?";
            String where = v.getAsString(whereColumn);
            whereArgs = new String[] { where };
            int count = db.update(tableName, v, whereClause, whereArgs);
            if (count <= 0) {
                db.insert(tableName, null, v);
            }
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
            String[] selectionArgs) {
        SQLiteDatabase db = mDataHelper.getWritableDatabase();

        int count = -1;
        int match = URI_MATCH.match(uri);
        String tableName = TABLE_MATCH.get(match);
        count = db.update(tableName, values, selection, selectionArgs);

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    public void updateOrInsert(Uri uri, ContentValues values, String selection,
            String[] selectionArgs) {
        int count = -1;
        count = update(uri, values, selection, selectionArgs);
        PropertyLogUtil.d(TAG, "count = " + count);
        if (count <= 0) {
            insert(uri, values);
        }
    }

    static class DatabaseHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "property.db";
        private static final int DATABASE_VERSION = 2;

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            PropertyLogUtil.d(TAG, "DatabaseHelper onCreate");
            ArrayList<String> sqlList = getCreateTableSqlList();
            for (String sql : sqlList) {
                db.execSQL(sql);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (oldVersion >= newVersion) {
                return;
            }
            PropertyLogUtil.d(TAG, "DatabaseHelper onUpgrade oldVersion: " + oldVersion
                    + ", newVersion: " + newVersion);

            ArrayList<String> dropList = getDropTableSqlList();
            for (String sql : dropList) {
                db.execSQL(sql);
            }

            onCreate(db);
        }

        private ArrayList<String> getCreateTableSqlList() {
            ArrayList<String> sqlList = new ArrayList<String>();
            // yezhuxinxi
            String yezhuxinxi = YezhuxinxiTable.getInstance().createTableSql();

            // ershouwupin
            String pinpaiType = ErshouwupinpinpaiTypeTable.getInstance()
                    .createTableSql();
            String ershouwupin = ErshouwupinTable.getInstance()
                    .createTableSql();
            String wupinType = ErshouwupinwupinTypeTable.getInstance()
                    .createTableSql();
            String xinjiuType = ErshouwupinxinjiuTypeTable.getInstance()
                    .createTableSql();

            // fangwuzulin
            String fangyuan = FangwuzulinfangyuanTable.getInstance()
                    .createTableSql();
            String huxing = FangwuzulinhuxingTypeTable.getInstance()
                    .createTableSql();
            String mianji = FangwuzulinmianjiTypeTable.getInstance()
                    .createTableSql();

            // tousujianyi
            String tousu = TousujianyiTable.getInstance().createTableSql();
            String tousuType = TousujianyiTypeTable.getInstance()
                    .createTableSql();

            // wuyebaoxiu
            String baoxiudan = WuyebaoxiudanTable.getInstance()
                    .createTableSql();
            String baoxiudanType = WuyebaoxiuTypeTable.getInstance()
                    .createTableSql();

            // wuyetongzhi
            String tongzhi = WuyetongzhiTable.getInstance().createTableSql();

            sqlList.add(yezhuxinxi);
            sqlList.add(pinpaiType);
            sqlList.add(ershouwupin);
            sqlList.add(wupinType);
            sqlList.add(xinjiuType);
            sqlList.add(fangyuan);
            sqlList.add(huxing);
            sqlList.add(mianji);
            sqlList.add(tousu);
            sqlList.add(tousuType);

            sqlList.add(baoxiudan);
            sqlList.add(baoxiudanType);
            sqlList.add(tongzhi);
            return sqlList;
        }

        private ArrayList<String> getDropTableSqlList() {
            ArrayList<String> sqlList = new ArrayList<String>();
            // yezhuxinxi
            String yezhuxinxi = YezhuxinxiTable.getInstance().createTableSql();

            // ershouwupin
            String pinpaiType = ErshouwupinpinpaiTypeTable.getInstance()
                    .dropTableSql();
            String ershouwupin = ErshouwupinTable.getInstance().dropTableSql();
            String wupinType = ErshouwupinwupinTypeTable.getInstance()
                    .dropTableSql();
            String xinjiuType = ErshouwupinxinjiuTypeTable.getInstance()
                    .dropTableSql();

            // fangwuzulin
            String fangyuan = FangwuzulinfangyuanTable.getInstance()
                    .dropTableSql();
            String huxing = FangwuzulinhuxingTypeTable.getInstance()
                    .dropTableSql();
            String mianji = FangwuzulinmianjiTypeTable.getInstance()
                    .dropTableSql();

            // tousujianyi
            String tousu = TousujianyiTable.getInstance().dropTableSql();
            String tousuType = TousujianyiTypeTable.getInstance()
                    .dropTableSql();

            // wuyebaoxiu
            String baoxiudan = WuyebaoxiudanTable.getInstance().dropTableSql();
            String baoxiudanType = WuyebaoxiuTypeTable.getInstance()
                    .dropTableSql();

            // wuyetongzhi
            String tongzhi = WuyetongzhiTable.getInstance().dropTableSql();

            sqlList.add(yezhuxinxi);
            sqlList.add(pinpaiType);
            sqlList.add(ershouwupin);
            sqlList.add(wupinType);
            sqlList.add(xinjiuType);
            sqlList.add(fangyuan);
            sqlList.add(huxing);
            sqlList.add(mianji);
            sqlList.add(tousu);
            sqlList.add(tousuType);

            sqlList.add(baoxiudan);
            sqlList.add(baoxiudanType);
            sqlList.add(tongzhi);
            return sqlList;
        }
    }
}