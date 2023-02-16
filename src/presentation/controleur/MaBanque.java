package presentation.controleur;


import metier.ServiceAuth.IServiceAuth;
import metier.ServiceAuth.ServiceAuth;
import presentation.vue.LoginFrames.LoginFrame;

public class MaBanque {

        public static void main(String[] args) {
                IServiceAuth serviceAuth = new ServiceAuth();

                new LoginFrame(serviceAuth);

        }
}