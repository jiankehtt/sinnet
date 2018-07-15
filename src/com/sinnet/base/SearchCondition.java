package com.sinnet.base;


public class SearchCondition {

	private String name;
	
	private Option option;
	
	private String value;

	private Type type;

	public SearchCondition() {
	}

	public SearchCondition(String name, Option option, String value, Type type) {
		this.name = name;
		this.option = option;
		this.value = value;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Option getOption() {
		return option;
	}

	public void setOption(Option option) {
		this.option = option;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}


	public static enum Option {
		LIKE_PRIFIX("-%"),	
		LIKE_SUFFIX("%-"),	
		LIKE("%-%"), 		
		EQ("="), 			
		NOT_EQ("!="), 		
		BIGGER(">"), 		
		BIGGER_EQ(">="), 	
		SMALLER("<"), 		
		SMALLER_EQ("<=")	
		;
		private String op;

		private Option(String op) {
			this.op = op;
		}
		
		public String getOp(){
			return op;
		}
	}
	

	public static enum Type{
		TEXT, 		
		INT, 		
		DOUBLE, 	
		TIME		
	}
}
