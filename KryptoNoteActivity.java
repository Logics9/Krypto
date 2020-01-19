import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;

public class KryptoNoteActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kryptonote_layout);
    }

    public void onSave(View v){
        try {
            String name = ((EditText)findViewById(R.id.file)).getText().toString();
            File dir =  this.getFilesDir();
            File file = new File(dir,name);
            FileWriter fw = new FileWriter(file);
            fw.write(((EditText)findViewById(R.id.data)).getText().toString());
            fw.close();

            Toast label = Toast.makeText(this,"Note Saved. ",Toast.LENGTH_LONG);
            label.show();
        }
        catch (Exception e){
            Toast label2 = Toast.makeText(this, e.getMessage(),Toast.LENGTH_LONG);
            label2.show();
        }
    }

    public void onLoad(View v){
        try {
            String name = ((EditText)findViewById(R.id.file)).getText().toString();

            File dir =  this.getFilesDir();
            File file = new File(dir,name);


            FileReader fr = new FileReader(file);



            String show ="";
            for(int c= fr.read(); c != -1; c= fr.read()){

                show += (char) c;
            }
            fr.close();

            ((EditText)findViewById(R.id.data)).setText(show);


            Toast label = Toast.makeText(this,"Note Loaded. ",Toast.LENGTH_LONG);
            label.show();

        }
        catch (Exception e){
            Toast label2 = Toast.makeText(this, e.getMessage(),Toast.LENGTH_LONG);
            label2.show();
        }
    }


    public void onCipher (View v){

        String key = ((EditText)findViewById(R.id.key)).getText().toString();
        String data = ((EditText)findViewById(R.id.data)).getText().toString();

        Cipher t = new Cipher(key);
        String result;

        switch (v.getId()){

            case R.id.encrypt: {

                result= t.encrypt(data);
                ((EditText)findViewById(R.id.data)).setText(result);
                break;
            }

            case R.id.decrypt: {

                result = t.decrypt(data);
                ((EditText)findViewById(R.id.data)).setText(result);
                break;
            }

            default:{
                break;
            }


        }

    }


}
