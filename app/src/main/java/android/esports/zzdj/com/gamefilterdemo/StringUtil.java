package android.esports.zzdj.com.gamefilterdemo;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


public class StringUtil {
	/**
	 * 验证字符串 合法性[a-z0-9+]
	 * @return
	 */
	public static boolean verifyStrsLegitimacy(String strs) {
		boolean result = false;
		Pattern p = Pattern.compile("[\\d[a-z]]+");
		Matcher matcher = p.matcher(strs);
		result = matcher.matches();
		return result;
	}
	/**
	 *
	 * @description 验证 用户密码 字符串 的合法性。
	 * e.g: 5-20位，字母、数字、字符(不包含中文字符) 组合。
	 * @param
	 * @return boolean
	 * @author Administrator create at 2014-9-4下午1:35:07
	 */
	public static boolean verifyUserPwdLegitimacy(String password) {
		if (!isContainChinese(password)) {
			Pattern p = Pattern.compile("^.{5,20}$");// 5-20位
			Matcher matcher = p.matcher(password);
			return matcher.matches();
		}
		return false;
	}

	/**
	 *
	 * @description 匹配 是否包含 中文 字符
	 * @return true 包含中文字符； false:不包含中文字符
	 */
	public static boolean isContainChinese(String str) {

		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(str);
		if (m.find()) {
			return true;
		}
		return false;
	}

	/**
	 *
	 * @description 用于将字节数组换成成16进制的字符串
	 * @param byteArray
	 * @return String
	 */
	public static String byteArrayToHex(byte[] byteArray) {
		// 首先初始化一个字符数组，用来存放每个16进制字符
		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		// new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
		char[] resultCharArray = new char[byteArray.length * 2];
		// 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
		int index = 0;
		for (byte b : byteArray) {
			resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
			resultCharArray[index++] = hexDigits[b & 0xf];
		}
		// 字符数组组合成字符串返回
		return new String(resultCharArray);
	}

	/**
	 * 是否为空
	 * @param str 字符串
	 * @return true 空 false 非空
	 */
	public static Boolean isEmpty(String str) {
		if(str == null || str.equals("")) {
			return true;
		}
		return false;
	}


	/**
	 * 验证手机号码正则表达式
	 *13开头的后面跟0-9的任意8位数；
	 *15开头的后面跟除了4以外的0-9的任意8位数；
	 *18开头的后面跟0-9的任意8位数；
	 *17开头的后面跟0-8的任意8位数，或者17[^9]；
	 *147，145开头后面跟任意8位数；
	 *166开头的后面跟任意8位数；
	 *198，199开头后面跟任意8位数；
	 */
	public static boolean isChinaPhoneLegal(String str)
			throws PatternSyntaxException {
		String regExp = "^((13[0-9])|(15[^4])|(166)|(17[0-8])|(18[0-9])|(19[8-9])|(147,145))\\d{8}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(str);
		return m.matches();
	}
	/**
	 * 校验用户名
	 * @param username
	 * 正则表达式:验证用户名(不包含中文和特殊字符)如果用户名使用手机号码或邮箱 则结合手机号验证和邮箱验证
	 * @return 校验通过返回true，否则返回false
	 */
	public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,17}$";
	public static boolean checkUserName(String username) {
		return Pattern.matches(REGEX_USERNAME, username);
	}
	/**
	 * 验证 用户名 字符串 的合法性。
	 * e.g: 5-20位，字母开头，字母、数字、下划线 组合。
	 * @author Administrator create at 2014-9-4上午11:12:44
	 */
	public static boolean verifyUserNameLegitimacy(String username) {
		// \w 单词字符：[a-zA-Z_0-9]
		Pattern p = Pattern.compile("^(?!_)(?!\\d)[\\w]{5,16}$");// 5-16位
		Matcher matcher = p.matcher(username);
		return matcher.matches();
	}

	/**
	 * 校验密码
	 * @param password
	 * @return 校验通过返回true，否则返回false
	 */
	public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,16}$";
	public static boolean checkPassword(String password) {
		return Pattern.matches(REGEX_PASSWORD, password);
	}
	/**
	 * 校验汉字
	 * @param chinese
	 * @return 校验通过返回true，否则返回false
	 * 正则表达式:验证汉字(1-9个汉字)  {1,9} 自定义区间
	 */
	public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5]{1,4}$";
	public static boolean checkIsChinese(String chinese) {
		return Pattern.matches(REGEX_CHINESE, chinese);
	}
	/**
	 * 校验IP地址
	 * @param ipAddress
	 * @return
	 */
	public static final String REGEX_IP_ADDR = "(2[5][0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})";
	public static boolean checkIsIPAddress(String ipAddress) {
		return Pattern.matches(REGEX_IP_ADDR, ipAddress);
	}
	/**
	 * 校验身份证
	 * @param idCard
	 * @return 校验通过返回true，否则返回false
	 */
	public static final String REGEX_ID_CARD = "(^\\d{15}$)|(^\\d{17}([0-9]|X)$)";
	public static boolean CheckisIDCard(String idCard) {
		return Pattern.matches(REGEX_ID_CARD, idCard);
	}

	/**
	 * 将unicode的汉字码转换成utf-8格式的汉字
	 * @param unicode
	 * @return
	 */
	public static String unicodeToString(String unicode) {
		String str = unicode.replace("0x", "\\");
		StringBuffer string = new StringBuffer();
		String[] hex = str.split("\\\\u");
		for (int i = 1; i < hex.length; i++) {
			int data = Integer.parseInt(hex[i], 16);
			string.append((char) data);
		}
		return string.toString();
	}
	/**
	 * 把时间戳转换成时间
	 */
	public static String TimeStampChange(String time) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(new Date(Long.parseLong(time)));
		String[] dateArray = date.split("-");
		return dateArray[1]+"-"+dateArray[2];
	}
	/**
	 * 把时间戳转换成时间 yyyy-MM-dd HH:mm:ss
	 */
	public static String TimeStampChangeHHMM(String time) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = format.format(new Date(Long.parseLong(time)*1000L));
		String[] dateArray = date.split(" ");
        String date1 = dateArray[0];
        String date2 = dateArray[1];
        String[] YearMonth = date1.split("-");
        String[] HourMinute = date2.split(":");
		return YearMonth[1]+"-"+YearMonth[2]+" "+HourMinute[0]+":"+HourMinute[1];
	}
	/**
	 * 把时间戳转换成时间 yyyy-MM-dd HH:mm:ss
	 */
	public static String TimeStampChangeYYMMDDHHMM(String time) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = format.format(new Date(Long.parseLong(time)*1000L));
		String[] dateArray = date.split(" ");
        String date1 = dateArray[0];
        String date2 = dateArray[1];
        String[] YearMonth = date1.split("-");
        String[] HourMinute = date2.split(":");
		return YearMonth[0]+"-"+YearMonth[1]+"-"+YearMonth[2]+" "+HourMinute[0]+":"+HourMinute[1];
	}
	/**
	 * 把时间戳转换成时间 yyyy-MM-dd HH:mm:ss
	 */
	public static String TimeChangeHHMM(String time) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = format.format(new Date(Long.parseLong(time)*1000L));
		String[] dateArray = date.split(" ");
        String date1 = dateArray[0];
        String date2 = dateArray[1];
        String[] YearMonth = date1.split("-");
        String[] HourMinute = date2.split(":");
		return YearMonth[1]+"月"+YearMonth[2]+"日 "+HourMinute[0]+":"+HourMinute[1];
	}

	/**
	 * 把时间戳转换成时间 yyyy-MM-dd HH:mm:ss
	 */
	public static String TimeChangeHHMM2(String time) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = format.format(new Date(Long.parseLong(time)*1000L));
		String[] dateArray = date.split(" ");
		String date1 = dateArray[0];
		String date2 = dateArray[1];
		String[] YearMonth = date1.split("-");
		String[] HourMinute = date2.split(":");
		return YearMonth[1]+"-"+YearMonth[2]+" "+HourMinute[0]+":"+HourMinute[1];
	}
	/**
	 * 把时间戳转换成时间 yyyy-MM-dd HH:mm:ss
	 */
	public static String TimeChangeMMDDHHMM(int time) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = format.format(new Date(time*1000L));
		String[] dateArray = date.split(" ");
		String date1 = dateArray[0];
		String date2 = dateArray[1];
		String[] YearMonth = date1.split("-");
		String[] HourMinute = date2.split(":");
		return YearMonth[1]+"/"+YearMonth[2]+" "+HourMinute[0]+":"+HourMinute[1];
	}

	/**
	 * 把时间戳转换成时间 yyyy-MM-dd HH:mm:ss
	 */
	public static String TimeChangeHHMM3(String time) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = format.format(new Date(Long.parseLong(time)*1000L));
		String[] dateArray = date.split(" ");
		String date1 = dateArray[0];
		String date2 = dateArray[1];
		String[] YearMonth = date1.split("-");
		String[] HourMinute = date2.split(":");
		return YearMonth[1]+"/"+YearMonth[2]+" "+HourMinute[0]+":"+HourMinute[1];
	}

	public static String times(String time) {
		SimpleDateFormat sdr = new SimpleDateFormat("MM/dd HH:mm:ss");
		@SuppressWarnings("unused")
		long lcc = Long.valueOf(time);
		int i = Integer.parseInt(time);
		String times = sdr.format(new Date(i * 1000L));
		return times;

	}
	public static String match_rounds(String time) {
		String  match_round ="";
		switch (Integer.parseInt(time)){
			case 1:
				match_round ="单";
				break;
			case 2:
				match_round="二";
				break;
			case 3:
				match_round="三";
				break;
			case 4:
				match_round="四";
				break;
			case 5:
				match_round="五";
				break;
			case 6:
				match_round="六";
				break;
			case 7:
				match_round="七";
				break;
			case 8:
				match_round="八";
				break;
			case 9:
				match_round="九";
				break;
			case 10:
				match_round="十";
				break;
				default:
					match_round = time;
					break;
		}
		return match_round;

	}

	public static String formatTime(Long ms) {
		Integer ss = 1000;
		Integer mi = ss * 60;
		Integer hh = mi * 60;
		Integer dd = hh * 24;

		Long day = ms / dd;
		Long hour = (ms - day * dd) / hh;
		Long minute = (ms - day * dd - hour * hh) / mi;
		Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
		Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;
		Log.e("222222",second+"");
		StringBuffer sb = new StringBuffer();
		day = day * 24;
		hour += day;
		if(hour > 0 && hour<10) {
			sb.append("0"+hour+":");
		}else if (hour >=10){
			sb.append(""+hour+":");
		}else if (hour <= 0){
			sb.append("0"+hour+":");
		}
		if(minute >= 0 && minute<10) {
			sb.append("0"+minute+":");
		}else if (minute >=10){
			sb.append(""+minute+":");
		}else if (minute <= 0){
			sb.append("00"+minute+":");
		}
		if(second >= 0 && second<10) {
			Log.e("1111111",second+"");
			sb.append("0"+second+"");
		}else if (second >=10){
			sb.append(""+second+"");
		}
		return sb.toString();
	}

	public static String getStrMetaData(Context context,String str){
		ApplicationInfo appInfo;
		try
		{
			appInfo = context.getPackageManager()
					.getApplicationInfo(context.getPackageName(),PackageManager.GET_META_DATA);
			String msg=appInfo.metaData.getString(str);
			if (TextUtils.isEmpty(msg)) {
				msg = appInfo.metaData.getInt(str)+"";
				if(msg.equals("0")) {
					msg = null;
				}
			}
			return msg;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
