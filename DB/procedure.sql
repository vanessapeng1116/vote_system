DROP PROCEDURE IF EXISTS sp_get_all_items_with_votes;
DROP PROCEDURE IF EXISTS sp_insert_item;
DROP PROCEDURE IF EXISTS sp_delete_item;
DROP PROCEDURE IF EXISTS sp_insert_vote;

create
definer = root@localhost procedure voting_system.sp_delete_item(IN p_id int)
BEGIN
DELETE FROM voting_item WHERE id = p_id;
END;

create
definer = root@localhost procedure voting_system.sp_get_all_items_with_votes()
BEGIN
SELECT
    i.id,
    i.name,
    COUNT(r.id) AS vote_count
FROM voting_item i
         LEFT JOIN voting_record r ON i.id = r.item_id
GROUP BY i.id, i.name;
END;

create
definer = root@localhost procedure voting_system.sp_insert_item(IN p_name varchar(255))
BEGIN
INSERT INTO voting_item (name) VALUES (p_name);
END;

create
definer = root@localhost procedure voting_system.sp_insert_vote(IN p_voter_name varchar(100), IN p_item_id int)
BEGIN
INSERT INTO voting_record (voter_name, item_id) VALUES (p_voter_name, p_item_id);
END;

