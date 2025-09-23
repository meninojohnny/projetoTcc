/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.GeoRegulariza.utilitarie;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author johnny
 */

public class Utils {

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }

    public static boolean isEmpty(String string) {
        return string == null || string.length() == 0;
    }

    public static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }

    /**
     * <p>
     * É considerado vazio todo e qualquer array cujo valor seja
     * <b>null</b>, ou o tamanho seja <b>zero</b>, ou todos os elementos
     * contidos no array sejam <b>null</b>. </p>
     *
     * @param args
     * @return
     */
    public static boolean isEmpty(Object... args) {

        // Qualquer valor passado que seja nulo sera considerado como vazio
        if (args == null || args.length == 0) {
            return true;
        } else {
            // pecorre o array, se existe um item não nulo é porque o array não esta vazio
            for (Object object : args) {
                if (object != null) {
                    return false;
                }
            }
            return true;
        }
    }

    public static boolean isNotEmpty(Object... args) {
        return !isEmpty(args);
    }

    /*public static boolean isEmpty(BasicBean bean) {
        return bean == null || bean.getId() == null;
    }

    public static boolean isNotEmpty(BasicBean bean) {
        return !isEmpty(bean);
    }*/
    public static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    public static String convertDateToString(Date date, String mask) {
        SimpleDateFormat format = new SimpleDateFormat(mask);
        String result = format.format(date);
        return result;
    }

    public static String colocarMascaraCpf(String cpf) {
        if (cpf.length() > 10) {
            cpf = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
        }
        return cpf;
    }

    public static String colocarMascaraCep(String cep) {
        if (cep.length() > 7) {
            cep = cep.substring(0, 2) + "." + cep.substring(2, 5) + "-" + cep.substring(5, 8);
        }
        return cep;
    }

    public static String colocarMascaraTelefone(String cep) {
        if (cep.length() > 9) {
            cep = "(" + cep.substring(0, 2) + ")" + cep.substring(2, 6) + "-" + cep.substring(6, 10);
        }
        return cep;
    }

    public static String colocarMascaraWhatsapp(String cep) {
        if (cep.length() > 10) {
            cep = "(" + cep.substring(0, 2) + ")" + cep.substring(2, 6) + "-" + cep.substring(6, 11);
        }
        return cep;
    }

    public static String addMascaraCnpj(String cnpj) {
        if (cnpj.length() > 12) {
            cnpj = cnpj.substring(0, 2) + "." + cnpj.substring(2, 5) + "." + cnpj.substring(5, 8) + "/" + cnpj.substring(8, 12) + "-" + cnpj.substring(12);
        }
        return cnpj;
    }
    
    public static LocalDateTime dateToLocalDatetime(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
    
    public static String formatarData(Date date) {
        LocalDateTime localDate = dateToLocalDatetime(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return localDate.format(formatter);
    }
}

