package com.stu.manage.entiry;

import java.util.List;

/**
 * @author: Sparks
 * @Date: 2024/1/17 22:32
 * @Description
 */
public class CommonResult<T> {
    private int count;

    private List<T> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public static class CommonResultBuilder<R> {
        private CommonResult<R> result;

        public CommonResultBuilder() {
            result = new CommonResult<>();
        }

        public CommonResultBuilder<R> counts(int count){
            result.setCount(count);
            return this;
        }

        public CommonResultBuilder<R> results(List<R> res){
            result.setResults(res);
            return this;
        }

        public CommonResult<R> build(){
            return result;
        }
    }
}
