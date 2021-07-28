package com.example.moviealarm;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

//import com.example.listviewsample2.R;

public class MakeTimeDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        //ダイアログビルダーを生成
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //ダイアログのタイトルを設定
        builder.setTitle(R.string.dialog_title);
        // EditTextを設定
        final EditText et_url = new EditText(getActivity());
        et_url.setHint("YoutubeURL貼り付け");
        builder.setView(et_url);
        //Positive Buttonを設定
        builder.setPositiveButton(R.string.dialog_btn_ok,new DialogButtonClickListener(){
            //押した時にEditTextのなかみをToastで表示
            @Override
            public void onClick(DialogInterface dialog,int which){
                String msg = et_url.getText().toString();
                Toast.makeText(getActivity(),msg,Toast.LENGTH_LONG).show();
            }
        });
        //Negative Buttonを設定
        builder.setNegativeButton(R.string.dialog_btn_ng,new DialogButtonClickListener());
        //Neutral Buttonを設定
        builder.setNeutralButton(R.string.dialog_btn_nu,new DialogButtonClickListener());
        //ダイアログオブジェクトを生成しリターン
        AlertDialog dialog = builder.create();
        return dialog;
    }

    private class DialogButtonClickListener implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialog,int which){
            String msg ="";
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    //dialogクラス内に記述。
                        //<補足>dialogでstringをreturn出来ないので。キャスト変換も出来なかった。
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    msg = getString(R.string.dialog_ng_toast);
                    break;
                case DialogInterface.BUTTON_NEUTRAL:
                    msg = getString(R.string.dialog_nu_toast);
                    break;
            }
            Toast.makeText(getActivity(),msg,Toast.LENGTH_LONG).show();
        }
    }

}
