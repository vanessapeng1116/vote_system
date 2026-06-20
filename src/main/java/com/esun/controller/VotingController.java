package com.esun.controller;

import com.esun.model.VotingItem;
import com.esun.service.VotingService;
import com.esun.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
// 允許跨域存取
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class VotingController {

    @Autowired
    private VotingService voteService;

    //全部投票項目跟累積票數
    @GetMapping(value = "/items", produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<VotingItem>> getItemList() {
        // 叫 Service
        List<VotingItem> list = voteService.getAllItemWithVotes();
        return ResponseEntity.ok(list);
    }

    //新增
    @PostMapping("/addItem")
    public ResponseEntity<String> addNewItem(@RequestParam("itemName") String itemName) {

        if (itemName == null || itemName.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("項目名稱不能是空的");
        }
        if(!SecurityUtil.isValidInput((itemName))){
            return  ResponseEntity.badRequest().body("名稱只能是中文英文還有數字");
        }
        String safeItemName=SecurityUtil.sanitize(itemName);
        voteService.addItem(itemName);
        return ResponseEntity.ok("success");
    }

    // 刪除
    @DeleteMapping("/deleteItem/{itemId}")
    public ResponseEntity<String> removeItem(@PathVariable("itemId") Integer itemId) {
        voteService.deleteItem(itemId);
        return ResponseEntity.ok("delete success");
    }

    // 送出投票
    @PostMapping("/submitVote")
    public ResponseEntity<String> doVote(@RequestParam("voterName") String voterName,
                                         @RequestParam("itemIds") List<Integer> itemIds) {
        if (voterName == null || voterName.trim().isEmpty() || itemIds == null || itemIds.isEmpty()) {
            return ResponseEntity.badRequest().body("欄位不完整");
        }
        if(!SecurityUtil.isValidInput(voterName)){
            return ResponseEntity.badRequest().body("投票人的名字只能是中文英文或數字");
        }

        try {
            voteService.castVotes(voterName, itemIds);
            return ResponseEntity.ok("vote success");
        } catch (Exception e) {

            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}