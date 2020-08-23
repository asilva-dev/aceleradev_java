package challenge;

public class CriptografiaCesariana implements Criptografia {

    @Override
    public String criptografar(String texto) {
        checkMessage(texto);
        String textCrypt = "";
        for (char c : texto.toLowerCase().toCharArray()) {
            if (c >= 'a' && c <= 'z')
                textCrypt = textCrypt + (char)(c+3);
            else
                textCrypt = textCrypt + c;
        }
        return textCrypt;
    }

    @Override
    public String descriptografar(String texto) {
        checkMessage(texto);
        String textDecrypt = "";
        for (char c : texto.toLowerCase().toCharArray()) {
            if (c >= 'a' && c <= 'z')
                textDecrypt = textDecrypt + (char)(c-3);
            else
                textDecrypt = textDecrypt + c;
        }
        return textDecrypt;
    }

    private void checkMessage(String message){
        if (message.equals("")) {
            throw new IllegalArgumentException();
        }
    }

}
