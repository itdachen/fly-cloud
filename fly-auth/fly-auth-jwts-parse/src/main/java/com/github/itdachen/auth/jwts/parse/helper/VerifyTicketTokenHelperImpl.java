package com.github.itdachen.auth.jwts.parse.helper;

import com.github.itdachen.auth.jwts.core.IJwtsInfo;
import com.github.itdachen.auth.jwts.core.SecretKeyConfiguration;
import com.github.itdachen.auth.jwts.parse.IVerifyTicketTokenHelper;
import com.github.itdachen.auth.jwts.parse.key.AuthClientTokenSecretKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description:
 * Created by 王大宸 on 2023/05/05 15:28
 * Created with IntelliJ IDEA.
 */
@Service
public class VerifyTicketTokenHelperImpl implements IVerifyTicketTokenHelper {

    private final AuthClientTokenSecretKey authClientTokenSecretKey;
    private final ParseTokenHelper parseTokenHelper;

    public VerifyTicketTokenHelperImpl(AuthClientTokenSecretKey authClientTokenSecretKey,
                                       ParseTokenHelper parseTokenHelper) {
        this.authClientTokenSecretKey = authClientTokenSecretKey;
        this.parseTokenHelper = parseTokenHelper;
    }

    @Override
    public IJwtsInfo verifyTicketToken(String token) throws Exception {
        IJwtsInfo iJwtsInfo = parseTokenHelper.parseToken(token, authClientTokenSecretKey.getTokenPublicKey());
        return iJwtsInfo;
    }


}
