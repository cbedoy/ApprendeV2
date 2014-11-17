package com.cbedoy.apprende.business.rank;

import com.cbedoy.apprende.business.BusinessController;
import com.cbedoy.apprende.business.rank.interfaces.IRankInformationDelegate;
import com.cbedoy.apprende.business.rank.interfaces.IRankInformationHandler;
import com.cbedoy.apprende.business.rank.interfaces.IRankRepresentationDelegate;
import com.cbedoy.apprende.business.rank.interfaces.IRankRepresentationHandler;
import com.cbedoy.apprende.business.rank.interfaces.IRankTransactionDelegate;
import com.cbedoy.apprende.business.rank.interfaces.IRankTransactionHandler;

import java.util.HashMap;

/**
 * Created by Carlos on 17/11/2014.
 * <p/>
 * Mobile App Developer
 * <p/>
 * Apprende
 */
public class RankBusinessController extends BusinessController implements IRankInformationDelegate, IRankTransactionDelegate, IRankRepresentationDelegate {

    private IRankInformationHandler informationHandler;
    private IRankRepresentationHandler representationHandler;
    private IRankTransactionHandler rankTransactionHandler;

    public void setInformationHandler(IRankInformationHandler informationHandler) {
        this.informationHandler = informationHandler;
    }

    public void setRepresentationHandler(IRankRepresentationHandler representationHandler) {
        this.representationHandler = representationHandler;
    }

    public void setRankTransactionHandler(IRankTransactionHandler rankTransactionHandler) {
        this.rankTransactionHandler = rankTransactionHandler;
    }

    @Override
    public void rankResponse(HashMap<String, Object> response) {

    }

    @Override
    public void getRankData() {

    }

    @Override
    public void didUserSelectedRankValue() {

    }
}
