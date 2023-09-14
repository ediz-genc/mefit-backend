INSERT INTO tb_user (user_id, user_length, user_weight, user_bio, user_pic_url, user_username)
VALUES
    ('58efed46-d459-4a14-b6bb-dec9b4240f94', 170, 65, 'Fitness enthusiast and nature lover.', 'https://example.com/user1.jpg', 'fitNatureLover'),
    ('3542EF3', 160, 55, 'Foodie and traveler.', 'https://example.com/user2.jpg', 'wanderlustFoodie'),
    ('23#45gs', 175, 70, 'Tech geek and aspiring astronaut.', 'https://example.com/user3.jpg', 'techSpaceFan'),
    ('adf#%%r', 180, 80, 'Bookworm and coffee addict.', 'https://example.com/user4.jpg', 'bookishBrew'),
    ('geg#%&d', 165, 50, 'Artist and dreamer.', 'https://example.com/user5.jpg', 'creativeDream'),
    ('#ea#345', 172, 68, 'Sports lover and adventurer.', 'https://example.com/user6.jpg', 'activeExplorer'),
    ('dfa245#', 155, 48, 'Musician and night owl.', 'https://example.com/user7.jpg', 'melodicNight'),
    ('345<adf', 185, 75, 'Animal lover and environmentalist.', 'https://example.com/user8.jpg', 'ecoAnimalFriend'),
    ('aaf#¤ae', 168, 58, 'Fashionista and travel enthusiast.', 'https://example.com/user9.jpg', 'stylishJourney'),
    ('dsus#%4', 178, 72, 'Cooking enthusiast and aspiring chef.', 'https://example.com/user10.jpg', 'culinaryArtist');

INSERT INTO tb_exercise (exercise_name, muscle_group, exercise_desc, exercise_img_url, exercise_vid_url, fitness_lvl)
VALUES
    ('Push-up', 'Chest', 'A classic bodyweight exercise targeting the chest and triceps.', 'https://example.com/exercise1.jpg', 'https://example.com/exercise1.mp4', 'Beginner'),
    ('Squat', 'Legs', 'A fundamental lower body exercise to strengthen the quadriceps and glutes.', 'https://example.com/exercise2.jpg', 'https://example.com/exercise2.mp4', 'Intermediate'),
    ('Pull-up', 'Back', 'An upper body exercise that targets the back and biceps.', 'https://example.com/exercise3.jpg', 'https://example.com/exercise3.mp4', 'Advanced'),
    ('Plank', 'Core', 'An isometric core exercise that also engages the shoulders and chest.', 'https://example.com/exercise4.jpg', 'https://example.com/exercise4.mp4', 'Beginner'),
    ('Deadlift', 'Back', 'A compound exercise targeting the back, hamstrings, and core.', 'https://example.com/exercise5.jpg', 'https://example.com/exercise5.mp4', 'Intermediate'),
    ('Lunges', 'Legs', 'A unilateral leg exercise that helps improve lower body strength and balance.', 'https://example.com/exercise6.jpg', 'https://example.com/exercise6.mp4', 'Intermediate'),
    ('Bicep Curl', 'Arms', 'An isolation exercise to target the biceps.', 'https://example.com/exercise7.jpg', 'https://example.com/exercise7.mp4', 'Beginner'),
    ('Plank Twist', 'Core', 'A variation of the plank exercise that engages the obliques.', 'https://example.com/exercise8.jpg', 'https://example.com/exercise8.mp4', 'Intermediate'),
    ('Dips', 'Arms', 'An upper body exercise targeting the triceps and shoulders.', 'https://example.com/exercise9.jpg', 'https://example.com/exercise9.mp4', 'Advanced'),
    ('Russian Twist', 'Core', 'An abdominal exercise that involves twisting motions.', 'https://example.com/exercise10.jpg', 'https://example.com/exercise10.mp4', 'Intermediate');

INSERT INTO tb_workout (workout_desc, workout_name)
VALUES 
    ('Test workout 1', 'Sweatifier'), ('Test workout 2', 'Pumppumppump'), ('Test workout 3', 'Weightwatcher');

INSERT INTO tb_goal (goal_name, start_date, end_date, completed)
VALUES 
    ('Week 1', '2023-09-04', '2023-09-10', false), ('Week 2', '2023-09-04', '2023-09-10', false), ('Week 3', '2023-09-04', '2023-09-10', false), ('Week 4', '2023-09-11', '2023-07-18', false);

UPDATE tb_user SET current_goal_goal_id = 1 WHERE user_id = '58efed46-d459-4a14-b6bb-dec9b4240f94';
UPDATE tb_user SET current_goal_goal_id = 2 WHERE user_id = '3542EF3';
UPDATE tb_user SET current_goal_goal_id = 3 WHERE user_id = '23#45gs';

UPDATE tb_goal SET user_id = '58efed46-d459-4a14-b6bb-dec9b4240f94' WHERE goal_id = 1;
UPDATE tb_goal SET user_id = '23#45gs' WHERE goal_id = 3;
UPDATE tb_goal SET user_id = '3542EF3' WHERE goal_id = 2;

INSERT INTO tb_program (program_category, program_desc, program_img_url, program_name)
VALUES 
    ('Weight loss', 'Designed to lose weight.', 'https://example.com/program1.jpg', 'Weight loss of D00M!'), 
    ('Muscle increase', 'Buff up!', 'https://example.com/program2.jpg', 'Get big!'), 
    ('TestCategory', 'TestDesc', 'testURL', 'testName');

-- INSERT INTO tb_exercise_workouts (exercises_exercise_id, workouts_workout_id) 
-- VALUES
-- (1,2), (2,2), (3,2), (4,2), (5,2), (10,1), (9,1), (8,1), (7,1), (6,1);

INSERT INTO tb_workout_exercises (exercise_id, workout_id) 
VALUES
(1,2), (2,2), (3,2), (4,2), (5,2), (10,1), (9,1), (8,1), (7,1), (6,1);

INSERT INTO tb_goal_programs (goal_id, program_id)
VALUES 
(2,1), (3,2);

INSERT INTO tb_goal_workouts (goal_id, workout_id)
VALUES 
(2,3), (3,2);

INSERT INTO tb_workout_programs (workouts_workout_id, programs_program_id)
VALUES
(1,1), (2,1), (3,1), (1,2), (2,2), (3,2);

INSERT INTO tb_goal_completed_workouts (completed_in_goal_goal_id, completed_workouts_workout_id)
VALUES
(1,2);

INSERT INTO tb_goal_completed_programs (completed_in_goal_goal_id, completed_programs_program_id)
VALUES
(1,1), (1,2);