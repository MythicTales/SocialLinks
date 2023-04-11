package it.mythictales.sociallink;

import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public final class SocialLink extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("[MondoEventi] Plugin Abilitato");
        getServer().getPluginManager().registerEvents(this,this);
        createConfig();
    }

    private void createConfig() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }

        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            Bukkit.getLogger().info(ChatColor.LIGHT_PURPLE + "Creating config.yml...");
            saveDefaultConfig();
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        YamlConfiguration config = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "config.yml"));
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;
        switch (cmd.getName().toLowerCase()) {
            case "discord":
                TextComponent message = new TextComponent( ChatColor.GREEN + "Clicca qui per il discord" );
                message.setClickEvent(new ClickEvent( ClickEvent.Action.OPEN_URL, config.getString("Discord") ));
                player.sendMessage(message);
                return true;
            case "tiktok":
                TextComponent messageTK = new TextComponent( ChatColor.GREEN + "Tag TikTok - Clicca per visitare la pagina:" + config.get("TikTok") );
                messageTK.setClickEvent(new ClickEvent( ClickEvent.Action.OPEN_URL, config.getString("TikTokLink")));
                player.sendMessage(messageTK);
                return true;
            case "instagram":
                TextComponent messageINST = new TextComponent( ChatColor.GREEN + "Tag Instagram - Clicca per visitare la pagina:" + config.get("Instagram") );
                messageINST.setClickEvent(new ClickEvent( ClickEvent.Action.OPEN_URL, config.getString("InstagramLink")));
                player.sendMessage(messageINST);
                return true;
            case "sito":
                TextComponent messageWB = new TextComponent( ChatColor.GREEN + "Clicca per visitare il nostro sito:");
                messageWB.setClickEvent(new ClickEvent( ClickEvent.Action.OPEN_URL, config.getString("Website")));
                player.sendMessage(messageWB);
                return true;
            case "forum":
                TextComponent messageFOR = new TextComponent( ChatColor.GREEN + "Tag Instagram - Clicca per visitare il Forum");
                messageFOR.setClickEvent(new ClickEvent( ClickEvent.Action.OPEN_URL, config.getString("Forum")));
                player.sendMessage(messageFOR);
                return true;
            default:
                sender.sendMessage("Comando sconosciuto.");
                return false;
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("[MondoEventi] Plugin Disabilitato");
    }
}
