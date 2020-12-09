package ru.dpopkov.knowthenics.model;

public class QuestionStat {
    private int interviewUsageCount;
    private int drillCount;
    private int successCount;
    private double knowingRate;

    public int getInterviewUsageCount() {
        return interviewUsageCount;
    }

    public void setInterviewUsageCount(int interviewUsageCount) {
        this.interviewUsageCount = interviewUsageCount;
    }

    public int getDrillCount() {
        return drillCount;
    }

    public void setDrillCount(int drillCount) {
        this.drillCount = drillCount;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(int successCount) {
        this.successCount = successCount;
    }

    public double getKnowingRate() {
        return knowingRate;
    }

    public void setKnowingRate(double knowingRate) {
        this.knowingRate = knowingRate;
    }
}
