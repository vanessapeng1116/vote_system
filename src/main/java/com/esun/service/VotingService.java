package com.esun.service;
import com.esun.model.VotingItem;
import com.esun.repository.VotingItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class VotingService {

    @Autowired
    private com.esun.repository.VotingItemRepository votingItemRepository;

    //查詢所有投票項目與票數統計
    @Transactional
    public  List<VotingItem> getAllItemWithVotes(){
        List<Object[]> results= votingItemRepository.getAllItemsWithVotes();
        List<VotingItem> items=new ArrayList<>();

        for(Object[] row: results){
            VotingItem item= new VotingItem();
            item.setId((Integer) row[0]);
            item.setName((String) row[1]);

            if(row[2]!=null){
                item.setVoteCount(((Number) row[2]).longValue());
            }else{
                item.setVoteCount(0L);
            }
            items.add(item);
        }
        return  items;
    }
    // 新增項目
    @Transactional
    public  void addItem(String name){
        votingItemRepository.insertItem(name);
    }
    //刪除項目
    @Transactional
    public void deleteItem(Integer id){
        votingItemRepository.deleteItem(id);
    }
    //多選投票
    @Transactional(rollbackFor = Exception.class)
    public void castVotes(String voterName, List<Integer> itemIds){
        if(itemIds==null||itemIds.isEmpty()) {
            throw new IllegalArgumentException("至少要投一票");
        }
        if(voterName==null||voterName.trim().isEmpty()){
            throw new IllegalArgumentException("姓名不能是空");
        }
        for(Integer itemId: itemIds){
            votingItemRepository.insertVote(voterName,itemId);
        }
    }


}
