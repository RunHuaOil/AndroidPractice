package com.runhuaoil.androidsqlite;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by RunHua on 2016/10/7.
 */

public class MyProvider extends ContentProvider {

    public static final int BOOK_DIR = 1;
    public static final int BOOK_ITEM = 2;
    public static final int CATEGORY_DIR = 3;
    public static final int CATEGORY_ITEM = 4;
    public static final String AUTHORITY = "com.runhuaoil.androidsqlite.provider";

    private MySQLiteHelper dbHelper;
    private SQLiteDatabase db;
    private static UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "Book", BOOK_DIR);
        uriMatcher.addURI(AUTHORITY, "Book/#", BOOK_ITEM);
        uriMatcher.addURI(AUTHORITY, "CateGory", CATEGORY_DIR);
        uriMatcher.addURI(AUTHORITY, "CateGory/#", CATEGORY_ITEM);
        //到时候外部访问我们内容提供器的URI就是通过上面的UriMatcher来匹配,匹配成功则返回addURI的第三个参数(code).
    }

    @Override
    public boolean onCreate() {
        dbHelper = new MySQLiteHelper(getContext(), "BookStore.db", null, 2);
        //外部访问我们的内容提供时创建数据库，若已经存在则不创建
        return true;
        //返回true，初始化内容提供器成功
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
        db = dbHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                cursor = db.query("Book",projection, selection, selectionArgs,null,null, sortOrder);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                // getPathSegments() 方法会把 AUTHORITY 后面的字段以 "/" 分隔，初始下标为0,1则为 id
                cursor = db.query("Book",projection, "id = ?", new String[]{bookId}, null, null, sortOrder);
                break;
            case CATEGORY_DIR:
                cursor = db.query("Category",projection, selection, selectionArgs,null,null, sortOrder);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                cursor = db.query("Category",projection, "id = ?", new String[]{categoryId},null,null, sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                return "vnd.android.cursor.dir/vnd.com.runhuaoil.androidsqlite.provider.Book";
            case BOOK_ITEM:
                return "vnd.android.cursor.item/vnd.com.runhuaoil.androidsqlite.provider.Book";
            case CATEGORY_DIR:
                return "vnd.android.cursor.dir/vnd.com.runhuaoil.androidsqlite.provider.CateGory";
            case CATEGORY_ITEM:
                return "vnd.android.cursor.item/vnd.com.runhuaoil.androidsqlite.provider.CateGory";
            default:
                break;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        db = dbHelper.getWritableDatabase();
        Uri uriReturn = null;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
            case BOOK_ITEM:
                Long newBookId = db.insert("Book", null, values);
                //the row ID of the newly inserted row, or -1 if an error occurred
                uriReturn = Uri.parse("content://" + AUTHORITY + "/Book/" + newBookId);
                break;
            case CATEGORY_DIR:
            case CATEGORY_ITEM:
                Long newCategoryId = db.insert("Category", null, values);
                //the row ID of the newly inserted row, or -1 if an error occurred
                uriReturn = Uri.parse("content://" + AUTHORITY + "/CateGory/" + newCategoryId);
                break;
            default:
                break;
        }
        return uriReturn;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        db = dbHelper.getWritableDatabase();
        int affectId = 0;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                affectId = db.delete("Book", selection, selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                affectId = db.delete("Book", "id = ?", new String[]{bookId});
                break;
            case CATEGORY_DIR:
                affectId = db.delete("Category", selection, selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                affectId = db.delete("Category", "id = ?", new String[]{categoryId});
                break;
            default:
                break;
        }
        return affectId;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        db = dbHelper.getWritableDatabase();
        int updateRowId = 0;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                updateRowId = db.update("Book", values, selection, selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                updateRowId = db.update("Book", values, "id = ?", new String[]{bookId});
                break;
            case CATEGORY_DIR:
                updateRowId = db.update("Category", values, selection, selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                updateRowId = db.update("Category", values, "id = ?", new String[]{categoryId});
                break;
            default:
                break;
        }
        return updateRowId;
    }
}
