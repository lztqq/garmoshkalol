
package com.jagrosh.jmusicbot.audio;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.jagrosh.jmusicbot.queue.Queueable;
import com.jagrosh.jmusicbot.utils.FormatUtil;

public class QueuedTrack implements Queueable {

    private final AudioTrack track;
    private final long owner;
    
    public QueuedTrack(AudioTrack track, long owner)
    {
        this.track = track;
        this.owner = owner;
    }
    
    @Override
    public long getIdentifier() {
        return owner;
    }
    
    public AudioTrack getTrack()
    {
        return track;
    }

    @Override
    public String toString() {
        return "`["+FormatUtil.formatTime(track.getDuration())+"]` **" + track.getInfo().title +"** - <@"+owner+">";
    }
    
}
