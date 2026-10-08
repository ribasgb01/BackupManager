CREATE TABLE markets (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    cnpj VARCHAR(18) NOT NULL UNIQUE,
    api_key VARCHAR(255) NOT NULL UNIQUE,
    expected_backup_time TIME NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE backup_histories (
    id UUID PRIMARY KEY,
    market_id UUID NOT NULL,
    created_at TIMESTAMP NOT NULL,
    completed_at TIMESTAMP,
    file_size_bytes BIGINT,
    file_hash_md5 VARCHAR(64),
    s3_key VARCHAR(500) NOT NULL,
    status VARCHAR(30) NOT NULL,
    error_message TEXT,

    CONSTRAINT fk_backup_histories_market FOREIGN KEY (market_id) REFERENCES markets(id)
);