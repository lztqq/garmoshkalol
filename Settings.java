
package com.jagrosh.jmusicbot;

public class Settings {
    public final static Settings DEFAULT_SETTINGS = new Settings(0, 0, 0, 100, null, false);
    
    private long textId;
    private long voiceId;
    private long roleId;
    private int volume;
    private String defaultPlaylist;
    private boolean repeatMode;
    
    public Settings(String textId, String voiceId, String roleId, int volume, String defaultPlaylist, boolean repeatMode)
    {
        try
        {
            this.textId = Long.parseLong(textId);
        }
        catch(NumberFormatException e)
        {
            this.textId = 0;
        }
        try
        {
            this.voiceId = Long.parseLong(voiceId);
        }
        catch(NumberFormatException e)
        {
            this.voiceId = 0;
        }
        try
        {
            this.roleId = Long.parseLong(roleId);
        }
        catch(NumberFormatException e)
        {
            this.roleId = 0;
        }
        this.volume = volume;
        this.defaultPlaylist = defaultPlaylist;
        this.repeatMode = repeatMode;
    }
    
    public Settings(long textId, long voiceId, long roleId, int volume, String defaultPlaylist, boolean repeatMode)
    {
        this.textId = textId;
        this.voiceId = voiceId;
        this.roleId = roleId;
        this.volume = volume;
        this.defaultPlaylist = defaultPlaylist;
        this.repeatMode = repeatMode;
    }
    
    public long getTextId()
    {
        return textId;
    }
    
    public long getVoiceId()
    {
        return voiceId;
    }
    
    public long getRoleId()
    {
        return roleId;
    }
    
    public int getVolume()
    {
        return volume;
    }
    
    public String getDefaultPlaylist()
    {
        return defaultPlaylist;
    }
    
    public boolean getRepeatMode()
    {
        return repeatMode;
    }
    
    public void setTextId(long id)
    {
        this.textId = id;
    }
    
    public void setVoiceId(long id)
    {
        this.voiceId = id;
    }
    
    public void setRoleId(long id)
    {
        this.roleId = id;
    }
    
    public void setVolume(int volume)
    {
        this.volume = volume;
    }
    
    public void setDefaultPlaylist(String defaultPlaylist)
    {
        this.defaultPlaylist = defaultPlaylist;
    }
    
    public void setRepeatMode(boolean mode)
    {
        this.repeatMode = mode;
    }
}
