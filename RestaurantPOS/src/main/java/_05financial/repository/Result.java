package _05financial.repository;

import java.io.Serializable;

public class Result<T> implements Serializable {
	
	private static final long serialVersionUID = 3637122497350396679L;

    private boolean success;
    private T data;
    private String msg;

    public Result() {
    }

    public Result(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Result(boolean success, String msg) {
        super();
        this.success = success;
        this.msg = msg;
    }

    public Result(boolean success, T data) {
        super();
        this.success = success;
        this.data = data;
    }


}
