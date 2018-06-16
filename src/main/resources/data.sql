INSERT INTO EVENT (NAME, DATE, CREATED_TS, SUMMARY) VALUES ('Java One', '2018-08-01', CURRENT_TIMESTAMP(), 'All about Java...');
INSERT INTO EVENT (NAME, DATE, CREATED_TS, SUMMARY) VALUES ('CS EXAMPLE', '2018-08-01', CURRENT_TIMESTAMP(), 'Nothing else metters...');

SET @id = SELECT ID FROM EVENT WHERE NAME = 'CS EXAMPLE';
INSERT INTO ACTIVITY (NAME, EVENT_ID, CREATED_TS, SUMMARY, DURATION, IS_NETWORKING) VALUES
('Architecting Your Codebase', @id, CURRENT_TIMESTAMP(), '', 60, FALSE),
('Overdoing it in Python', @id, CURRENT_TIMESTAMP(), '', 45, FALSE),
('Flavors of Concurrency in Java', @id, CURRENT_TIMESTAMP(), '', 30, FALSE),
('Ruby Errors from Mismatched Gem Versions', @id, CURRENT_TIMESTAMP(), '', 45, FALSE),
('JUnit 5 - Shaping the Future of Testing on the JVM', @id, CURRENT_TIMESTAMP(), '', 45, FALSE),
('Cloud Native Java lightning', @id, CURRENT_TIMESTAMP(), '', 5, FALSE),
('Communicating Over Distance', @id, CURRENT_TIMESTAMP(), '', 60, FALSE),
('AWS Technical Essentials', @id, CURRENT_TIMESTAMP(), '', 45, FALSE),
('Continuous Delivery', @id, CURRENT_TIMESTAMP(), '', 30, FALSE),
('Monitoring Reactive Applications', @id, CURRENT_TIMESTAMP(), '', 30, FALSE),
('Pair Programming vs Noise', @id, CURRENT_TIMESTAMP(), '', 45, FALSE),
('Rails Magic', @id, CURRENT_TIMESTAMP(), '', 60, FALSE),
('Networking Event X', @id, CURRENT_TIMESTAMP(), '', 60, TRUE),
('Networking Event Y', @id, CURRENT_TIMESTAMP(), '', 60, TRUE),
('Microservices "Just Right"', @id, CURRENT_TIMESTAMP(), '', 60, FALSE),
('Clojure Ate Scala (on my project)', @id, CURRENT_TIMESTAMP(), '', 45, FALSE),
('Perfect Scalability', @id, CURRENT_TIMESTAMP(), '', 30, FALSE),
('Apache Spark', @id, CURRENT_TIMESTAMP(), '', 30, FALSE),
('Async Testing on JVM', @id, CURRENT_TIMESTAMP(), '', 60, FALSE),
('A World Without HackerNews', @id, CURRENT_TIMESTAMP(), '', 30, FALSE),
('User Interface CSS in Apps', @id, CURRENT_TIMESTAMP(), '', 30, FALSE);

SET @id = SELECT ID FROM EVENT WHERE NAME = 'Java One';
INSERT INTO ACTIVITY (NAME, EVENT_ID, CREATED_TS, SUMMARY, DURATION, IS_NETWORKING) VALUES
('Event 1', @id, CURRENT_TIMESTAMP(), 'Event 1 summary...', 15, FALSE),
('Event 2', @id, CURRENT_TIMESTAMP(), 'Event 2 summary...', 15, FALSE),
('Event 3', @id, CURRENT_TIMESTAMP(), 'Event 3 summary...', 30, FALSE),
('Event 4', @id, CURRENT_TIMESTAMP(), 'Event 4 summary...', 45, FALSE),
('Event 5', @id, CURRENT_TIMESTAMP(), 'Event 5 summary...', 45, FALSE),
('Event 6', @id, CURRENT_TIMESTAMP(), 'Event 6 summary...', 30, FALSE),
('Event 7', @id, CURRENT_TIMESTAMP(), 'Event 7 summary...', 15, FALSE),
('Event 8', @id, CURRENT_TIMESTAMP(), 'Event 8 summary...', 15, FALSE),
('Event 9', @id, CURRENT_TIMESTAMP(), 'Event 9 summary...', 30, FALSE),
('Event 10', @id, CURRENT_TIMESTAMP(), 'Event 10 summary...', 5, FALSE),
('Event 11', @id, CURRENT_TIMESTAMP(), 'Event 11 summary...', 5, FALSE);