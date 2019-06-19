package com.example.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class profileAct extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    public static final String NODE_USERS = "users";

    //private FirebaseDatabase rootref;
    private EditText fname;
    private EditText cotact;
    private EditText paddress;
    private EditText blood;
    private EditText area;
    private Button save;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
//notification coding
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (task.isSuccessful())
                        {
                            String token = task.getResult().getToken();
                            //textView.setText("Text :" +token);
                          saveToken(token);
                        }
                        else {
                            //textView.setText(task.getException().getMessage());
                        }
                    }
                });
        //notification coding

        firebaseAuth = FirebaseAuth.getInstance();




        databaseReference = FirebaseDatabase.getInstance().getReference();



        FirebaseUser  user = firebaseAuth.getCurrentUser();
        fname = (EditText) findViewById(R.id.fname);
        cotact = (EditText) findViewById(R.id.cotact);
        paddress = (EditText) findViewById(R.id.paddress);
        area = (EditText) findViewById(R.id.area);
        blood = (EditText) findViewById(R.id.blood);

        save= (Button) findViewById(R.id.save);



        save.setOnClickListener(this);




    }
    //notificaton coding

    private void saveToken(String token) {
        String email = firebaseAuth.getCurrentUser().getEmail();
        users User = new users(token,email);
        DatabaseReference dbusers = FirebaseDatabase.getInstance().getReference(NODE_USERS);
        dbusers.child(firebaseAuth.getCurrentUser().getUid())
                .setValue(User).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(profileAct.this, "Token Saved", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
 // notification coding
    private void saveuserinformatio() {
        String name = fname.getText().toString().trim();

        String cont = cotact.getText().toString().trim();
        String address = paddress.getText().toString().trim();
        String are = area.getText().toString().trim();
        String bloodt = blood.getText().toString().trim();
        String string1 = "nazimabad";
        String string2 = "northkarachi";
        String string3= "northnazimabad";
        String string4="Defence";
        String string5="Bahadurabad";
        String string6="Clifton";
        String string7="GulshaneIqbal";


        userinformatio userinformatio = new userinformatio(name, cont, address, are, bloodt);


        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference.child("Registration").child(user.getUid()).setValue(userinformatio);


        Toast.makeText(this, "informatio saved..", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), profileact2.class));

        if (are.equalsIgnoreCase(string1)){
            nazimabad nazimabad = new nazimabad(name, cont, are);
            databaseReference.child("nazimabad").child(user.getUid()).setValue(nazimabad);
         }
         if(are.equalsIgnoreCase(string2))
         {
             northk northk = new northk(name, cont, are);
             databaseReference.child("northkarachi").child(user.getUid()).setValue(northk);
        }
        if (are.equalsIgnoreCase(string3)){

            northnazimabad northnazimabad = new northnazimabad(name, cont, are);
            databaseReference.child("northnazimabad").child(user.getUid()).setValue(northnazimabad);
        }
        if (are.equalsIgnoreCase(string4))
        {

            Defence Defence = new Defence (name, cont, are);
            databaseReference.child("Defence").child(user.getUid()).setValue(Defence);
        }
        if (are.equalsIgnoreCase(string5))
        {

            Bahadurabad Bahadurabad = new Bahadurabad (name, cont, are);
            databaseReference.child("Bahadurabad").child(user.getUid()).setValue(Bahadurabad);
        }
        if (are.equalsIgnoreCase(string6))
        {

            Clifton Clifton = new Clifton (name, cont, are);
            databaseReference.child("Clifton").child(user.getUid()).setValue(Clifton);
        }
        if (are.equalsIgnoreCase(string7))
        {

            Gulshaneiqbal Gulshaneiqbal= new Gulshaneiqbal (name, cont, are);
            databaseReference.child("GulshaneIqbal").child(user.getUid()).setValue(Gulshaneiqbal);
        }


    }



        @Override
        public void onClick (View view){

            if(view==save){

                saveuserinformatio();



            }


        }


}