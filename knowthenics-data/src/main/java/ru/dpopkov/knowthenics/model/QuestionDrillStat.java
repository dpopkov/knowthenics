package ru.dpopkov.knowthenics.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "question_drill_stats")
public class QuestionDrillStat extends BaseEntity {

    @Column(name = "drill_count")
    private int drillCount;

    @Column(name = "success_count")
    private int successCount;

    @Column(name = "knowing_rate")
    private double knowingRate;

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
