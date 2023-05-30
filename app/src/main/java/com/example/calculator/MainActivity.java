package com.example.calculator;
//import android.support.v4.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView soln,result;
    MaterialButton C,L,button_R,div,sub,add,multiply,equal,dot,AC;
    MaterialButton btn7,btn1,btn2,btn3,btn4,btn5,btn6,btn8,btn9,btn0;
    private MaterialButton button;
    // private MaterialButton button

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        soln=findViewById(R.id.soln);
        result=findViewById(R.id.result);
        assignId(C,R.id.C);
        assignId(multiply,R.id.multiply);
        assignId(add,R.id.add);
        assignId(equal,R.id.equal);
        assignId(btn0,R.id.btn0);
        assignId(btn1,R.id.btn1);
        assignId(AC,R.id.AC);
        assignId(dot,R.id.dot);
        assignId(div,R.id.div);
        assignId(sub,R.id.sub);
        assignId(L,R.id.L);
        assignId(button_R,R.id.button_R);
        assignId(btn2,R.id.btn2);
        assignId(btn3,R.id.btn3);
        assignId(btn4,R.id.btn4);
        assignId(btn5,R.id.btn5);
        assignId(btn6,R.id.btn6);
        assignId(btn7,R.id.btn6);
        assignId(btn8,R.id.btn8);
        assignId(btn9,R.id.btn9);
       // assignId
       // assignId(btnC,R.id.C);







    }



    void assignId(MaterialButton btn, int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);

    }

@Override
    public void onClick(View view){
        MaterialButton button=(MaterialButton) view;
        String buttonText=button.getText().toString();
        String dataTocalculate=soln.getText().toString();
        if(buttonText.equals("AC")){
            soln.setText("");
            result.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            soln.setText(result.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataTocalculate=dataTocalculate.substring(0,dataTocalculate.length()-1);
        }else{
            dataTocalculate=dataTocalculate+buttonText;


        }


       // dataTocalculate=dataTocalculate+buttonText;
        soln.setText(dataTocalculate);
        String finalResult=getResult(dataTocalculate);
        if(!finalResult.equals("Err")){
            result.setText(finalResult);

        }
    }
    String getResult(String data){
        try {
            Context context=Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initStandardObjects();
          String finalResult= context.evaluateString(scriptable,data,"Javascript",1,null).toString();
          if(finalResult.endsWith(".0")){
              finalResult=finalResult.replace(".0","");
          }
          return finalResult;
        }
        catch (Exception e){
            return "Err";
        }

    }
}