CREATE TABLE mini_link (
                           id BIGSERIAL PRIMARY KEY,
                           mini_code VARCHAR(255) NOT NULL UNIQUE,
                           original_url TEXT NOT NULL
);