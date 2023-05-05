package com.github.itdachen.auth.jwts.parse;

import com.github.itdachen.auth.jwts.core.IJwtsInfo;

/**
 * Description:
 * Created by 王大宸 on 2023/05/05 15:27
 * Created with IntelliJ IDEA.
 */
public interface IVerifyTicketTokenHelper {

    IJwtsInfo verifyTicketToken(String token) throws Exception;

}
