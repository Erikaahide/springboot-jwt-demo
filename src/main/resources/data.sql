-- Clean before start
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE post;
TRUNCATE TABLE users;
SET FOREIGN_KEY_CHECKS = 1;

-- 👤 User 1: Ana
INSERT INTO users (id, name, email, password, created_at)
VALUES (
  1,
  'Ana',
  'ana@mail.com',
  '$2a$10$hErfuwVUTq1fgq0E.zdz4uYoKJ2zqzBKZbT8c.0L77z5rT4ivyiJq', -- password: 123456
  NOW()
);

-- 👤 User 2: Rodri
INSERT INTO users (id, name, email, password, created_at)
VALUES (
  2,
  'Rodri',
  'rodri@mail.com',
  '$2a$10$hErfuwVUTq1fgq0E.zdz4uYoKJ2zqzBKZbT8c.0L77z5rT4ivyiJq', -- password: 123456
  NOW()
);

-- Ana's Post
INSERT INTO post (id, user_id, content, created_at)
VALUES (
  1,
  1,
  'Hi, my first post 💪',
  NOW()
);

-- Rodri's Post 
INSERT INTO post (id, user_id, content, created_at)
VALUES (
  2,
  2,
  'Hi, how are you? 🏋️',
  NOW()
);
