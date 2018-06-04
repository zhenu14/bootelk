package com.logalert.util;
import java.util.List;
import java.util.Map;

public class EsHelper {
    private Map<String,Long> appnames;
    private List<Map<String, Object>> sourceList;

    public Map<String, Long> getAppnames() {
        return appnames;
    }

    public void setAppnames(Map<String, Long> appnames) {
        this.appnames = appnames;
    }

    public List<Map<String, Object>> getSourceList() {
        return sourceList;
    }

    public void setSourceList(List<Map<String, Object>> sourceList) {
        this.sourceList = sourceList;
    }
}
