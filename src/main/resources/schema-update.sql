-- Run this in PostgreSQL if profile/notes save still fails after restart
ALTER TABLE profile ADD COLUMN IF NOT EXISTS user_email VARCHAR(255);
ALTER TABLE profile ADD COLUMN IF NOT EXISTS mobile VARCHAR(255);
ALTER TABLE profile ADD COLUMN IF NOT EXISTS user_id VARCHAR(255);
ALTER TABLE profile ADD COLUMN IF NOT EXISTS login_time VARCHAR(255);

ALTER TABLE notes ADD COLUMN IF NOT EXISTS uploaded_by VARCHAR(255);
