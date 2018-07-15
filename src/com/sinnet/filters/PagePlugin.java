package com.sinnet.filters;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Properties;

import javax.xml.bind.PropertyException;

import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.log4j.Logger;

import com.sinnet.base.PageBean;
import com.sinnet.base.SearchCondition;
import com.sinnet.utils.DateUtils;
import com.sinnet.utils.ReflectUtils;
import com.sinnet.utils.StringUtils;
import com.sinnet.utils.Tools;


@Intercepts({ 
		@Signature(method = "prepare", type = StatementHandler.class, args = { Connection.class }),
		@Signature(type = StatementHandler.class, method = "parameterize", args = { Statement.class }) })
public class PagePlugin implements Interceptor {

	private String pageSqlId = ""; 
	private Logger logger = Logger.getLogger(PagePlugin.class);


	public Object intercept(Invocation ivk) throws Throwable {
		Method m = ivk.getMethod();
		if ("prepare".equals(m.getName())) {
			return prepare(ivk);
		} else if ("parameterize".equals(m.getName())) {
			return parameterize(ivk);
		}
		return ivk.proceed();
	}

	@SuppressWarnings("unchecked")
	public Object parameterize(Invocation ivk) throws Throwable {
		Object returnObj = ivk.proceed();
		if (ivk.getTarget() instanceof RoutingStatementHandler) {
			RoutingStatementHandler statementHandler = (RoutingStatementHandler) ivk.getTarget();
			BaseStatementHandler delegate = (BaseStatementHandler) ReflectUtils.getValueByFieldName(statementHandler, "delegate");
			MappedStatement mappedStatement = (MappedStatement) ReflectUtils.getValueByFieldName(delegate, "mappedStatement");
		
			if (mappedStatement.getId().toLowerCase().matches(pageSqlId)) {	// 9����Ҫ��ҳ��SQL
				Statement stmt = (Statement) ivk.getArgs()[0];
				if(stmt instanceof PreparedStatement){
					PreparedStatement ps = (PreparedStatement) stmt;	// ��ȡ��ѯ���
					
					Map<String, Object> objs = (Map<String, Object>) statementHandler.getBoundSql().getParameterObject();
					if(objs != null){
						for(int i = 0, size = objs.size(); i < size; i++){
							ps.setObject(i+1, objs.get((i+1+"")));
						}
					}
				}
			}
		}

		return returnObj;
	}

	public Object prepare(Invocation ivk) throws Throwable {
		if (ivk.getTarget() instanceof RoutingStatementHandler) {
			RoutingStatementHandler statementHandler = (RoutingStatementHandler) ivk.getTarget();
			BaseStatementHandler delegate = (BaseStatementHandler) ReflectUtils.getValueByFieldName(statementHandler, "delegate");
			MappedStatement mappedStatement = (MappedStatement) ReflectUtils.getValueByFieldName(delegate, "mappedStatement");

			if (mappedStatement.getId().toLowerCase().matches(pageSqlId)) { // 9����Ҫ��ҳ��SQL
				BoundSql boundSql = delegate.getBoundSql();
				Object parameterObject = boundSql.getParameterObject();// ��ҳSQL<select>��parameterType���Զ�Ӧ��ʵ�����Mapper�ӿ���ִ�з�ҳ�����Ĳ���,�ò����Ϊ��
				if (parameterObject == null) {
					throw new NullPointerException("parameterObject��δʵ��");
				} else {
					Pair pair = Pair.parse(parameterObject);
					if (pair == null) {
						pair = new Pair();
					}

					Connection connection = (Connection) ivk.getArgs()[0];
					String sqlParamStr = boundSql.getSql();

					String[] sqlParam = sqlParamStr.split(";");
					if (sqlParam.length != 2) { 
						return ivk.proceed();
					}
				
					String columns = sqlParam[0];
					String tableName = sqlParam[1];

					
					List<SearchCondition> conditions = pair.list;
					List<Object> params = new ArrayList<Object>();
					String sql = getSqlQueryString(tableName, conditions, params);

					
					int count = count(connection, "select count(1) " + sql, params);
					if (count < 0)
						return ivk.proceed();

					if (pair.page == null)
						pair.page = new PageBean();

					
					pair.page.setTotalRecorde(count);

					
					String pageSql = generatePageSql("select " + columns + " " + sql, pair.page, params, boundSql, pair, ivk);
					logger.debug("pageSql : "+pageSql);
					ReflectUtils.setValueByFieldName(boundSql, "sql", pageSql); // ����ҳsql��䷴���BoundSql.
				}
			}
		}
		return ivk.proceed();
	}


	private void setParameters(PreparedStatement ps, List<Object> parameterObject) throws SQLException {
		int i = 1;
		for (Object val : parameterObject) {
			ps.setObject(i++, val);
		}
	}


	private String generatePageSql(String sql, PageBean page, List<Object> params, BoundSql boundSql, Pair pair, Invocation ivk)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>(); 

		int i = 1;
		for (Object condition : params) { 
			sql = sql.replaceFirst("--", "?");
			map.put(i + "", condition);
			i++;
		}
		ReflectUtils.setValueByFieldName(boundSql, "parameterObject", map); 
		
		if(page.getPageNo() < 1)
			page.setPageNo(1);
		
		
		StringBuilder pageSql = new StringBuilder(sql);
		if (!StringUtils.isEmpty(page.getOrderProperty())) {
			pageSql.append(" order by ");
			pageSql.append(page.getOrderProperty());
			pageSql.append(" " + page.getOrderDesc());
		}
		pageSql.append(" limit ");
		pageSql.append((page.getPageNo() - 1) * page.getPageSize()+"");
		pageSql.append(",");
		pageSql.append(page.getPageSize()+"");
		return pageSql.toString();
	}

	public Object plugin(Object arg0) {
		return Plugin.wrap(arg0, this);
	}

	public void setProperties(Properties p) {
		pageSqlId = p.getProperty("pageSqlId");
		if (Tools.isEmpty(pageSqlId)) {
			try {
				throw new PropertyException("pageSqlId is null");
			} catch (PropertyException e) {
				e.printStackTrace();
			}
		}
	}

	private String getSqlQueryString(String tableName, List<SearchCondition> conditions, List<Object> params) {
		StringBuilder sb = new StringBuilder("from " + tableName);
		sb.append(" where 1=1");
		Object value = null;
		if (conditions != null && conditions.size() > 0) {
			for (ListIterator<SearchCondition> it = conditions.listIterator(); it.hasNext();) {
				SearchCondition condition = it.next();
				if (condition != null && (value = filterConditionAndGetValue(condition)) != null) {
					sb.append(" and ");
					sb.append(condition.getName());
					sb.append(getValueAndConditionOfCondition(condition, params, value));
				} else {
					it.remove(); 
				}
			}
		}
		return sb.toString();
	}


	public String getValueAndConditionOfCondition(SearchCondition condition, List<Object> params, Object refer) {
		String ret = "--";
		switch (condition.getOption()) { 
		case LIKE:
		case LIKE_SUFFIX:
		case LIKE_PRIFIX:
			ret = " like '" + condition.getOption().getOp().replace("-", refer.toString()) + "'";
			break;
		default:
			ret = condition.getOption().getOp() + ret;
			params.add(refer);
			break;
		}
		return ret;
	}


	public Object filterConditionAndGetValue(SearchCondition condition) {
		Object ret = null;
		String value = condition.getValue();
		SearchCondition.Type curType = condition.getType();

		switch (curType) {
		case TEXT:
			if (!StringUtils.isEmpty(value))
				ret = value;
			break;
		case DOUBLE:
			try {
				ret = Double.valueOf(value);
			} catch (Exception e) {
			}
		case INT:
			try {
				ret = Integer.valueOf(value);
			} catch (Exception e) {
			}
		case TIME:
			try {
				ret = DateUtils.parseIso8601Date(value); // YYYY-MM-dd
			} catch (Exception e) {
			}
		}
		return ret;
	}


	@SuppressWarnings("unchecked")
	public
	static class Pair {
		public PageBean page;
		public List<SearchCondition> list;

		private Pair() {
			super();
		}

		public Pair(HashMap map) {
			for (Object obj : map.keySet()) {
				Object val = map.get(obj);
				if (val instanceof PageBean) {
					this.page = (PageBean) val;
				} else if (val instanceof List) {
					this.list = (List<SearchCondition>) val;
				}
			}
		}

		public Pair(PageBean p, List<SearchCondition> list) {
			this.page = p;
			this.list = list;
		}

		public static Pair parse(Object parameterObject) {
			Pair dataPair = null;
			
			if (parameterObject instanceof HashMap) {
				dataPair = new Pair((HashMap) parameterObject);
			} else if (parameterObject instanceof PageBean) {
				dataPair = new Pair((PageBean) parameterObject, null);
			} else if (parameterObject instanceof List) {
				dataPair = new Pair(null, (List<SearchCondition>) parameterObject);
			}
			return dataPair;
		}
	}

	
	private int count(Connection connection, String sql, List<Object> params) throws Exception {
		sql = sql.replace("--", "?"); 
		PreparedStatement countStmt = null;
		ResultSet rs = null;
		try {
			countStmt = connection.prepareStatement(sql);
			setParameters(countStmt, params);
			rs = countStmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			logger.warn("sql:" + sql, e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (countStmt != null)
					countStmt.close();
			} catch (Exception e) {
			}
		}
		return -1;
	}

	public String getPageSqlId() {
		return pageSqlId;
	}

	public void setPageSqlId(String pageSqlId) {
		this.pageSqlId = pageSqlId;
	}
}
