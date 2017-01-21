package com.tjw.sim2;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * ^-^ Created by tang-jw on 1/21.
 */

public class ConfirmDialog extends DialogFragment {

    private static final String ARG_PARAM1 = "title";
    private static final String ARG_PARAM2 = "info";

    private String mTitle;
    private String mInfo;
    private DialogListener mListener;

    public void setListener(DialogListener listener) {
        mListener = listener;
    }

    public static ConfirmDialog newInstance(@NonNull String msg) {
        ConfirmDialog fragment = new ConfirmDialog();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, null);
        args.putString(ARG_PARAM2, msg);
        fragment.setArguments(args);
        return fragment;
    }

    public static ConfirmDialog newInstance(@NonNull String title, @NonNull String info) {
        ConfirmDialog fragment = new ConfirmDialog();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, title);
        args.putString(ARG_PARAM2, info);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate");
        if (getArguments() != null) {
            mTitle = getArguments().getString(ARG_PARAM1);
            mInfo = getArguments().getString(ARG_PARAM2);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        System.out.println("onCreateDialog");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = View.inflate(getActivity(), R.layout.dialog_confirm, null);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_dialog_title);
        TextView tvInfo = (TextView) view.findViewById(R.id.tv_dialog_info);
        Button btnOk = (Button) view.findViewById(R.id.btn_ok);
        Button btnNo = (Button) view.findViewById(R.id.btn_no);


        tvInfo.setText(mInfo);
        if (TextUtils.isEmpty(mTitle)) {
            tvTitle.setVisibility(View.GONE);
        } else {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(mTitle);
        }

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.okClick();
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
//                mListener.okClick();
            }
        });

        builder.setView(view);

        return builder.create();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("onStart");
        Window window = getDialog().getWindow();
        if (window != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            window.setLayout((int) (dm.widthPixels * 0.76), ViewGroup.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawableResource(android.R.color.transparent);
        }
    }

    public interface DialogListener {
        void okClick();
        void noClick();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        System.out.println("onDismiss");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mListener = null;
    }
}
