package com.ysh.projectY.form;

public class BCSchoolsCOVID19TotalSummary {

    private long total;
    private String updateDateTime;
    private String weeklyChanges;
    private String dailyChanges;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public String getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(String updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public String getWeeklyChanges() {
        return weeklyChanges;
    }

    public void setWeeklyChanges(String weeklyChanges) {
        this.weeklyChanges = weeklyChanges;
    }

    public String getDailyChanges() {
        return dailyChanges;
    }

    public void setDailyChanges(String dailyChanges) {
        this.dailyChanges = dailyChanges;
    }

    @Override
    public String toString() {
        return "BCSchoolsCOVID19TotalSummary{" +
                "total=" + total +
                ", updateDateTime='" + updateDateTime + '\'' +
                ", weeklyChanges='" + weeklyChanges + '\'' +
                ", dailyChanges='" + dailyChanges + '\'' +
                '}';
    }
}
