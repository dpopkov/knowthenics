package ru.dpopkov.knowthenics.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.dpopkov.knowthenics.model.*;
import ru.dpopkov.knowthenics.services.*;
import ru.dpopkov.knowthenics.services.map.*;

/**
 * Initializes data on startup or the application.
 */
@Component
public class DataLoader implements CommandLineRunner {

    private final AnswerService answerService;
    private final CategoryService categoryService;
    private final QuestionService questionService;
    private final SourceService sourceService;

    public DataLoader(AnswerService answerService, CategoryService categoryService,
                      QuestionService questionService, SourceService sourceService) {
        this.answerService = answerService;
        this.categoryService = categoryService;
        this.questionService = questionService;
        this.sourceService = sourceService;
    }

    /**
     * Callback used to run the bean.
     */
    @Override
    public void run(String... args) {
        Category catCore = saveCategory();
        System.out.println("Category loaded ...");

        saveQuestion(1L, catCore, "What is JVM?", "Java Virtual Machine");
        saveQuestion(2L, catCore, "What is JRE?", "Java Runtime Environment");
        Question jdk = saveQuestion(3L, catCore, "What is JDK?", "Java Development Kit");
        System.out.println("Questions loaded ...");

        Source wiki = new Source();
        wiki.setId(1L);
        wiki.setShortTitle("Wikipedia");
        wiki.setFullTitle("Wikipedia - the Free Encyclopedia");
        wiki.setSourceType("web");
        wiki.setUrl("https://www.wikipedia.org/");
        sourceService.save(wiki);
        System.out.println("Source loaded ...");

        saveAnswer(1L, jdk,
                "The Java Development Kit is an implementation of the Java Platform released by Oracle"
                        + " in the form of a binary product aimed at Java developers.", wiki);
        System.out.println("Answer loaded ...");
    }

    private Question saveQuestion(Long id, Category category, String wording, String shortAnswer) {
        Question question = new Question();
        question.setId(id);
        question.setCategory(category);
        question.setWordingEn(wording);
        question.setShortAnswerEn(shortAnswer);
        questionService.save(question);
        return question;
    }

    private void saveAnswer(Long id, Question question, String wording, Source source) {
        Answer answer = new Answer();
        answer.setId(id);
        answer.setQuestion(question);
        answer.setWordingEn(wording);
        answer.setAnswerType("original");
        answer.setSource(source);
        answerService.save(answer);
    }

    private Category saveCategory() {
        Category catCore = new Category();
        catCore.setId(1L);
        catCore.setName("Java Core");
        catCore.setDescription("Java syntax and main library classes");
        categoryService.save(catCore);
        return catCore;
    }
}
