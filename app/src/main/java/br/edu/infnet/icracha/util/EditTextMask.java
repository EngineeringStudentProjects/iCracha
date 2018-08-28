package br.edu.infnet.icracha.util;

import android.text.Editable;
import android.text.TextWatcher;

public class EditTextMask implements TextWatcher {

    private boolean isRunning = false;
    private boolean isDeleting = false;
    private String mask;

    public EditTextMask(String mask){
        this.mask = mask;
    }

    //Construtor de mascara para CPF,feito para n deixar hardcoded a mascara e caso exista uma necessidade de reutilizar a mascara

    /***
     * Construtor de máscara para CPF, feito para não deixar o valor diretamente no código
     * da activity e caso exista uma necessidade de reutilizar a mesma.
     *
     * @return máscara para o EditText
     */
    public static EditTextMask construirCPF(){
        return new EditTextMask("###.###.###-##");
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        isDeleting = count > after;
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    /***
     * Método para adicioanr a máscara no EditText enquanto o usuário digita os valores,
     * aplicando após o mesmo digitar o último char antes dos pontos
     *
     * @param s
     *
     */
    @Override
    public void afterTextChanged(Editable s) {
        if(isRunning || isDeleting){
            return;
        }

        isRunning = true;

        int editableLength = s.length();

        if (editableLength < mask.length()) {
            if (mask.charAt(editableLength) != '#') {
                s.append(mask.charAt(editableLength));
            } else if (mask.charAt(editableLength - 1) != '#') {
                s.insert(editableLength - 1, mask, editableLength - 1, editableLength);
            }
        }

        isRunning = false;

    }
}