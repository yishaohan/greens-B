package com.ysh.projectY.paypal;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

public class Credentials {
    /*final static String clientID = "AYsnjLgEmwy2RIhy0QdO335NCySWCFKnxecxVmVhTVUxLE8hdw_ZNs1LvGD5sH61BErt2JxRuTc7I9Pn";
    final static String secret = "EPG8TJiiiHlA5wJOjqM_4AG0ddWeDPupGKdGWpONfcGRwY2auStDXBTbJjbT2Hi2Bzfn9ZOD1lmBrTbJ";
    final private static PayPalEnvironment env = new PayPalEnvironment.Sandbox(clientID, secret);
    public final static PayPalHttpClient paypalClient = new PayPalHttpClient(env);*/

    @Value("${projectY.paypal-isSandbox}")
    private boolean flag;

    final String clientID;
    final String secret;
    //    final private static PayPalEnvironment env = new PayPalEnvironment.Sandbox(clientID, secret);
    final private PayPalEnvironment env;
    public final PayPalHttpClient paypalClient;

    public Credentials() {
        System.out.println("flag3: " + flag);
        if (flag) {
            clientID = "AYsnjLgEmwy2RIhy0QdO335NCySWCFKnxecxVmVhTVUxLE8hdw_ZNs1LvGD5sH61BErt2JxRuTc7I9Pn";
            secret = "EPG8TJiiiHlA5wJOjqM_4AG0ddWeDPupGKdGWpONfcGRwY2auStDXBTbJjbT2Hi2Bzfn9ZOD1lmBrTbJ";
            env = new PayPalEnvironment.Sandbox(clientID, secret);
        } else {
            clientID = "AViXcCKHnqll0J1Lh6mZNFeZ626CepBfscyBpuhV3jed0e8ZCvcMzP4n10eB2qx_gKtSfoZYxMOQgb1y";
            secret = "EC2bYzGx99Ap7ibkO1_zjzSmm_spTUNnBOq95eDpq6LICZrXCEFGhM65Ioum1JSvrPMNOdjL9rgGN3Nk";
            env = new PayPalEnvironment.Live(clientID, secret);
        }

        paypalClient = new PayPalHttpClient(env);
    }

    public boolean isFlag() {
        return flag;
    }

    public String getClientID() {
        return clientID;
    }

    public String getSecret() {
        return secret;
    }

    public PayPalHttpClient getPaypalClient() {
        return paypalClient;
    }
}
