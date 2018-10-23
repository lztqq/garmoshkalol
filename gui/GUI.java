
package com.jagrosh.jmusicbot.gui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import com.jagrosh.jmusicbot.Bot;

public class GUI extends JFrame {
    
    private final ConsolePanel console;
    private final GuildsPanel guilds;
    private final Bot bot;
    
    public GUI(Bot bot) {
        super();
        this.bot = bot;
        console = new ConsolePanel();
        guilds = new GuildsPanel(bot);
    }
    
    public void init()
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("JMusicBot");
        JTabbedPane tabs = new JTabbedPane();
        //tabs.add("Guilds", guilds);
        tabs.add("Console", console);
        getContentPane().add(tabs);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        addWindowListener(new WindowListener() {
            @Override public void windowOpened(WindowEvent e) {}
            @Override public void windowClosing(WindowEvent e) {bot.shutdown();}
            @Override public void windowClosed(WindowEvent e) {}
            @Override public void windowIconified(WindowEvent e) {}
            @Override public void windowDeiconified(WindowEvent e) {}
            @Override public void windowActivated(WindowEvent e) {}
            @Override public void windowDeactivated(WindowEvent e) {}
        });
    }
}
