package ru.dpopkov.knowthenics.dto;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class AnswerDTO extends BaseDTO {

    private Long questionId;
    private String wordingEn;
    private String wordingRu;
    private String answerType;
    private SourceDTO source;
    private String sourceDetails;
    private String comment;
    private Set<KeyTermDTO> keyTerms = new HashSet<>();

    @Builder
    public AnswerDTO(Long id, Long questionId, String wordingEn, String wordingRu, String answerType, SourceDTO source,
                     String sourceDetails, String comment, Set<KeyTermDTO> keyTerms) {
        super(id);
        this.questionId = questionId;
        this.wordingEn = wordingEn;
        this.wordingRu = wordingRu;
        this.answerType = answerType;
        this.source = source;
        this.sourceDetails = sourceDetails;
        this.comment = comment;
        this.keyTerms = keyTerms != null ? keyTerms : new HashSet<>();
    }
}
