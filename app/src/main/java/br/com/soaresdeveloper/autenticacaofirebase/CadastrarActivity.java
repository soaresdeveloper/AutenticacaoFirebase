package br.com.soaresdeveloper.autenticacaofirebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CadastrarActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private EditText cEmail, cPassword, confirmarPassword;
    private Button btnCadastrar;

    private static final String TAG = "LOG: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        cEmail = (EditText) findViewById(R.id.cEmail);
        cPassword = (EditText) findViewById(R.id.cPassword);
        confirmarPassword = (EditText) findViewById(R.id.confirmarPassword);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);


        mAuth = FirebaseAuth.getInstance();

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cPassword.getText().toString().equals(confirmarPassword.getText().toString())) {
                    cadastrarUsuario(cEmail.getText().toString(), cPassword.getText().toString());
                } else {
                    confirmarPassword.setError("As senhas não correspondem");
                }
            }
        });

    }

    private void cadastrarUsuario(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(CadastrarActivity.this, "Cadastro efetuado com sucesso.", Toast.LENGTH_SHORT).show();
                            user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(CadastrarActivity.this, "Ocorreu um erro durante o cadastro.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }


}
