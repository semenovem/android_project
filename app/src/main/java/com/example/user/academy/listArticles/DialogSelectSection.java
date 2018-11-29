package com.example.user.academy.listArticles;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.user.academy.R;
import com.example.user.academy.repo.RepoArticle;

public class DialogSelectSection extends DialogFragment {

    RepoArticle repo = RepoArticle.getInstance();

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        System.out.println("___ ++ ____" + repo.getSelectedArticleId());

        builder.setTitle("Select `section`")
            .setSingleChoiceItems(R.array.sections, 0,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        repo.setSelectedArticleId(which);
                    }
                })

            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    System.out.println(repo.getSelectedArticleId());
                    // FIRE ZE MISSILES!
                }
            })
            .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog

                    System.out.println("_ok");

                }
            });

        return builder.create();
    }
}