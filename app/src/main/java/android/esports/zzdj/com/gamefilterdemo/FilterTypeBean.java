package android.esports.zzdj.com.gamefilterdemo;

import java.io.Serializable;
import java.util.ArrayList;

public class FilterTypeBean implements Serializable{
    private String name;//id 筛选条目id
    private String title;//titie 筛选条目标题
    private String value;//返回后台的值
    private String type;//0单选 1多选
    private String return_value;//返回给后端的values;
    private String isExpand;//是否展开 false 为展开 true 展开
    private ArrayList<FilterItemBean> config;//筛选条目 可选择项目列表
    private String startTime;//比赛开始时间
    private String endTime;//比赛结束时间

    public FilterTypeBean(String name, String title, String value, String type, String return_value, String isExpand, ArrayList<FilterItemBean> config, String startTime, String endTime) {
        this.name = name;
        this.title = title;
        this.value = value;
        this.type = type;
        this.return_value = return_value;
        this.isExpand = isExpand;
        this.config = config;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public FilterTypeBean() {
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReturn_value() {
        return return_value;
    }

    public void setReturn_value(String return_value) {
        this.return_value = return_value;
    }

    public ArrayList<FilterItemBean> getConfig() {
        return config;
    }

    public void setConfig(ArrayList<FilterItemBean> config) {
        this.config = config;
    }

    public String getIsExpand() {
        return isExpand;
    }

    public void setIsExpand(String isExpand) {
        this.isExpand = isExpand;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "FilterTypeBean{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", value='" + value + '\'' +
                ", type='" + type + '\'' +
                ", return_value='" + return_value + '\'' +
                ", isExpand='" + isExpand + '\'' +
                ", config=" + config +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
