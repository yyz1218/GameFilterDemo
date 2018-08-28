package android.esports.zzdj.com.gamefilterdemo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;

public class FilterGameListViewAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<FilterTypeBean> filterTypeBeanList;
    private ArrayList<FilterItemBean> filterItemBeanList;
    private ArrayList<FilterItemBean> filterFirstItemBeanList;
    private LayoutInflater inflater;
    public FilterGameListViewAdapter(Context context, ArrayList<FilterTypeBean> filterTypeBeanList ) {
        this.context = context;
        this.filterTypeBeanList = filterTypeBeanList;
        inflater = LayoutInflater.from(context);
    }

    public void updateFilterListViewAdapter(Context context,ArrayList<FilterTypeBean> filterTypeBeanList){
        this.context = context;
        this.filterTypeBeanList = filterTypeBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (filterTypeBeanList != null){
            return filterTypeBeanList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return filterTypeBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.activity_filter_game_listitem,parent,false);
            vh = new ViewHolder();
            vh.zz_filter_title_tv = (TextView) convertView.findViewById(R.id.zz_filter_title_tv);
            vh.zz_filter_starttime_tv = (TextView) convertView.findViewById(R.id.zz_filter_starttime_tv);
            vh.zz_filter_endtime_tv = (TextView) convertView.findViewById(R.id.zz_filter_endtime_tv);
            vh.zz_expand_iv = (ImageView) convertView.findViewById(R.id.zz_expand_iv);
            vh.zz_filter_child_ll = (LinearLayout) convertView.findViewById(R.id.zz_filter_child_ll);
            vh.zz_filter_gamestart_ll = (LinearLayout) convertView.findViewById(R.id.zz_filter_gamestart_ll);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        final FilterTypeBean filterTypeBean= filterTypeBeanList.get(position);
        filterItemBeanList = filterTypeBeanList.get(position).getConfig();
        filterFirstItemBeanList = new ArrayList<FilterItemBean>();
        vh.zz_filter_title_tv.setText(filterTypeBean.getTitle());

        if(position == (filterTypeBeanList.size()-1)){
            vh.zz_filter_gamestart_ll.setVisibility(View.VISIBLE);
            if(!StringUtil.isEmpty(filterTypeBean.getStartTime())){

                vh.zz_filter_starttime_tv.setText(filterTypeBean.getStartTime());
            }else{
                vh.zz_filter_starttime_tv.setText("开始时间 00月/00日");
            }
            if(!StringUtil.isEmpty(filterTypeBean.getEndTime())){
                vh.zz_filter_endtime_tv.setText(filterTypeBean.getEndTime());
            }else{
                vh.zz_filter_endtime_tv.setText("结束时间 00月/00日");
            }
        }else{
            vh.zz_filter_gamestart_ll.setVisibility(View.GONE);
        }

        if(filterTypeBean.getIsExpand().equals("true")){
            vh.zz_expand_iv.setBackgroundResource(R.drawable.selector_index_filter_collapse);
        }else{
            vh.zz_expand_iv.setBackgroundResource(R.drawable.selector_index_filter_expand);
        }

        if(filterTypeBeanList.get(position).getConfig().size()<=3){//大于三时候 扩展箭头显示，否则隐藏
            vh.zz_expand_iv.setVisibility(View.INVISIBLE);
        }else{
            vh.zz_expand_iv.setVisibility(View.VISIBLE);
        }

        int size =0;
        if(filterTypeBean.getIsExpand().equals("false")){//如果是第一次进入赛选界面 或者伸展状态
            filterFirstItemBeanList.clear();
            for(int i=0;i<filterItemBeanList.size();i++){
                if(i<3){
                    filterFirstItemBeanList.add(filterTypeBeanList.get(position).getConfig().get(i));
                }
            }
        }else{
            filterItemBeanList = filterTypeBeanList.get(position).getConfig();
        }
        //如果是折叠状态
        if(filterTypeBean.getIsExpand().equals("false")){
            if(filterFirstItemBeanList.size()%3==0){
                size = filterFirstItemBeanList.size()/3;
            }else{
                size = filterFirstItemBeanList.size()/3 +1;
            }
        }else{//如果伸展状态
            if(filterItemBeanList.size()%3==0){
                size = filterItemBeanList.size()/3;
            }else{
                size = filterItemBeanList.size()/3 +1;
            }
        }

        vh.zz_filter_child_ll.removeAllViews();
        for(int i=0;i<size;i++){
            View viewOne = inflater.inflate(R.layout.activity_filter_game_childitem,null);
            final RelativeLayout zz_filter_one_rl = (RelativeLayout) viewOne.findViewById(R.id.zz_filter_one_rl);
            final RelativeLayout zz_filter_two_rl = (RelativeLayout) viewOne.findViewById(R.id.zz_filter_two_rl);
            final RelativeLayout zz_filter_three_rl = (RelativeLayout) viewOne.findViewById(R.id.zz_filter_three_rl);
            TextView tv1 = (TextView) viewOne.findViewById(R.id.tv1);
            TextView tv2 = (TextView) viewOne.findViewById(R.id.tv2);
            TextView tv3 = (TextView) viewOne.findViewById(R.id.tv3);
            //最后一排
            if(i==size-1){
                int j;
                if(filterTypeBean.getIsExpand().equals("false")){
                   j = filterFirstItemBeanList.size()%3;
                }else{
                   j = filterItemBeanList.size()%3;
                }
                 switch (j){
                     case 0://0 最后一排有三个
                         zz_filter_one_rl.setVisibility(View.VISIBLE);
                         zz_filter_two_rl.setVisibility(View.VISIBLE);
                         zz_filter_three_rl.setVisibility(View.VISIBLE);
                         if(filterItemBeanList.get(i*3).getIsCheck().equals("true")){
                             zz_filter_one_rl.setBackgroundResource(R.drawable.filter_item_click_bg);
                         }else{
                             zz_filter_one_rl.setBackgroundResource(R.drawable.filter_item_noclick_bg);
                         }
                         if(filterItemBeanList.get(i*3+1).getIsCheck().equals("true")){
                             zz_filter_two_rl.setBackgroundResource(R.drawable.filter_item_click_bg);
                         }else{
                             zz_filter_two_rl.setBackgroundResource(R.drawable.filter_item_noclick_bg);
                         }
                         if(filterItemBeanList.get(i*3+2).getIsCheck().equals("true")){
                             zz_filter_three_rl.setBackgroundResource(R.drawable.filter_item_click_bg);
                         }else{
                             zz_filter_three_rl.setBackgroundResource(R.drawable.filter_item_noclick_bg);
                         }
                         tv1.setText(filterItemBeanList.get(i*3).getTitle());
                         tv2.setText(filterItemBeanList.get(i*3+1).getTitle());
                         tv3.setText(filterItemBeanList.get(i*3+2).getTitle());
                         break;
                     case 1://最后一排有一个
                         zz_filter_one_rl.setVisibility(View.VISIBLE);
                         zz_filter_two_rl.setVisibility(View.INVISIBLE);
                         zz_filter_three_rl.setVisibility(View.INVISIBLE);
                         if(filterItemBeanList.get(i*3).getIsCheck().equals("true")){
                             zz_filter_one_rl.setBackgroundResource(R.drawable.filter_item_click_bg);
                         }else{
                             zz_filter_one_rl.setBackgroundResource(R.drawable.filter_item_noclick_bg);
                         }
                         tv1.setText(filterItemBeanList.get(i*3).getTitle());
                         break;
                     case 2://最后一排有2个
                         zz_filter_one_rl.setVisibility(View.VISIBLE);
                         zz_filter_two_rl.setVisibility(View.VISIBLE);
                         zz_filter_three_rl.setVisibility(View.INVISIBLE);
                         if(filterItemBeanList.get(i*3).getIsCheck().equals("true")){
                             zz_filter_one_rl.setBackgroundResource(R.drawable.filter_item_click_bg);
                         }else{
                             zz_filter_one_rl.setBackgroundResource(R.drawable.filter_item_noclick_bg);
                         }
                         if(filterItemBeanList.get(i*3+1).getIsCheck().equals("true")){
                             zz_filter_two_rl.setBackgroundResource(R.drawable.filter_item_click_bg);
                         }else{
                             zz_filter_two_rl.setBackgroundResource(R.drawable.filter_item_noclick_bg);
                         }
                         tv1.setText(filterItemBeanList.get(i*3).getTitle());
                         tv2.setText(filterItemBeanList.get(i*3+1).getTitle());
                         break;
                 }
            }else{//不是最后一排，全部显示
                zz_filter_one_rl.setVisibility(View.VISIBLE);
                zz_filter_two_rl.setVisibility(View.VISIBLE);
                zz_filter_three_rl.setVisibility(View.VISIBLE);

                if(filterItemBeanList.get(i).getIsCheck().equals("true")){
                    zz_filter_one_rl.setBackgroundResource(R.drawable.filter_item_click_bg);
                }else{
                    zz_filter_one_rl.setBackgroundResource(R.drawable.filter_item_noclick_bg);
                }
                if(filterItemBeanList.get(i*3+1).getIsCheck().equals("true")){
                    zz_filter_two_rl.setBackgroundResource(R.drawable.filter_item_click_bg);
                }else{
                    zz_filter_two_rl.setBackgroundResource(R.drawable.filter_item_noclick_bg);
                }
                if(filterItemBeanList.get(i*3+2).getIsCheck().equals("true")){
                    zz_filter_three_rl.setBackgroundResource(R.drawable.filter_item_click_bg);
                }else{
                    zz_filter_three_rl.setBackgroundResource(R.drawable.filter_item_noclick_bg);
                }
                tv1.setText(filterItemBeanList.get(i*3).getTitle());
                tv2.setText(filterItemBeanList.get(i*3+1).getTitle());
                tv3.setText(filterItemBeanList.get(i*3+2).getTitle());
            }
            final int list_position = i;//哪排
            //多选
            if(filterTypeBean.getType().equals("checkbox")){
                zz_filter_one_rl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(filterTypeBeanList.get(position).getConfig().get(list_position*3).getIsCheck().equals("false")){
                            zz_filter_one_rl.setBackgroundResource(R.drawable.filter_item_click_bg);
                            filterTypeBeanList.get(position).getConfig().get(list_position*3).setIsCheck("true");
                        }else{
                            zz_filter_one_rl.setBackgroundResource(R.drawable.filter_item_noclick_bg);
                            filterTypeBeanList.get(position).getConfig().get(list_position*3).setIsCheck("false");
                        }
                        GlobalParams.filter_type = filterTypeBeanList;
                    }
                });
                zz_filter_two_rl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(filterTypeBeanList.get(position).getConfig().get(list_position*3+1).getIsCheck().equals("false")){
                            zz_filter_two_rl.setBackgroundResource(R.drawable.filter_item_click_bg);
                            filterTypeBeanList.get(position).getConfig().get(list_position*3+1).setIsCheck("true");
                        }else{
                            zz_filter_two_rl.setBackgroundResource(R.drawable.filter_item_noclick_bg);
                            filterTypeBeanList.get(position).getConfig().get(list_position*3+1).setIsCheck("false");
                        }
                        GlobalParams.filter_type = filterTypeBeanList;
                    }
                });
                zz_filter_three_rl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(filterTypeBeanList.get(position).getConfig().get(list_position*3+2).getIsCheck().equals("false")){
                            zz_filter_three_rl.setBackgroundResource(R.drawable.filter_item_click_bg);
                            filterTypeBeanList.get(position).getConfig().get(list_position*3+2).setIsCheck("true");
                        }else{
                            zz_filter_three_rl.setBackgroundResource(R.drawable.filter_item_noclick_bg);
                            filterTypeBeanList.get(position).getConfig().get(list_position*3+2).setIsCheck("false");
                        }
                        GlobalParams.filter_type = filterTypeBeanList;
                    }
                });
            }else{
                //单选
                zz_filter_one_rl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(filterTypeBeanList.get(position).getConfig().get(list_position*3).getIsCheck().equals("false")){
                            zz_filter_one_rl.setBackgroundResource(R.drawable.filter_item_click_bg);
                            filterTypeBeanList.get(position).getConfig().get(list_position*3).setIsCheck("true");
                        }else{
                            zz_filter_one_rl.setBackgroundResource(R.drawable.filter_item_noclick_bg);
                            filterTypeBeanList.get(position).getConfig().get(list_position*3).setIsCheck("false");
                        }
                        for(int m=0;m<filterTypeBeanList.get(position).getConfig().size();m++){
                            if(m!=list_position*3){
                                filterTypeBeanList.get(position).getConfig().get(m).setIsCheck("false");
                            }
                        }
                        GlobalParams.filter_type = filterTypeBeanList;
                        notifyDataSetChanged();

                    }
                });

                zz_filter_two_rl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(filterTypeBeanList.get(position).getConfig().get(list_position*3+1).getIsCheck().equals("false")){
                            zz_filter_two_rl.setBackgroundResource(R.drawable.filter_item_click_bg);
                            filterTypeBeanList.get(position).getConfig().get(list_position*3+1).setIsCheck("true");
                        }else{
                            zz_filter_two_rl.setBackgroundResource(R.drawable.filter_item_noclick_bg);
                            filterTypeBeanList.get(position).getConfig().get(list_position*3+1).setIsCheck("false");
                        }
                        for(int m=0;m<filterTypeBeanList.get(position).getConfig().size();m++){
                            if(m!=list_position*3+1){
                                filterTypeBeanList.get(position).getConfig().get(m).setIsCheck("false");
                            }
                        }
                        GlobalParams.filter_type = filterTypeBeanList;
                        notifyDataSetChanged();
                    }
                });

                zz_filter_three_rl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(filterTypeBeanList.get(position).getConfig().get(list_position*3+2).getIsCheck().equals("false")){
                            zz_filter_three_rl.setBackgroundResource(R.drawable.filter_item_click_bg);
                            filterTypeBeanList.get(position).getConfig().get(list_position*3+2).setIsCheck("true");
                        }else{
                            zz_filter_three_rl.setBackgroundResource(R.drawable.filter_item_noclick_bg);
                            filterTypeBeanList.get(position).getConfig().get(list_position*3+2).setIsCheck("false");
                        }
                        for(int m=0;m<filterTypeBeanList.get(position).getConfig().size();m++){
                            if(m!=list_position*3+2){
                                filterTypeBeanList.get(position).getConfig().get(m).setIsCheck("false");
                            }
                        }
                        GlobalParams.filter_type = filterTypeBeanList;
                        notifyDataSetChanged();
                    }
                });
            }
            vh.zz_filter_child_ll.addView(viewOne);
        }
        vh.zz_expand_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(filterTypeBeanList.get(position).getIsExpand().equals("false")){//未展开
                    vh.zz_expand_iv.setBackgroundResource(R.drawable.zz_filter_index_filter_collapse);
                    filterTypeBeanList.get(position).setIsExpand("true");
                }else {//展开
                    vh.zz_expand_iv.setBackgroundResource(R.drawable.zz_filter_index_filter_expand);
                    filterTypeBeanList.get(position).setIsExpand("false");

                }
                GlobalParams.filter_type = filterTypeBeanList;
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    class ViewHolder{
        ImageView zz_expand_iv;//奖品图片
        TextView zz_filter_title_tv;//奖品名称
        TextView zz_filter_starttime_tv;//开始时间
        TextView zz_filter_endtime_tv;//结束时间
        LinearLayout zz_filter_child_ll;
        LinearLayout zz_filter_gamestart_ll;
    }
}
