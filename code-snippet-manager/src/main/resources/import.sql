-- Delete existing data
DELETE FROM snippets;
DELETE FROM users;

-- Insert test user
INSERT INTO users (id, username, email, password, full_name, created_at) VALUES 
(1, 'testuser', 'test@example.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTV2UiC', 'Test User', NOW());

-- Insert sample snippets
INSERT INTO snippets (id, title, description, code, language, created_at, updated_at, user_id) VALUES 
(1, 'Spring Boot Controller', 'Basic REST controller example', '@RestController\npublic class HelloController {\n    @GetMapping("/hello")\n    public String hello() {\n        return "Hello World!";\n    }\n}', 'Java', NOW(), NOW(), 1),
(2, 'Python Flask Route', 'Simple Flask endpoint', '@app.route("/hello")\ndef hello():\n    return "Hello, World!"', 'Python', NOW(), NOW(), 1),
(3, 'JavaScript Function', 'Arrow function example', 'const greet = (name) => {\n    return `Hello, ${name}!`;\n};', 'JavaScript', NOW(), NOW(), 1);