package kn.beautynow.dominio.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.util.Map;
import java.util.Set;

import kn.beautynow.dominio.usuario.Usuario;


public class Session {
    private SharedPreferences sessao = new SharedPreferences() {
        @Override
        public Map<String, ?> getAll() {
            return null;
        }

        @android.support.annotation.Nullable
        @Override
        public String getString(String key, @android.support.annotation.Nullable String defValue) {
            return null;
        }

        @android.support.annotation.Nullable
        @Override
        public Set<String> getStringSet(String key, @android.support.annotation.Nullable Set<String> defValues) {
            return null;
        }

        @Override
        public int getInt(String key, int defValue) {
            return 0;
        }

        @Override
        public long getLong(String key, long defValue) {
            return 0;
        }

        @Override
        public float getFloat(String key, float defValue) {
            return 0;
        }

        @Override
        public boolean getBoolean(String key, boolean defValue) {
            return false;
        }

        @Override
        public boolean contains(String key) {
            return false;
        }

        @Override
        public Editor edit() {
            return null;
        }

        @Override
        public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {

        }

        @Override
        public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {

        }
    };

    public SharedPreferences getSessao() {
        return sessao;
    }

    public SharedPreferences editSessao(Usuario user, Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString("usuario", json);
        editor.commit();
        return sessao;
    }public Usuario getSession(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("MyObject", "");
        Usuario obj = gson.fromJson(json, Usuario.class);
        return obj;
    }

}
