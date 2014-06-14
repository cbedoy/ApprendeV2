package com.cbedoy.apprende.interfaces;

import java.util.HashMap;

import com.cbedoy.apprende.keysets.UserKeySet;

public interface IProfileRepresentacionDelegate {
	
	public void reloadViewWithData(HashMap<UserKeySet, Object> dataModel);
	
}
