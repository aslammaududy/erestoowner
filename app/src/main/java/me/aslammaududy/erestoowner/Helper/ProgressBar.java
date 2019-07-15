package me.aslammaududy.erestoowner.Helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import me.aslammaududy.erestoowner.R;

public class ProgressBar {
    private static AlertDialog dialog;

    public static void show(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.progressbar, null);
        dialog = new AlertDialog.Builder(context).create();
        dialog.setTitle("Loading...");
        dialog.setView(view);
        dialog.show();
    }

    public static void dissmiss() {
        dialog.dismiss();
    }


}
