package ru.dpopkov.knowthenics.dto;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class QuestionDTO extends BaseDTO {

    private CategoryDTO category;
    private String wordingEn;
    private String wordingRu;
    private String shortAnswerEn;
    private String shortAnswerRu;
    private AnswerDTO preferredAnswer;
    private Set<AnswerDTO> answers = new HashSet<>();
    // Field for QuestionDrillStat not implemented yet
    private String comment;
    private Set<KeyTermDTO> keyTerms = new HashSet<>();
    private int interviewUsageCount;

    @Builder
    public QuestionDTO(Long id, CategoryDTO category, String wordingEn, String wordingRu, String shortAnswerEn,
                       String shortAnswerRu, AnswerDTO preferredAnswer, Set<AnswerDTO> answers, String comment,
                       Set<KeyTermDTO> keyTerms, int interviewUsageCount) {
        super(id);
        this.category = category;
        this.wordingEn = wordingEn;
        this.wordingRu = wordingRu;
        this.shortAnswerEn = shortAnswerEn;
        this.shortAnswerRu = shortAnswerRu;
        this.preferredAnswer = preferredAnswer;
        this.answers = answers != null ? answers : new HashSet<>();
        this.comment = comment;
        this.keyTerms = keyTerms != null ? keyTerms : new HashSet<>();
        this.interviewUsageCount = interviewUsageCount;
    }
}
