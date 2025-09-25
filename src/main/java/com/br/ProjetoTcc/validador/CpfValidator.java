/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.ProjetoTcc.validador;

import com.br.GeoRegulariza.utilitarie.Characters;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 *
 * @author johnny
 */

public class CpfValidator implements
        ConstraintValidator<CPF, String> {

    @Override
    public void initialize(CPF constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return validaCPF(Characters.removecaracter(value));
    }

    private static boolean validaCPF(String cpf) {
        if (cpf == null || cpf.trim().equals("")) {
            return true;
        } else if (cpf.length() != 11 || isCPFPadrao(cpf)) {
            return false;
        }

        /**
         * mock, para satisfazer campo de cpf/cnpj do mobile
         */
        if (cpf.length() == 14) {
            CnpjValidator cv = new CnpjValidator();
            return cv.isCNPJ(cpf);
        }

        try {
            Long.parseLong(cpf);
        } catch (NumberFormatException e) {
            return false;
        }



        return calcDigVerif(cpf.substring(0, 9)).equals(cpf.substring(9, 11));
    }

    private static boolean isCPFPadrao(String cpf) {
        if (cpf.equals("11111111111") || cpf.equals("22222222222")
                || cpf.equals("00000000000")
                || cpf.equals("33333333333")
                || cpf.equals("44444444444")
                || cpf.equals("55555555555")
                || cpf.equals("66666666666")
                || cpf.equals("77777777777")
                || cpf.equals("88888888888")
                || cpf.equals("99999999999")) {

            return true;
        }

        return false;
    }

    private static String calcDigVerif(String num) {
        Integer primDig, segDig;
        int soma = 0, peso = 10;
        for (int i = 0; i < num.length(); i++) {
            soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;
        }

        if (soma % 11 == 0 | soma % 11 == 1) {
            primDig = new Integer(0);
        } else {
            primDig = new Integer(11 - (soma % 11));
        }

        soma = 0;
        peso = 11;
        for (int i = 0; i < num.length(); i++) {
            soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;
        }

        soma += primDig.intValue() * 2;
        if (soma % 11 == 0 | soma % 11 == 1) {
            segDig = new Integer(0);
        } else {
            segDig = new Integer(11 - (soma % 11));
        }

        return primDig.toString() + segDig.toString();
    }
}

