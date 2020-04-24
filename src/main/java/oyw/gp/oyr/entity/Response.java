package oyw.gp.oyr.entity;

import lombok.Data;

@Data
public class Response<T> {
    /**
     * success / filed
     */
    private String status;

    /**
     * 具体的错误信息
     */
    private String message;

    /**
     * 状态码
     */
    private int code;

    /**
     * 返回的数据
     */
    private T data;

    /**
     * 成功返回,不含数据
     * 
     * @param code
     * @param data
     * @return
     */
    public static Response result(int code) {
        Response response = new Response<>();
        response.setStatus("success");
        response.setCode(code);
        return response;
    }

    /**
     * 成功返回,含数据
     * 
     * @param code
     * @param data
     * @return
     */
    public static Response<Object> result(int code, Object data) {
        Response<Object> response = new Response<>();
        response.setStatus("success");
        response.setCode(code);
        response.setData(data);
        return response;
    }

    /**
     * 失败返回
     * 
     * @param code
     * @param message
     * @return
     */
    public static Response error(int code, String message) {
        Response response = new Response<>();
        response.setStatus("filed");
        response.setCode(code);
        response.setMessage(message);
        return response;
    }
}