package ru.dpopkov.knowthenics.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.dpopkov.knowthenics.model.*;
import ru.dpopkov.knowthenics.repositories.UserRepository;
import ru.dpopkov.knowthenics.services.*;

import java.util.Set;

/**
 * Initializes data on startup or the application.
 */
@Slf4j
@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final AnswerService answerService;
    private final CategoryService categoryService;
    private final QuestionService questionService;
    private final SourceService sourceService;
    private final KeyTermService keyTermService;
    private final QCollectionService qCollectionService;
    private final FlashCardService flashCardService;
    private final DeckService deckService;

    public DataLoader(UserRepository userRepository, AnswerService answerService, CategoryService categoryService,
                      QuestionService questionService, SourceService sourceService,
                      KeyTermService keyTermService, QCollectionService qCollectionService, FlashCardService flashCardService, DeckService deckService) {
        this.userRepository = userRepository;
        this.answerService = answerService;
        this.categoryService = categoryService;
        this.questionService = questionService;
        this.sourceService = sourceService;
        this.keyTermService = keyTermService;
        this.qCollectionService = qCollectionService;
        this.flashCardService = flashCardService;
        this.deckService = deckService;
    }

    /**
     * Callback used to run the bean.
     */
    @Override
    public void run(String... args) {
        if (questionService.count() == 0) {
            loadData();
        }
    }

    private void loadData() {
        log.info("Loading bootstrap data ...");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        addUser("admin", "admin", encoder, "read");
        addUser("guest", "guest", encoder, "read");
        log.info("Users loaded.");

        Category catCore = saveCategory("Java Core", "Java syntax and main library classes");
        saveCategory("Spring", "Spring framework");
        Category catJdbc = saveCategory("JDBC", "JDBC");
        log.info("Categories loaded.");

        KeyTerm jdkTerm = new KeyTerm("JDK", "JDK - Java Development Kit");
        keyTermService.save(jdkTerm);
        KeyTerm jreTerm = new KeyTerm("JRE", "JRE - Java Runtime Environment");
        keyTermService.save(jreTerm);
        KeyTerm jdbcTerm = new KeyTerm("JDBC", "Java database connectivity");
        keyTermService.save(jdbcTerm);
        KeyTerm databaseTerm = new KeyTerm("Database", "Organized collection of data");
        keyTermService.save(databaseTerm);
        log.info("KeyTerms loaded.");

        Question jvm = saveQuestion(catCore, "What is JVM?", "Java Virtual Machine");
        Question jre = saveQuestion(catCore, "What is JRE?", "Java Runtime Environment", jreTerm);
        Question jdk = saveQuestion(catCore, "What is JDK?", "Java Development Kit", jdkTerm);
        Question jdbc = saveQuestion(catJdbc, "What is JDBC?", "Java database connectivity",
                jdbcTerm, databaseTerm);
        log.info("Questions loaded.");

        Source wiki = saveSource("Wikipedia", "Wikipedia - the Free Encyclopedia", "https://www.wikipedia.org/");
        Source javarevisitedBlog = saveSource("Javarevisited", "Javarevisited - Blog about Java, "
                        + "Programming, Spring, Hibernate, Interview Questions, Books and Online Course Recommendations",
                "https://javarevisited.blogspot.com");
        Source baeldung = saveSource("Baeldung",
                "Baeldung helps developers explore the Java ecosystem and simply be better engineers.",
                "https://www.baeldung.com");
        log.info("Sources loaded.");

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
        log.info("Answers loaded.");

        QCollection coreJavaQuestions = new QCollection();
        coreJavaQuestions.setTitle("Core Java");
        coreJavaQuestions.setDescription("Core Java basics");
        coreJavaQuestions.addQuestion(jvm);
        coreJavaQuestions.addQuestion(jre);
        coreJavaQuestions.addQuestion(jdk);
        qCollectionService.save(coreJavaQuestions);
        log.info("QCollection loaded.");

        FlashCard fc1 = new FlashCard(jvm);
        FlashCard fc2 = new FlashCard(jre);
        FlashCard fc3 = new FlashCard(jdk);
        Deck deck = new Deck("Core Java questions deck");
        deckService.save(deck);
        flashCardService.save(fc1);
        flashCardService.save(fc2);
        flashCardService.save(fc3);
        deck.addCard(fc1);
        deck.addCard(fc2);
        deck.addCard(fc3);
        deckService.save(deck);
        log.info("####### Cards and Decks loaded");
    }

    private void addUser(String username, String rawPassword, PasswordEncoder encoder, String... authorities) {
        EncryptionAlgorithm algorithm = (encoder instanceof BCryptPasswordEncoder)
                ? EncryptionAlgorithm.BCRYPT : EncryptionAlgorithm.NOOP;
        User user = new User(username, encoder.encode(rawPassword), algorithm);
        for (String auth : authorities) {
            Authority authority = new Authority(auth, user);
            user.addAuthority(authority);
        }
        userRepository.saveAndFlush(user);
    }

    private Question saveQuestion(Category category, String wording, String shortAnswer, KeyTerm... keyTerms) {
        Question question = Question.builder().category(category).wordingEn(wording).shortAnswerEn(shortAnswer)
                .stat(new QuestionDrillStat()).build();
        for (KeyTerm kt : keyTerms) {
            question.addKeyTerm(kt);
        }
        questionService.save(question);
        return question;
    }

    private Source saveSource(String title, String fullTitle, String url) {
        Source source = Source.builder().shortTitle(title).fullTitle(fullTitle)
                .sourceType(SourceType.WEB_SITE).url(url).build();
        sourceService.save(source);
        return source;
    }

    private Answer saveAnswer(Question question, String wording, Source source, String sourceDetails,
                              KeyTerm... keyTerms) {
        Answer answer = Answer.builder().question(question).wordingEn(wording).answerType(AnswerType.ORIGINAL)
                .source(source).sourceDetails(sourceDetails).build();
        for (KeyTerm kt : keyTerms) {
            answer.addKeyTerm(kt);
        }
        answerService.save(answer);
        return answer;
    }

    private Category saveCategory(String name, String description) {
        Category cat = Category.builder().name(name).description(description).build();
        categoryService.save(cat);
        return cat;
    }
}
