DROP TABLE IF EXISTS attraction_tag;
DROP TABLE IF EXISTS tourist_attraction;
DROP TABLE IF EXISTS tag;

CREATE TABLE IF NOT EXISTS tag (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS tourist_attraction (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    city VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS attraction_tag (
    attraction_id INT NOT NULL,
    tag_id INT NOT NULL,
    PRIMARY KEY (attraction_id, tag_id),
    FOREIGN KEY (attraction_id) REFERENCES tourist_attraction(id) ON DELETE CASCADE,
    FOREIGN KEY (tag_id) REFERENCES tag(id)
);