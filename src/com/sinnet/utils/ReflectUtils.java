package com.sinnet.utils;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class ReflectUtils {

	private static final Logger logger = Logger.getLogger(ReflectUtils.class);

	/**
	 * 根据函数名称获取同名函数数组
	 * 
	 * @param cls
	 *            类class
	 * @param name
	 *            函数名称
	 * @return 函数数组
	 */
	public static Method[] getMethod(Class<?> cls, String name) {
		Method[] methods = cls.getDeclaredMethods();
		if (methods == null || methods.length == 0)
			return null;
		List<Method> list = new ArrayList<Method>();
		for (Method m : methods) {
			if (m.getName().equalsIgnoreCase(name))
				list.add(m);
		}
		return list.toArray(new Method[0]);
	}

	/**
	 * 获取set方法名称
	 * 
	 * @param fieldName
	 * @return
	 */
	public static String getSetMethodName(String fieldName) {
		StringBuffer buff = new StringBuffer("set");
		buff.append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1));
		return buff.toString();
	}

	/**
	 * 获取get方法名称
	 * 
	 * @param fieldName
	 * @return
	 */
	public static String getGetMethodName(String fieldName) {
		StringBuffer buff = new StringBuffer("get");
		buff.append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1));
		return buff.toString();
	}

	/**
	 * 把一个object的数组变成指定类型的数组
	 * 
	 * @param value
	 */
	public static Object toPrimitiveArrayAs(Object[] value, Class<?> type) {
		if (value == null)
			return null;
		Object rst = null;
		if (type.equals(boolean[].class)) {
			rst = new boolean[value.length];
			for (int i = 0; i < value.length; i++) {
				((boolean[]) rst)[i] = (Boolean) value[i];
			}
		} else if (type.equals(int[].class)) {
			rst = new int[value.length];
			for (int i = 0; i < value.length; i++) {
				((int[]) rst)[i] = (Integer) value[i];
			}
		} else if (type.equals(char[].class)) {
			rst = new char[value.length];
			for (int i = 0; i < value.length; i++) {
				((char[]) rst)[i] = (Character) value[i];
			}
		} else if (type.equals(double[].class)) {
			rst = new double[value.length];
			for (int i = 0; i < value.length; i++) {
				((double[]) rst)[i] = (Double) value[i];
			}
		} else if (type.equals(float[].class)) {
			rst = new float[value.length];
			for (int i = 0; i < value.length; i++) {
				((float[]) rst)[i] = (Float) value[i];
			}
		}
		return rst;
	}

	/**
	 * 判断是否是一个基本类型数组
	 * 
	 * @param type
	 * @return
	 */
	public static boolean isPrimitiveArray(Class<?> type) {
		return type.isArray() && type.getComponentType().isPrimitive();
	}

	/**
	 * 获取指定函数名称返回类型
	 * 
	 * @param cls
	 * @param getMethodName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Class getReturnType(Class cls, String getMethodName) {
		try {
			Method method = cls.getMethod(getMethodName);
			if (method != null) {
				return method.getReturnType();
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	/**
	 * 调用指定函数名称的函数
	 * 
	 * @param bean
	 * @param methodname
	 * @param argsType
	 * @param value
	 */
	@SuppressWarnings("unchecked")
	public static Object invokeMethod(Object bean, String methodname, Object argsType, Object value) {
		Object rst = null;
		try {
			Method method = null;
			if (argsType == null) {
				method = bean.getClass().getMethod(methodname);
				rst = method.invoke(bean);
			} else if (argsType instanceof Class) {
				method = bean.getClass().getMethod(methodname, (Class<?>) argsType);
				rst = method.invoke(bean, value);
			} else if (argsType.getClass().isArray()) {
				method = bean.getClass().getMethod(methodname, (Class<?>[]) argsType);
				rst = method.invoke(bean, value);
			}
		} catch (IllegalArgumentException e) {
		} catch (Exception e1) {
			logger.error(e1, e1);
		}
		return rst;
	}

	/**
	 * 执行函数
	 * 
	 * @param cls
	 * @param method
	 * @param json
	 * @return
	 * @throws Exception
	 */
	public static Object invokeMethod(Class<?> cls, Method method, String json) throws Exception {
		Object[] arguments = null;
		if (json != null) {
			if (json.startsWith("[") && json.endsWith("]")) {
				List<String> list = (List<String>) JsonUtils.decodeList(json, String.class);
				if (list != null && list.size() > 0) {
					Class<?>[] argsType = method.getParameterTypes();
					arguments = new Object[argsType.length];
					for (int i = 0; i < argsType.length; i++) {
						arguments[i] = JsonUtils.decodeObject(list.get(i), argsType[i]);
					}
				}
			} else {
				arguments = new Object[1];
				arguments[0] = JsonUtils.decodeObject(json, method.getParameterTypes()[0]);
			}
		}
		return _invokeMtd(cls, method, arguments);
	}

	/**
	 * JAVA对象转化为Map
	 * 
	 * @param bean
	 * @param ignoreNull
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map beanToMap(Object bean) {
		try {
			return org.apache.commons.beanutils.BeanUtils.describe(bean);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	/**
	 * 获取一个类是有字段包括继承的
	 * 
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Field[] getClassAllFields(Class bean) {
		Class c = bean;
		List<Field> fieldList = new ArrayList<Field>();
		while (c.getSuperclass() != null) {
			if (c.getName().equals(Object.class.getName())) {
				break;
			} else {
				Field[] fields = c.getDeclaredFields();
				fieldList.addAll(Arrays.asList(fields));
				c = c.getSuperclass();
			}
		}
		return fieldList.toArray(new Field[0]);
	}

	/**
	 * 获取一个类是有method包括继承的
	 * 
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Method[] getClassAllMethods(Class bean) {
		Class c = bean;
		List<Method> fieldList = new ArrayList<Method>();
		while (c.getSuperclass() != null) {
			if (c.getName().equals(Object.class.getName())) {
				break;
			} else {
				Method[] Methods = c.getMethods();
				fieldList.addAll(Arrays.asList(Methods));
				c = c.getSuperclass();
			}
		}
		return fieldList.toArray(new Method[0]);
	}

	/**
	 * 获取基本类型的包装类型，如果无包装类就返回本身
	 * 
	 * @param c
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Class getSynthetic(Class c) {
		if (c.isPrimitive()) {
			if (c.getName().equals("int")) {
				return Integer.class;
			} else if (c.getName().equals("float")) {
				return Float.class;
			} else if (c.getName().equals("double")) {
				return Double.class;
			} else if (c.getName().equals("char")) {
				return Character.class;
			} else if (c.getName().equals("byte")) {
				return Byte.class;
			} else if (c.getName().equals("long")) {
				return Long.class;
			} else if (c.getName().equals("short")) {
				return Short.class;
			} else if (c.getName().equals("boolean")) {
				return Boolean.class;
			}
		}
		return c;
	}

	/**
	 * 获取基本类型，如果无基本类型返回null
	 * 
	 * @param c
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Class getPrimitive(Class c) {
		if (c.isPrimitive()) {
			return c;
		}
		try {
			return (Class) c.getField("TYPE").get("TYPE");
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 获取指定class所有method的map
	 * 
	 * @param bean
	 * @param single
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getClassAllMethodsMap(Class bean, boolean... single) {
		Map<String, Object> map = new HashMap<String, Object>();
		Method[] methods = getClassAllMethods(bean);
		if (methods != null) {
			boolean isSingle = single != null ? single[0] : false;
			if (isSingle) {
				for (Method method : methods) {
					if (!map.containsKey(method.getName())) {
						map.put(method.getName(), method);
					}
				}
			} else {
				for (Method method : methods) {
					if (!map.containsKey(method.getName())) {
						map.put(method.getName(), method);
					} else {
						if (map.get(method.getName()) instanceof Method) {
							List<Method> list = new ArrayList<Method>();
							list.add((Method) map.get(method.getName()));
							list.add(method);
							map.put(method.getName(), list);
						} else {
							((List<Method>) map.get(method.getName())).add(method);
						}
					}
				}
			}
		}
		return map;
	}

	/**
	 * 将字符串转化为数字格式,如果转换类型不是number则返回其本身,如果返回类型是number,但是转化失败返回null
	 * 
	 * @param str
	 *            字符串
	 * @param returnType
	 *            基本类型class
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Object stringToNumber(Object str, Class returnType) {
		if (str == null)
			return null;
		Class primitiveCls = null;
		if (returnType.isArray()) {
			returnType = returnType.getComponentType();
		}
		if (returnType.isPrimitive()) {
			primitiveCls = returnType;
			returnType = getSynthetic(returnType);
		} else {
			primitiveCls = getPrimitive(returnType);
		}
		if (!Number.class.equals(returnType.getSuperclass())) {
			return str;
		}
		Object rst = str;
		try {
			Method parseMethod = returnType.getMethod(getMethodName(primitiveCls.getName(), "parse"), String.class);
			if (str.getClass().isArray() && str.getClass().getComponentType().equals(String.class)) {
				String[] strs = (String[]) str;
				rst = Array.newInstance(primitiveCls, strs.length);
				for (int i = 0; i < strs.length; i++) {
					if (strs[i].equals(""))
						Array.set(rst, i, getPrimitiveDefault(primitiveCls));
					else
						Array.set(rst, i, parseMethod.invoke(null, strs[i]));
				}
			} else if (str.getClass().equals(String.class)) {
				if (str.equals(""))
					rst = getPrimitiveDefault(primitiveCls);
				else
					rst = parseMethod.invoke(null, str);
			} else {
				formatNumber(str.toString(), returnType);
			}
		} catch (Exception e) {
		}
		return rst;
	}

	@SuppressWarnings("unchecked")
	public static Object getPrimitiveDefault(Class cls) {
		if (cls != null && cls.isPrimitive()) {
			if (cls == boolean.class) {
				return false;
			} else if (cls == byte.class || cls == short.class || cls == int.class || cls == long.class) {
				return 0;
			} else if (cls == char.class) {
				return '\u0000';
			} else if (cls == float.class || cls == double.class) {
				return 0.0;
			}
		}
		return null;
	}

	/**
	 * prefix前缀，比如setField ：prefix = set ，name = field
	 * 
	 * @param name
	 * @param type
	 * @return
	 */
	public static String getMethodName(String name, String prefix) {
		return prefix + Character.toUpperCase(name.charAt(0)) + name.substring(1);
	}

	/**
	 * 获取指定的函数，如果函数不存在返回null
	 * 
	 * @param cls
	 * @param methodName
	 * @param parameterType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Method getMethod(Class<? extends Object> cls, String methodName, Class parameterType) {
		try {
			return cls.getMethod(methodName, parameterType);
		} catch (SecurityException e) {
		} catch (NoSuchMethodException e) {
		}
		return null;
	}

	/**
	 * 将复合类型转换为基本类型，如果转换失败返回null
	 * 
	 * @param object
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Object toPrimitive(Object object, Class cls) throws Exception {
		if (object == null || cls == null || !cls.isPrimitive())
			return null;
		if (object.getClass().isPrimitive()) {
			if (object.getClass().equals(cls))
				return object;
			else {
				Constructor cons = getSynthetic(object.getClass()).getConstructor(object.getClass());
				if (cons != null)
					object = cons.newInstance(object);
			}
		}
		if (cls.equals(boolean.class)) {
			if (String.class.isInstance(object))
				return object.toString().equalsIgnoreCase("true");
			else if (Boolean.class.isInstance(object))
				return ((Boolean) object).booleanValue();
		} else if (cls.equals(byte.class)) {
			if (String.class.isInstance(object) || Character.class.isInstance(object))
				return Byte.parseByte(object.toString());
			else if (object.getClass().getMethod("byteValue") != null)
				return object.getClass().getMethod("byteValue").invoke(object);
		} else if (cls.equals(char.class)) {
			if (String.class.isInstance(object) || Character.class.isInstance(object))
				return object.toString().toCharArray()[0];
		} else if (cls.equals(short.class) || cls.equals(int.class) || cls.equals(long.class) || cls.equals(Float.class) || cls.equals(double.class)) {
			return stringToNumber(object, cls);
		}
		return null;
	}

	/**
	 * 执行函数
	 * 
	 * @param cls
	 * @param method
	 * @param arguments
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Object _invokeMtd(Class cls, Method method, Object[] arguments) throws Exception {
		Object bean = null;
		if (cls != null && !Modifier.isStatic(method.getModifiers())) {
			bean = cls.newInstance();
		}
		if (arguments == null || arguments.length == 0)
			return method.invoke(bean);
		else if (arguments.length == 1)
			return method.invoke(bean, arguments[0]);
		else
			return method.invoke(bean, arguments);
	}

	/**
	 * 将字符串转换为Number类型 包括 BigDecimal, BigInteger, Byte, Double, Float, Integer,
	 * Long, and Short
	 * 
	 * @param str
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Object formatNumber(String str, Class cls) throws Exception {
		if (str == null || !StringUtils.isNumber(str)) {
			return null;
		} else {
			if (cls.isPrimitive()) {
				cls = ReflectUtils.getSynthetic(cls);
			}
			Constructor cons = cls.getConstructor(String.class);
			return cons.newInstance(str);
		}
	}

	/**
	 * 获取函数
	 * 
	 * @param cls
	 * @param methodName
	 * @param argTypes
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Method getMethod(Class cls, String methodName, Class... argTypes) {
		try {
			return cls.getMethod(methodName, argTypes);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 获取属性值
	 * 
	 * @param sqlMapClient
	 * @param string
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Object getFieldValue(Object target, String fieldName) throws Exception {
		if (target == null || fieldName == null)
			return null;
		Field field = getField(target.getClass(), fieldName);
		if (field == null) {
			if (target instanceof Map) {
				return ((Map) target).get(fieldName);
			} else
				Assert.isNull("属性" + fieldName + "在实体" + target.getClass() + "中找不到！");
		}
		Method method = getMethod(target.getClass(), getMethodName(fieldName, "get"), new Class[0]);
		if (method == null) {
			if (!Modifier.isPublic(field.getModifiers())) {
				field.setAccessible(true);
			}
			return field.get(target);
		} else {
			return method.invoke(target);
		}
	}

	/**
	 * 设置属性值
	 * 
	 * @param target
	 * @param fieldName
	 * @param value
	 */
	@SuppressWarnings("unchecked")
	public static void setFieldValue(Object target, String fieldName, Object value) throws Exception {
		if (target == null || fieldName == null)
			return;
		Field field = getField(target.getClass(), fieldName);
		if (field == null) {
			if (target instanceof Map) {
				((Map) target).put(fieldName, value);
			} else
				Assert.isNull("属性" + fieldName + "在实体" + target.getClass() + "中找不到！");
		}
		Method method = getMethod(target.getClass(), getMethodName(fieldName, "get"), new Class[0]);
		if (method != null) {
			Method m = getMethod(target.getClass(), getMethodName(fieldName, "set"), new Class[] { method.getReturnType() });
			if (m != null) {
				m.invoke(target, value);
				return;
			}
		}
		if (!Modifier.isPublic(field.getModifiers())) {
			field.setAccessible(true);
		}
		field.set(target, value);
	}

	/**
	 * 获取字段对象
	 * 
	 * @param cls
	 * @param fieldName
	 * @return
	 */
	public static Field getField(Class<? extends Object> cls, String fieldName) {
		while (cls.getSuperclass() != null) {
			if (cls.getName().equals(Object.class.getName())) {
				break;
			} else {
				Field[] fields = cls.getDeclaredFields();
				if (fields != null) {
					for (Field field : fields) {
						if (field.getName().equals(fieldName)) {
							return field;
						}
					}
				}
				cls = cls.getSuperclass();
			}
		}
		return null;
	}

	/**
	 * 获取obj对象fieldName的Field
	 * 
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static Field getFieldByFieldName(Object obj, String fieldName) {
		for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
			}
		}
		return null;
	}

	/**
	 * 获取obj对象fieldName的属性值
	 * 
	 * @param obj
	 * @param fieldName
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static Object getValueByFieldName(Object obj, String fieldName) throws SecurityException, NoSuchFieldException, IllegalArgumentException,
			IllegalAccessException {
		Field field = getFieldByFieldName(obj, fieldName);
		return getValueByField(obj, field);
	}
	
	public static Object getValueByField(Object obj, Field field) throws IllegalArgumentException, IllegalAccessException{
		Object value = null;
		if (field != null) {
			if (field.isAccessible()) {
				value = field.get(obj);
			} else {
				field.setAccessible(true);
				value = field.get(obj);
				field.setAccessible(false);
			}
		}
		return value;
		
	}

	/**
	 * 设置obj对象fieldName的属性值
	 * 
	 * @param obj
	 * @param fieldName
	 * @param value
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static void setValueByFieldName(Object obj, String fieldName, Object value) throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		Field field = obj.getClass().getDeclaredField(fieldName);
		if (field.isAccessible()) {
			field.set(obj, value);
		} else {
			field.setAccessible(true);
			field.set(obj, value);
			field.setAccessible(false);
		}
	}
}