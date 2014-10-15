package com.cbedoy.apprende.new_version.service;

import org.pademobile.com.currency.interfaces.ICurrencyInformationDelegate;
import org.pademobile.com.currency.interfaces.ICurrencyInformationHandler;
import org.pademobile.com.interfaces.IMementoHandler;
import org.pademobile.com.interfaces.IRestService;
import org.pademobile.com.login.interfaces.ILoginInformationDelegate;
import org.pademobile.com.login.interfaces.ILoginInformationHandler;
import org.pademobile.com.objects.Memento;
import org.pademobile.com.payment.interfaces.IPaymentOriginsInformationDelegate;
import org.pademobile.com.payment.interfaces.IPaymentOriginsInformationHandler;
import org.pademobile.com.serviceinformation.interfaces.IServiceInformationInformationDelegate;
import org.pademobile.com.serviceinformation.interfaces.IServiceInformationInformationHandler;
import org.pademobile.com.usercertification.interfaces.IUserCertificationInformationDelegate;
import org.pademobile.com.usercertification.interfaces.IUserCertificationInformationHandler;
import org.pademobile.com.userselector.interfaces.IUserSelectorInformationDelegate;
import org.pademobile.com.userselector.interfaces.IUserSelectorInformationHandler;
import org.pademobile.com.wallet.interfaces.IWalletInformationDelegate;
import org.pademobile.com.wallet.interfaces.IWalletInformationHandler;
import org.pademobile.com.walletselector.interfaces.IWalletSelectorInformationDelegate;
import org.pademobile.com.walletselector.interfaces.IWalletSelectorInformationHandler;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InformationService implements ILoginInformationHandler, IUserCertificationInformationHandler, IUserSelectorInformationHandler, IServiceInformationInformationHandler, ICurrencyInformationHandler, IPaymentOriginsInformationHandler, IWalletSelectorInformationHandler, IWalletInformationHandler {

    private IRestService mRestService;
    private IMementoHandler mMementoHandler;
    private ILoginInformationDelegate mLoginController;
    private ICurrencyInformationDelegate mCurrenciesController;
    private IUserSelectorInformationDelegate mUserSelectorController;
    private IPaymentOriginsInformationDelegate mPaymentOriginsController;
    private IWalletSelectorInformationDelegate mWalletSelectorController;
    private IUserCertificationInformationDelegate mCertificationController;
    private IServiceInformationInformationDelegate mServiceInformationController;

    public void setRestService(IRestService restService) {
        mRestService = restService;
    }

    public void setMementoHandler(IMementoHandler mementoHandler) {
        mMementoHandler = mementoHandler;
    }

    public void setLoginController(ILoginInformationDelegate loginController) {
        mLoginController = loginController;
    }

    public void setCurrenciesController(ICurrencyInformationDelegate currencyController) {
        mCurrenciesController = currencyController;
    }

    public void setUserSelectorController(IUserSelectorInformationDelegate userSelectorController) {
        mUserSelectorController = userSelectorController;
    }

    public void setCertificationController(IUserCertificationInformationDelegate certificationController) {
        mCertificationController = certificationController;
    }

    public void setWalletSelectorController(IWalletSelectorInformationDelegate walletSelectorController) {
        mWalletSelectorController = walletSelectorController;
    }

    public void setPaymentOriginsController(IPaymentOriginsInformationDelegate paymentOriginsController) {
        mPaymentOriginsController = paymentOriginsController;
    }

    public void setServiceInformationController(IServiceInformationInformationDelegate serviceInformationController) {
        mServiceInformationController = serviceInformationController;
    }

    @Override
    public void login() {
        String url = "/ws/users.py/login";
        Memento memento = mMementoHandler.getTopMemento();
        HashMap<String, Object> data = memento.getMementoData();

        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("pin", data.get("pin"));
        parameters.put("nick", data.get("nick"));
        parameters.put("codigo_pais", data.get("codigo_pais"));
        parameters.put("idioma", data.get("idioma"));

        IRestService.IRestCallback callback = new IRestService.IRestCallback() {
            @Override
            public void run(HashMap<String, Object> response) {
                mLoginController.loginResponse(response);
            }
        };

        mRestService.request(url, parameters, callback);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void sendReferences() {
        String url = "/ws/netverify.py/finalizar";
        Memento memento = mMementoHandler.getTopMemento();
        HashMap<String, Object> data = memento.getMementoData();
        HashMap<String, Object> login_response = (HashMap<String, Object>) data.get("login_response");

        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id_usuario", login_response.get("id_usuario"));
        parameters.put("id_sesion", login_response.get("id_sesion"));
        parameters.put("codigo_pais", data.get("codigo_pais"));
        parameters.put("idioma", data.get("idioma"));
        parameters.put("merchantIdScanReference", data.get("generated_scan_reference"));
        parameters.put("jumioIdScanReference", data.get("external_scan_reference"));
        parameters.put("email", data.get("user_email"));

        IRestService.IRestCallback callback = new IRestService.IRestCallback() {
            @Override
            public void run(HashMap<String, Object> response) {
                mCertificationController.sendReferencesResponse(response);
            }
        };

        mRestService.request(url, parameters, callback);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void getAvailableUsers() {
        Memento memento = mMementoHandler.getTopMemento();
        HashMap<String, Object> data = memento.getMementoData();

        HashMap<String, Object> targetUser = (HashMap<String, Object>) data.get("target_user");
        ArrayList<HashMap<String, Object>> usersList = (ArrayList<HashMap<String, Object>>) data.get("users_list");

        ArrayList<HashMap<String, Object>> responseList = new ArrayList<HashMap<String, Object>>();
        if (targetUser != null) {
            responseList.add(targetUser);
        }
        if (usersList != null) {
            responseList.addAll(usersList);
        }

        HashMap<String, Object> response = new HashMap<String, Object>();
        response.put("status", true);
        response.put("users_list", responseList);

        mUserSelectorController.availableUsersResponse(response);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void requestServiceInformation() {
        String url = "/ws/util.py/get_caracteristicas";
        Memento memento = mMementoHandler.getTopMemento();
        HashMap<String, Object> data = memento.getMementoData();
        HashMap<String, Object> loginResponse = (HashMap<String, Object>) data.get("login_response");
        HashMap<String, Object> targetUser = (HashMap<String, Object>) data.get("target_user");

        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id_usuario", loginResponse.get("id_usuario"));
        parameters.put("id_sesion", loginResponse.get("id_sesion"));
        parameters.put("codigo_pais", data.get("codigo_pais"));
        parameters.put("idioma", data.get("idioma"));

        String entry = data.get("punto_de_entrada").toString();
        if (entry.compareToIgnoreCase("enviodinero") == 0 && data.get("codigo_pais").toString().compareToIgnoreCase(targetUser.get("codigo_pais").toString()) != 0)
            entry = "remesadirigida";
        parameters.put("punto_de_entrada", entry);

        IRestService.IRestCallback callback = new IRestService.IRestCallback() {
            @Override
            public void run(HashMap<String, Object> response) {
                mServiceInformationController.serviceInformationResponse(response);
            }
        };

        mRestService.request(url, parameters, callback);
    }

    @Override
    public void getAvailableCurrencies() {
        String url = "/ws/divisas.py/listado";
        Memento memento = mMementoHandler.getTopMemento();
        HashMap<String, Object> data = memento.getMementoData();

        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idioma", data.get("idioma"));
        parameters.put("seleccion", true);

        IRestService.IRestCallback callback = new IRestService.IRestCallback() {
            @Override
            public void run(HashMap<String, Object> response) {
                mCurrenciesController.currenciesResponse(response);
            }
        };

        mRestService.request(url, parameters, callback);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void requestPaymentOrigins() {
        String url = "/ws/origenesdefondos.py/gestor_origenes_propios";
        Memento memento = mMementoHandler.getTopMemento();
        HashMap<String, Object> data = memento.getMementoData();
        HashMap<String, Object> loginResponse = (HashMap<String, Object>) data.get("login_response");

        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id_usuario", loginResponse.get("id_usuario"));
        parameters.put("id_sesion", loginResponse.get("id_sesion"));
        parameters.put("codigo_pais", data.get("codigo_pais"));
        parameters.put("idioma", data.get("idioma"));
        parameters.put("comando", "listado");

        IRestService.IRestCallback callback = new IRestService.IRestCallback() {
            @Override
            public void run(HashMap<String, Object> response) {
                mPaymentOriginsController.paymentOriginsResponse(response);
            }
        };

        mRestService.request(url, parameters, callback);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void deletePaymentOrigin() {
        String url = "/ws/origenesdefondos.py/gestor_origenes_propios";
        Memento memento = mMementoHandler.getTopMemento();
        HashMap<String, Object> data = memento.getMementoData();
        HashMap<String, Object> loginResponse = (HashMap<String, Object>) data.get("login_response");
        HashMap<String, Object> paymentOrigin = (HashMap<String, Object>) data.get("deleted_origin");

        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id_usuario", loginResponse.get("id_usuario"));
        parameters.put("id_sesion", loginResponse.get("id_sesion"));
        parameters.put("codigo_pais", data.get("codigo_pais"));
        parameters.put("idioma", data.get("idioma"));
        parameters.put("comando", "borrar");
        parameters.put("id_origen_o_perfil", paymentOrigin.get("id"));

        IRestService.IRestCallback callback = new IRestService.IRestCallback() {
            @Override
            public void run(HashMap<String, Object> response) {
                mPaymentOriginsController.deletePaymentOriginResponse(response);
            }
        };

        mRestService.request(url, parameters, callback);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setPreferredPaymentOrigin() {
        try {
            Memento memento = mMementoHandler.getTopMemento();
            HashMap<String, Object> data = memento.getMementoData();
            HashMap<String, Object> paymentOrigin = (HashMap<String, Object>) data.get("preferred_origin");

            String id = paymentOrigin.get("id").toString();
            Method method = mPaymentOriginsController.getClass().getDeclaredMethod("preferredPaymentOriginResponse", HashMap.class);

            setPreferredPaymentOrigin(id, mPaymentOriginsController, method);
        } catch (Exception ex) {
            HashMap<String, Object> response = new HashMap<String, Object>();
            response.put("status", false);
            response.put("error", "java_lang_reflect_method");
            response.put("message", "Unknown Declared Method");

            mPaymentOriginsController.preferredPaymentOriginResponse(response);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void getAvailableWallets() {
        Memento memento = mMementoHandler.getTopMemento();
        HashMap<String, Object> data = memento.getMementoData();
        HashMap<String, Object> paymentOriginsResponse = (HashMap<String, Object>) data.get("payment_origins_response");
        HashMap<String, Object> resultado = (HashMap<String, Object>) paymentOriginsResponse.get("resultado");
        ArrayList<HashMap<String, Object>> creadores = (ArrayList<HashMap<String, Object>>) resultado.get("creadores");

        HashMap<String, Object> response = new HashMap<String, Object>();
        response.put("status", true);
        response.put("available_wallets", creadores);

        mWalletSelectorController.availableWalletsResponse(response);
    }

    @SuppressWarnings("unchecked")
    private void setPreferredPaymentOrigin(String id, final Object receiver, final Method method) {
        String url = "/ws/origenesdefondos.py/gestor_origenes_propios";
        Memento memento = mMementoHandler.getTopMemento();
        HashMap<String, Object> data = memento.getMementoData();
        HashMap<String, Object> loginResponse = (HashMap<String, Object>) data.get("login_response");

        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id_usuario", loginResponse.get("id_usuario"));
        parameters.put("id_sesion", loginResponse.get("id_sesion"));
        parameters.put("codigo_pais", data.get("codigo_pais"));
        parameters.put("idioma", data.get("idioma"));
        parameters.put("comando", "cambiar_por_defecto");
        parameters.put("id_origen_o_perfil", id);

        IRestService.IRestCallback callback = new IRestService.IRestCallback() {
            @Override
            public void run(HashMap<String, Object> response) {
                try {
                    method.invoke(receiver, response);
                } catch (Exception ex) {

                }
            }
        };

        mRestService.request(url, parameters, callback);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void addWallet() {
        String url = "/ws/origenesdefondos.py/gestor_origenes_propios";
        Memento memento = mMementoHandler.getTopMemento();
        HashMap<String, Object> data = memento.getMementoData();
        HashMap<String, Object> loginResponse = (HashMap<String, Object>) data.get("login_response");
        HashMap<String, Object> selectedWallet = (HashMap<String, Object>) data.get("selected_wallet");
        HashMap<String, Object> walletParameters = (HashMap<String, Object>) data.get("wallet_parameters");
        final IWalletInformationDelegate walletController = (IWalletInformationDelegate) data.get("wallet_controller");

        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id_usuario", loginResponse.get("id_usuario"));
        parameters.put("id_sesion", loginResponse.get("id_sesion"));
        parameters.put("codigo_pais", data.get("codigo_pais"));
        parameters.put("idioma", data.get("idioma"));
        parameters.put("comando", "crear");
        parameters.put("id_origen_o_perfil", selectedWallet.get("id"));

        for (Map.Entry<String, Object> entry : walletParameters.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            parameters.put(key, value);
        }

        IRestService.IRestCallback callback = new IRestService.IRestCallback() {
            @Override
            public void run(HashMap<String, Object> response) {
                boolean status = (Boolean) response.get("status");
                if (status) {
                    HashMap<String, Object> data = new HashMap<String, Object>();
                    data.put("wallet_response", response);
                    mMementoHandler.setStateForOwner(data, this);

                    HashMap<String, Object> resultado = (HashMap<String, Object>) response.get("resultado");
                    String idOrigenCreado = resultado.get("id_origen_creado").toString();

                    try {
                        Method method = walletController.getClass().getDeclaredMethod("addWalletResponse", HashMap.class);

                        setPreferredPaymentOrigin(idOrigenCreado, walletController, method);
                    } catch (Exception ex) {
                        HashMap<String, Object> internalResponse = new HashMap<String, Object>();
                        internalResponse.put("status", false);
                        internalResponse.put("error", "java_lang_reflect_method");
                        internalResponse.put("message", "Unknown Declared Method");

                        walletController.addWalletResponse(internalResponse);
                    }
                } else {
                    walletController.addWalletResponse(response);
                }
            }
        };

        mRestService.request(url, parameters, callback);
    }

}
