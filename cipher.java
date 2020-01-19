import java.security.AlgorithmConstraints;

public class Cipher
{
    private String key;
    private static final String ALPHABET =" ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public Cipher(String K){

        this.key = K;

    }

    private String makePad(String note){

        String pad = this.key;
        for (;pad.length()< note.length();){
            pad += this.key;

        }
        return pad;
    }


    public String encrypt(String note){

        String pad = makePad(note);
        String result ="";

        for(int i = 0;i<note.length();i++){

            String c = note.substring(i,i+1);
            int position = ALPHABET.indexOf(c);
            int shift = Integer.parseInt(pad.substring(i,i+1));
            int newPosition = (position + shift)% ALPHABET.length();
            result = result + ALPHABET.substring(newPosition,newPosition + 1);
        }
        return result;
    }

    public String decrypt(String note){

        String pad = makePad(note);
        String result ="";

        for(int i = 0;i < note.length();i++){


            String c = note.substring(i,i+1);
            int position = ALPHABET.indexOf(c) ;
            int shift = Integer.parseInt(pad.substring(i,i+1));
            int newPosition = (position - shift)% ALPHABET.length() ;


                if (newPosition < 0)
                {
                    newPosition += ALPHABET.length();

                }
            result = result + ALPHABET.substring(newPosition, newPosition + 1);

        }
        return result;
    }

    public static void main(String[] args)
    {
        Cipher a = new Cipher("1234");

        String result = a.encrypt("MY NAME IS TAMAL HELLO BYE PEOPLE");

        String result2 = a.decrypt(result);



        System.out.println(result);

        System.out.println(result2);
    }
}
