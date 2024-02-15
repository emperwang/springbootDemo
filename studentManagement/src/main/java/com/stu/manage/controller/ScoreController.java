package com.stu.manage.controller;

import com.stu.manage.entity.CommonResult;
import com.stu.manage.entity.Score;
import com.stu.manage.service.IScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author: Sparks
 * @Date: 2024/1/18 0:03
 * @Description
 */
@RestController
@RequestMapping("score")
public class ScoreController {

    private IScoreService scoreService;


    @GetMapping("list")
    public CommonResult<Score> listScore(){
        CommonResult.CommonResultBuilder<Score> builder = new CommonResult.CommonResultBuilder<>();
        List<Score> scores = scoreService.listScores();
        return builder.counts(scores.size()).results(scores).build();
    }

    @DeleteMapping(value = "deleteId/{id}")
    public ResponseEntity deleteRoleById(@PathVariable(name = "id") long id){
        int ct = scoreService.deleteScoreById(id);
        return ct>0?ResponseEntity.status(HttpStatus.NO_CONTENT).build():ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    @DeleteMapping(value = "deleteIds")
    public int deleteRoleByMultipleIds(@RequestParam(name = "ids",required = true) List<Long> ids){
        return scoreService.deleteScoreByIds(ids);
    }


    @PutMapping(value = "updateById")
    public int updateRole(@RequestBody Score u){
        return scoreService.updateScoreById(u);
    }


    @PostMapping(value = "save")
    public ResponseEntity saveRole(@RequestBody Score u){
        return scoreService.saveScore(u)!=null?ResponseEntity.status(HttpStatus.CREATED).body(u):ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
    }

    @PostMapping(value = "upload")
    public ResponseEntity uploadScore(MultipartFile file){
        List<Score> scores = scoreService.batchInsert(file);

        return ResponseEntity.ok().body(scores);
    }

    @Autowired
    public void setScoreService(IScoreService scoreService) {
        this.scoreService = scoreService;
    }
}
