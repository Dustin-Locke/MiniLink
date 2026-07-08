CREATE TABLE link_event (
                            id BIGSERIAL PRIMARY KEY,
                            mini_link_id BIGINT NOT NULL,
                            original_url VARCHAR(2048) NOT NULL,
                            mini_code VARCHAR(255) NOT NULL,
                            event_date TIMESTAMP NOT NULL,
                            event_type VARCHAR(50) NOT NULL,
                            ip_address VARCHAR(255),
                            user_agent VARCHAR(512)
);

