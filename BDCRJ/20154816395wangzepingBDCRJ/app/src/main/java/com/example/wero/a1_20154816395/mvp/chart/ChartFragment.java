package com.example.wero.a1_20154816395.mvp.chart;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;

import com.blankj.utilcode.util.LogUtils;
import com.example.wero.a1_20154816395.R;
import com.example.wero.a1_20154816395.bean.UserStudyInfoBean;
import com.example.wero.a1_20154816395.mvp.MvpFragment;
import com.example.wero.a1_20154816395.utils.CustomXAxisValueFormatter;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-5-20
 */

public class ChartFragment extends MvpFragment<ChartPresenter> implements ChartContract.View, OnChartValueSelectedListener, SwipeRefreshLayout.OnRefreshListener{
    @BindView(R.id.srefresh_chart) SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.chart) BarChart mChart;

    BarData mBarData;
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_chart;
    }

    @Override
    protected void initView() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.cfore2, R.color.cfore1);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mPresenter.loadUserStudyInfo();
    }

    @Override
    public void showProgress() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    protected ChartPresenter createPresenter() {
        return new ChartPresenter(this);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    @Override
    public void setData(List<UserStudyInfoBean> userStudyInfoBeanList) {
        //排序
        Collections.sort(userStudyInfoBeanList, new Comparator<UserStudyInfoBean>() {
            @Override
            public int compare(UserStudyInfoBean o1, UserStudyInfoBean o2) {
                DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date d1 = dateFormat.parse(o1.getDate());
                    Date d2 = dateFormat.parse(o2.getDate());
                    if(d1.equals(d2)){
                        return 0;
                    }else if(d1.before(d2)){
                        return -1;
                    }else if(d1.after(d2)){
                        return 1;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
        mBarData = generateData(userStudyInfoBeanList);
        List<String> dates = new ArrayList<>();
        mChart.setOnChartValueSelectedListener(this);
        mChart.setDrawBarShadow(false);
        mChart.setDrawValueAboveBar(true);
        mChart.getDescription().setEnabled(false);
        mChart.setDrawGridBackground(false);
        mChart.setHighlightPerTapEnabled(true);
        //设置能否缩放
        mChart.setScaleEnabled(false);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setLabelCount(userStudyInfoBeanList.size());
        xAxis.setGranularity(1);
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAvoidFirstLastClipping(false);//图表将避免第一个和最后一个标签条目被减掉在图表或屏幕的边缘

        for (int i=0; i<userStudyInfoBeanList.size(); i++){
            dates.add(userStudyInfoBeanList.get(i).getDate());
        }
        xAxis.setValueFormatter(new CustomXAxisValueFormatter(dates));

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setDrawAxisLine(false);
        leftAxis.setEnabled(false);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setEnabled(false);
        rightAxis.setDrawAxisLine(false);
        mChart.setData(mBarData);
        mChart.animateY(700);
        mChart.setFitBars(false); //X轴自适应所有柱形图
    }

    private BarData generateData(List<UserStudyInfoBean> userStudyInfoBeanList) {
        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
        for (int i = 0; i < userStudyInfoBeanList.size(); i++) {
            LogUtils.e(userStudyInfoBeanList.get(i).getCount());
            entries.add(new BarEntry(i, userStudyInfoBeanList.get(i).getCount()));
        }
        BarDataSet d = new BarDataSet(entries, "学习进度");
        d.setColors(ColorTemplate.VORDIPLOM_COLORS);
        d.setBarShadowColor(Color.rgb(203, 203, 203));

        ArrayList<IBarDataSet> sets = new ArrayList<IBarDataSet>();
        sets.add(d);
        BarData cd = new BarData(sets);
        cd.setBarWidth(0.4f);
        cd.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return String.valueOf((int)value);
            }
        });
        return cd;
    }

    @Override
    public void onRefresh() {
        mPresenter.loadUserStudyInfo();
    }

    @Override
    public void onResume() {
        mPresenter.loadUserStudyInfo();
        super.onResume();
    }
}
