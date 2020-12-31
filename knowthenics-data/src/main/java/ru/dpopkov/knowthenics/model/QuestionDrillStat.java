package ru.dpopkov.knowthenics.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "question_drill_stats")
public class QuestionDrillStat extends BaseEntity {

    @Column(name = "drill_count")
    private int drillCount;

    @Column(name = "success_count")
    private int successCount;

    @Column(name = "knowing_rate")
    private double knowingRate;

    @Builder
    public QuestionDrillStat(Long id, int drillCount, int successCount, double knowingRate) {
        super(id);
        this.drillCount = drillCount;
        this.successCount = successCount;
        this.knowingRate = knowingRate;
    }
}
