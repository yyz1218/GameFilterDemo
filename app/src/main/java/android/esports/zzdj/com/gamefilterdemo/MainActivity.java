package android.esports.zzdj.com.gamefilterdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView zz_filter_lv;
    private FilterGameListViewAdapter filterGameListViewAdapter;
    //最外层数据
    private ArrayList<FilterTypeBean> filterTypeBeanList;
    //内层数据
    private ArrayList<FilterItemBean> filterItemBeanList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        zz_filter_lv = (ListView) findViewById(R.id.zz_filter_lv);
    }
    private void initData() {
        filterTypeBeanList = new ArrayList<FilterTypeBean>();
        if(GlobalParams.isDoFilterGame==false){//第一次筛选信息
            //生成最外层的数据
            for(int i=0;i<4;i++){
                filterItemBeanList = new ArrayList<FilterItemBean>();
                FilterTypeBean filterTypeBean = new FilterTypeBean();
                //此字段可以采用boolean型，由于自己需要，自己设置成String型
                filterTypeBean.setIsExpand("false");
                filterTypeBean.setTitle("英雄联盟"+i);
                filterTypeBean.setType("radio");
                filterTypeBean.setValue(i+"");
                filterTypeBean.setName("赛事的Name"+i);
                //生成内层的数据
                for(int j=0;j<5;j++){
                    FilterItemBean filterItemBean = new FilterItemBean();
                    filterItemBean.setIsCheck("false");
                    filterItemBean.setTitle("娱乐"+j);
                    filterItemBean.setValue(j+"");
                    filterItemBeanList.add(filterItemBean);
                }
                filterTypeBean.setConfig(filterItemBeanList);
                filterTypeBeanList.add(filterTypeBean);
            }
        }
        GlobalParams.filter_type = filterTypeBeanList;
        filterGameListViewAdapter = new FilterGameListViewAdapter(MainActivity.this
                , GlobalParams.filter_type);
        zz_filter_lv.setAdapter(filterGameListViewAdapter);

    }
}
