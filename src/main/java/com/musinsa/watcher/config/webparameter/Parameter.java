package com.musinsa.watcher.config.webparameter;

public enum Parameter {
  BRAND("brand"),
  CATEGORY("category"),
  MIN_PRICE("minprice"),
  MAX_PRICE("maxprice"),
  ONLY_TODAY_UPDATED_DATA("onlyTodayUpdatedData"),
  PAGE_SIZE("pagesize"),
  PAGE_NUM("page"),
  SORT("sort"),
  NULL("");

  String parameter;

  Parameter(String parameter){
    this.parameter = parameter;
  }

  public String getParameter() {
    return parameter;
  }
}
