package sanghvph30000.fpoly.appmusic;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterSong extends RecyclerView.Adapter<AdapterSong.ViewHolder> {
    ArrayList<Song> listsong;
    Context context;
    SongDAO songDAO;
    ServiceSong serviceSong;
    TextView title;

    public AdapterSong(ArrayList<Song> listsong, Context context, SongDAO songDAO, TextView title) {
        this.listsong = listsong;
        this.context = context;
        this.songDAO = songDAO;
        this.title = title;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ImageView;
        TextView item_tennhac,TextViewArtistTitle;
        RelativeLayout itemnhac;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_tennhac = itemView.findViewById(R.id.item_tennhac);
            itemnhac = itemView.findViewById(R.id.itemnhac);
            TextViewArtistTitle = itemView.findViewById(R.id.TextViewArtistTitle);
            ImageView = itemView.findViewById(R.id.ImageView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_nhac, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.item_tennhac.setText(listsong.get(position).getTen());
        holder.itemnhac.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Showdata(context, listsong.get(position));
                return false;
            }
        });
        holder.itemnhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chuyenten = listsong.get(position).getTen();
                title.setText(chuyenten);

                Intent chuyenlink = new Intent(context,ServiceSong.class);
                chuyenlink.putExtra("linkne",listsong.get(position).getLink());
                context.startService(chuyenlink);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listsong.size();
    }

    public void Showdata(Context context, Song song) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Thông Tin Bài Nhạc");
        builder.setMessage("Tên Bài Nhạc:" + song.getTen() + "\n Link Nhạc:" + song.getLink());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        builder.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
    }


}
