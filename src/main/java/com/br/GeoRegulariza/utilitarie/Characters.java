/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.GeoRegulariza.utilitarie;

import java.security.SecureRandom;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author johnny
 */

public class Characters {

    public static String cpfMask = "###.###.###-##";
    public static String cnpjMask = "##.###.###/####-##";
    public static String cepMask = "##.###-###";
    public static String phoneMask = "(##) ####-####";
    public static String inscricaoCadastralMask = "##.##.###.####.##";

    public static String removecaracter(String s) {
        if (s != null) {
            s = s.replaceAll("\\.", "")
                    .replace("-", "")
                    .replace("/", "")
                    .replace("-", "")
                    .replace("[", "")
                    .replace("]", "")
                    .replace("{", "")
                    .replace("}", "")
                    .replace("_", "")
                    .replace("(", "")
                    .replace(")", "")
                    .replace(" ", "");
        }
        return s;
    }

    public static String removeCaracteresEspeciaispor(String text, String por) {
        if (text == null) {
            return "";
        }
        return text.replaceAll("\\.", por).replaceAll(",", por).replaceAll("ç", por).replaceAll(" ", por).replaceAll("-", por).replaceAll("[ãâàáä]", por).replaceAll("[êèéë]", por).replaceAll("[îìíï]", por).replaceAll("[õôòóö]", por).replaceAll("[ûúùü]", por).replaceAll("[ÃÂÀÁÄ]", por).replaceAll("[ÊÈÉË]", por).replaceAll("[ÎÌÍÏ]", por).replaceAll("[ÕÔÒÓÖ]", por).replaceAll("[ÛÙÚÜ]", por).replaceAll("\\[\\´\\`\\?!\\@\\#\\$\\%\\¨\\*", por).replaceAll("\\(\\)\\=\\{\\}\\[\\]\\~\\^\\]", por).replaceAll("[\\.\\;\\-\\_\\+\\'\\ª\\º\\:\\;\\/]", por);
    }

    public static String htmlToText(String string) {

        string = string.replaceAll("\\<.*?\\>", "");

        string = string.replaceAll("&nbsp;", " ");

        string = string.replaceAll("&Acirc;", "Â");
        string = string.replaceAll("&Agrave;", "À");
        string = string.replaceAll("&Aacute;", "Á");
        string = string.replaceAll("&Auml;", "Ä");
        string = string.replaceAll("&Atilde;", "Ã");
        string = string.replaceAll("&Auml;", "Ä");
        string = string.replaceAll("&Aring;", "Å");

        string = string.replaceAll("&acirc;", "â");
        string = string.replaceAll("&agrave;", "à");
        string = string.replaceAll("&aacute;", "á");
        string = string.replaceAll("&auml;", "ä");
        string = string.replaceAll("&atilde;", "ã");
        string = string.replaceAll("&atilde;", "ä");
        string = string.replaceAll("&aring;", "å");

        string = string.replaceAll("&Ecirc;", "Ê");
        string = string.replaceAll("&Egrave;", "È");
        string = string.replaceAll("&Eacute;", "É");
        string = string.replaceAll("&Euml;", "Ë");

        string = string.replaceAll("&ecirc;", "ê");
        string = string.replaceAll("&egrave;", "è");
        string = string.replaceAll("&eacute;", "é");
        string = string.replaceAll("&euml;", "ë");

        string = string.replaceAll("&Icirc;", "Î");
        string = string.replaceAll("&Iacute;", "Í");
        string = string.replaceAll("&Igrave;", "Ì");
        string = string.replaceAll("&Iuml;", "Ï");

        string = string.replaceAll("&icirc;", "î");
        string = string.replaceAll("&iacute;", "í");
        string = string.replaceAll("&igrave;", "ì");
        string = string.replaceAll("&iuml;", "ï");

        string = string.replaceAll("&Ocirc;", "Ô");
        string = string.replaceAll("&Otilde;", "Õ");
        string = string.replaceAll("&Ograve;", "Ò");
        string = string.replaceAll("&Oacute;", "Ó");
        string = string.replaceAll("&Ouml;", "Ö");

        string = string.replaceAll("&ocirc;", "ô");
        string = string.replaceAll("&otilde;", "õ");
        string = string.replaceAll("&ograve;", "ò");
        string = string.replaceAll("&oacute;", "ó");
        string = string.replaceAll("&ouml;", "ö");

        string = string.replaceAll("&Ucirc;", "Û");
        string = string.replaceAll("&Ugrave;", "Ù");
        string = string.replaceAll("&Uacute;", "Ú");
        string = string.replaceAll("&Uuml;", "Ü");

        string = string.replaceAll("&ucirc;", "û");
        string = string.replaceAll("&ugrave;", "ù");
        string = string.replaceAll("&uacute;", "ú");
        string = string.replaceAll("&uuml;", "ü");

        string = string.replaceAll("&Ccedil;", "Ç");
        string = string.replaceAll("&ccedil;", "ç");

        string = string.replaceAll("&yacute;", "ý");
        string = string.replaceAll("&Yacute;", "Ý");
        string = string.replaceAll("&yuml;", "ÿ");
        string = string.replaceAll("&Yuml;", "Ÿ");

        string = string.replaceAll("&ntilde;", "ñ");
        string = string.replaceAll("&Ntilde;", "Ñ");

        string = string.replaceAll("&ordm;", "º");
        string = string.replaceAll("&ordm;", "°");
        string = string.replaceAll("&ordf;", "ª");
        string = string.replaceAll("&sup1;", "¹");
        string = string.replaceAll("&sup2;", "²");
        string = string.replaceAll("&sup3;", "³");
        string = string.replaceAll("&sect;", "§");
        string = string.replaceAll("&euro;", "€");
        string = string.replaceAll("&reg", "®");
        string = string.replaceAll("&oslash;", "ø");
        string = string.replaceAll("&thorn;", "þ");
        string = string.replaceAll("&aelig;", "æ");
        string = string.replaceAll("&AElig;", "Æ");
        string = string.replaceAll("&szlig;", "ß");
        string = string.replaceAll("&eth;", "ð");
        string = string.replaceAll("&raquo;", "»");
        string = string.replaceAll("&laquo;", "«");
        string = string.replaceAll("&copy;", "©");
        string = string.replaceAll("&micro;", "µ");
        string = string.replaceAll("&yen;", "¥");
        string = string.replaceAll("&ETH;", "Ð");
        string = string.replaceAll("&cent;", "¢");
        string = string.replaceAll("&#8355;", "₣");
        string = string.replaceAll("&#8354;", "₢");
        string = string.replaceAll("&circ;", "ˆ");
        string = string.replaceAll("&tilde;", "˜");
        string = string.replaceAll("&uml;", "¨");
        string = string.replaceAll("&cute;", "´");
        string = string.replaceAll("&#40;", "(");
        string = string.replaceAll("&#41;", ")");
        string = string.replaceAll("&ordf;", "ª");
        string = string.replaceAll("&#40;", "ª");
        string = string.replaceAll("&#46;", ".");
        string = string.replaceAll("&#46;", ".");
        string = string.replaceAll("&ndash;", "-");
        string = string.replaceAll("&mdash;", "-");
        string = string.replaceAll("&deg;", "º");
        string = string.replaceAll("&#36;", "°");
        string = string.replaceAll("&ldquo;", "“");
        string = string.replaceAll("&rdquo;", "”");
        string = string.replaceAll("&amp;", "&");
        string = string.replaceAll("&rsquo;", "'");
        string = string.replaceAll("&quot;", "”");

        return string;
    }

    /**
     * Remove caracteres não alphanuméricos, exceto o ponto (.)
     *
     * @param s
     * @return string com os caracteres removidos
     */
    public static String removeCaracterIgnoraPonto(String s) {
        if (s != null) {
            s = s.replace("-", "").replace("/", "").replace("[", "").replace("]", "").replace("{", "").replace("}", "");
        }
        return s;
    }

    public static String removerAcentos(String s) {
        if (s != null) {
            s = s.replaceAll("[ãâàáä]", "a")
                    .replaceAll("[êèéë]", "e")
                    .replaceAll("[îìíï]", "i")
                    .replaceAll("[õôòóö]", "o")
                    .replaceAll("[ûúùü]", "u")
                    .replaceAll("[ÃÂÀÁÄ]", "A")
                    .replaceAll("[ÊÈÉË]", "E")
                    .replaceAll("[ÎÌÍÏ]", "I")
                    .replaceAll("[ÕÔÒÓÖ]", "O")
                    .replaceAll("[ÛÙÚÜ]", "U")
                    .replace('ç', 'c')
                    .replace('Ç', 'C')
                    .replace("[ñǹń]", "n")
                    .replace("[ÑŃǸ]", "N");
        }

        return s;
    }

    public static String removeSpecialCharacter(String s) {
        if (s != null) {
            s = s.replace("¦", "").replace(":", "").replace("-", "").replaceAll("[ãâàáä]", "a").replaceAll("[êèéëẽ]", "e").replaceAll("[îìíïĩ]", "i").replaceAll("[õôòóö]", "o").replaceAll("[ûúùüũ]", "u").replaceAll("[ÃÂÀÁÄ]", "A").replaceAll("[ÊÈÉËẼ]", "E").replaceAll("[ÎÌÍÏĨ]", "I").replaceAll("[ÕÔÒÓÖ]", "O").replaceAll("[ÛÙÚÜŨ]", "U").replace('ç', 'c').replace('Ç', 'C').replace('ñ', 'n').replace('Ñ', 'N').replaceAll("!", "").replace("/", "").replace("-", "").replace("[", "").replace("]", "").replace("{", "").replace("}", "").replace("(", "").replace(")", "").replace("§", "").replace((char) 160, (char) 32);
        }
        return s;
    }

    /**
     * Remove caracteres especiais e substitui letras acentuadas
     *
     * @param text
     * @return
     */
    public static String removeCaracteresEspeciais(String text) {
        if (text == null) {
            return "";
        }
        return text.replace("¦", "").replaceAll("-", "").replaceAll("[ãâàáä]", "a").replaceAll("[êèéë]", "e").replaceAll("[îìíï]", "i").replaceAll("[õôòóö]", "o").replaceAll("[ûúùü]", "u").replaceAll("[ÃÂÀÁÄ]", "A").replaceAll("[ÊÈÉË]", "E").replaceAll("[ÎÌÍÏ]", "I").replaceAll("[ÕÔÒÓÖ]", "O").replaceAll("[ÛÙÚÜ]", "U").replace('ç', 'c').replace('Ç', 'C').replace('ñ', 'n').replace('Ñ', 'N').replaceAll("!", "").replaceAll("\\[\\´\\`\\?!\\@\\#\\$\\%\\¨\\*", " ").replaceAll("\\(\\)\\=\\{\\}\\[\\]\\~\\^\\]", " ").replaceAll("[\\.\\;\\-\\_\\+\\'\\ª\\º\\:\\;\\/\\°]", " ").replace((char) 160, (char) 32).replace("\"", "");
    }

    public static String removeEspacos(String text) {
        return text.replace(" ", "");
    }

    public static String substituirEspacos(String s) {
        if (s != null) {
            s = s.replace(' ', '_');
        }

        return s;
    }

    public static String addMask(String s, String mask) {

        if (s == null || s.isEmpty()) {
            return "";
        }

        MaskFormatter maskFormatter;

        try {
            maskFormatter = new MaskFormatter(mask);
            maskFormatter.setValueContainsLiteralCharacters(false);
            return maskFormatter.valueToString(s);
        } catch (ParseException ex) {
            System.err.println(ex);
        }

        return "";
    }

    public static String formataString(String string, int tamanho, char complemento, boolean alinhaAEsquerda) {
        string = string == null || string.isEmpty() ? "" : string;
        if (string.length() < tamanho) {
            String c = String.valueOf(complemento);
            while (string.length() < tamanho) {
                if (alinhaAEsquerda) {
                    string += c;
                } else {
                    string = c + string;
                }
            }
        } else if (string.length() > tamanho) {
            int diferenca = string.length() - tamanho;
            if (alinhaAEsquerda) {
                string = string.substring(0, string.length() - diferenca);
            } else {
                string = string.substring(diferenca, string.length());
            }
        }

        return string;
    }

    private static String geradorString(int tamanho) {
        SecureRandom random = new SecureRandom();
        String[] numeros = {"0", "1", "b", "2", "4", "5", "6", "7", "8", "9"};
        String[] letras = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
            "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
            "x", "y", "z"};

        StringBuilder senha = new StringBuilder();

        for (int i = 0; i < tamanho; i++) {
            if (random.nextBoolean()) {
                senha.append(numeros[random.nextInt(numeros.length)]);
            } else {
                if (random.nextBoolean()) {
                    senha.append(letras[random.nextInt(letras.length)]);
                } else {
                    senha.append(letras[random.nextInt(letras.length)].toUpperCase());
                }
            }
        }
        return senha.toString();
    }

    public static String formatarNomeExibicao(String nome) {
        if (nome != null) {
            // Remove prefixos de datas e números
            nome = nome.replaceAll("^\\d{8,}_", ""); // Remove datas e underscores no início
            // Remove extensões e deixa apenas o nome principal
            nome = nome.replaceAll("\\.[A-Za-z]{3,4}$", ""); // Remove extensão do arquivo
            // Substitui underscores e normaliza espaços
            nome = nome.replace("_", " ").trim();

            // Lista de palavras que devem permanecer minúsculas
            List<String> palavrasMinusculas = Arrays.asList("da", "de", "do", "das", "dos", "e");

            // Divide as palavras e capitaliza, ajustando exceções
            String[] palavras = nome.toLowerCase().split(" ");
            List<String> palavrasFormatadas = new ArrayList<>();

            for (String palavra : palavras) {
                if (!palavra.isEmpty()) { // Ignorar palavras vazias
                    if (palavrasFormatadas.isEmpty() || !palavrasMinusculas.contains(palavra)) {
                        // Capitaliza a primeira letra se não for uma palavra de exceção
                        palavra = palavra.substring(0, 1).toUpperCase() + palavra.substring(1);
                    }
                    palavrasFormatadas.add(palavra);
                }
            }
            nome = String.join(" ", palavras);
        }
        return nome;
    }
}

