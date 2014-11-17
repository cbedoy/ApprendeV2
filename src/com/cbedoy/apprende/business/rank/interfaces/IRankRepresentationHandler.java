package com.cbedoy.apprende.business.rank.interfaces;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Carlos on 17/11/2014.
 * <p/>
 * Mobile App Developer
 * <p/>
 * Apprende
 */
public interface IRankRepresentationHandler
{
    public void reloadWithRankData(List<HashMap<String, Object>> rankData);
}
