package kn.beautynow.dominio.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.util.Map;
import java.util.Set;

import kn.beautynow.dominio.cliente.Cliente;
import kn.beautynow.dominio.fornecedor.Fornecedor;
import kn.beautynow.dominio.usuario.Usuario;


public class Session {
    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }
    public void editSessao(Usuario user, Context context){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString("usuario", json);
        editor.commit();
    }
    public void editSessaoCliente(Cliente user, Context context){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString("Cliente", json);
        editor.commit();
    }
    public void editSessaoFornecedor(Fornecedor user, Context context){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString("Fornecedor", json);
        editor.commit();
    }
    public static Usuario getSession(Context context){
        Gson gson = new Gson();
        String json = getSharedPreferences(context).getString("usuario", "");
        Usuario object = gson.fromJson(json, Usuario.class);
        return object;
    }

    public static Cliente getSessionCliente(Context context){
        Gson gson = new Gson();
        String json = getSharedPreferences(context).getString("Cliente", "");
        Cliente object = gson.fromJson(json, Cliente.class);
        return object;
    }
    public static Fornecedor getSessionFornecedor(Context context){
        Gson gson = new Gson();
        String json = getSharedPreferences(context).getString("Fornecedor", "");
        Fornecedor object = gson.fromJson(json, Fornecedor.class);
        return object;
    }

}
