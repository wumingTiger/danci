package com.example.wero.a1_20154816395.mvp.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.wero.a1_20154816395.R;
import com.example.wero.a1_20154816395.mvp.MvpActivity;
import com.example.wero.a1_20154816395.mvp.nomvpactivity.MainActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wero on 18-4-23.
 */

public class LoginActivity extends MvpActivity<LoginPresenter> implements LoginContract.View, View.OnClickListener{
    @BindView(R.id.et_username) public EditText et_username;
    @BindView(R.id.et_password) public EditText et_password;
    @BindView(R.id.btn_reg) public Button btn_reg;
    @BindView(R.id.btn_log) public Button btn_log;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public void loginSuccess() {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void loginFail(String failMsg) {
        et_password.setText("");
        et_username.setFocusable(true);
        et_password.setError("用户名或密码错误");
    }

    @Override
    public void registerFail(String failMsg) {
        et_password.setText("");
        et_username.setText("");
        et_username.setFocusable(true);
        et_password.setError("用户已存在");
    }

    @Override
    public void showProgress() {
        mProgressDialog.show();
    }

    @Override
    public void hideProgress() {
        mProgressDialog.dismiss();
    }

    @Override
    protected void initView() {
        btn_log.setOnClickListener(this);
        btn_reg.setOnClickListener(this);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_login;
    }

    @Override
    public void onClick(View v) {
        showProgress();
        switch (v.getId()){
            case R.id.btn_log:
                mPresenter.login(et_username.getText().toString(), et_password.getText().toString());
                break;
            case R.id.btn_reg:
                mPresenter.register(et_username.getText().toString(), et_password.getText().toString());
                break;
        }
    }
}
