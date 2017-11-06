package my.hrsikeshbrahmbhatt.mydataapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {


    EditText myInput;
    TextView myText;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myInput =(EditText) findViewById(R.id.myInput);
        myText =(TextView) findViewById(R.id.myText);

        dbHandler = new MyDBHandler(this,null,null,1);
        printDatabase();

    }

    public void printDatabase(){

        String dbString = dbHandler.databaseToString();
        myText.setText(dbString);
        myInput.setText("");

    }

    public void addButtonClick(View view){

        Products products = new Products(myInput.getText().toString());
        dbHandler.addProduct(products);
        printDatabase();

    }


    public void deleteButtonClick(View view){

        String inputText = myInput.getText().toString();
        dbHandler.deleteProduct(inputText);
        printDatabase();

    }


}
