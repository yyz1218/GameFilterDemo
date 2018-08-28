package android.esports.zzdj.com.gamefilterdemo;

import android.net.Uri;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GlobalParams {

    //筛选项list
    public static ArrayList<FilterTypeBean> filter_type;
    //1正常请求赛事信息 2 经过赛选条件返回的赛事信息
    public static String  filterGameType="1";
    //是否筛选过信息
    public static boolean  isDoFilterGame=false;
}
