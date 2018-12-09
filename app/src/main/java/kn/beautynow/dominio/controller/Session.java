package kn.beautynow.dominio.controller;

import android.content.SharedPreferences;

import java.util.Map;
import java.util.Set;


public class Session {
    private SharedPreferences sessao = new SharedPreferences() {
        @Override
        public Map<String, ?> getAll() {
            return null;
        }

        @androidx.annotation.Nullable
        @Override
        public String getString(String key, @androidx.annotation.Nullable String defValue) {
            return null;
        }

        @androidx.annotation.Nullable
        @Override
        public Set<String> getStringSet(String key, @androidx.annotation.Nullable Set<String> defValues) {
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

    private SharedPreferences getSessao() {
        return sessao;
    }
    public SharedPreferences editSessao(String email, String senha){
        SharedPreferences.Editor editor = sessao.edit();
        editor.putString("email", email);
        editor.putString("senha", senha);
        editor.commit();
        return sessao;
    }

}
