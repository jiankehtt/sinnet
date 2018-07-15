package com.sinnet.utils;

import java.io.Reader;
import java.lang.reflect.Array;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public abstract class StringUtils {

	/**
	 * Check that the given CharSequence is neither <code>null</code> nor of
	 * length 0. Note: Will return <code>true</code> for a CharSequence that
	 * purely consists of whitespace.
	 * <p>
	 * 
	 * <pre>
	 * StringUtils.hasLength(null) = false
	 * StringUtils.hasLength(&quot;&quot;) = false
	 * StringUtils.hasLength(&quot; &quot;) = true
	 * StringUtils.hasLength(&quot;Hello&quot;) = true
	 * </pre>
	 * 
	 * @param str
	 *            the CharSequence to check (may be <code>null</code>)
	 * @return <code>true</code> if the CharSequence is not null and has
	 *         length
	 * @see #hasText(String)
	 */
	public static boolean hasLength(CharSequence str) {
		return (str != null && str.length() > 0);
	}

	/**
	 * Check that the given String is neither <code>null</code> nor of length
	 * 0. Note: Will return <code>true</code> for a String that purely
	 * consists of whitespace.
	 * 
	 * @param str
	 *            the String to check (may be <code>null</code>)
	 * @return <code>true</code> if the String is not null and has length
	 * @see #hasLength(CharSequence)
	 */
	public static boolean hasLength(String str) {
		return hasLength((CharSequence) str);
	}

	/**
	 * Check whether the given CharSequence has actual text. More specifically,
	 * returns <code>true</code> if the string not <code>null</code>, its
	 * length is greater than 0, and it contains at least one non-whitespace
	 * character.
	 * <p>
	 * 
	 * <pre>
	 * StringUtils.hasText(null) = false
	 * StringUtils.hasText(&quot;&quot;) = false
	 * StringUtils.hasText(&quot; &quot;) = false
	 * StringUtils.hasText(&quot;12345&quot;) = true
	 * StringUtils.hasText(&quot; 12345 &quot;) = true
	 * </pre>
	 * 
	 * @param str
	 *            the CharSequence to check (may be <code>null</code>)
	 * @return <code>true</code> if the CharSequence is not <code>null</code>,
	 *         its length is greater than 0, and it does not contain whitespace
	 *         only
	 * @see java.lang.Character#isWhitespace
	 */
	public static boolean hasText(CharSequence str) {
		if (!hasLength(str)) {
			return false;
		}
		int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check whether the given String has actual text. More specifically,
	 * returns <code>true</code> if the string not <code>null</code>, its
	 * length is greater than 0, and it contains at least one non-whitespace
	 * character.
	 * 
	 * @param str
	 *            the String to check (may be <code>null</code>)
	 * @return <code>true</code> if the String is not <code>null</code>,
	 *         its length is greater than 0, and it does not contain whitespace
	 *         only
	 * @see #hasText(CharSequence)
	 */
	public static boolean hasText(String str) {
		return hasText((CharSequence) str);
	}

	/**
	 * Check whether the given CharSequence contains any whitespace characters.
	 * 
	 * @param str
	 *            the CharSequence to check (may be <code>null</code>)
	 * @return <code>true</code> if the CharSequence is not empty and contains
	 *         at least 1 whitespace character
	 * @see java.lang.Character#isWhitespace
	 */
	public static boolean containsWhitespace(CharSequence str) {
		if (!hasLength(str)) {
			return false;
		}
		int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check whether the given String contains any whitespace characters.
	 * 
	 * @param str
	 *            the String to check (may be <code>null</code>)
	 * @return <code>true</code> if the String is not empty and contains at
	 *         least 1 whitespace character
	 * @see #containsWhitespace(CharSequence)
	 */
	public static boolean containsWhitespace(String str) {
		return containsWhitespace((CharSequence) str);
	}

	/**
	 * Trim <i>all</i> whitespace from the given String: leading, trailing, and
	 * inbetween characters.
	 * 
	 * @param str
	 *            the String to check
	 * @return the trimmed String
	 * @see java.lang.Character#isWhitespace
	 */
	public static String trimAllWhitespace(String str) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		int index = 0;
		while (sb.length() > index) {
			if (Character.isWhitespace(sb.charAt(index))) {
				sb.deleteCharAt(index);
			} else {
				index++;
			}
		}
		return sb.toString();
	}

	/**
	 * Trim leading whitespace from the given String.
	 * 
	 * @param str
	 *            the String to check
	 * @return the trimmed String
	 * @see java.lang.Character#isWhitespace
	 */
	public static String trimLeadingWhitespace(String str) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		while (sb.length() > 0 && Character.isWhitespace(sb.charAt(0))) {
			sb.deleteCharAt(0);
		}
		return sb.toString();
	}

	/**
	 * Trim trailing whitespace from the given String.
	 * 
	 * @param str
	 *            the String to check
	 * @return the trimmed String
	 * @see java.lang.Character#isWhitespace
	 */
	public static String trimTrailingWhitespace(String str) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		while (sb.length() > 0
				&& Character.isWhitespace(sb.charAt(sb.length() - 1))) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	/**
	 * Trim all occurences of the supplied leading character from the given
	 * String.
	 * 
	 * @param str
	 *            the String to check
	 * @param leadingCharacter
	 *            the leading character to be trimmed
	 * @return the trimmed String
	 */
	public static String trimLeadingCharacter(String str, char leadingCharacter) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		while (sb.length() > 0 && sb.charAt(0) == leadingCharacter) {
			sb.deleteCharAt(0);
		}
		return sb.toString();
	}

	/**
	 * Trim all occurences of the supplied trailing character from the given
	 * String.
	 * 
	 * @param str
	 *            the String to check
	 * @param trailingCharacter
	 *            the trailing character to be trimmed
	 * @return the trimmed String
	 */
	public static String trimTrailingCharacter(String str,
			char trailingCharacter) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		while (sb.length() > 0
				&& sb.charAt(sb.length() - 1) == trailingCharacter) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	/**
	 * Test if the given String starts with the specified prefix, ignoring
	 * upper/lower case.
	 * 
	 * @param str
	 *            the String to check
	 * @param prefix
	 *            the prefix to look for
	 * @see java.lang.String#startsWith
	 */
	public static boolean startsWithIgnoreCase(String str, String prefix) {
		if (str == null || prefix == null) {
			return false;
		}
		if (str.startsWith(prefix)) {
			return true;
		}
		if (str.length() < prefix.length()) {
			return false;
		}
		String lcStr = str.substring(0, prefix.length()).toLowerCase();
		String lcPrefix = prefix.toLowerCase();
		return lcStr.equals(lcPrefix);
	}

	/**
	 * Test if the given String ends with the specified suffix, ignoring
	 * upper/lower case.
	 * 
	 * @param str
	 *            the String to check
	 * @param suffix
	 *            the suffix to look for
	 * @see java.lang.String#endsWith
	 */
	public static boolean endsWithIgnoreCase(String str, String suffix) {
		if (str == null || suffix == null) {
			return false;
		}
		if (str.endsWith(suffix)) {
			return true;
		}
		if (str.length() < suffix.length()) {
			return false;
		}

		String lcStr = str.substring(str.length() - suffix.length())
				.toLowerCase();
		String lcSuffix = suffix.toLowerCase();
		return lcStr.equals(lcSuffix);
	}

	/**
	 * Replace all occurences of a substring within a string with another
	 * string.
	 * 
	 * @param inString
	 *            String to examine
	 * @param oldPattern
	 *            String to replace
	 * @param newPattern
	 *            String to insert
	 * @return a String with the replacements
	 */
	public static String replace(String inString, String oldPattern,
			String newPattern) {
		if (!hasLength(inString) || !hasLength(oldPattern)
				|| newPattern == null) {
			return inString;
		}
		StringBuilder sb = new StringBuilder();
		int pos = 0; // our position in the old string
		int index = inString.indexOf(oldPattern);
		// the index of an occurrence we've found, or -1
		int patLen = oldPattern.length();
		while (index >= 0) {
			sb.append(inString.substring(pos, index));
			sb.append(newPattern);
			pos = index + patLen;
			index = inString.indexOf(oldPattern, pos);
		}
		sb.append(inString.substring(pos));
		// remember to append any characters to the right of a match
		return sb.toString();
	}

	/**
	 * Delete all occurrences of the given substring.
	 * 
	 * @param inString
	 *            the original String
	 * @param pattern
	 *            the pattern to delete all occurrences of
	 * @return the resulting String
	 */
	public static String delete(String inString, String pattern) {
		return replace(inString, pattern, "");
	}

	/**
	 * Delete any character in a given String.
	 * 
	 * @param inString
	 *            the original String
	 * @param charsToDelete
	 *            a set of characters to delete. E.g. "az\n" will delete 'a's,
	 *            'z's and new lines.
	 * @return the resulting String
	 */
	public static String deleteAny(String inString, String charsToDelete) {
		if (!hasLength(inString) || !hasLength(charsToDelete)) {
			return inString;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < inString.length(); i++) {
			char c = inString.charAt(i);
			if (charsToDelete.indexOf(c) == -1) {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 为任意的BEAN生成toString方法的内容
	 * 
	 * @param obj
	 * @return
	 */
	public static String toStringBuilder(Object obj) {
		return ToStringBuilder.reflectionToString(obj, new StringStyle());
	}

	/**
	 * 将一个字符串转换为List，字符串中间以','号分隔
	 * 
	 * @param inputStr
	 * @return
	 */
	public static List<String> asList(String inputStr) {
		List<String> retList = new LinkedList<String>();
		if (inputStr != null && inputStr.length() != 0) {
			String[] parts = inputStr.split(",");
			for (int i = 0; i < parts.length; i++) {
				if (!parts[i].trim().equals(""))
					retList.add(parts[i]);
			}
		}
		return retList;
	}

	/**
	 * 将一个数组转换成字段串
	 * 
	 * @param items
	 * @param joinSymbol
	 * @return
	 */
	public static String asString(String[] items, String joinSymbol) {
		StringBuffer strBuffer = new StringBuffer();
		for (int i = 0; i < items.length; i++) {
			if (i != 0)
				strBuffer.append(joinSymbol);
			strBuffer.append(items[i]);
		}
		return strBuffer.toString();
	}

	/**
	 * 判断字符串
	 * 
	 * @param input
	 * @return
	 */
	public static Boolean isEmpty(String input) {
		return input == null || input.trim().length() == 0;
	}

	/**
	 * 判断字符串
	 * 
	 * @param input
	 * @return
	 */
	public static String castEmpty(String input) {
		return isEmpty(input) ? "" : input.trim();
	}

	/**
	 * 将字符串以一串特殊字符，分隔为一个数组
	 * 
	 * @param inputStr
	 * @param splitStr
	 * @return
	 */
	public static String[] split(String inputStr, String splitStr) {
		List<String> ret = new LinkedList<String>();
		String curStr = "";
		for (int i = 0; i < inputStr.length(); i++) {
			if ((inputStr.charAt(i) + "").equals(splitStr)) {
				ret.add(curStr);
				curStr = "";
			} else {
				curStr += inputStr.charAt(i);
			}
		}
		ret.add(curStr);
		String[] retArr = new String[ret.size()];
		for (int i = 0; i < ret.size(); i++) {
			retArr[i] = ret.get(i);
		}
		return retArr;
	}

	/**
	 * 连接一个数组为一个字符串
	 * 
	 * @param array
	 *            需要进行连接的数组
	 * @param sepator
	 *            分隔字符串
	 * @return
	 */
	public static String join(Object array, String sepator) {
		if (array == null || !array.getClass().isArray())
			return null;
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < Array.getLength(array); i++) {
			sb.append(Array.get(array, i)).append(sepator);
		}
		if (sb.length() > 0)
			sb.delete(sb.length() - sepator.length(), sb.length());
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	public static Object splitObj(String str, String separator, Class cls) {
		if (str == null || separator == null || str.equals(""))
			return null;
		if (separator.equals(""))
			return str.split("");
		Object rst = null;
		List<String> list = new ArrayList<String>();
		int start = 0;
		while (start < str.length()) {
			String substr = str.substring(start);
			int index = substr.indexOf(separator);
			if (index >= 0) {
				list.add(substr.substring(0, index));
				start = start + index + 1;
			} else {
				list.add(substr);
				break;
			}
		}
		if (list.size() > 0) {
			rst = Array.newInstance(cls, list.size());
			for (int i = 0; i < list.size(); i++) {
				Array
						.set(rst, i, ReflectUtils.stringToNumber(list.get(i),
								cls));
			}
		}
		return rst;
	}

	/**
	 * 验证一个字符串是否是数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		if (str == null || str.trim().length() == 0)
			return false;
		str = str.trim();
		String[] strs = StringUtils.split(str, ".");
		if (strs.length == 1 && strs[0].matches("^[-+]{0,1}[0-9]+$")) {
			return true;
		} else if (strs.length == 2 && strs[0].matches("^[-+]{0,1}[0-9]+$")
				&& strs[1].matches("^[0-9]+$")) {
			return true;
		}
		return false;
	}

	/**
	 * 拼接字符串
	 * 
	 * @param ary
	 * @param joinStr
	 * @return
	 */
	public static String join(String[] ary, String joinStr) {
		if (ary == null || joinStr == null)
			return null;
		StringBuffer ren = new StringBuffer("");
		for (int i = 0; i < ary.length; i++) {
			ren.append(ary[i]);
			if (i + 1 < ary.length)
				ren.append(joinStr);
		}
		return ren.toString();
	}

	/**
	 * 替换字符
	 * 
	 * @param src
	 *            源字符串
	 * @param offset
	 *            开始位置 从0开始
	 * @param size
	 *            要替换的字符长度
	 * @param rpc
	 *            替换的字符串
	 */
	public static String replace(String src, int offset, int size, String rpc) {
		if (src == null || rpc == null)
			return null;
		StringBuffer sb = new StringBuffer("");
		if (offset > 0)
			sb.append(src.substring(0, offset));
		sb.append(rpc);
		if (offset + size < src.length())
			sb.append(src.substring(offset + size));
		return sb.toString();
	}

	/**
	 * 使用正则表达式替换字符
	 * 
	 * @param src
	 * @param regex
	 * @param array
	 * @return
	 */
	public static String replace(String src, String regex, String[] array) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(src);
		StringBuffer buff = new StringBuffer();
		for (int i = 0; m.find(); i++) {
			buff.append(src.substring(0, m.start())).append(array[i]);
			src = src.substring(m.end());
			m = p.matcher(src);
		}
		if (src.length() > 0) {
			buff.append(src);
		}
		return buff.toString();
	}

	/**
	 * 需找符合指定表达式的字符串
	 * 
	 * @param source
	 * @param regex
	 * @return 未找到返回null
	 */
	public static String[] find(String source, String regex) {
		if (source == null || regex == null)
			return null;
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(source);
		List<String> ren = new ArrayList<String>();
		while (m.find()) {
			int start = m.start(1);
			ren.add(m.group(1));
			source = source.substring(0, start);
			m = p.matcher(source);
		}
		return ren.size() > 0 ? ren.toArray(new String[0]) : null;
	}

	/**
	 * 复制property里面的属性并且替换${}内容
	 * 
	 * @param props
	 * @return
	 */
	public static Properties getProperties(Properties props) {
		if (props == null)
			return null;
		Properties p = new Properties();
		Pattern pt = Pattern.compile("[\\S]*(\\$\\{[a-zA-Z]+\\})[\\S]*");
		for (Object key : props.keySet()) {
			String value = (String) props.get(key);
			Matcher m = pt.matcher(value);
			while (m.find()) {
				String name = m.group(1).substring(2, m.group(1).length() - 1);
				value = value.substring(0, m.start(1))
						+ props.getProperty(name) + value.substring(m.end(1));
				m = pt.matcher(value);
			}
			p.put(key, value);
		}
		return p;
	}

	/**
	 * 将字符串转化为Number，如果没小数点就转化为int，有小数点就转化为double
	 * 
	 * @param str
	 * @return
	 */
	public static Object toNumber(String str) {
		if (isNumber(str)) {
			if (str.indexOf('.') == -1) {
				return Integer.parseInt(str);
			} else {
				return Double.parseDouble(str);
			}
		} else {
			return null;
		}
	}

	/**
	 * HTML字符串转化为非HTML字符串
	 * 
	 * @param inputString
	 * @return
	 */
	public static String html2Text(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		Pattern p_script;
		Matcher m_script;
		Pattern p_style;
		Matcher m_style;
		Pattern p_html;
		Matcher m_html;
		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script>]*?>[\s\S]*?<\/script>//
			// }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style>]*?>[\s\S]*?<\/style>//
			// }
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签

			htmlStr = htmlStr.replaceAll("&nbsp;", " ");
			htmlStr = htmlStr.replaceAll("&lt;", "<");
			htmlStr = htmlStr.replaceAll("&gt;", ">");
			htmlStr = htmlStr.replaceAll("&uot;", "\"");
			htmlStr = htmlStr.replaceAll("&amp;", "&");
			textStr = htmlStr;
		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}
		return textStr;
	}
	
	/**
	 * 在一个字符串前，补0，返回一个定长的字符串
	 * @param src
	 * @param length
	 * @return
	 */
	public static String insertZeroBeforeString(String src,int length){
	  StringBuffer sb = new StringBuffer();
	  int leftLen = length - src.length();
	  for(int i=0;i<leftLen;i++)
	    sb.append("0");
	  sb.append(src);
	  return sb.toString();
	}
	/**
	 * 在一个字符串后，补0，返回一个定长的字符串. added by libo
	 * @param src
	 * @param length
	 * @return
	 */
	public static String appendZeroAfterString(String src,int length){
	  StringBuffer sb = new StringBuffer();
	  int leftLen = length - src.length();
	  sb.append(src);
	  for(int i=0;i<leftLen;i++)
	    sb.append("0");
	  return sb.toString();
	}
	/**
	 * 在一个数字前，补0，返回一个定长的字符串
	 * @param num
	 * @param length
	 * @return
	 */
	public static String insertZero(int num,int length){
	  StringBuffer sb = new StringBuffer();
	  String numstr = String.valueOf(num);
	  int leftLen = length - numstr.length();
	  for(int i=0;i<leftLen;i++)
	    sb.append("0");
	  sb.append(numstr);
	  return sb.toString();
	}
	public static String formatPlayDate(String playDate){
		if(!StringUtils.isEmpty(playDate)&&playDate.length()==8){
			return playDate.substring(0,4)+"-"+playDate.substring(4,6)+"-"+playDate.substring(6,8);
		}
		return playDate;
	}
	
	 /**
     * 将CLOB转成String ,静态方法
     * @param clob 字段
     * @return 内容字串，如果出现错误，返回null
     */
    public  static String clob2String(Clob clob)
    {
      if (clob == null)
      {
        return null;
      }
      
      StringBuffer sb = new StringBuffer(65535);//64K
      Reader clobStream = null;//创建一个输入流对象
      try
      {
        clobStream = clob.getCharacterStream();
        char[] b = new char[60000];//每次获取60K
        int i = 0;
        while((i = clobStream.read(b)) != -1)
        {
          sb.append(b,0,i);
        }
      }
      catch(Exception ex)
      {
        sb = null;
      }
      finally
      {
        try
        {
          if (clobStream != null)
            clobStream.close();
        }
        catch (Exception e)
        {
        }
      }
      if (sb == null)
        return null;
      else
        return sb.toString();
    }

}
class StringStyle extends ToStringStyle {
	private static final long serialVersionUID = 1L;
	public StringStyle() {
		super();
		this.setUseClassName(false);
		this.setUseIdentityHashCode(false);
		this.setUseFieldNames(true);
		this.setContentStart("{");
		this.setContentEnd("}");
		this.setFieldNameValueSeparator(":");
		this.setNullText("");
	}
}