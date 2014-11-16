package com.cbedoy.apprende.business.singup;

import com.cbedoy.apprende.business.BusinessController;
import com.cbedoy.apprende.business.singup.interfaces.ISignUpInformationHandler;
import com.cbedoy.apprende.business.singup.interfaces.ISignUpRepresentationHandler;
import com.cbedoy.apprende.business.singup.interfaces.ISignUpTransactionHandler;
import com.cbedoy.apprende.business.singup.interfaces.ISingupInformationDelegate;
import com.cbedoy.apprende.business.singup.interfaces.ISingupRepresentationDelegate;
import com.cbedoy.apprende.business.singup.interfaces.ISingupTransactionDelegate;
import com.cbedoy.apprende.interfaces.IMessageRepresentationHandler;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Carlos on 15/10/2014.
 */
public class SignUpBusinessController extends BusinessController implements ISingupTransactionDelegate, ISingupRepresentationDelegate, ISingupInformationDelegate
{
    private ISignUpInformationHandler informationHandler;
    private ISignUpRepresentationHandler representationHandler;
    private ISignUpTransactionHandler transactionHandler;

    public void setInformationHandler(ISignUpInformationHandler informationHandler) {
        this.informationHandler = informationHandler;
    }

    public void setRepresentationHandler(ISignUpRepresentationHandler representationHandler) {
        this.representationHandler = representationHandler;
    }

    public void setTransactionHandler(ISignUpTransactionHandler transactionHandler) {
        this.transactionHandler = transactionHandler;
    }

    @Override
    public void singupResponse(HashMap<String, Object> response) {

    }

    @Override
    public void singupWithData(HashMap<String, Object> currentInformation) {
        String username = currentInformation.get("username").toString();
        String email = currentInformation.get("email").toString();
        String password = currentInformation.get("password").toString();
        String confirm_password = currentInformation.get("confirm_password").toString();
        String first_name = currentInformation.get("first_name").toString();
        String last_name = currentInformation.get("last_name").toString();

        if(!validateCommonField(username) && !validateCommonField(last_name) && validateCommonField(first_name)){
            messageRepresentationHandler.showCode(IMessageRepresentationHandler.NOTIFICATION_CODE.K_INVALID_COMMON_FIELDS);
        }else if(!validateEmail(email)){
            messageRepresentationHandler.showCode(IMessageRepresentationHandler.NOTIFICATION_CODE.K_INVALID_EMAIL);
        }else if(!valideEqualsPasswords(password, confirm_password)){
            messageRepresentationHandler.showCode(IMessageRepresentationHandler.NOTIFICATION_CODE.K_INVALID_PASSWORD_FORMAT);
        }else{
            mementoHandler.setStateForOwner(currentInformation, this);

            messageRepresentationHandler.showLoading();
            startSingup();
        }
    }

    @Override
    public void startSingup() {
        representationHandler.showSingupView();
    }

    private boolean validateEmail(String email){
        final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean validateCommonField(String common){
        return common.length()>6 ? true : false;
    }

    private boolean valideEqualsPasswords(String passwordOne, String passwordTwo){
        return passwordOne.equals(passwordTwo);
    }


    @Override
    public void backRequested() {
        mementoHandler.popDataFor(this);
    }

}
