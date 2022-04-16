package com.model2.mvc.common;


public class SearchVO {
	
	private int page; //현재페이지
	String searchCondition; 
	String searchKeyword;
	int pageUnit; //조회된 페이지 건수 - list.size() = 3, 정보가 들어간 건수는 map.size() = 2로 표현
	
	public SearchVO(){
	}
	
	public int getPageUnit() {
		return pageUnit;
	}
	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}

	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
}