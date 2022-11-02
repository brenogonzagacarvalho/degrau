package com.degrau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

import com.degrau.databinding.ActivityCadastroProfessorBinding;
import com.google.firebase.auth.FirebaseAuth;

public class CadastroActivityProfessor extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private ActivityCadastroProfessorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = ActivityCadastroProfessorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        binding.btnRegistar.setOnClickListener(view -> validarDados());


        TextView criarContaCalendly = (TextView) findViewById(R.id.editSobreProf);
        criarContaCalendly.setMovementMethod(LinkMovementMethod.getInstance());
    }


    private void validarDados(){
        String email = binding.editEmailProf.getText().toString().trim();
        String senha = binding.editSenhaProf.getText().toString().trim();



        if(!email.isEmpty()){
            if(!senha.isEmpty()){
                criarContaFirebase(email, senha);
            }else{
                Toast.makeText(this,"Informe uma senha", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this,"Informe seu e-mail", Toast.LENGTH_SHORT).show();
        }


    }

    private void criarContaFirebase(String email,String senha){
            mAuth.createUserWithEmailAndPassword(
                    email, senha
            ).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    finish();
                    startActivity(new Intent(this, SplashActivity.class));
                }else{
                    Toast.makeText(this,"Ocorreu um erro", Toast.LENGTH_SHORT).show();
                }
            });
    }
}