package com.realpower.petitionwatch.net.param;

/**
 * Created by Administrator on 2018/1/4.
 */

public class SearchMonitoredParam {

    /**
     * criteria : string
     * id : string
     */

    private String criteria;
    private String id;

    public SearchMonitoredParam(String criteria) {
        this.criteria = criteria;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
