package oyw.gp.oyr.service;

import org.springframework.stereotype.Service;

import oyw.gp.oyr.entity.Response;

/**
 * UserInfoService
 */
@Service
public interface UserInfoService
{
    /**
     * 
     * @return
     */
    public Response<Object> reviseTelephone();

    /**
     * 
     * @return
     */
    public Response<Object> revisePassword();

    /**
     * 
     * @return
     */
    public Response<Object> reviseAddress();

    /**
     * 
     * @return
     */
    public Response<Object> reviseUsername();
}
