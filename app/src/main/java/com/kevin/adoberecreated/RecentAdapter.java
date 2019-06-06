package com.kevin.adoberecreated;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecentAdapter extends RecyclerView.Adapter<RecentAdapter.ViewHolder> {

    ArrayList<RecentModel> recentModelArrayList;
    Context context;
    FragmentManager fragmentManager;

    public RecentAdapter(ArrayList<RecentModel> recentModelArrayList, Context context) {
        this.recentModelArrayList = recentModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recent_recyclerview_item, viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final RecentModel recentModel = recentModelArrayList.get(i);

        viewHolder.fileName.setText(recentModel.fileName);
        viewHolder.fileType.setText(recentModel.fileType);
        viewHolder.fileOpened.setText(recentModel.fileOpened);
        viewHolder.fileSize.setText(recentModel.fileSize);

        Glide.with(context).load(recentModel.fileThumbnailUrl).into(viewHolder.fileThumbnail);

        viewHolder.optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View bottomSheetFileView = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_file_layout, null);

                ImageView fileThumbnail = bottomSheetFileView.findViewById(R.id.file_thumbnail_imageview);
                TextView fileName = bottomSheetFileView.findViewById(R.id.file_name_textview);
                TextView fileType = bottomSheetFileView.findViewById(R.id.file_type_textview);
                TextView fileOpened = bottomSheetFileView.findViewById(R.id.file_opened_textview);
                TextView fileSize = bottomSheetFileView.findViewById(R.id.file_size_textview);

                Glide.with(context).load(recentModel.fileThumbnailUrl).into(fileThumbnail);
                fileName.setText(recentModel.fileName);
                fileType.setText(recentModel.fileType);
                fileOpened.setText(recentModel.fileOpened);
                fileSize.setText(recentModel.fileSize);

                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
                bottomSheetDialog.setContentView(bottomSheetFileView);
                bottomSheetDialog.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return recentModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView fileThumbnail;
        TextView fileName;
        TextView fileType;
        TextView fileOpened;
        TextView fileSize;
        ImageButton optionsButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fileThumbnail = itemView.findViewById(R.id.file_thumbnail_imageview);
            fileName = itemView.findViewById(R.id.file_name_textview);
            fileType = itemView.findViewById(R.id.file_type_textview);
            fileOpened = itemView.findViewById(R.id.file_opened_textview);
            fileSize = itemView.findViewById(R.id.file_size_textview);
            optionsButton = itemView.findViewById(R.id.options_button);
        }
    }


}
