# create user

# A add friend B
INSERT INTO `chat-app`.friends (user_id_1,user_id_2)
	VALUES (1,3);
# B accept A
UPDATE `chat-app`.friends
	SET confirmed=1
	WHERE id=3;
