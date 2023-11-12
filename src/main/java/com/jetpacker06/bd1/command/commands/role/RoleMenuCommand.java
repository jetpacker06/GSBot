package com.jetpacker06.bd1.command.commands.role;
@SuppressWarnings("all")
public class RoleMenuCommand { /*
    public static HashMap<String, Integer> rolesColorsMap = new HashMap<>();
    private boolean rolesInitialized = false;
    static {
        rolesColorsMap.put("CS academy", Colors.BLUE);
        rolesColorsMap.put("BF academy", Colors.LIGHT_GREEN);
        rolesColorsMap.put("Biomed academy", Colors.RED);
    }
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (CommandUtil.notInCorrectServer(event)) return;
        if (!event.getName().equals("role-menu")) return;
        Guild guild = event.getGuild();
        assert guild != null;
        JDA jda = TheBotEver.jda;
        //this will only run once per startup
        if (!rolesInitialized) {
            for (String roleName : rolesColorsMap.keySet()) {
                List<Role> roles = guild.getRolesByName(roleName, true);
                if (roles.size() == 0) {
                    guild.createRole()
                            .setName(roleName)
                            .setColor(rolesColorsMap.get(roleName))
                            .setMentionable(true)
                    .queue();
                }
            }
            rolesInitialized = true;
        }
        String title = event.getOption("title", "Embed title was missing!", OptionMapping::getAsString);

        SelectMenu.Builder bobTheBuilder = SelectMenu.create(title + "-rolemenu");
        bobTheBuilder.setPlaceholder("Select one.");
        String roles = event.getOption("roles", "", OptionMapping::getAsString);
        for (String option : roles.split(";")) {
            bobTheBuilder.addOption(option, option + "2");
        }
        EmbedBuilder eBuilder = Util.blueBuilder();
        eBuilder.setTitle(title);
        if (Commands.optionExists("description")) {
            OptionMapping description = event.getOption("description");
            assert description != null;
            eBuilder.appendDescription(description.getAsString());
        }
        TextChannel channel;
        if (event.getGuild().equals(Guilds.theBoys)) {
            channel = Channels.roles;
        } else {
            channel = Channels.jgeneral;
        }

        channel.sendMessageEmbeds(eBuilder.build()).setActionRow(bobTheBuilder.build()).queue();
        event.reply("Done!").setEphemeral(true).queue();

        for (String roleName : roles.split(";")) {
            List<Role> role = event.getGuild().getRolesByName(roleName, true);
            if (role.size() == 0) {
                RoleAction roleAction = guild.createRole();
                roleAction
                        .setName(roleName)
                        .setMentionable(true);
                if (rolesColorsMap.containsKey(roleName))
                    roleAction.setColor(rolesColorsMap.get(roleName));
                roleAction.queue();
            }
        }
    }

    @Override
    public void onSelectMenuInteraction(@NotNull SelectMenuInteractionEvent event) {
        Guild guild = event.getGuild();
        assert guild != null;
        Member member = event.getMember();
        assert member != null;

        ArrayList<Role> roles = new ArrayList<>();
        for (SelectOption option : event.getSelectedOptions()) {
            String roleName = option.getLabel();
            List<Role> role = guild.getRolesByName(roleName, true);
            roles.add(role.get(0));
            guild.modifyMemberRoles(member, roles).queue();
        }
        event.reply("Applied role!").setEphemeral(true).queue();
    } */
}
