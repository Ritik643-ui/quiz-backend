INSERT INTO questions (
    id,
    prompt,
    correct_choice_index
)
VALUES (
           1,
           'What is the capital of Nepal?',
           0
       )
    ON CONFLICT (id) DO NOTHING;

INSERT INTO choices (
    question_id,
    choice_index,
    choice_text
)
VALUES
    (1, 0, 'Kathmandu'),
    (1, 1, 'Delhi'),
    (1, 2, 'Cape Town'),
    (1, 3, 'New-York')
    ON CONFLICT (question_id, choice_index) DO NOTHING;