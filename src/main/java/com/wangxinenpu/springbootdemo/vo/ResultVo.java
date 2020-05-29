package com.wangxinenpu.springbootdemo.vo;

public class ResultVo<T>   {
    private static final long serialVersionUID = -1222614520893986846L;
    private T result;
    private boolean isSuccess = false;
    private String code;
    private String resultDes;

    public ResultVo() {
    }

    public T getResult() {
        return this.result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getResultDes() {
        return this.resultDes;
    }

    public void setResultDes(String resultDes) {
        this.resultDes = resultDes;
    }
}
