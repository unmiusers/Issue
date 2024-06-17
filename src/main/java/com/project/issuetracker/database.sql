-- 创建数据库
CREATE DATABASE issue;

-- 使用数据库
USE issue;

-- 创建项目表
CREATE TABLE Project (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         description TEXT
);

-- 创建用户表
CREATE TABLE User (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(255) NOT NULL,
                      password VARCHAR(255) NOT NULL,
                      email VARCHAR(255) NOT NULL,
                      role VARCHAR(50) NOT NULL, -- PL, Dev, Tester, Admin
                      project_id BIGINT,
                      FOREIGN KEY (project_id) REFERENCES Project(id) ON DELETE SET NULL
);

-- 创建问题表
CREATE TABLE Issue (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       description TEXT,
                       status VARCHAR(50) NOT NULL, -- New, Assigned, Fixed, Resolved, Closed
                       project_id BIGINT,
                       reporter_id BIGINT,
                       assignee_id BIGINT,
                       fixer_id BIGINT,
                       FOREIGN KEY (project_id) REFERENCES Project(id) ON DELETE CASCADE,
                       FOREIGN KEY (reporter_id) REFERENCES User(id) ON DELETE SET NULL,
                       FOREIGN KEY (assignee_id) REFERENCES User(id) ON DELETE SET NULL,
                       FOREIGN KEY (fixer_id) REFERENCES User(id) ON DELETE SET NULL
);

-- 创建评论表
CREATE TABLE Comment (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         content TEXT,
                         issue_id BIGINT,
                         user_id BIGINT,
                         FOREIGN KEY (issue_id) REFERENCES Issue(id) ON DELETE CASCADE,
                         FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE
);
-- 插入项目数据
INSERT INTO Project (name, description) VALUES ('Project A', 'Description for Project A');
INSERT INTO Project (name, description) VALUES ('Project B', 'Description for Project B');

-- 插入用户数据
INSERT INTO User (username, password, email, role, project_id) VALUES ('admin', 'adminpass', 'admin@example.com', 'Admin', NULL);
INSERT INTO User (username, password, email, role, project_id) VALUES ('pl1', 'pl1pass', 'pl1@example.com', 'PL', 1);
INSERT INTO User (username, password, email, role, project_id) VALUES ('dev1', 'dev1pass', 'dev1@example.com', 'Dev', 1);
INSERT INTO User (username, password, email, role, project_id) VALUES ('tester1', 'tester1pass', 'tester1@example.com', 'Tester', 1);

-- 插入问题数据
INSERT INTO Issue (title, description, status, project_id, reporter_id, assignee_id, fixer_id)
VALUES ('Issue 1', 'Description for Issue 1', 'New', 1, 4, NULL, NULL);
INSERT INTO Issue (title, description, status, project_id, reporter_id, assignee_id, fixer_id)
VALUES ('Issue 2', 'Description for Issue 2', 'Assigned', 1, 4, 3, NULL);

-- 插入评论数据
INSERT INTO Comment (content, issue_id, user_id) VALUES ('Comment on Issue 1', 1, 4);
INSERT INTO Comment (content, issue_id, user_id) VALUES ('Comment on Issue 2', 2, 3);
