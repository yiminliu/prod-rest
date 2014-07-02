package com.bedrosians.bedlogic.domain.account.enums;


public enum Status {

		ACTIVE("ACTIVE"),
		INACTIVE("INACTIVE"),
		DISCONTINUED("DISCONTINUED"),
		DELETED("DELETED");
			
		private String name;
		private Status(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
}
