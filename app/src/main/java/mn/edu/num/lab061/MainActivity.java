package mn.edu.num.lab061;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    public static final String AUTHORITY = "mn.edu.num.zoloo.NameProvider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/names");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(CONTENT_URI, null, null, null, null);
        cursor.moveToFirst();
        do{
            Log.i("cursor", cursor.getString(0) +": "+ cursor.getString(1));
        }
        while (cursor.moveToNext());

        // Insert into Content provider
        ContentValues values = new ContentValues();
        values.put("name", "Jargal");
        resolver.insert(CONTENT_URI, values);
        // Update on Content provider
        resolver.update(CONTENT_URI, values, "id=? and name=?", new String[]{"1", "zoloo"} );
        // Delete from Content provider
        resolver.delete(CONTENT_URI, "id=?", new String[]{"5"});
    }
}