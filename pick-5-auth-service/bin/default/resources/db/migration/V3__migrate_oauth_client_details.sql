-- V3__migrate_oauth_client_details.sql
--
-- Migrate oauth_client_details DATA
-- ---------------------------------
UPDATE oauth_client_details
  SET client_secret = '$2a$10$VgDtwxlgAt2aaf1iXH4jTe/dgFDpRBOj8iGM0L6e1SASeRDSiTUwq'
WHERE client_id = 'testclient'
