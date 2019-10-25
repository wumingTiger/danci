package com.example.wero.a1_20154816395.mvp.chart;

import com.example.wero.a1_20154816395.bean.UserStudyInfoBean;
import com.example.wero.a1_20154816395.mvp.IPresenter;
import com.example.wero.a1_20154816395.mvp.IView;

import java.util.List;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-5-17
 */

public interface ChartContract {
    public interface Presenter extends IPresenter{
        void loadUserStudyInfo();
    }

    public interface View extends IView{
        void setData(List<UserStudyInfoBean> userStudyInfoBean);
    }
}
