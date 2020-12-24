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
    private final KeyTermService keyTermService;

    public DataLoader(AnswerService answerService, CategoryService categoryService,
                      QuestionService questionService, SourceService sourceService,
                      KeyTermService keyTermService) {
        this.answerService = answerService;
        this.categoryService = categoryService;
        this.questionService = questionService;
        this.sourceService = sourceService;
        this.keyTermService = keyTermService;
    }

    /**
     * Callback used to run the bean.
     */
    @Override
    public void run(String... args) {
        Category catCore = saveCategory("Java Core", "Java syntax and main library classes");
        saveCategory("Spring", "Spring framework");
        Category catJdbc = saveCategory("JDBC", "JDBC");
        System.out.println("Categories loaded ...");

        KeyTerm jdkTerm = new KeyTerm("JDK", "JDK - Java Development Kit");
        keyTermService.save(jdkTerm);
        KeyTerm jreTerm = new KeyTerm("JRE", "JRE - Java Runtime Environment");
        keyTermService.save(jreTerm);
        KeyTerm jdbcTerm = new KeyTerm("JDBC", "Java database connectivity");
        keyTermService.save(jdbcTerm);
        System.out.println("KeyTerms loaded ...");

        saveQuestion(catCore, "What is JVM?", "Java Virtual Machine");
        saveQuestion(catCore, "What is JRE?", "Java Runtime Environment", jreTerm);
        Question jdk = saveQuestion(catCore, "What is JDK?", "Java Development Kit", jdkTerm);
        Question jdbc = saveQuestion(catJdbc, "What is JDBC?", "Java database connectivity", jdbcTerm);
        System.out.println("Questions loaded ...");

        Source wiki = saveSource("Wikipedia", "Wikipedia - the Free Encyclopedia", "https://www.wikipedia.org/");
        Source javarevisitedBlog = saveSource("Javarevisited", "Javarevisited - Blog about Java, "
                + "Programming, Spring, Hibernate, Interview Questions, Books and Online Course Recommendations",
                "https://javarevisited.blogspot.com");
        Source baeldung = saveSource("Baeldung",
                "Baeldung helps developers explore the Java ecosystem and simply be better engineers.",
                "https://www.baeldung.com");
        System.out.println("Sources loaded ...");

        Answer ansJdk = saveAnswer(jdk,
                "The Java Development Kit is an implementation of the Java Platform released by Oracle"
                        + " in the form of a binary product aimed at Java developers.", wiki,
                "https://en.wikipedia.org/wiki/Java_Development_Kit", jdkTerm);
        jdk.setPreferredAnswer(ansJdk);
        saveAnswer(jdk,
                "JDK (Java Development Kit) is a software development environment "
                        + "used in Java platform programming. It contains a complete Java Runtime Environment, "
                        + "a so-called private runtime. The name came from the fact that it contains more tools than "
                        + "the standalone JRE as well as the other components needed for developing Java applications.",
                baeldung, "https://www.baeldung.com/oracle-jdk-vs-openjdk", jdkTerm, jreTerm);
        questionService.save(jdk);

        Answer ansJdbc = saveAnswer(jdbc, "JDBC is Java database connectivity as name implies it’s a java API "
                + "for communicating to relational database, API has java classes and interfaces using that developer can "
                + "easily interact with database.", javarevisitedBlog,
                "https://javarevisited.blogspot.com/2012/12/top-10-jdbc-interview-questions-answers.html",
                jdbcTerm);
        jdbc.setPreferredAnswer(ansJdbc);
        questionService.save(jdbc);
        System.out.println("Answers loaded ...");
    }

    private Question saveQuestion(Category category, String wording, String shortAnswer, KeyTerm... keyTerms) {
        Question question = new Question();
        question.setCategory(category);
        question.setWordingEn(wording);
        question.setShortAnswerEn(shortAnswer);
        question.setStat(new QuestionDrillStat());
        for (KeyTerm kt : keyTerms) {
            question.addKeyTerm(kt);
        }
        questionService.save(question);
        return question;
    }

    private Source saveSource(String title, String fullTitle, String url) {
        Source source = new Source();
        source.setShortTitle(title);
        source.setFullTitle(fullTitle);
        source.setSourceType(SourceType.WEB_SITE);
        source.setUrl(url);
        sourceService.save(source);
        return source;
    }

    private Answer saveAnswer(Question question, String wording, Source source, String sourceDetails,
                              KeyTerm... keyTerms) {
        Answer answer = new Answer();
        answer.setQuestion(question);
        answer.setWordingEn(wording);
        answer.setAnswerType(AnswerType.ORIGINAL);
        answer.setSource(source);
        answer.setSourceDetails(sourceDetails);
        for (KeyTerm kt : keyTerms) {
            answer.addKeyTerm(kt);
        }
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
