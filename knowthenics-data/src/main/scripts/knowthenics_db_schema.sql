create table answer_key_term
(
    answer_id   bigint not null,
    key_term_id bigint not null,
    primary key (answer_id, key_term_id)
) engine = InnoDB;
create table answers
(
    id             bigint not null auto_increment,
    answer_type    varchar(255),
    comment        varchar(255),
    source_details varchar(255),
    wording_en     longtext,
    wording_ru     longtext,
    question_id    bigint,
    source_id      bigint,
    primary key (id)
) engine = InnoDB;
create table categories
(
    id          bigint not null auto_increment,
    description varchar(255),
    name        varchar(255),
    primary key (id)
) engine = InnoDB;
create table decks
(
    id          bigint not null auto_increment,
    description varchar(255),
    name        varchar(255),
    primary key (id)
) engine = InnoDB;
create table flashcards
(
    id               bigint  not null auto_increment,
    answer_wording   varchar(255),
    level            integer not null,
    question_wording varchar(255),
    deck_id          bigint,
    question_id      bigint,
    primary key (id)
) engine = InnoDB;
create table key_terms
(
    id          bigint not null auto_increment,
    description varchar(255),
    name        varchar(64),
    primary key (id)
) engine = InnoDB;
create table qcollection_question
(
    qcollection_id bigint not null,
    question_id    bigint not null,
    primary key (qcollection_id, question_id)
) engine = InnoDB;
create table qcollections
(
    id          bigint not null auto_increment,
    description varchar(255),
    title       varchar(255),
    primary key (id)
) engine = InnoDB;
create table question_drill_stats
(
    id            bigint not null auto_increment,
    drill_count   integer,
    knowing_rate  double precision,
    success_count integer,
    primary key (id)
) engine = InnoDB;
create table question_key_term
(
    question_id bigint not null,
    key_term_id bigint not null,
    primary key (question_id, key_term_id)
) engine = InnoDB;
create table questions
(
    id                  bigint not null auto_increment,
    comment             varchar(255),
    usage_count         integer,
    short_answer_en     varchar(255),
    short_answer_ru     varchar(255),
    wording_en          varchar(255),
    wording_ru          varchar(255),
    category_id         bigint,
    preferred_answer_id bigint,
    question_stat_id    bigint,
    primary key (id)
) engine = InnoDB;
create table sources
(
    id          bigint not null auto_increment,
    description varchar(255),
    full_title  varchar(255),
    short_title varchar(128),
    source_type varchar(255),
    url         varchar(255),
    primary key (id)
) engine = InnoDB;
create table study_sessions
(
    id                 bigint  not null auto_increment,
    current_card_index integer not null,
    finished           datetime,
    started            datetime,
    primary key (id)
) engine = InnoDB;
create table study_sessions_flash_cards
(
    study_session_id bigint not null,
    flash_cards_id   bigint not null
) engine = InnoDB;
create table topics
(
    id             bigint not null auto_increment,
    description    varchar(255),
    knowing_rate   double precision,
    name           varchar(255),
    qcollection_id bigint,
    primary key (id)
) engine = InnoDB;
alter table answer_key_term
    add constraint FK__answer_key_term__key_term_id foreign key (key_term_id) references key_terms (id);
alter table answer_key_term
    add constraint FK__answer_key_term__answer_id foreign key (answer_id) references answers (id);
alter table answers
    add constraint FK__answers__question_id foreign key (question_id) references questions (id);
alter table answers
    add constraint FK__answers__source_id foreign key (source_id) references sources (id);
alter table flashcards
    add constraint FK__flashcards__deck_id foreign key (deck_id) references decks (id);
alter table flashcards
    add constraint FK__flashcards__question_id foreign key (question_id) references questions (id);
alter table qcollection_question
    add constraint FK__qcollection_question__question_id foreign key (question_id) references questions (id);
alter table qcollection_question
    add constraint FK__qcollection_question__qcollection_id foreign key (qcollection_id) references qcollections (id);
alter table question_key_term
    add constraint FK__question_key_term__key_term_id foreign key (key_term_id) references key_terms (id);
alter table question_key_term
    add constraint FK__question_key_term__question_id foreign key (question_id) references questions (id);
alter table questions
    add constraint FK__questions__category_id foreign key (category_id) references categories (id);
alter table questions
    add constraint FK__questions__preferred_answer_id foreign key (preferred_answer_id) references answers (id);
alter table questions
    add constraint FK__questions__question_stat_id foreign key (question_stat_id) references question_drill_stats (id);
alter table study_sessions_flash_cards
    add constraint FK__study_sessions_flash_cards__flash_cards_id foreign key (flash_cards_id) references flashcards (id);
alter table study_sessions_flash_cards
    add constraint FK__study_sessions_flash_cards__study_session_id foreign key (study_session_id) references study_sessions (id);
alter table topics
    add constraint FK__topics__qcollection_id foreign key (qcollection_id) references qcollections (id);
