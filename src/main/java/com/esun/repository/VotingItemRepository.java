package com.esun.repository;

import com.esun.model.VotingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotingItemRepository extends JpaRepository<VotingItem, Integer> {


    @Query(value = "CALL voting_system.sp_get_all_items_with_votes()", nativeQuery = true)
    List<Object[]> getAllItemsWithVotes();

    @Procedure(procedureName = "sp_insert_item")
    void insertItem(@Param("p_name") String name);

    @Procedure(procedureName = "sp_delete_item")
    void deleteItem(@Param("p_id") Integer Id);

    @Procedure(procedureName = "sp_insert_vote")
    void insertVote(@Param("p_voter_name") String name, @Param("p_voter_id") Integer Id);
}