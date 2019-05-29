package com.br.frederykantunnes.tcc_final;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;
public class MainActivity extends AppCompatActivity {
    ConnectedThread  connectedThread;
    int configurar = 0;
    Button btnConecta;
    ImageButton btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11;
    TextView status, configMsg;
    private static final int SOLICITA_ATIVACAO = 1;
    private static final int SOLICITA_CONEXAO = 2;
    BluetoothAdapter meuBluetoothAdapter = null;
    BluetoothDevice meuDevice = null;
    BluetoothSocket meuSocket = null;
    boolean conexao = false;
    private static String MAC = null;
    UUID meu_uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnConecta = (Button)findViewById(R.id.buttonConectar);
        btn0 = (ImageButton)findViewById(R.id.button0);
        btn1 = (ImageButton)findViewById(R.id.button1);
        btn2 = (ImageButton)findViewById(R.id.button2);
        btn3 = (ImageButton)findViewById(R.id.button3);
        btn4 = (ImageButton)findViewById(R.id.button4);
        btn5 = (ImageButton)findViewById(R.id.button5);
        btn6 = (ImageButton)findViewById(R.id.button6);
        btn7 = (ImageButton)findViewById(R.id.button7);
        btn8 = (ImageButton)findViewById(R.id.button8);
        btn9 = (ImageButton)findViewById(R.id.button9);
        btn10 = (ImageButton)findViewById(R.id.button10);
        btn11 = (ImageButton)findViewById(R.id.button11);
        status = (TextView) findViewById(R.id.status);
        configMsg = (TextView) findViewById(R.id.configMsg);

        meuBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(meuBluetoothAdapter==null){
            Toast.makeText(this, "Não tem adaptador", Toast.LENGTH_LONG).show();
        }else if(!meuBluetoothAdapter.isEnabled()){
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, SOLICITA_ATIVACAO);
        }

        btnConecta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(conexao){
                    //desconectar
                    try {
                        meuSocket.close();
                        conexao = false;
                        status.setText("Status: Desconectado");
                        ativa_desativa("desativa");
                        Toast.makeText(MainActivity.this, "Bluetooth foi desconectado", Toast.LENGTH_LONG).show();
                    }catch (IOException erro){
                        Toast.makeText(MainActivity.this, "Ocorreu um erro: "+erro.toString(), Toast.LENGTH_LONG).show();
                        conexao = false;
                    }
                }else{
                    //conectar
                    conexao = true;
                    Intent abreLista = new Intent(MainActivity.this, ListaDeDispositivos.class);
                    startActivityForResult(abreLista, SOLICITA_CONEXAO);
                }
            }
        });

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(conexao){
                    if(configurar==1){
                        connectedThread.write("q");
                        configurar=0;
                        configMsg.setText("");
                        Toast.makeText(MainActivity.this, "Função configurada", Toast.LENGTH_LONG).show();
                    }else {
                        connectedThread.write("0");
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Bluetooth não conectado", Toast.LENGTH_LONG).show();
                }
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(conexao){
                    if(configurar==1){
                        connectedThread.write("w");
                        configurar=0;
                        configMsg.setText("");
                        Toast.makeText(MainActivity.this, "Função configurada", Toast.LENGTH_LONG).show();
                    }else {
                        connectedThread.write("1");
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Bluetooth não conectado", Toast.LENGTH_LONG).show();
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(conexao){
                    if(configurar==1){
                        connectedThread.write("e");
                        configurar=0;
                        configMsg.setText("");
                        Toast.makeText(MainActivity.this, "Função configurada", Toast.LENGTH_LONG).show();
                    }else {
                        connectedThread.write("2");
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Bluetooth não conectado", Toast.LENGTH_LONG).show();
                }
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(conexao){
                    if(configurar==1){
                        connectedThread.write("r");
                        configurar=0;
                        configMsg.setText("");
                        Toast.makeText(MainActivity.this, "Função configurada", Toast.LENGTH_LONG).show();
                    }else {
                        connectedThread.write("3");
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Bluetooth não conectado", Toast.LENGTH_LONG).show();
                }
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(conexao){
                    if(configurar==1){
                        connectedThread.write("t");
                        configurar=0;
                        configMsg.setText("");
                        Toast.makeText(MainActivity.this, "Função configurada", Toast.LENGTH_LONG).show();
                    }else {
                        connectedThread.write("4");
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Bluetooth não conectado", Toast.LENGTH_LONG).show();
                }
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(conexao){
                    if(configurar==1){
                        Toast.makeText(MainActivity.this, "Esta função não está disponível para configuração", Toast.LENGTH_LONG).show();
                    }else {
                        connectedThread.write("5");
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Bluetooth não conectado", Toast.LENGTH_LONG).show();
                }
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(conexao){
                    if(configurar==1){
                        connectedThread.write("y");
                        configurar=0;
                        configMsg.setText("");
                        Toast.makeText(MainActivity.this, "Função configurada", Toast.LENGTH_LONG).show();
                    }else {
                        connectedThread.write("6");
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Bluetooth não conectado", Toast.LENGTH_LONG).show();
                }
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(conexao){
                    if(configurar==1){
                        connectedThread.write("u");
                        configurar=0;
                        configMsg.setText("");
                        Toast.makeText(MainActivity.this, "Função configurada", Toast.LENGTH_LONG).show();
                    }else {
                        connectedThread.write("7");
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Bluetooth não conectado", Toast.LENGTH_LONG).show();
                }
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(conexao){
                    if(configurar==1){
                        connectedThread.write("i");
                        configurar=0;
                        configMsg.setText("");
                        Toast.makeText(MainActivity.this, "Função configurada", Toast.LENGTH_LONG).show();
                    }else {
                        connectedThread.write("8");
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Bluetooth não conectado", Toast.LENGTH_LONG).show();
                }
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(conexao){
                    if(configurar==1){
                        connectedThread.write("o");
                        configurar=0;
                        configMsg.setText("");
                        Toast.makeText(MainActivity.this, "Função configurada", Toast.LENGTH_LONG).show();
                    }else {
                        connectedThread.write("9");
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Bluetooth não conectado", Toast.LENGTH_LONG).show();
                }
            }
        });

        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(conexao){
                    if(configurar==1){
                        connectedThread.write("p");
                        configurar=0;
                        configMsg.setText("");
                        Toast.makeText(MainActivity.this, "Função configurada", Toast.LENGTH_LONG).show();
                    }else {
                        connectedThread.write("a");
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Bluetooth não conectado", Toast.LENGTH_LONG).show();
                }
            }
        });

        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(conexao){
                    if(configurar==1){
                        Toast.makeText(MainActivity.this, "Esta função não está disponível para configuração", Toast.LENGTH_LONG).show();
                    }else {
                        connectedThread.write("b");
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Bluetooth não conectado", Toast.LENGTH_LONG).show();
                }
            }
        });

        ativa_desativa("desativa");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){

            case SOLICITA_ATIVACAO:
                if(resultCode== Activity.RESULT_OK){

                    Toast.makeText(this, "O Bluetooth foi ativado", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this, "O Bluetooth não foi ativado", Toast.LENGTH_LONG).show();
                }
                break;
            case SOLICITA_CONEXAO:
                if(resultCode== Activity.RESULT_OK){
                    MAC = data.getExtras().getString(ListaDeDispositivos.ENDERECO_MAC);

                    meuDevice=meuBluetoothAdapter.getRemoteDevice(MAC);
                    try {
                        meuSocket = meuDevice.createRfcommSocketToServiceRecord(meu_uuid);
                        meuSocket.connect();
                        conexao = true;
                        connectedThread = new ConnectedThread(meuSocket);
                        connectedThread.start();
                        Toast.makeText(this, "Conexão Realizada com: "+MAC, Toast.LENGTH_LONG).show();
                        status.setText("Status: Conectado "+MAC);
                        ativa_desativa("ativa");
                        btnConecta.setText("Desconectar");
                    } catch (IOException error){
                        Toast.makeText(this, "Erro de Conexão", Toast.LENGTH_LONG).show();
                        btnConecta.setText("Conectar");
                    }
                }else{
                    Toast.makeText(this, "Falha ao obter o MAC", Toast.LENGTH_LONG).show();
                    btnConecta.setText("Conectar");
                }
                break;



        }
    }


    private class ConnectedThread extends Thread {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket) {
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) { }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            byte[] buffer = new byte[1024];  // buffer store for the stream
            int bytes; // bytes returned from read()

        }

        /* Call this from the main activity to send data to the remote device */
        public void write(String enviar) {
            byte[] msgBuffer = enviar.getBytes();
            try {
                mmOutStream.write(msgBuffer);
            } catch (IOException e) { }
        }

    }

    public void ativa_desativa(String acao){

        if(acao.equalsIgnoreCase("ativa")){
            btn0.setEnabled(true);
            btn1.setEnabled(true);
            btn2.setEnabled(true);
            btn3.setEnabled(true);
            btn4.setEnabled(true);
            btn5.setEnabled(true);
            btn6.setEnabled(true);
            btn7.setEnabled(true);
            btn8.setEnabled(true);
            btn9.setEnabled(true);
            btn10.setEnabled(true);
            btn11.setEnabled(true);
        }
        else if(acao.equalsIgnoreCase("desativa")){
            btn0.setEnabled(false);
            btn1.setEnabled(false);
            btn2.setEnabled(false);
            btn3.setEnabled(false);
            btn4.setEnabled(false);
            btn5.setEnabled(false);
            btn6.setEnabled(false);
            btn7.setEnabled(false);
            btn8.setEnabled(false);
            btn9.setEnabled(false);
            btn10.setEnabled(false);
            btn11.setEnabled(false);
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.config) {
            configurar=1;
            configMsg.setText("Clique na função para o sensor!");
            return true;
        }

        if (id == R.id.informa) {
            AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
            dialogo.setTitle("UNIVERSIDADE FEDERAL RURAL DE PERNAMBUCO");
            dialogo.setMessage("TÍTULO: Protótipo de prótese robótica de punho e mão utilizando arduíno.\n\n\nAluno: Frederyk Antunnes de Sousa Alves\n\nOrientador: Prof. Héldon José Oliveira Albuquerque");
            dialogo.setNeutralButton("Sair", null);
            dialogo.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
