package android.esports.zzdj.com.gamefilterdemo;

import android.os.Parcel;
import android.os.Parcelable;

public class FilterItemBean implements Parcelable {
    private String icon;//条目logo url 没有logo不返回此字段
    private String title;//条目名称
    private String value;//条目值 回传此字段
    private String isCheck="false";
    public FilterItemBean() {
    }

    public FilterItemBean(String icon, String title,String value,String isCheck) {
        this.icon = icon;
        this.title = title;
        this.value = value;
        this.isCheck = isCheck;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public String getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck;
    }

    @Override
    public String toString() {
        return "FilterItemBean{" +
                "icon='" + icon + '\'' +
                ", title='" + title + '\'' +
                ", value='" + value + '\'' +
                ", isCheck='" + isCheck + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        // 1.必须按成员变量声明的顺序封装数据，不然会出现获取数据出错
        // 2.序列化对象
        parcel.writeString(icon);
        parcel.writeString(title);
        parcel.writeString(value);
        parcel.writeString(isCheck);
    }

    protected FilterItemBean(Parcel in) {
        icon = in.readString();
        title = in.readString();
        value = in.readString();
        isCheck = in.readString();
    }

    public static final Creator<FilterItemBean> CREATOR = new Creator<FilterItemBean>() {
        @Override
        public FilterItemBean createFromParcel(Parcel in) {
            return new FilterItemBean(in);
        }

        @Override
        public FilterItemBean[] newArray(int size) {
            return new FilterItemBean[size];
        }
    };
}
