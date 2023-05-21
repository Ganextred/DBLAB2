INSERT INTO role (name)
VALUES
    ('Role 1'),
    ('Role 2'),
    ('Role 3'),
    ('Role 4'),
    ('Role 5');

INSERT INTO usr (id, username, email, active, password)
VALUES
    (1, 'User 1', 'user1@example.com', true, 'password1'),
    (2, 'User 2', 'user2@example.com', true, 'password2'),
    (3, 'User 3', 'user3@example.com', true, 'password3');
    (4, 'User 4', 'user4@example.com', true, 'password4');

INSERT INTO user_role (user_id, role_id)
VALUES
    ((SELECT id FROM usr WHERE username = 'User 1'), (SELECT id FROM role WHERE name = 'Role 1')),
    ((SELECT id FROM usr WHERE username = 'User 1'), (SELECT id FROM role WHERE name = 'Role 2')),
    ((SELECT id FROM usr WHERE username = 'User 2'), (SELECT id FROM role WHERE name = 'Role 1')),
    ((SELECT id FROM usr WHERE username = 'User 3'), (SELECT id FROM role WHERE name = 'Role 1')),
    ((SELECT id FROM usr WHERE username = 'User 3'), (SELECT id FROM role WHERE name = 'Role 2'));
    ((SELECT id FROM usr WHERE username = 'User 3'), (SELECT id FROM role WHERE name = 'Role 3'));
    ((SELECT id FROM usr WHERE username = 'User 4'), (SELECT id FROM role WHERE name = 'Role 1')),
    ((SELECT id FROM usr WHERE username = 'User 4'), (SELECT id FROM role WHERE name = 'Role 2'));

INSERT INTO Apartment (id, price, beds, clazz)
VALUES
    (1, 100, 2, 1),
    (2, 150, 3, 2),
    (3, 200, 4, 3),
    (4, 120, 2, 1),
    (5, 180, 3, 2);

INSERT INTO apartment_status (id, apartment_id, user_id, arrival_day, end_day, status, pay_time_limit)
VALUES
    (1, 1, 1, '2023-05-25', '2023-05-30', 'BOOKED', '2024-06-09 23:59:59'),
    (2, 2, 2, '2023-06-01', '2023-06-05', 'BOUGHT', '2024-06-09 23:59:59'),
    (3, 3, 3, '2023-06-10', '2023-06-15', 'BOUGHT', '2024-06-09 23:59:59'),
    (4, 1, 4, '2023-06-05', '2023-06-10', 'BOUGHT', '2024-06-09 23:59:59'),
    (5, 2, 1, '2023-05-20', '2023-05-25', 'BOUGHT', '2024-06-09 23:59:59'),
    (6, 3, 1, '2023-05-20', '2023-05-25', 'BOUGHT', '2024-06-09 23:59:59'),
    (7, 4, 1, '2023-05-20', '2023-05-25', 'BOUGHT', '2024-06-09 23:59:59'),
    (8, 5, 1, '2023-05-20', '2023-05-25', 'BOUGHT', '2024-06-09 23:59:59'),
    (9, 1, 2, '2023-06-05', '2023-06-10', 'BOUGHT', '2024-06-09 23:59:59'),
    (10, 3, 4, '2023-06-25', '2023-06-30', 'BOUGHT', '2024-06-09 23:59:59'),
    (11, 4, 2, '2023-06-10', '2023-06-15', 'BOOKED', '2024-06-09 23:59:59'),
    (12, 5, 3, '2023-06-20', '2023-06-25', 'BOOKED', '2024-06-09 23:59:59'),
    (13, 2, 4, '2023-06-05', '2023-06-10', 'BOOKED', '2024-06-09 23:59:59'),
    (14, 3, 2, '2023-06-20', '2023-06-25', 'BOOKED', '2024-06-09 23:59:59'),
    (15, 4, 3, '2023-06-25', '2023-06-30', 'BOOKED', '2024-06-09 23:59:59');
