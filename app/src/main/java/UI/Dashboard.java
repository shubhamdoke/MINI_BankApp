package UI;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.mini_bank.R;

public class Dashboard extends AppCompatActivity {

    Button withdrawal,add,changepin,history;
    TextView username,balance;
     String code,amount;
    String bal,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        withdrawal=findViewById(R.id.withdrawal);
        add=findViewById(R.id.add);
        changepin=findViewById(R.id.changepin);
        history=findViewById(R.id.history);

        username=findViewById(R.id.username);
        balance=findViewById(R.id.Balance);

        bal="10000";
        pass="0000";
        username.setText("sd");
        balance.setText(String.valueOf(bal));

        withdrawal.setOnClickListener(v -> showAlertDialog_withrawal());
        add.setOnClickListener(v -> showAlertDialog_add());
        changepin.setOnClickListener(v -> showAlertDialog_changepin());
        history.setOnClickListener(v -> {
            startActivities(new Intent(Dashboard.this,History.class));
        });

    }
    private void showAlertDialog_changepin() {
        // Create an alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(Dashboard.this);
        builder.setTitle("Change Pin");

        // set the custom layout
        final View customLayout = getLayoutInflater().inflate(R.layout.change_pin_dialog, null);
        builder.setView(customLayout);

        // add a button
        builder.setPositiveButton("Change Pin", (dialog, which) -> {

            // send data from the AlertDialog to the Activity
            EditText old_pin = customLayout.findViewById(R.id.old_pin);
            EditText new_pin = customLayout.findViewById(R.id.new_pin);
            EditText confram_pin = customLayout.findViewById(R.id.confirm_pin);

            String old_pin1=old_pin.getText().toString();
            String new_pin1=new_pin.getText().toString();
            String confirm_pin1=confram_pin.getText().toString();


            if(old_pin1.equals(pass)&&new_pin1.equals(confirm_pin1))
            {
                pass=new_pin1;
                Toast.makeText(this, "PIN Changed Successfully", Toast.LENGTH_SHORT).show();

            } else if (old_pin1==null) {
                Toast.makeText(this, "please enter old PIN", Toast.LENGTH_SHORT).show();

            } else if (new_pin1==null) {
                Toast.makeText(this, "please Enter new PIN", Toast.LENGTH_SHORT).show();

            } else if (confirm_pin1 == null) {
                Toast.makeText(this, "please Reenter new PIN in cofirm ", Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(this, "please enter correct PIN", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("cancle", (dialog, which) -> {

            dialog.dismiss();
        });
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();


}

    private void showAlertDialog_add() {
        // Create an alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(Dashboard.this);
        builder.setTitle("Add Money");

        // set the custom layout
        final View customLayout = getLayoutInflater().inflate(R.layout.withdrawal_dialog, null);
        builder.setView(customLayout);

        // add a button
        builder.setPositiveButton("Add Money", (dialog, which) -> {

            // send data from the AlertDialog to the Activity
            EditText amt = customLayout.findViewById(R.id.amount);
            EditText pin = customLayout.findViewById(R.id.pass);
            amount = amt.getText().toString();
            code= pin.getText().toString();

            if(pass.equals(code))
            {
                int balance1= Integer.parseInt(bal)+Integer.parseInt(amount);
                bal=String.valueOf(balance1);
                balance.setText(bal);

            }
        });
        builder.setNegativeButton("cancle", (dialog, which) -> {

            dialog.dismiss();
        });
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showAlertDialog_withrawal() {
        // Create an alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(Dashboard.this);
        builder.setTitle("Withdraw Money");

        // set the custom layout
        final View customLayout = getLayoutInflater().inflate(R.layout.withdrawal_dialog, null);
        builder.setView(customLayout);

        // add a button
        builder.setPositiveButton("withdraw", (dialog, which) -> {

            // send data from the AlertDialog to the Activity
            EditText amt = customLayout.findViewById(R.id.amount);
            EditText pin = customLayout.findViewById(R.id.pass);
             amount = amt.getText().toString();
              code= pin.getText().toString();

            if(pass.equals(code))
            {
                int balance1= Integer.parseInt(bal)-Integer.parseInt(amount);
                bal=String.valueOf(balance1);
                balance.setText(bal);

            }
        });
        builder.setNegativeButton("cancle", (dialog, which) -> {

            dialog.dismiss();
        });
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}