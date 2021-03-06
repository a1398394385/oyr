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
     * @param code 状态码
     * @return Response<T>
     */
    public static <T> Response<T> result(int code) {
        Response<T> response = new Response<>();
        response.setStatus("success");
        response.setCode(code);
        return response;
    }

    /**
     * 成功返回,含数据
     *
     * @param code 状态码
     * @param data 返回数据
     * @return Response<T>
     */
    public static <T> Response<T> result(int code, T data) {
        Response<T> response = new Response<>();
        response.setStatus("success");
        response.setCode(code);
        response.setData(data);
        return response;
    }

    /**
     * 失败返回
     *
     * @param code    状态码
     * @param message 错误信息
     * @return Response<T>
     */
    public static <T> Response<T> error(int code, String message) {
        Response<T> response = new Response<>();
        response.setStatus("filed");
        response.setCode(code);
        response.setMessage(message);
        return response;
    }
}
