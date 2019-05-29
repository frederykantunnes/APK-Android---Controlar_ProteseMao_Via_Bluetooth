package com.br.frederykantunnes.tcc_final;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Set;

public class ListaDeDispositivos extends ListActivity {

    private BluetoothAdapter meuBluetooth = null;
    static String ENDERECO_MAC = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayAdapter<String> ArrayBluetooth = new ArrayAdapter<String >(this, android.R.layout.simple_list_item_1);
        meuBluetooth = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> dispositivosPareados = meuBluetooth.getBondedDevices();
        if(dispositivosPareados.size()>0){
            for (BluetoothDevice dispositivos : dispositivosPareados){
                String nomeBt = dispositivos.getName();
                String macBt = dispositivos.getAddress();
                ArrayBluetooth.add(nomeBt +"\n"+ macBt);
            }
        }
        setListAdapter(ArrayBluetooth);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String informacao = ((TextView)v).getText().toString();
        String mac = informacao.substring(informacao.length() - 17);
        Intent retornaMac = new Intent();
        retornaMac.putExtra(ENDERECO_MAC, mac);
        setResult(RESULT_OK, retornaMac);
        finish();
    }
}
