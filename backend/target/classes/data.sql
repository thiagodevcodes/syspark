INSERT INTO TB_ROLE (id, description, created_at, updated_at, is_active, version)
VALUES
  (UUID(), 'Administrator', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE, 1),
  (UUID(), 'Regular User', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE, 1);
