package com.stickwarsaga.modpanel;

import android.content.Context;
import android.content.SharedPreferences;

public class ModPanel {

    private SharedPreferences prefs;
    private static final String PREFS_NAME = "StickWarModPrefs";

    // Mod konstanları
    private static final String KEY_MONEY = "money";
    private static final String KEY_POPULATION = "population";
    private static final String KEY_GENERALS = "generals";
    private static final String KEY_TIME_SKIP = "timeSkip";

    public ModPanel(Context context) {
        this.prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    /**
     * Sınırsız para ekle
     */
    public void addMoney(long amount) {
        long currentMoney = prefs.getLong(KEY_MONEY, 0);
        long newMoney = currentMoney + amount;
        prefs.edit().putLong(KEY_MONEY, newMoney).apply();
    }

    /**
     * Nüfus sınırı ayarla
     */
    public void setUnlimitedPopulation(int limit) {
        prefs.edit().putInt(KEY_POPULATION, limit).apply();
    }

    /**
     * Tüm generalleri unlock et + özel yetenekler
     */
    public void unlockAllGenerals() {
        // Tüm generallerin ID'lerini ekle
        StringBuilder generals = new StringBuilder();
        
        // Stick War Saga'daki tüm generaller
        String[] allGenerals = {
            "Stick", "Archer", "Mage", "Barbarian", "Spearton", 
            "Swordwrath", "Alchemist", "Ninja", "Paladin", "Deathknight",
            "Shadow", "Werewolf", "Dragon", "Phoenix", "Titan"
        };

        for (String general : allGenerals) {
            generals.append(general).append(",");
        }

        prefs.edit().putString(KEY_GENERALS, generals.toString()).apply();
    }

    /**
     * Süreyi atla (saat cinsinden)
     */
    public void skipTime(int hours) {
        long timeSkipped = prefs.getLong(KEY_TIME_SKIP, 0);
        long newTime = timeSkipped + (hours * 3600000); // Milisaniye cinsine çevir
        prefs.edit().putLong(KEY_TIME_SKIP, newTime).apply();
    }

    /**
     * Tüm modları sıfırla
     */
    public void resetAll() {
        prefs.edit()
            .remove(KEY_MONEY)
            .remove(KEY_POPULATION)
            .remove(KEY_GENERALS)
            .remove(KEY_TIME_SKIP)
            .apply();
    }

    /**
     * Mevcut para miktarını al
     */
    public long getMoney() {
        return prefs.getLong(KEY_MONEY, 0);
    }

    /**
     * Mevcut nüfus limitini al
     */
    public int getPopulation() {
        return prefs.getInt(KEY_POPULATION, 100);
    }

    /**
     * Aktif generalleri al
     */
    public String getActiveGenerals() {
        return prefs.getString(KEY_GENERALS, "");
    }

    /**
     * Atılan zamanı al (milisaniye cinsinden)
     */
    public long getTimeSkipped() {
        return prefs.getLong(KEY_TIME_SKIP, 0);
    }
}
