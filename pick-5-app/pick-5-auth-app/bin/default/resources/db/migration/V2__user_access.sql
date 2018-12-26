-- V2__user_access.sql
--
-- Create user_access table
CREATE TABLE user_access(
  p_key SERIAL NOT NULL,
  id VARCHAR(24) NOT NULL,
  user_id VARCHAR(24) NOT NULL,
  login_attempts INTEGER NOT NULL DEFAULT 1,
  created_on BIGINT,
  updated_on BIGINT,
  is_deleted boolean NOT NULL DEFAULT FALSE,
  version INTEGER NOT NULL DEFAULT 0,
  CONSTRAINT pk_user_access PRIMARY KEY (p_key),
  CONSTRAINT user_access_uq_id UNIQUE (id),
  CONSTRAINT user_access_uq_user_id UNIQUE (user_id)
);
