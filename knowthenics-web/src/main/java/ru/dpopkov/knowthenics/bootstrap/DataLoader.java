package ru.dpopkov.knowthenics.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.dpopkov.knowthenics.model.*;
import ru.dpopkov.knowthenics.services.*;

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
        Category catCore = saveCategory("Java Core", "Java syntax and main library classes");
        saveCategory("Spring", "Spring framework");
        System.out.println("Category loaded ...");

        saveQuestion(catCore, "What is JVM?", "Java Virtual Machine");
        saveQuestion(catCore, "What is JRE?", "Java Runtime Environment");
        Question jdk = saveQuestion(catCore, "What is JDK?", "Java Development Kit");
        System.out.println("Questions loaded ...");

        Source wiki = new Source();
        wiki.setShortTitle("Wikipedia");
        wiki.setFullTitle("Wikipedia - the Free Encyclopedia");
        wiki.setSourceType("web");
        wiki.setUrl("https://www.wikipedia.org/");
        sourceService.save(wiki);
        System.out.println("Source loaded ...");

        Answer ansJdk = saveAnswer(jdk,
                "The Java Development Kit is an implementation of the Java Platform released by Oracle"
                        + " in the form of a binary product aimed at Java developers.", wiki);
        jdk.setPreferredAnswer(ansJdk);
        System.out.println("Answer loaded ...");
    }

    private Question saveQuestion(Category category, String wording, String shortAnswer) {
        Question question = new Question();
        question.setCategory(category);
        question.setWordingEn(wording);
        question.setShortAnswerEn(shortAnswer);
        questionService.save(question);
        return question;
    }

    private Answer saveAnswer(Question question, String wording, Source source) {
        Answer answer = new Answer();
        answer.setQuestion(question);
        answer.setWordingEn(wording);
        answer.setAnswerType("original");
        answer.setSource(source);
        answerService.save(answer);
        return answer;
    }

    private Category saveCategory(String name, String description) {
        Category cat = new Category();
        cat.setName(name);
        cat.setDescription(description);
        categoryService.save(cat);
        return cat;
    }
}
