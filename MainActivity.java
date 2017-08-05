package kuliah.datastorage;

import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    Button simpan;
    Button baca;
    RadioButton shared;
    RadioButton files;
    RadioButton sql;
    TextView hasil;
    EditText input;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        simpan = (Button)findViewById(R.id.button);
        baca = (Button)findViewById(R.id.button2);
        shared = (RadioButton)findViewById(R.id.radioButton5);
        files = (RadioButton)findViewById(R.id.radioButton4);
        sql = (RadioButton)findViewById(R.id.radioButton3);
        hasil = (TextView)findViewById(R.id.textView);
        input = (EditText) findViewById(R.id.editText);
    }

    public void simpan(View v){
        data = input.getText().toString();
        if(shared.isSelected()) {

            SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(getString(R.string.saved_data), data);
            editor.commit();

        }else if(files.isSelected()){

            String filename = getString(R.string.saved_data);
            String string = data;
            FileOutputStream outputStream;

            try {
                outputStream.write(string.getBytes());
                File file = new File(this.getFilesDir(), filename);
                outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else{

        }
    }

    public void ambil(View v){
        data = "";
        if(shared.isSelected()) {

            String defaultValue = getResources().getString(R.string.saved_data_default);
            SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
            data = sharedPref.getString(getString(R.string.saved_data), defaultValue);

        } else if(files.isSelected()) {

            BufferedReader input = null;
            File file = null;
            try {
                file = new File(this.getFilesDir(), getString(R.string.saved_data));

                input = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                StringBuffer buffer = new StringBuffer();
                String line;

                while ((line = input.readLine()) != null) {
                    buffer.append(line);
                }

                data = buffer.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{

        }
        hasil.setText(data);
    }
}
