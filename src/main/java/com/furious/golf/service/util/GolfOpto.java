package com.furious.golf.service.util;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class GolfOpto {
    public static void main(String[] args) throws IOException {

    }

    public List<GolfOptoLineup> getBestLineups( GolfOptoSettings settings,List<GolfPlayerProjections> projections, String site) {
        System.out.println("Opto Start " + new Date());
        Date start = new Date();

        int count = 1;
        Map<String, Integer> playerCountMap = new HashMap<>();
        List<GolfOptoLineup> bestLineups = new ArrayList<>();

        Map<String, GolfOptoPlayer> golfers = new HashMap<>();
        System.out.println("Unique players " + settings.getUniquePlayers());
        HashMap<String, GolfOptoPlayer> players = new HashMap<>();
        List<GolfOptoPlayer> mustPlayers = new ArrayList<>();
        for (GolfPlayerProjections projection : projections) {
            GolfOptoPlayer player = new GolfOptoPlayer();
            player.setPlayerId(projection.getPlayerId());
            player.setSiteId(String.valueOf(projection.getSiteId()));
            player.setPlayerName(projection.getPlayerName());
            player.setSalary(projection.getSalary());
            player.setActual(projection.getActual());

            double value = projection.getProj() * 1000;
            value = value / projection.getSalary();
            player.setValue(value);
            if (projection.isLocked()) {
                player.setProj(projection.getProj() + 50);
                if (projection.getMax() == 100) {
                    player.setProj(projection.getProj() + 100);
                    mustPlayers.add(player);
                }
            }
            else {
                player.setProj(projection.getProj());
            }

            player.setOwnership(projection.getOwnership());

            player.setMinExposure(0.0D);
            if (projection.getMax() == 0) {

                player.setMaxExposure(settings.getGlobalExposure());
            }
            else {
                System.out.println(player.getPlayerName() + " : " + projection.getMax());
                double exposure = settings.getNumberLineups() * ((double) projection.getMax() / 100);
                player.setMaxExposure(exposure);
            }
            golfers.put(player.getSiteId(), player);
            players.put(player.getSiteId(), player);
        }

        System.out.println("Opto cycle " + new Date());
        int optosize = 1;
        count = settings.getNumberLineups();

        for (int i = 0; i < count; i++) {

            System.out.println(
                "Best Lineups Size " + new Date() + " | " + bestLineups.size() + " For Lineup Count " + settings
                    .getNumberLineups() + " Player size " + players.size());
            List<GolfOptoLineup> optos = getOptos(players, settings, site, optosize);

        }
        return null;
    }

    public List<GolfOptoLineup> getOptos(HashMap<String, GolfOptoPlayer> players, GolfOptoSettings settings,
                                        String site, Integer optosize) {


        int innerOpto = optosize;
        int teamMax = 5;
        HashMap<String, Integer> teamCountMap = new HashMap<>();
        HashMap<Integer, GolfOptoLineup> lineupMlbHashMap = new HashMap<>();

        Collection<GolfOptoLineup> lineups = new ArrayList<>();

        List<GolfOptoPlayer> firstSlot = new ArrayList<>();
        ArrayList playerList = new ArrayList(players.values());
        firstSlot = getOptimal(playerList, 3);

        for (GolfOptoPlayer first : firstSlot) {
            GolfOptoLineup mainLineup = new GolfOptoLineup();
            HashMap<String, GolfOptoPlayer> playersFirst = new HashMap<>(players);
            mainLineup.setG1(first);
            playersFirst.remove(first.getSiteId());

            List<GolfOptoPlayer> secondPlayers = getOptimal(new ArrayList(playersFirst.values()),3);
            for (GolfOptoPlayer second : secondPlayers) {
                Map<String, GolfOptoPlayer> playersSecond = listToMap(secondPlayers);
                mainLineup.setG2(second);
                playersSecond.remove(second.getSiteId());

                List<GolfOptoPlayer> thirdPlayers = getOptimal(new ArrayList(playersSecond.values()),3);
                for (GolfOptoPlayer third : thirdPlayers) {
                    Map<String, GolfOptoPlayer> playersThird = listToMap(thirdPlayers);
                    mainLineup.setG3(third);
                    playersThird.remove(third.getSiteId());

                    List<GolfOptoPlayer> fourthPlayers =getOptimal(new ArrayList(playersThird.values()),3);
                    for (GolfOptoPlayer fourth : fourthPlayers) {
                        Map<String, GolfOptoPlayer> playersFourth = listToMap(fourthPlayers);
                        mainLineup.setG4(fourth);
                        playersFourth.remove(fourth.getSiteId());

                        List<GolfOptoPlayer> fifthPlayers = getOptimal(new ArrayList(playersFourth.values()),3);
                        for (GolfOptoPlayer fifth : fifthPlayers) {
                            Map<String, GolfOptoPlayer> playersFifth = listToMap(fifthPlayers);
                            mainLineup.setG5(fifth);
                            playersFifth.remove(fifth.getSiteId());

                            List<GolfOptoPlayer> sixthPlayers = getOptimal(new ArrayList(playersFifth.values()),3);
                            for (GolfOptoPlayer sixth : sixthPlayers) {
                                mainLineup.setG6(sixth);

                            }
                        }
                    }

                }

            }

        }
        return null;

    }
    public List<GolfOptoPlayer> getOptimal(List<GolfOptoPlayer> players, int unique) {

        List<GolfOptoPlayer> returnList = new ArrayList<>();
        int secCount = unique;

        if(players != null) {
            if (players.size() == 1) {
                returnList.add(players.get(0));
                return returnList;
            }

            players.sort(Comparator.comparingDouble(GolfOptoPlayer::getValue).reversed());
            //get 2 low salary
            //System.out.println(players.size() + " " + unique);

            if (players.size() > 1 && players.size() >= secCount) {
                int count = secCount;
                for (int i = 0; i < count; i++) {
                    returnList.add(players.get(i));
                }

                players.sort(Comparator.comparingDouble(GolfOptoPlayer::getProj).reversed());
                if (players.size() < secCount) {
                    count = players.size();
                } else {
                    count = secCount;
                }
                for (int i = 0; i < count; i++) {
                    if (!returnList.get(0).equals(players.get(i))) {
                        returnList.add(players.get(i));
                    }
                }
            } else {
                int count = players.size();
                for (int i = 0; i < count; i++) {
                    returnList.add(players.get(i));
                }

                players.sort(Comparator.comparingDouble(GolfOptoPlayer::getProj).reversed());
                if (players.size() < players.size()) {
                    count = players.size();
                } else {
                    count = players.size();
                }
                for (int i = 0; i < count; i++) {
                    if (!returnList.get(0).equals(players.get(i))) {
                        returnList.add(players.get(i));
                    }
                }

            }
        }

        return returnList;
    }
    private Map<String, GolfOptoPlayer> listToMap( List<GolfOptoPlayer> playerPool){
        Map<String, GolfOptoPlayer> map = playerPool.stream()
            .collect(Collectors.toMap(GolfOptoPlayer::getSiteId, p -> p));


        return map;
    }
}
