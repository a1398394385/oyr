package oyw.gp.oyr.entity;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Auth
{
    HttpServletRequest httpServletRequest;

    public Auth(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    };

    public HashMap<String, Object> getSession() {
        HttpSession httpSession = httpServletRequest.getSession(false);
        HashMap<String, Object> session = new HashMap<>();

        if (httpSession != null && httpSession.getAttribute("id") != null) {
            session.put("id", httpSession.getAttribute("id"));
            session.put("username", httpSession.getAttribute("username"));
            session.put("telephone", httpSession.getAttribute("telephone"));
            session.put("address", httpSession.getAttribute("address"));
            return session;
        }

        return null;
    }

    /**
     * 前端获取session中用户信息
     * 
     * @param order
     * @return
     */
    public HashMap<String, Object> getSession(Boolean order) {
        HttpSession httpSession = httpServletRequest.getSession(false);
        HashMap<String, Object> session = new HashMap<>();

        if (httpSession != null && httpSession.getAttribute("id") != null && order == true) {
            session.put("id", httpSession.getAttribute("id"));
            session.put("username", httpSession.getAttribute("username"));
            session.put("telephone", httpSession.getAttribute("telephone"));
            session.put("address", httpSession.getAttribute("address"));
            session.put("price", httpSession.getAttribute("price"));
            session.put("phoneId", httpSession.getAttribute("phoneId"));
            return session;
        }

        return null;
    }
}
