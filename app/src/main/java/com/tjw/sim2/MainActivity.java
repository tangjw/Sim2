package com.tjw.sim2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void createDialog(View view) {
        final ConfirmDialog dialog = ConfirmDialog.newInstance("将联系人XXX删除,将同时删除与该联系人的聊天记录");
        dialog.show(getSupportFragmentManager(), "dialog1");

        dialog.setListener(new ConfirmDialog.DialogListener() {
            @Override
            public void okClick() {
                Toast.makeText(MainActivity.this, "删除成功!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }

            @Override
            public void noClick() {
                Toast.makeText(MainActivity.this, "取消删除", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
