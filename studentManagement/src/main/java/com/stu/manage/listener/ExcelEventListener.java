package com.stu.manage.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stu.manage.constant.ColumnConstant;
import com.stu.manage.entity.Score;
import com.stu.manage.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: Sparks
 * @Date: 2024/2/15 15:53
 * @Description
 */
@Slf4j
public class ExcelEventListener extends AnalysisEventListener<Map<Integer,String>> {

    private ObjectMapper mapper = new ObjectMapper();
    //名次	学年	学期	学号	姓名	高数  5	线代  2.5	c++  4	英语  4	思修	形策	python	学科导论	大学生职业该规划	体育	专业认知	军事技能训练	德育成绩
    // excel表头如上, 前5列不变, 最后一列不变. 其中带的数字是学分, 有学分表示必修
    private List<String> heads;

    // 每列对应的成绩
    private List<Score> scores;

    // 成绩对应的学分
    private Map<String, Float> scoreToCredit;

    // 学期 学年对应的课程
    private Map<String,String> academicToCourses;

    // 学生信息
    private Set<Long> uids;
    private List<User> users;

    public ExcelEventListener() {
        this.heads = new ArrayList<>(20);
        this.scores = new ArrayList<>(20);
        this.scoreToCredit = new ConcurrentHashMap<>(10);
        this.academicToCourses = new ConcurrentHashMap<>(10);
        this.uids = new HashSet<>(20);
        this.users = new ArrayList<>(20);
    }

    @Override
    public void invoke(Map<Integer, String> data, AnalysisContext analysisContext) {
        if (data.size() <= 0) return;
        try {
            convertToScore(data);
            log.info("read data: {}", mapper.writeValueAsString(scores));
        } catch (Exception e) {
            log.error("ExcelEventListener ", e);
        }
    }

    @Override
    public void invokeHead(Map<Integer, ReadCellData<?>> headMap, AnalysisContext context) {
        try {
            headMap.entrySet().forEach(entry -> {
                String headTmp = entry.getValue().getStringValue();
                String[] courseCredit = headTmp.split("[ ]+");
                this.heads.add(courseCredit[0]);
                if (courseCredit.length >= 2 && !StringUtils.isEmpty(courseCredit[1])){
                    scoreToCredit.put(courseCredit[0], Float.valueOf(courseCredit[1]));
                }
            });
            log.info("value: {}, credit: {}",mapper.writeValueAsString(heads), mapper.writeValueAsString(scoreToCredit));
        } catch (Exception e) {
            log.error("ExcelEventListener invokeHead", e);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    private void convertToScore(Map<Integer, String> data){
        Long uid = Long.parseLong(data.get(3));
        String userName = data.get(4);
        String acadmic = data.get(1);
        int semester = Integer.parseInt(data.get(2));

        if (!uids.contains(uid)){
            User user = new User();
            user.setUid(uid);
            user.setUsername(userName);
            users.add(user);
        }

        String academicKey = String.join("_", acadmic, data.get(2));
        if (!academicToCourses.containsKey(academicKey)){
            String columns = String.join(",", this.heads.subList(5, this.heads.size()));
            this.academicToCourses.put(academicKey, columns);
        }

        for (int i = 5; i < this.heads.size(); i++){
            float num = Float.parseFloat(data.get(i));
            Score score = new Score();
            score.setUid(uid);
            score.setUsername(userName);
            score.setAcademicYear(acadmic);
            score.setSemester(semester);
            score.setScore(num);
            String course = this.heads.get(i);
            score.setCourse(course);
            score.setCredit(scoreToCredit.containsKey(course)?scoreToCredit.get(course):1);
            int nature = scoreToCredit.containsKey(course)?1:
                    Arrays.asList(ColumnConstant.SPORT, ColumnConstant.SPORTS, ColumnConstant.EDUCATION_SCORE).contains(course)?0:2;
            score.setCourseNature(nature);
            this.scores.add(score);
        }
    }

    public List<Score> getScores() {
        return scores;
    }

    public List<String> getHeads() {
        return heads;
    }

    public Map<String, String> getAcademicToCourses() {
        return academicToCourses;
    }

    public List<User> getUsers() {
        return users;
    }
}
