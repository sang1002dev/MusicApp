package sanghvph30000.fpoly.appmusic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class SongDAO {
    DBHelper dbHelper;

    public SongDAO(Context context) {
        dbHelper = new DBHelper(context);
    }
    public ArrayList<Song> GetDSS(){
          ArrayList<Song> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM nhac",null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                Song song = new Song();
                song.setId(Integer.parseInt(cursor.getString(0)));
                song.setTen(cursor.getString(1));
                song.setLink(cursor.getString(2));
                list.add(song);
            }while (cursor.moveToNext());
        }
        return list;
    }
    public long Themsong(Song song){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tennhac",song.getTen());
        contentValues.put("linknhac",song.getLink());

        return sqLiteDatabase.insert("nhac",null,contentValues);
    }

}
