INSERT INTO questions (api_id, question) VALUES (1, 'Which artisan command will you used to enable maintenance mode in Laravel?');
INSERT INTO questions (api_id, question) VALUES (2, 'How do you create a new Docker image and store it on the local system?');
INSERT INTO questions (api_id, question) VALUES (3, 'How to list all nodes in your Docker swarm?');
INSERT INTO questions (api_id, question) VALUES (4, 'Which command is used to copy file across different system?');

INSERT INTO answers (answer, correct, question_id) VALUES ('php artisan maintenance', false, 1);
INSERT INTO answers (answer, correct, question_id) VALUES ('php artisan down', true, 1);
INSERT INTO answers (answer, correct, question_id) VALUES ('php artisan stop', false, 1);
INSERT INTO answers (answer, correct, question_id) VALUES ('php artisan pause', false, 1);

INSERT INTO answers (answer, correct, question_id) VALUES ('docker add <conatainer id> <username/imagename>', false, 2);
INSERT INTO answers (answer, correct, question_id) VALUES ('docker --commit <conatainer id> <username/imagename>', false, 2);
INSERT INTO answers (answer, correct, question_id) VALUES ('docker commit <conatainer id> <username/imagename>', true, 2);

INSERT INTO answers (answer, correct, question_id) VALUES ('docker node get', false, 3);
INSERT INTO answers (answer, correct, question_id) VALUES ('docker node print', false, 3);
INSERT INTO answers (answer, correct, question_id) VALUES ('docker node get-all', false, 3);
INSERT INTO answers (answer, correct, question_id) VALUES ('docker node ls', true, 3);

INSERT INTO answers (answer, correct, question_id) VALUES ('rcp', false, 4);
INSERT INTO answers (answer, correct, question_id) VALUES ('scp', true, 4);
INSERT INTO answers (answer, correct, question_id) VALUES ('mcp', false, 4);
INSERT INTO answers (answer, correct, question_id) VALUES ('ncp', false, 4);
INSERT INTO answers (answer, correct, question_id) VALUES ('rsync', true, 4);