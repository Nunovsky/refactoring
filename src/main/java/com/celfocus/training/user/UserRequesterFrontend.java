package com.celfocus.training.user;

import com.celfocus.training.Saver;
import com.celfocus.training.entity.User;
import com.celfocus.training.entity.cart.ItemInfo;
import com.celfocus.training.entity.cart.ShoppingCart;
import com.celfocus.training.util.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User For Frontent
 */
public class UserRequesterFrontend {

    /**
     * Metodo utilizado para retornar o Usuario no formato do frontend solicitado
     *
     * @param type tipo do frontend utilizado
     * @param user usuario que será renderizado
     * @return o texto no formato solicitado com as informarções do user
     */
    public String returnFrontendUser(String type, User user) {
        if (type.equals("html")) {
            return "<div>"
                + "<h1>User</h1>"
                + "<span>" + user.getName() + "</span>"
                + "<span>" + user.getBirthday() + "</span>"
                + "<span>" + user.isNotMinor() + "</span>"
                + "</div>";
        } else {
            if (type.equals("xml")) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                    + "<name> " + user.getName() + "</name>"
                    + "<bd>" + user.getBirthday() + "</bd>"
                    + "<older> " + user.isNotMinor() + "</older>";
            } else {
                //do nothing
                return "";
            }
        }
    }

    /**
     * Metodo utilizado para retornar o Shoppingcart no formato do frontend solicitado
     *
     * @param type         tipo do frontend utilizado
     * @param shoppingCart shoppingCart que será renderizado
     * @return o texto no formato solicitado com as informarções do shoppingCart
     */
    public String returnFrontendShoppingCart(String type, ShoppingCart shoppingCart) {
        if (type.equals("html")) {
            return "<div>"
                + "<h1>ShoppingCart</h1>"
                + "<span> " + shoppingCart.getUser() + "</span>"
                + "<span> " + shoppingCart.getItems() + "</span>"
                + "</div>";
        } else {
            if (type.equals("xml")) {
                return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                    + "<user> " + shoppingCart.getUser() + "</user>"
                    + "<itens> " + shoppingCart.getItems() + "</itens>";
            } else {
                //do nothing
                return "";
            }
        }
    }

    /**
     * Metodo utilizado para retornar o Item no formato do frontend solicitado
     *
     * @param type tipo do frontend utilizado
     * @param item item que será renderizado
     * @return o texto no formato solicitado com as informarções do item
     */
    public String returnFrontendItem(String type, ItemInfo item) {
        if (type.equals("html")) {
            return "<div>"
                + "<h1>Item</h1>"
                + "<span> " + item.getName() + "</span>"
                + "<span> " + item.getValor() + "</span>"
                + "</div>";
        } else {
            if (type.equals("xml")) {
                return "<name> " + item.getName() + "</name>"
                    + "<valor> " + item.getValor() + "</valor>";
            } else {
                //do nothing
                return "";
            }
        }
    }

    /**
     * Cria ou atualiza usuario
     *
     * @param name
     * @param birthDate
     * @param isUserOlder
     */
    public void createOrUpdateUser(String name, String birthDate, String isUserOlder) {
        Saver saver = new Saver();

        name = name.toUpperCase();

        Date d = Utils.toDate(birthDate, new SimpleDateFormat("dd/mm/yyyy"));
        if (new Date().getYear() - d.getYear() < 65) {
            isUserOlder = "false";
        }

        saver.saveOrUpdateUser(name, Utils.toDate(birthDate, new SimpleDateFormat("dd/mm/yyyy")), isUserOlder.equals("true") ? true : false);
    }

    /**
     * Remover Usuario
     */
    public void deleteUser(String arg0) {
        Saver saver = new Saver();
        saver.deleteUserOrNot(arg0);
    }

    /**
     * Adicionar item ao carrinho
     */
    public void addItemToShoppingCart(String user, String nameItem, int qt) {
        Saver saver = new Saver();

        nameItem = nameItem.toLowerCase().concat("_item");

        saver.addItemToUser(user, nameItem, qt);
    }

}