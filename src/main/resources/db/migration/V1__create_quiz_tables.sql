CREATE TABLE IF NOT EXISTS questions (
                                         id INTEGER PRIMARY KEY,
                                         prompt TEXT NOT NULL,
                                         correct_choice_index INTEGER NOT NULL
                                         CHECK (correct_choice_index >= 0)
    );

CREATE TABLE IF NOT EXISTS choices (
                                       question_id INTEGER NOT NULL
                                       REFERENCES questions(id),
    choice_index INTEGER NOT NULL
    CHECK (choice_index >= 0),
    choice_text TEXT NOT NULL,
    PRIMARY KEY (question_id, choice_index)
    );