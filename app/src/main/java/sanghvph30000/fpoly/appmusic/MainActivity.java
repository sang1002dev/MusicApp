package sanghvph30000.fpoly.appmusic;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageButton start, pause, stop, add;
    EditText tenbaihat;
    SongDAO songDAO;
    ArrayList<Song> listnhac;
    AdapterSong adapterSong;
    boolean check = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycview);
        start = findViewById(R.id.btn_start);
        pause = findViewById(R.id.btn_pause);
        stop = findViewById(R.id.btn_stop);
        add = findViewById(R.id.btn_add);
        tenbaihat = findViewById(R.id.ed_tenbaihat);
        reloaddata();
        Intent intentsong = new Intent(MainActivity.this, ServiceSong.class);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Themnhac();
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check == false) {
                    startService(intentsong);
                    check = true;
                }


            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check == true) {
                    startService(intentsong);
                    check = false;
                }


            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intentsong);
                check = true;

            }
        });
    }

    public void reloaddata() {
        songDAO = new SongDAO(getApplicationContext());
        listnhac = songDAO.GetDSS();
        adapterSong = new AdapterSong(listnhac, MainActivity.this, songDAO, tenbaihat);
        recyclerView.setAdapter(adapterSong);
    }


    public void Themnhac() {
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialog_themnhac);
        EditText tennhac = dialog.findViewById(R.id.dialog_tennhac);
        EditText linknhac = dialog.findViewById(R.id.dialog_linknhac);
        Button themnhac = dialog.findViewById(R.id.dialog_btnthemnhac);
        Button thoatnhac = dialog.findViewById(R.id.dialog_btnthoatnhac);
        dialog.show();
        themnhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String themten = tennhac.getText().toString();
                String themlinknhac = linknhac.getText().toString();
                if (tennhac.length() == 0) {
                    tennhac.requestFocus();
                    tennhac.setError("Không bỏ trống tên nhạc");
                } else if (linknhac.length() == 0) {
                    linknhac.requestFocus();
                    linknhac.setError("Không bỏ trống link nhạc");
                } else {
                    Song themsong = new Song(themten, themlinknhac);
                    if (songDAO.Themsong(themsong) > 0) {
                        Toast.makeText(MainActivity.this, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                        reloaddata();

                    }
                }
            }
        });
        thoatnhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });


    }
}