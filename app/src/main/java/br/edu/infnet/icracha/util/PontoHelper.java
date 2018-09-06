package br.edu.infnet.icracha.util;

import java.util.Calendar;

public class PontoHelper {

    public PontoHelper(){
        c = Calendar.getInstance();
    }

    private Calendar c;

    public String getHoraAtual(){

        //Formata Horário
        String hora = doisDigitos(Integer.toString(c.get(Calendar.HOUR_OF_DAY)));
        String minuto = doisDigitos(Integer.toString(c.get(Calendar.MINUTE)));

        return hora + ":" + minuto;
    }

    public String getDataAtual(){

        String ano = doisDigitos(Integer.toString(c.get(Calendar.YEAR)));
        String mes = doisDigitos(Integer.toString(c.get(Calendar.MONTH) + 1));
        String dia = doisDigitos(Integer.toString(c.get(Calendar.DAY_OF_MONTH)));

        return ano + "-" + mes + "-" + dia;

    }

    private String doisDigitos(String valor){

        if(valor.length() < 2){ return "0" + valor; }

        return valor;
    }

}
